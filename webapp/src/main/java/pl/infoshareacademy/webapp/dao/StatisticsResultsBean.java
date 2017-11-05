package pl.infoshareacademy.webapp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Query query = em.createQuery("SELECT DATE_FORMAT(sc.date, '%Y-%m-%d') AS date, COUNT(sc.parametr) FROM Statistics sc WHERE sc.name LIKE 'M%' GROUP BY DATE_FORMAT(sc.date, '%Y-%m-%d')");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }
}