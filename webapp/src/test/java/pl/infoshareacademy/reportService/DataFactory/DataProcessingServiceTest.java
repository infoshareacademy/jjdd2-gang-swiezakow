package pl.infoshareacademy.reportService.DataFactory;

import org.junit.Test;
import pl.infoshareacademy.reportService.ModelsStore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

public class DataProcessingServiceTest {


    @Test
    public void processingDataFromDB() throws Exception {
        //given
        DataProcessingService sut = new DataProcessingService();

        List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature = new ArrayList<>();
        DetailedStatisticsModel model = new DetailedStatisticsModel("2017-10-30");
        model.setFeature1Quantity(25);
        model.setFeature2Quantity(5);
        model.setFeature3Quantity(1);
        model.setFeature4Quantity(10);
        model.setVisits(52);
        lastMonthUserActivityInIndividualFeature.add(model);

        SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature = new SumDetailedStaticsModel(
                6,3,8,3,6
        );

        List<RushHourModel> lastMonthUserActivityIntervalStat = new ArrayList<>();
        RushHourModel hourModel = new RushHourModel(0, 5l);
        lastMonthUserActivityIntervalStat.add(hourModel);

        DataStore given = new DataStore(new StatisticsStore(lastMonthUserActivityInIndividualFeature,
                lastMonthSumUserActivityInIndividualFeature,
                lastMonthUserActivityIntervalStat,
                "2017-10-30 19:50"));


        //when
        String result = sut.processingDataFromDB(given);
        //then

        assertThat(result).containsPattern(Pattern.compile("asdvnjhfdstradfgh"));

    }

}