package com.example.productivitywell.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.productivitywell.R;

import java.lang.reflect.Array;
import java.util.Timer;
import java.util.TimerTask;

import static com.parse.Parse.getApplicationContext;


public class timerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    long time = 5;
    long money=0;
    private CountDownTimer countDownTimer;
    private boolean timerRunning=false;
    private boolean moneyEntered=false;
    private TextView countdownText;
    private Button startButton;
    public EditText etAmount;


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
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
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
                time = newVal*5;
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

            }
        }, 0, 1000);

        etAmount=view.findViewById(R.id.etAmount);
        etAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        startButton = view.findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moneytext=etAmount.getText().toString();
                System.out.println("you have eneterd"+moneytext);
                money=Long.parseLong(moneytext);
                if(money>=3) {
                    CircleAngleAnimation animation = new CircleAngleAnimation(circle, 360);
                    animation.setDuration(time * 60000);
                    System.out.println(time + "this is time");
                    circle.startAnimation(animation);
                    numberPicker.setVisibility(numberPicker.GONE);
                    countdownText.setVisibility(countdownText.VISIBLE);
                    startButton.setVisibility(startButton.GONE);
                    time = time * 60000;
                    startStop();
                }
                else{

                    Toast.makeText(getApplicationContext(),"Please enter the amount",Toast.LENGTH_SHORT).show();
                    onViewCreated(view, savedInstanceState);
                }

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
    countDownTimer = new CountDownTimer(time, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            time = millisUntilFinished;
            updateTimer();
        }

        @Override
        public void onFinish() {

        }
    }.start();
    timerRunning=true;
    }

    public void stopTimer() {
        //timer can't be stopped so no code here
        timerRunning=false;
    }
    public void updateTimer(){
        int minutes = (int)time/60000;
        int seconds = (int)time%60000/1000;

        String timeLeftText;

        timeLeftText=""+minutes;
        timeLeftText+=":";
        if (seconds<10) timeLeftText+="0";
        timeLeftText+=seconds;
        countdownText.setText(timeLeftText);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}