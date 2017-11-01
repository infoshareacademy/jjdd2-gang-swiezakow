package pl.infoshareacademy.reportService.DataFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.infoshareacademy.reportService.ModelsStore.DataStore;
import pl.infoshareacademy.reportService.ModelsStore.StatisticsStore;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Singleton
public class DataProcessingService {

    @Inject
    ReportResultBean reportResultBean;

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
