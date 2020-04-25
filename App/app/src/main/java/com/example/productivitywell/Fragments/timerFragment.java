package com.example.productivitywell.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.productivitywell.MainActivity;
import com.example.productivitywell.R;
import com.uzairiqbal.circulartimerview.CircularTimerListener;
import com.uzairiqbal.circulartimerview.CircularTimerView;
import com.uzairiqbal.circulartimerview.TimeFormatEnum;

import org.w3c.dom.Text;

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

    private void start(final View view){
        final View startBtn = view.findViewById(R.id.startBtn);
            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findTime(view);
                }
            });


    }

    private  void findTime(View view){
        final EditText time = view.findViewById(R.id.time);
        final int timer = Integer.parseInt(time.getText().toString());
        Button startBtn=view.findViewById(R.id.startBtn);
        //Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
                //R.anim.fadeout);
        //animFadeout.setAnimationListener((Animation.AnimationListener) this);
        time.setVisibility(View.VISIBLE);
        //time.startAnimation(animFadeout);
        time.setVisibility(View.INVISIBLE);
        startBtn.setVisibility(View.INVISIBLE);
        Timer(timer, view);
    }

    private void Timer(int amtoftime, View view){


        final CircularTimerView progressBar = view.findViewById(R.id.progress_circular);
        progressBar.setProgress(0);

        // To Initialize Timer
        progressBar.setCircularTimerListener(new CircularTimerListener() {
            @Override
            public String updateDataOnTick(long remainingTimeInMs) {
                return String.valueOf((int)Math.ceil((remainingTimeInMs / 1000.f)));
            }

            @Override
            public void onTimerFinished() {
                progressBar.setPrefix("");
                progressBar.setSuffix("");
                progressBar.setText("Time Up!");
            }
        }, amtoftime, TimeFormatEnum.MINUTES, 10);

        // To start timer
         progressBar.startTimer();

    }
}
