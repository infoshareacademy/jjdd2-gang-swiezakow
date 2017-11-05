package pl.infoshareacademy.reportService;

import org.junit.Test;
import pl.infoshareacademy.reportService.ModelsStore.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

public class UpdateRESTTimerThreadTest {
    @Test
    public void deleteTask() throws Exception {
        //given
        UpdateRESTTimerThread sut = new UpdateRESTTimerThread();
        //when
        sut.deleteTask(2);
        //then
    }

    @Test
    public void addTaskToReportModuleTest() throws Exception {
//        //given
//        RecipientModel given = new RecipientModel(
//                new ArrayList<>(Arrays.asList("asfgdsg@ajdngdsj.pl")),
//                "2017-10-30 11:30",
//                10,
//                1);
//
//        UpdateRESTTimerThread sut = new UpdateRESTTimerThread();
//        //when
//        sut.addTaskInReportModule(given);
    }

//    @Test
//    public void updateDataInReportModuleTest() throws Exception {
//        //given
//
//        DataProcessingService dataProcessingService = new DataProcessingService();
//
//        List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature = new ArrayList<>();
//        DetailedStatisticsModel model = new DetailedStatisticsModel("2017-10-30");
//        model.setFeature1Quantity(25);
//        model.setFeature2Quantity(5);
//        model.setFeature3Quantity(1);
//        model.setFeature4Quantity(10);
//        model.setVisits(52);
//        lastMonthUserActivityInIndividualFeature.add(model);
//
//        SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature = new SumDetailedStaticsModel(
//                6,3,8,3,6
//        );
//
//        List<RushHourModel> lastMonthUserActivityIntervalStat = new ArrayList<>();
//        RushHourModel hourModel = new RushHourModel(0, 5l);
//        lastMonthUserActivityIntervalStat.add(hourModel);
//
//        DataStore given = new DataStore(new StatisticsStore(lastMonthUserActivityInIndividualFeature,
//                lastMonthSumUserActivityInIndividualFeature,
//                lastMonthUserActivityIntervalStat,
//                "2020-10-10 01:01"));
//
//        String result = dataProcessingService.processingDataFromDB(given);
//
//        assertThat(result).containsPattern(" ");
//
//        UpdateRESTTimerThread sut = new UpdateRESTTimerThread();
//        //when
//        sut.updateDataInReportModule(result);
//
//        //then
//    }

    @Test
    public void getActualTasksTest() throws Exception {
        //given
//        UpdateRESTTimerThread sut = new UpdateRESTTimerThread();
//        //when
//        Optional<ArrayList<RecipientModel>> result = sut.getActualTasks();
//        //then
//        System.out.println(result.get().get(0).getClass());
//        assertThat(result.get().get(0).getClass()).hasSameClassAs(RecipientModel.class);
    }

    @Test
    public void getLastUpdateDatasTest() throws Exception {
        //given
//        UpdateRESTTimerThread sut = new UpdateRESTTimerThread();
//        //when
//        String result = sut.getLastUpdateDate();
//        //then
//        assertThat(result).containsPattern(Pattern.compile(":"));
    }

}