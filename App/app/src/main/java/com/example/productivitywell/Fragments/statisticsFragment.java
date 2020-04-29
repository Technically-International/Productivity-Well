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

import java.util.ArrayList;
import java.util.List;

//
public class statisticsFragment extends Fragment {
    public static final String TAG = "Statistics Fragemnt";

    public int focusTime =0;
    int studyTime;
    int sleepTime;
    int workTime;
    int other;
    ArrayList<PieEntry> dataVals = new ArrayList<>();

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


        BarChart barChart = view.findViewById(R.id.mp_BarChart);
        addBarChart(barChart);

        PieChart pieChart = view.findViewById(R.id.mp_PieChart);
        addPieChart(pieChart);

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
        public void getData(){

        }




    public ArrayList<PieEntry> dataValues2() {
        ParseQuery <Statsdata> query = ParseQuery.getQuery(Statsdata.class);
        query.findInBackground(new FindCallback<Statsdata>() {
            @Override


            public void done(List<Statsdata> datas, ParseException e) {
                if (e != null){
                    Log.e(TAG, " Issue with Login",e );
                    return;
                }
                int num  = 1;
                for (Statsdata data: datas){
                    if (num <2) {
                        dataVals.add(new PieEntry(2,"focus"));
                        Log.i(TAG, "in the place" + data.getFocusTime());
                        dataVals.add(new PieEntry(studyTime,"study"));
                        dataVals.add(new PieEntry(sleepTime,"sleep"));
                        dataVals.add(new PieEntry(workTime,"work"));
                        dataVals.add(new PieEntry(other,"other"));
                        focusTime = data.getFocusTime();

//                  focusTime = data.getFocusTime();
//                  sleepTime = data.getSleepTime();
//                  studyTime = data.getStudyTime();
//                  workTime = data.getWorkTime();
//                  other = data.getOtherTime();

                    }
                    num +=1;

                }

            }

        });
    return dataVals;
    }


    public ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0,3));
        dataVals.add(new BarEntry(1,4));
        dataVals.add(new BarEntry(2,6));
        dataVals.add(new BarEntry(3,2));
        dataVals.add(new BarEntry(4,6));
        dataVals.add(new BarEntry(5,1));
        dataVals.add(new BarEntry(6,4));
        return dataVals;
    }




}
