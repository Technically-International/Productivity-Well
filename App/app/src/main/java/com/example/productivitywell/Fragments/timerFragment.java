package com.example.productivitywell.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.productivitywell.R;
import com.example.productivitywell.Statsdata;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.parse.Parse.getApplicationContext;


public class timerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    long num = 5;
    private CountDownTimer countDownTimer;
    private boolean timerRunning=false;
    private TextView countdownText;
    private Button startButton;
    private String labelText = "Choose Label";
    private int timeUsed = 5;

    public timerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // load the animation


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_timer, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Circle circle =  view.findViewById(R.id.circle);
        countdownText = view.findViewById(R.id.countdownTimer);
        final NumberPicker numberPicker = view.findViewById(R.id.numberPicker);

        Spinner typeLabels = view.findViewById(R.id.styleLabel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.typeLabel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeLabels.setAdapter(adapter);
        typeLabels.setOnItemSelectedListener(this);


        //24 different selectable times from 5 to 120 minutes in 5 minute increments
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(24);


        //numberpicker listener to update list of numbers according to user input
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //picker.setValue((newVal < oldVal)?oldVal-5:oldVal+5);
                num = newVal*5;
                System.out.println("yo yo");
            }
        });
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 5;
                return "" + temp;
            }
        };
        numberPicker.setFormatter(formatter);


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(num);

            }
        }, 0, 1000);



        startButton = view.findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleAngleAnimation animation = new CircleAngleAnimation(circle, 360);
                animation.setDuration(num*60000);
                System.out.println(num+"this is time");
                timeUsed = (int) num;
                circle.startAnimation(animation);
                numberPicker.setVisibility(numberPicker.GONE);
                countdownText.setVisibility(countdownText.VISIBLE);
                num=num*60000;
                startStop();

            }
        });
    }

    public void startStop() {
        if(timerRunning){
            stopTimer();
        }
        else {
            startTimer();
        }
    }

    public void startTimer() {
    countDownTimer = new CountDownTimer(num, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            num = millisUntilFinished;
            updateTimer();
        }

        @Override
        public void onFinish() {
            ParseUser currentUser = ParseUser.getCurrentUser();
            Date date = new Date();

            System.out.println("Times up date: " + date +"User: "+currentUser+"label: " +labelText + " time used: " + num);
            saveData(labelText,currentUser,timeUsed,date);
        }
    }.start();
    timerRunning=true;
    }

    public void stopTimer() {
        //timer can't be stopped so no code here
        timerRunning=false;
    }
    public void updateTimer(){
        int minutes = (int)num/60000;
        int seconds = (int)num%60000/1000;

        String timeLeftText;

        timeLeftText=""+minutes;
        timeLeftText+=":";
        if (seconds<10) timeLeftText+="0";
        timeLeftText+=seconds;
        countdownText.setText(timeLeftText);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        labelText = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void saveData(String typeLabel, ParseUser currentUser, int timeUsed, Date todaysDate) {
        Statsdata data = new Statsdata();

        data.settDate(todaysDate);
        data.setUser(currentUser);

        switch(typeLabel) {
            case "Choose Label":
                data.setOtherTime(timeUsed);
                break;
            case "Focus":
                data.setFocusTime(timeUsed);
                break;
            case "Study":
                data.setStudyTime(timeUsed);
                break;
            case "Sleep":
                data.setSleepTime(timeUsed);
                break;
            case "Work":
                data.setWorkTime(timeUsed);
                break;
        }

        data.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e("adf", "error while saving", e);
                    Toast.makeText(getContext(), "Error while saving", Toast.LENGTH_SHORT).show();
                }
                Log.i("asdf", "Post save was successful");
            }
        });
    }
}