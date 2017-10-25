package pl.infoshareacademy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;
import pl.infoshareacademy.webapp.entities.Statistics;
import pl.infoshareacademy.webapp.raportSender.RaportPdfGenerator;
import pl.infoshareacademy.webapp.statistics.StatisticEvents;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/test")
public class TestClass extends HttpServlet {

    @Inject
    private StatisticsResultsBean statisticsResultsBean;

    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestClass.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RaportPdfGenerator raportPdfGenerator = new RaportPdfGenerator();
//        raportPdfGenerator.generatePDF(statisticsResultsBean);


        PrintWriter out = resp.getWriter();
//        statisticsResultsBean.getLast30DaysDetails().stream().forEach(s -> out.println(s.toString() + "<br>"));


//        statisticsResultsBean.getAllKnowledge().forEach(s -> out.println(s.toString()));

        statisticsResultsBean.zajebiscie().forEach(s -> out.println(s.toString()));
//        statisticsResultsBean.getLast30daysVisits().stream().forEach(s -> out.println(s.toString() + "<br>"));
//        out.println();
//        statisticsResultsBean.getLast30daysDetailsFeature1().stream().forEach(s -> out.println(s.toString() + "<br>"));
//        out.println();
//        statisticsResultsBean.getLast30daysDetailsFeature2().stream().forEach(s -> out.println(s.toString() + "<br>"));
//        out.println();
//        statisticsResultsBean.getLast30daysDetailsFeature3().stream().forEach(s -> out.println(s.toString() + "<br>"));
//        out.println();
//        statisticsResultsBean.getLast30daysDetailsFeature4().stream().forEach(s -> out.println(s.toString() + "<br>"));

    }
}
