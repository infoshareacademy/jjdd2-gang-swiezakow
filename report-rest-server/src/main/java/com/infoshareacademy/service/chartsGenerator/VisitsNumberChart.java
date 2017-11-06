package com.infoshareacademy.service.chartsGenerator;

import com.infoshareacademy.model.databaseinputs.DetailedStatisticsModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.util.List;

public class VisitsNumberChart {

    public void getVisitsNumberChart(List<DetailedStatisticsModel> db) throws Exception {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        db.forEach(s -> line_chart_dataset.addValue(s.getVisits(), "visits", s.getDate()));

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Last month activity","Date",
                "Visits number",
                line_chart_dataset,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */
        File lineChart = new File( "LineChart.png" );
        ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
    }
}