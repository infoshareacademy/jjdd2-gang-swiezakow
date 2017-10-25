package pl.infoshareacademy.webapp.dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
            String name = (String) objects[0].toString();
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
        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'M%' AND date(date)>" + "\'" + monthAgo +"\'" + " group by date(date)");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getLast30daysDetailsFeature1() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY1%' AND date(date)>" + "\'" + monthAgo +"\'" + " group by date(date)");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getLast30daysDetailsFeature2() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY2%' AND date(date)>" + "\'" + monthAgo +"\'" + " group by date(date)");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getLast30daysDetailsFeature3() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY3%' AND date(date)>" + "\'" + monthAgo +"\'" + " group by date(date)");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<StatisticResult> getLast30daysDetailsFeature4() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY4%' AND date(date)>" + "\'" + monthAgo +"\'" + " group by date(date)");
        List<Object[]> resultList = query.getResultList();

        return getResults(resultList);
    }

    public List<DetailedStatisticsResult> zajebiscie() {

        List<StatisticResult> visits = getLast30daysVisits();
        List<StatisticResult> feature1 = getLast30daysDetailsFeature1();
        List<StatisticResult> feature2 = getLast30daysDetailsFeature2();
        List<StatisticResult> feature3 = getLast30daysDetailsFeature3();
        List<StatisticResult> feature4 = getLast30daysDetailsFeature4();

        LocalDate start = LocalDate.now().minusMonths(1l);
        LocalDate end = LocalDate.now();
        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.format(DateTimeFormatter.ISO_LOCAL_DATE));
            start = start.plusDays(1);
        }

        List<DetailedStatisticsResult> detailedStatistics = new ArrayList<>();

        totalDates.stream().forEach(s -> detailedStatistics.add(new DetailedStatisticsResult(s)));


        for (int i = 0; i < detailedStatistics.size(); i++) {
            for (int j = 0; j < visits.size(); j++) {
                if (detailedStatistics.get(i).getDate().equals(visits.get(j).getName())) {
                    detailedStatistics.get(i).setVisits(visits.get(j).getNumber());
                }
            }
        }

        for (int i = 0; i < detailedStatistics.size(); i++) {
            for (int j = 0; j < feature1.size(); j++) {
                if (detailedStatistics.get(i).getDate().equals(feature1.get(j).getName())) {
                    detailedStatistics.get(i).setFeature1Quantity(feature1.get(j).getNumber());
                }
            }
        }

        for (int i = 0; i < detailedStatistics.size(); i++) {
            for (int j = 0; j < feature2.size(); j++) {
                if (detailedStatistics.get(i).getDate().equals(feature2.get(j).getName())) {
                    detailedStatistics.get(i).setFeature2Quantity(feature2.get(j).getNumber());
                }
            }
        }

        for (int i = 0; i < detailedStatistics.size(); i++) {
            for (int j = 0; j < feature3.size(); j++) {
                if (detailedStatistics.get(i).getDate().equals(feature3.get(j).getName())) {
                    detailedStatistics.get(i).setFeature3Quantity(feature3.get(j).getNumber());
                }
            }
        }

        for (int i = 0; i < detailedStatistics.size(); i++) {
            for (int j = 0; j < feature4.size(); j++) {
                if (detailedStatistics.get(i).getDate().equals(feature4.get(j).getName())) {
                    detailedStatistics.get(i).setFeature4Quantity(feature4.get(j).getNumber());
                }
            }
        }

        return detailedStatistics;




    }


















//    public List<DetailedStatisticsResult> getAllKnowledge() {
//        Query q = em.createNativeQuery(
//                "select DISTINCT date(s.date) AS date, Menu.menu, Cat1.cat1, Cat2.cat2, Cat3.cat3, Cat4.cat4 from STATISTICS s" +
//                        "  LEFT JOIN (select date(s.date) as date, count(s.name) as menu from STATISTICS s join STATISTICS sc on s.id=sc.id where date(s.date)>'2017-09-23' and s.name like 'M%' group by date(s.date))" +
//                        "    as Menu on date(s.date)=date(Menu.date)" +
//                        "  LEFT JOIN (select date(s.date) as date, count(s.name) as cat1 from STATISTICS s join STATISTICS sc on s.id=sc.id where date(s.date)>'2017-09-23' and s.name like 'CATEGORY1%' group by date(s.date))" +
//                        "     as Cat1 on date(Cat1.date)=date(s.date)" +
//                        "  LEFT JOIN (select date(s.date) as date, count(s.name) as cat2 from STATISTICS s join STATISTICS sc on s.id=sc.id where date(s.date)>'2017-09-23' and s.name like 'CATEGORY2%' group by date(s.date))" +
//                        "     as Cat2 on date(Cat2.date)=date(s.date)" +
//                        "  LEFT JOIN (select date(s.date) as date, count(s.name) as cat3 from STATISTICS s join STATISTICS sc on s.id=sc.id where date(s.date)>'2017-09-23' and s.name like 'CATEGORY3%' group by date(s.date))" +
//                        "    as Cat3 on date(Cat3.date)=date(s.date)" +
//                        "  LEFT JOIN (select date(s.date) as date, count(s.name) as cat4 from STATISTICS s join STATISTICS sc on s.id=sc.id where date(s.date)>'2017-09-23' and s.name like 'CATEGORY4%' group by date(s.date))" +
//                        "    as Cat4 on date(Cat4.date)=date(s.date)" +
//                        "where date(s.date)>'2017-09-23' order by date(s.date) desc", DetailedStatisticsResult.class);
//
//        return q.getResultList();
//
//    }



//    public void getLast30DaysDetails() {
//
//        Multimap<String, Integer> multimap = ArrayListMultimap.create();
//        multimap.put("jabko", 5);
//        multimap.put("jabko", 5);
//        multimap.put("jabko", 11);
//        multimap.put("jabko", 36);
//        multimap.put("jabko", 15);
//        multimap.put("jabko", 16);
//        multimap.put("jabko", 12);
//        multimap.put("jabko", 1);
//        multimap.put("jabko", 121);


//        getLast30daysVisits().stream().forEach(statisticResult -> multimap.put("jabko", 5));
//        getLast30daysDetailsFeature1().forEach(statisticResult -> multimap.put("jabko", 6));
//        getLast30daysDetailsFeature2().forEach(statisticResult -> multimap.put("jabko", 8));
//        getLast30daysDetailsFeature3().forEach(statisticResult -> multimap.put("jabko", 11));
//        getLast30daysDetailsFeature4().forEach(statisticResult -> multimap.put("jabko", 23));
                //statisticResult.getName(), statisticResult.getNumber()));

//        List<String> results = new ArrayList<>();

        //for (String key : multimap.keys()) {
//            multimap.get("jabko").forEach(s -> results.add(s.toString()));
//            DetailedStatisticsResult dsr = new DetailedStatisticsResult(
//
//            )

        //}

//    }






//    public List<DetailedStatisticsResult> getLast30DaysDetails() {
//
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);

//        List<Object[]> resultList = query.getResultList();
//
//        return getDetailedStatistics(resultList);
//    }

//    select distinct date(date),
//    (select count(name) from STATISTICS where name like 'M%' and date(date)=date(date)) as "Visits",
//            (select count(name) from STATISTICS where name like 'CATEGORY1%' and date(date)=date(date)) as "Category 1",
//            (select count(name) from STATISTICS where name like 'CATEGORY2%' and date(date)=date(date)) as "Category 2",
//            (select count(name) from STATISTICS where name like 'CATEGORY3%' and date(date)=date(date)) as "Category 3",
//            ( select count(name) from STATISTICS where name like 'CATEGORY4%' and date(date)=date(date)) as "Category 4"
//    from STATISTICS where date(date)>"2017-09-23" group by date;



//select name, count(date) from STATISTICS group by name; <- ilosc wejsc do featureow
    //select date(date), count(name) from STATISTICS where name like 'M%' group by date(date); <-ilosc wejsc w danym dniu

    /*
select distinct (select count(name) from STATISTICS where name like 'M%') as "Visits",
(select count(name) from STATISTICS where name like 'CATEGORY1%') as "Category 1",
(select count(name) from STATISTICS where name like 'CATEGORY2%') as "Category 2",
(select count(name) from STATISTICS where name like 'CATEGORY3%') as "Category 3", (
select count(name) from STATISTICS where name like 'CATEGORY4%') as "Category 4" from STATISTICS where date(date)>"2017-09-23"
*/
//select date(date), name as "Visits", count(name) from STATISTICS group by date(date), name;

    /* select distinct date(date) as mydate, (select count(name) from STATISTICS where name like 'M%' and date(date)=mydate) as "Visits", (select count(name) from STATISTICS where name like 'CATEGORY1%' and date(date)=mydate) as "Category 1", (select count(name) from STATISTICS where name like 'CATEGORY2%' and date(date)=mydate) as "Category 2", (select count(name) from STATISTICS where name like 'CATEGORY3%' and date(date)=mydate) as "Category 3", ( select count(name) from STATISTICS where name like 'CATEGORY4%' and date(date)=mydate) as "Category 4" from STATISTICS where date(date)>"2017-09-23" group by date;
     */

//    private List<DetailedStatisticsResult> getDetailedStatistics(List<Object[]> resultList) {
//        List<DetailedStatisticsResult> results = new ArrayList<>();
//        for (Object[] objects : resultList) {
//            String date = (String) objects[0].toString();
//            DetailedStatisticsResult sr = new DetailedStatisticsResult(
//                    (String) objects[0].toString(),
//                    (Integer) objects[1],
//                    (Integer) objects[2],
//                    (Integer) objects[3],
//                    (Integer) objects[4],
//                    (Integer) objects[5]);
//
//            results.add(sr);
//        }
//        return results;
//    }
}
