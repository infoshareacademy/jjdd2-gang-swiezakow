package com.infoshareacademy.service.chartsGenerator;

import com.infoshareacademy.model.SumDetailedStaticsModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;

public class CompareEntriesChart {

    private SumDetailedStaticsModel db;

    public CompareEntriesChart(SumDetailedStaticsModel db) {
        this.db = db;
    }

    public void generateCompareEntriesChart()throws Exception {
        final String menuEntries = "MENU";
        final String feature1 = "FEATURE 1";
        final String feature2 = "FEATURE 2";
        final String feature3 = "FEATURE 3";
        final String feature4 = "FEATURE 4";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue(db.getFeature1Sum(), feature1, feature1);
        dataset.addValue(db.getFeature2Sum(), feature2, feature2);
        dataset.addValue(db.getFeature3Sum(), feature3, feature3);
        dataset.addValue(db.getFeature4Sum(), feature4, feature4);
        dataset.addValue(db.getVisitSum(), feature4, menuEntries);

        JFreeChart barChart = ChartFactory.createBarChart(
                "Compare the activity of the individual functionalities",
                "Feature", "Visits number",
                dataset,PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */
        File BarChart = new File( "BarChart.png" );
        ChartUtilities.saveChartAsPNG( BarChart , barChart , width , height );
    }
}