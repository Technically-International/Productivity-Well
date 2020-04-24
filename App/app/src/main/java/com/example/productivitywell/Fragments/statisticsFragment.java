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
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

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

    int[] colorClassArray = new int[]{Color.LTGRAY,Color.BLUE, Color.CYAN,Color.DKGRAY,Color.GREEN,Color.MAGENTA, Color.RED};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BarChart barChart = view.findViewById(R.id.mp_BarChart);



        BarDataSet barDataset1 = new BarDataSet(dataValues1(), "Dataset 1");

        BarData barData = new BarData();
        barData.addDataSet(barDataset1);

        barChart.setData(barData);
        barChart.invalidate();

        PieChart pieChart = view.findViewById(R.id.mp_PieChart);

        PieDataSet pieDataSet = new PieDataSet(dataValues2(),"");
        pieDataSet.setColors(colorClassArray);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private ArrayList<PieEntry> dataValues2() {
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        dataVals.add(new PieEntry(15,"Sun"));
        dataVals.add(new PieEntry(15,"Mon"));
        dataVals.add(new PieEntry(15,"Tue"));
        dataVals.add(new PieEntry(15,"Wed"));
        dataVals.add(new PieEntry(15,"Thu"));
        dataVals.add(new PieEntry(15,"Fri"));
        dataVals.add(new PieEntry(15,"Sat"));

        return dataVals;
    }

    private ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0,3));
        dataVals.add(new BarEntry(1,4));
        dataVals.add(new BarEntry(2,6));
        dataVals.add(new BarEntry(3,11));
        return dataVals;
    }
}
