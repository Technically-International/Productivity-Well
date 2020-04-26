package com.example.productivitywell.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.productivitywell.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;


//

public class statisticsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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

    private ArrayList<PieEntry> dataValues2() {
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        dataVals.add(new PieEntry(10,"focus"));
        dataVals.add(new PieEntry(5,"study"));
        dataVals.add(new PieEntry(40,"sleep"));
        dataVals.add(new PieEntry(25,"work"));
        dataVals.add(new PieEntry(20,"other"));

        return dataVals;
    }


    private ArrayList<BarEntry> dataValues1() {
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
