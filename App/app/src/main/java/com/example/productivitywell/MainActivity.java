package com.example.productivitywell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.productivitywell.Fragments.settingsFragment;
import com.example.productivitywell.Fragments.statisticsFragment;
import com.example.productivitywell.Fragments.timerFragment;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    Fragment fragment;
    String fragTagger="";
    public long money=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_stats:
                        // TODO: update fragment
                        fragment = new statisticsFragment();
                        fragTagger = "stats";
                        break;
                    case R.id.action_timer:
                        fragment = new timerFragment();
                        fragTagger = "timer";
                        break;
                    case R.id.action_settings:
                    default:
                        fragment = new settingsFragment();
                        fragTagger = "settings";
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_timer);
    }

    public long getMoney(long moneytobededucted){
        money=moneytobededucted;
        return money;
    }

    @Override
    public void onPause() {

        super.onPause();
        if (fragTagger=="timer"){
            Toast.makeText(getApplicationContext(),"You are losing monies",Toast.LENGTH_SHORT).show();
            System.out.print("This money to be removed"+money);
        }

    }
}











//
//                  focusTime = data.getFocusTime();
//                  sleepTime = data.getSleepTime();
//                  studyTime = data.getStudyTime();
//                  workTime = data.getWorkTime();
//                  other = data.getOtherTime();