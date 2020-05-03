package com.example.productivitywell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.productivitywell.Fragments.settingsFragment;
import com.example.productivitywell.Fragments.statisticsFragment;
import com.example.productivitywell.Fragments.timerFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            System.out.println("This money to be removed"+money);
            subtractMoneyParse((int) money);
        }

    }


    ParseObject user;
    public void subtractMoneyParse(final int moneyLost) {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");

        //query.whereEqualTo("username","john"); // I'm just putting Beta, but it should be the frat you want (or that the user clicked)
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> userList, ParseException e) {
                if (e != null) {
                    user = userList.get(0); // I'm assuming the first instance should always be the one you work with. In fact, there should only be one instance of each
                    System.out.println("User: " + user);
                    user.put("money", 25);
                    System.out.println("User money: " + user.get("money"));
                    user.saveInBackground();
                } else {
                    System.out.println(e);
                }
            }
        });



//        //query.whereEqualTo(User.KEY_USER, ParseUser.getCurrentUser());
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<User> users, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, " Issue with Login", e);
//                    return;
//                }
//                System.out.println("Parse is being called money " + users);
//                for (User user: users){
//                    int currentMoney = Integer.parseInt(user.getMoney()) - moneyLost;
//                    System.out.println("money To loose: " + Integer.parseInt(user.getMoney()));
//                    user.setMoney(currentMoney);
//
//                }
//
//            }
//
//        });



    }
}
