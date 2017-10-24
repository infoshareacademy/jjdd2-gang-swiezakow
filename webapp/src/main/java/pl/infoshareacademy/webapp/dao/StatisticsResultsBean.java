package pl.infoshareacademy.webapp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class StatisticsResultsBean {
    @PersistenceContext(unitName = "zakupyunit")
    private EntityManager em;

    public List<StatisticResult> getNumberOfCategoryEntries() {
        Query query = em.createQuery("SELECT st.name, count(st) AS Visits FROM Statistics st WHERE st.name LIKE 'CATEGORY__ENTRY' GROUP BY st.name ORDER BY Visits DESC ");
        query = query.setMaxResults(5);
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getNumberOfVisistors() {
        Query query = em.createQuery("SELECT sc.name, count(sc) FROM Statistics sc WHERE sc.name LIKE 'MEN%' GROUP BY sc.name");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getMostPopularCategories() {
        Query query = em.createQuery("SELECT sc.parametr AS Phrase, count(sc.parametr) FROM Statistics sc WHERE sc.name LIKE 'CATEGORY__CH%' GROUP BY sc.parametr ORDER BY sc.parametr DESC ");
        query = query.setMaxResults(5);
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    private List<StatisticResult> getResults(List<Object[]> resultList) {
        List<StatisticResult> results = new ArrayList<>();
        for (Object[] objects : resultList) {
            String name = (String) objects[0];
            Long number = (Long) objects[1];
            StatisticResult sr = new StatisticResult(name, number.intValue());

            results.add(sr);
        }
        return results;
    }

    public List<StatisticResult> getMostPopularPhrase() {
        Query query = em.createQuery("SELECT sc.parametr AS Phrase, count(sc.parametr) FROM Statistics sc WHERE sc.name LIKE 'CATEGORY__SE%' GROUP BY sc.parametr ORDER BY sc.parametr DESC ");
        query = query.setMaxResults(5);
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getEntriesPerDay() {
        Query query = em.createQuery("SELECT DATE_FORMAT(sc.date, '%Y-%m-%d %H:%i') AS date, COUNT(sc.parametr) FROM Statistics sc WHERE sc.name LIKE 'M%' GROUP BY DATE_FORMAT(sc.date, '%Y-%m-%d %H:%i')");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getLast30daysVisits() {
        LocalDate monthAgo = LocalDate.now();
        monthAgo.minusMonths(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        Query query = em.createQuery("select date(date), count(name) from STATISTICS where name like 'M%' AND date(date)>" + monthAgo +" group by date(date)");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

//select name, count(date) from STATISTICS group by name; <- ilosc wejsc do featureow
    //select date(date), count(name) from STATISTICS where name like 'M%' group by date(date); <-ilosc wejsc w danym dniu

    /*
select distinct (select count(name) from STATISTICS where name like 'M%') as "Visits",
(select count(name) from STATISTICS where name like 'CATEGORY1%') as "Category 1",
(select count(name) from STATISTICS where name like 'CATEGORY2%') as "Category 2",
(select count(name) from STATISTICS where name like 'CATEGORY3%') as "Category 3", (
select count(name) from STATISTICS where name like 'CATEGORY4%') as "Category 4" from STATISTICS where date(date)>"2017-09-23"
*/

}
