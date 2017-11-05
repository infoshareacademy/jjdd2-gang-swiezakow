package pl.infoshareacademy.webapp.statistics;

import pl.infoshareacademy.webapp.AllegroCategoryService;
import pl.infoshareacademy.webapp.dao.StatisticResult;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;
import pl.infoshareacademy.webapp.raports.DataSets;
import pl.infoshareacademy.webapp.raports.Report;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StatisticService {

    private static final List<String> colors = Arrays.asList("#A7FFEB", "#FF8A80", "#FFE57F", "#B388FF");
    private static final List<String> colors2 = Arrays.asList("#FF4081", "#82B1FF", "#EEFF41", "#9E9E9E", "#69F0AE");
    private static final List<String> colors3 = Arrays.asList("#A7FFEB", "#82B1FF", "#FF8A80", "#9E9E9E", "#B388FF");

    @Inject
    private StatisticsResultsBean resultsBean;

    @Inject
    private AllegroCategoryService categoryService;

    public Report getStatsForMostPopularCategories() {
        List<StatisticResult> mostPopularCategories = resultsBean.getMostPopularCategories();
        List<StatisticResult> results = idNameToCategoryName(mostPopularCategories);

        DataSets dataset = getDataset(results, colors2);
        List<String> label = getLabel(results);

        return new Report(Arrays.asList(dataset), label);
    }

    public Report getStatsForCategoryEntries() {
        List<StatisticResult> numberOfCategoryEntries = resultsBean.getNumberOfCategoryEntries();

        DataSets dataset = getDataset(numberOfCategoryEntries, colors);
        List<String> label = getLabel(numberOfCategoryEntries);

        return new Report(Arrays.asList(dataset), label);
    }

    public Report getStatsForMostPopularPhrase() {
        List<StatisticResult> mostPopularPhrase = resultsBean.getMostPopularPhrase();

        DataSets dataset = getDataset(mostPopularPhrase, colors3);
        List<String> label = getLabel(mostPopularPhrase);

        return new Report(Arrays.asList(dataset), label);
    }

    public Report getEntriesPerDay() {
        List<StatisticResult> entriesPerDay = resultsBean.getEntriesPerDay();

        DataSets dataset = getDataset(entriesPerDay, colors3);
        List<String> label = getLabel(entriesPerDay);

        return new Report(Arrays.asList(dataset), label);
    }

    private List<StatisticResult> idNameToCategoryName(List<StatisticResult> list) {
        ArrayList<StatisticResult> statisticResults = new ArrayList<>();
        for (StatisticResult statisticResult : list) {
            String name = categoryService.getCategoryName(Integer.parseInt(statisticResult.getName()));
            statisticResults.add(new StatisticResult(name, statisticResult.getNumber()));
        }
        return statisticResults;
    }

    private DataSets getDataset(List<StatisticResult> results, List<String> colors) {
        return new DataSets(colors, results.stream()
                .map(StatisticResult::getNumber)
                .collect(Collectors.toList()));
    }

    private List<String> getLabel(List<StatisticResult> results) {
        return results.stream()
                .map(StatisticResult::getName)
                .collect(Collectors.toList());
    }

}
