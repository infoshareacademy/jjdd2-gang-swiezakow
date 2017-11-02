package pl.infoshareacademy.reportService.DataFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.reportService.ModelsStore.DataStore;
import pl.infoshareacademy.reportService.ModelsStore.StatisticsStore;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataProcessingService {

    private final static Logger logger = LogManager.getLogger(DataProcessingService.class);


    public DataProcessingService(StatisticsResultsBean reportResultBean) {
        this.reportResultBean = reportResultBean;
    }

    StatisticsResultsBean reportResultBean;

    public DataStore getNewDataSetForDB() {
        logger.info("building newDataSet");
        return new DataStore(new StatisticsStore(
                reportResultBean.getLastMonthDetails(),
                reportResultBean.getSumDetailedStatistics(),
                reportResultBean.getRushHourStatistics(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
    }

    public String processingDataFromDB(DataStore dataStore) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataStore);
            return jsonInString;
        } catch (JsonProcessingException e) {
            logger.fatal("Problem with mapping newDataSet to JSON" + e);
            return "Problem";
        }


    }


}
