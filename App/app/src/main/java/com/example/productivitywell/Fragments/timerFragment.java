package com.example.productivitywell.Fragments;

import android.R.layout;
import android.graphics.Color;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.example.productivitywell.MainActivity;
import com.example.productivitywell.R;
import com.uzairiqbal.circulartimerview.CircularTimerListener;
import com.uzairiqbal.circulartimerview.CircularTimerView;
import com.uzairiqbal.circulartimerview.TimeFormatEnum;

import org.w3c.dom.Text;

import java.util.Calendar;

import java.util.concurrent.TimeUnit;

import static com.parse.Parse.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link timerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class timerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int time;
    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();


    public timerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment timerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static timerFragment newInstance(String param1, String param2) {
        timerFragment fragment = new timerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // load the animation


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_timer, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        start(view);

    }

    private void start(final View view) {
        //Spinner widget for user to select time
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.times, R.layout.spinner_item);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerSelected(parentView, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void spinnerSelected(AdapterView<?> parent, final View view, int position, long id) {
        String time = parent.getItemAtPosition(position).toString();
        final int time1 = Integer.parseInt(time);

        System.out.println("here");
        final View startBtn = view.findViewById(R.id.startBtn);
        //listening for when user presses start after selecting time
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    findTime(view, time1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void findTime(View view, int amtoftime) throws InterruptedException {
        System.out.println("here");
        Button startBtn = view.findViewById(R.id.startBtn);
        Spinner spinner = view.findViewById((R.id.spinner));
        //Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
        //R.anim.fadeout);
        //animFadeout.setAnimationListener((Animation.AnimationListener) this);
        spinner.setVisibility(View.VISIBLE);
        //time.startAnimation(animFadeout);
        spinner.setVisibility(View.INVISIBLE);
        startBtn.setVisibility(View.INVISIBLE);
        Timer(amtoftime, view);
    }

    private void Timer(int amtoftime, View view) throws InterruptedException {

        CircularProgressBar progressBar = view.findViewById(R.id.progress_bar);
        //amtoftime=amtoftime*60;
        float progressTime;
        float currentTime=0;
        float progress1;
        progressBar.setProgress(20);
        System.out.println("hereno111111w");
        for(int i=0;i<=amtoftime; i++){
            progress1=(currentTime/amtoftime)*100;
            progressBar.setProgress(progress1);
            System.out.println("herenow" + progress1);
            TimeUnit.SECONDS.sleep(1);
            currentTime++;

        }

    }
}








