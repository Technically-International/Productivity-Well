package com.example.productivitywell.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.productivitywell.Post;
import com.example.productivitywell.R;
import com.example.productivitywell.Statsdata;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//
public class statisticsFragment extends Fragment {
    public static final String TAG = "Statistics Fragemnt";
    public int focusTime;
    public int studyTime;
    public int sleepTime;
    public int workTime;
    public int other;

    int sunTime;
    int monTime;
    int tueTime;
    int wedTime;
    int thuTime;
    int friTime;
    int satTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    public void queryStats(){
//        ParseQuery <Statsdata> query = ParseQuery.getQuery(Statsdata.class);
//        query.findInBackground(new FindCallback<Statsdata>() {
//            @Override
//            public void done(List<Statsdata> datas, ParseException e) {
//                if (e != null){
//                    Log.e(TAG, " Issue with Login",e );
//                    return;
//                }
//                int num  = 1;
//                for (Statsdata data: datas){
//                    if (num <2) {
//                        focusTime = data.getFocusTime();
//                        Log.i(TAG, "heree" + focusTime);
////                  focusTime = data.getFocusTime();
////                  sleepTime = data.getSleepTime();
////                  studyTime = data.getStudyTime();
////                  workTime = data.getWorkTime();
////                  other = data.getOtherTime();
//                    }
//                    num +=1;
//
//                }
//            }
//        });
//
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_statistics, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queryStats(view);





    }



    public void addBarChart(BarChart barChart){
        String[] labels = new String[]{"Sun","Mon","Tues","Wed", "Thu","Fri","Sat"};
        BarDataSet barDataset1 = new BarDataSet(dataValues1(), "Dataset 1");
        BarData barData = new BarData();
        barData.addDataSet(barDataset1);
        barChart.setDrawValueAboveBar(false);
        barChart.setData(barData);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        barChart.invalidate();
    }

    public void addPieChart(PieChart pieChart){

        int[] colorClassArray = new int[]{Color.LTGRAY,Color.BLUE, Color.CYAN,Color.DKGRAY,Color.GREEN};

        PieDataSet pieDataSet = new PieDataSet(dataValues2(),"");
        pieDataSet.setColors(colorClassArray);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getBackground();
        pieChart.setDrawSliceText(false);
        pieChart.setUsePercentValues(false);
        pieChart.invalidate();
    }






    public ArrayList<PieEntry> dataValues2() {

        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(focusTime,"focus"));
        System.out.println(" in the fragment focus time is" + focusTime);
        dataVals.add(new PieEntry(studyTime,"study"));
        dataVals.add(new PieEntry(sleepTime,"sleep"));
        dataVals.add(new PieEntry(workTime,"work"));
        dataVals.add(new PieEntry(other,"other"));
    return dataVals;
    }


    public ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0,sunTime));
        dataVals.add(new BarEntry(1,monTime));
        dataVals.add(new BarEntry(2,tueTime));
        dataVals.add(new BarEntry(3,wedTime));
        dataVals.add(new BarEntry(4,thuTime));
        dataVals.add(new BarEntry(5,friTime));
        dataVals.add(new BarEntry(6,satTime));
        return dataVals;
    }

    public void queryStats(final View view) {
        ParseQuery<Statsdata> query = ParseQuery.getQuery(Statsdata.class);
        query.whereEqualTo(Statsdata.KEY_USER, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Statsdata>() {
            @Override
            public void done(List<Statsdata> datas, ParseException e) {
                if (e != null) {
                    Log.e(TAG, " Issue with Login", e);
                    return;
                }
                //statisticsFragment statisticsFragment = new statisticsFragment();

                for (Statsdata data: datas){
                        Log.e(TAG,"The message is " + data.getFocusTime());
                        focusTime = data.getFocusTime() + focusTime;
                        sleepTime = data.getSleepTime() + sleepTime;
                        studyTime = data.getStudyTime() + studyTime;
                        other = data.getOtherTime() + other;
                        workTime = data.getWorkTime() + workTime;

                        SimpleDateFormat sdf = new SimpleDateFormat("E");
                        String DayOfWeek = sdf.format(data.gettDate());

                        switch(DayOfWeek){
                            case "Sun":
                                monTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                            case "Mon":
                                tueTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                            case "Tue":
                                wedTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                            case "Wed":
                                thuTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                            case "Thu":
                                friTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                            case "Fri":
                                satTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                            case "Sat":
                                sunTime += data.getFocusTime() + data.getSleepTime() + data.getStudyTime() + data.getWorkTime();
                                break;
                        }

                        BarChart barChart = view.findViewById(R.id.mp_BarChart);
                        addBarChart(barChart);
                        PieChart pieChart = view.findViewById(R.id.mp_PieChart);
                        addPieChart(pieChart);
                }
                System.out.println("this is sun time "+ sunTime);


            }

        });



    }
}
