package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import pl.infoshareacademy.webapp.chartsGenerator.CompareEntriesChart;
import pl.infoshareacademy.webapp.chartsGenerator.RushHoursCharts;
import pl.infoshareacademy.webapp.chartsGenerator.VisitsNumberChart;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;
import pl.infoshareacademy.webapp.raportSender.RaportPdfGenerator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class TestClass extends HttpServlet {

    @Inject
    private StatisticsResultsBean dataBaseResults;

    @Inject
    private CompareEntriesChart compareEntriesChart;

    @Inject
    private VisitsNumberChart visitsNumberChart;

    @Inject
    private RushHoursCharts rushHoursCharts;

    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestClass.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter out = resp.getWriter();

        dataBaseResults.getLastMonthDetails().forEach(s -> out.println(s.toString()));

        out.println(dataBaseResults.getSumDetailedStatistics().toString());

        try {
            compareEntriesChart.generateCompareEntriesChart();
            visitsNumberChart.getVisitsNumberChart();
            rushHoursCharts.generateRushHoursCharts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RaportPdfGenerator raportPdfGenerator = new RaportPdfGenerator();
        raportPdfGenerator.generatePDF(dataBaseResults);

//        EmailSender emailSender = new EmailSender();
//        emailSender.sendEmail("bogumilp98@gmail.com");


        dataBaseResults.getRushHourStatistics().forEach(rushHourModel -> out.println(rushHourModel.toString()));



    }
}
