package pl.infoshareacademy.webapp;

import org.junit.Test;
import pl.infoshareacademy.webapp.dao.StatisticResult;
import pl.infoshareacademy.webapp.dao.StatisticsBean;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;
import pl.infoshareacademy.webapp.entities.Statistics;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static pl.infoshareacademy.webapp.statistics.StatisticEvents.CATEGORY1_ENTRY;
import static pl.infoshareacademy.webapp.statistics.StatisticEvents.CATEGORY2_ENTRY;
import static pl.infoshareacademy.webapp.statistics.StatisticEvents.CATEGORY3_ENTRY;

public class StatisticsIT extends BaseTest {

    @Inject
    StatisticsBean statisticsBean;

    @Inject
    StatisticsResultsBean resultsBean;

    private final Statistics stat1 = new Statistics("MENU_ENTRY", "");
    private final Statistics stat2 = new Statistics("MENU_ENTRY", "");
    private final Statistics stat3 = new Statistics("CATEGORY1_CHOICE", "62219");
    private final Statistics stat4 = new Statistics("CATEGORY1_CHOICE", "121172");

    @Test
    public void testForGetNumberOfVisitors() throws Exception {
        statisticsBean.saveStatistics(stat1);
        statisticsBean.saveStatistics(stat2);

        StatisticResult result = resultsBean.getNumberOfVisistors().get(0);

        assertEquals(2, result.getNumber().intValue());
    }

    @Test
    public void testForMostPopularCategories() throws Exception {
        statisticsBean.saveStatistics(stat3);
        statisticsBean.saveStatistics(stat4);

        StatisticResult statisticResult = resultsBean.getMostPopularCategories().get(0);

        assertEquals("62219", statisticResult.getName());
    }

    @Test
    public void testForNumberOfCategoryEntries() throws Exception {
        for (int i = 0; i < 10; i++) {
            statisticsBean.saveStatistics(new Statistics(CATEGORY3_ENTRY.toString(), ""));
        }

        for (int i = 0; i < 5; i++) {
            statisticsBean.saveStatistics(new Statistics(CATEGORY1_ENTRY.toString(), ""));
        }

        for (int i = 0; i < 3; i++) {
            statisticsBean.saveStatistics(new Statistics(CATEGORY2_ENTRY.toString(), ""));
        }

        List<StatisticResult> numberOfCategoryEntries = resultsBean.getNumberOfCategoryEntries();
        StatisticResult statisticResult = numberOfCategoryEntries.get(0);
        StatisticResult statisticResult2 = numberOfCategoryEntries.get(2);

        assertEquals(10, statisticResult.getNumber().intValue());
        assertEquals(CATEGORY3_ENTRY.toString(), statisticResult.getName());

        assertEquals(3, statisticResult2.getNumber().intValue());
        assertEquals(CATEGORY2_ENTRY.toString(), statisticResult2.getName());
    }
}