package pl.infoshareacademy.reportService.DataFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.infoshareacademy.reportService.ModelsStore.DataStore;
import pl.infoshareacademy.reportService.ModelsStore.StatisticsStore;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataProcessingService {

    public DataProcessingService(StatisticsResultsBean reportResultBean) {
        this.reportResultBean = reportResultBean;
    }

    StatisticsResultsBean reportResultBean;

    public DataStore getNewDataSetFromDB() {
        return new DataStore(new StatisticsStore(
                reportResultBean.getLastMonthDetails(),
                reportResultBean.getSumDetailedStatistics(),
                reportResultBean.getRushHourStatistics(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
    }

    public String processingDataFromDB(DataStore dataStore) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataStore);
            return jsonInString;
        } catch (JsonProcessingException e) {
            return "Problem";
        }


    }


}
