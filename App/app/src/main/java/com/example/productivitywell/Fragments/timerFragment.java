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
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.productivitywell.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.parse.Parse.getApplicationContext;


public class timerFragment extends Fragment {

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
    int num = 5;
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Circle circle =  view.findViewById(R.id.circle);
        final TextView conutdownTimer = view.findViewById(R.id.countdownTimer);

        final NumberPicker numberPicker = view.findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(24);

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

        Button startButton = view.findViewById(R.id.startBtn);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleAngleAnimation animation = new CircleAngleAnimation(circle, 360);
                animation.setDuration(num*1000);
                System.out.println(num+"this is time");
                circle.startAnimation(animation);
                numberPicker.setVisibility(numberPicker.GONE);
                conutdownTimer.setVisibility(conutdownTimer.VISIBLE);

            }
        });
    }
}