package pl.infoshareacademy.webapp.raportSender;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import pl.infoshareacademy.webapp.dao.StatisticsBean;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class RaportPdfGeneratorTest {

    @Inject
    StatisticsBean statisticsBean;

    @Inject
    StatisticsResultsBean statisticsResultsBean;

    @Test
    public void generatePDF() throws Exception {
        //given
        RaportPdfGenerator sut = new RaportPdfGenerator();
        //when
        sut.generatePDF(statisticsResultsBean);
        //then
        assertTrue(true);
    }




}