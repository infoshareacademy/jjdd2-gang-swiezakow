package com.infoshareacademy.service.chartsGenerator;

import com.infoshareacademy.model.RushHourModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.util.List;

public class RushHoursChart {

    private List<RushHourModel> db;

    public RushHoursChart(List<RushHourModel> db) {
        this.db = db;
    }

    public void generateRushHoursCharts() throws Exception {

        DefaultCategoryDataset line_chart1_dataset = new DefaultCategoryDataset();

        db.forEach(s -> line_chart1_dataset.addValue(s.getQuantity(), "activity", s.getHour()));

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Rush hour activity", "Hour",
                "Visits number",
                line_chart1_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */
        File lineChart = new File("LineChart1.png");
        ChartUtilities.saveChartAsPNG(lineChart, lineChartObject, width, height);
    }
}