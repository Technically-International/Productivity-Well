package com.example.productivitywell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.productivitywell.Fragments.settingsFragment;
import com.example.productivitywell.Fragments.statisticsFragment;
import com.example.productivitywell.Fragments.timerFragment;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_stats:
                        // TODO: update fragment
                        fragment = new statisticsFragment();
                        break;
                    case R.id.action_timer:
                        fragment = new timerFragment();
                        break;
                    case R.id.action_settings:
                    default:
                        fragment = new settingsFragment();
                        break;
                }
                System.out.println(fragment.getTag());
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_timer);
    }

    @Override
    public void onPause() {

        super.onPause();
        Toast.makeText(getApplicationContext(),"You are losing monies",Toast.LENGTH_SHORT).show();
    }
}











//
//                  focusTime = data.getFocusTime();
//                  sleepTime = data.getSleepTime();
//                  studyTime = data.getStudyTime();
//                  workTime = data.getWorkTime();
//                  other = data.getOtherTime();