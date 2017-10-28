//package pl.infoshareacademy.webapp.dao;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//@Stateless
//public class RaportReasultsBean {
//
//    @PersistenceContext(unitName = "zakupyunit")
//    private EntityManager em;
//
//    public List<StatisticResult> getMostPopularCategories() {
//        Query query = em.createQuery("SELECT sc.parametr AS Phrase, count(sc.parametr) FROM Statistics sc WHERE sc.name LIKE 'CATEGORY__CH%' GROUP BY sc.parametr ORDER BY sc.parametr DESC ");
//        query = query.setMaxResults(5);
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    private List<StatisticResult> getResults(List<Object[]> resultList) {
//        List<StatisticResult> results = new ArrayList<>();
//        for (Object[] objects : resultList) {
//            String name = (String) objects[0].toString();
//            Long number = (Long) objects[1];
//            StatisticResult sr = new StatisticResult(name, number.intValue());
//
//            results.add(sr);
//        }
//        return results;
//    }
//
//    public List<StatisticResult> getMostPopularPhrase() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("SELECT parametr, count(parametr) FROM Statistics WHERE parametr!=\'" + monthAgo +"\' AND name='CATEGORY3_SEARCH' AND date(date)>'' GROUP BY parametr ORDER BY parametr DESC");
//        query = query.setMaxResults(5);
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    public List<RushHourModel> getRushHourStatistics() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("select hour(date), count(name) from Statistics where name in (\'MENU_ENTRY\', \'CATEGORY1_ENTRY\', \'CATEGORY2_ENTRY\', \'CATEGORY3_ENTRY\', \'CATEGORY4_ENTRY\') and date(date)>\'" + monthAgo + "\' group by hour(date)");
//        List<Object[]> resultList = query.getResultList();
//        List<RushHourModel> convertedDates = convertQueryToRushHourModel(resultList);
//
//        List<RushHourModel> rushHourModelList = new ArrayList<>();
//        for (long i = 0; i < 24; i++) {
//            rushHourModelList.add(new RushHourModel((int)i, (long) 0));
//            for (long j = 0; j < convertedDates.size(); j++) {
//                if (rushHourModelList.get(((int) i)).getHour().equals(convertedDates.get(((int) j)).getHour())) {
//                    rushHourModelList.get(((int) i)).setQuantity(convertedDates.get(((int) j)).getQuantity());
//                }
//
//            }
//        }
//        return rushHourModelList;
//
//    }
//
//    private List<RushHourModel> convertQueryToRushHourModel(List<Object[]> resultList) {
//        List<RushHourModel> results = new ArrayList<>();
//        for (Object[] objects : resultList) {
//            Integer hour = (Integer) objects[0];
//            Long quantity = (Long) objects[1];
//            RushHourModel rhm = new RushHourModel(hour, quantity);
//            results.add(rhm);
//        }
//        return results;
//    }
//
//    public SumDetailedStaticsModel getSumDetailedStatistics() {
//        List<DetailedStatisticsModel> overview = getLastMonthDetails();
//        Integer visitSum = 0;
//        Integer feature1Sum = 0;
//        Integer feature2Sum = 0;
//        Integer feature3Sum = 0;
//        Integer feature4Sum = 0;
//
//        for (DetailedStatisticsModel anOverview : overview) {
//            visitSum += anOverview.getVisits();
//            feature1Sum += anOverview.getFeature1Quantity();
//            feature2Sum += anOverview.getFeature2Quantity();
//            feature3Sum += anOverview.getFeature3Quantity();
//            feature4Sum += anOverview.getFeature4Quantity();
//        }
//
//        return new  SumDetailedStaticsModel(visitSum,
//                feature1Sum,
//                feature2Sum,
//                feature3Sum,
//                feature4Sum);
//
//    }
//
//    public List<StatisticResult> getLast30daysVisits() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'M%' AND date(date)>" + "\'" + monthAgo + "\'" +" group by date(date)");
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    private List<StatisticResult> getLast30daysDetailsFeature1() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY1_E%' AND date(date)>" + "\'" + monthAgo + "\'" + " group by date(date)");
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    private List<StatisticResult> getLast30daysDetailsFeature2() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY2_E%' AND date(date>" + "\'" + monthAgo +"\'" + " group by date(date)");
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    private List<StatisticResult> getLast30daysDetailsFeature3() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY3_E%' AND date(date)>" + "\'" + monthAgo + "\'" + " group by date(date)");
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    private List<StatisticResult> getLast30daysDetailsFeature4() {
//        LocalDate monthAgo = LocalDate.now().minusMonths(1l);
//        monthAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);
//        Query query = em.createQuery("select date(date), count(name) from Statistics where name like 'CATEGORY4_E%' AND date(date)>" + "\'" + monthAgo + "\'" +" group by date(date)");
//        List<Object[]> resultList = query.getResultList();
//
//        return getResults(resultList);
//    }
//
//    public List<DetailedStatisticsModel> getLastMonthDetails() {
//
//        List<StatisticResult> visits = getLast30daysVisits();
//        List<StatisticResult> feature1 = getLast30daysDetailsFeature1();
//        List<StatisticResult> feature2 = getLast30daysDetailsFeature2();
//        List<StatisticResult> feature3 = getLast30daysDetailsFeature3();
//        List<StatisticResult> feature4 = getLast30daysDetailsFeature4();
//
//        LocalDate start = LocalDate.now().minusMonths(1l);
//        LocalDate end = LocalDate.now();
//        List<String> totalDates = new ArrayList<>();
//        while (!start.isAfter(end)) {
//            totalDates.add(start.format(DateTimeFormatter.ISO_LOCAL_DATE));
//            start = start.plusDays(1);
//        }
//
//        List<DetailedStatisticsModel> detailedStatistics = new ArrayList<>();
//
//        totalDates.stream().forEach(s -> detailedStatistics.add(new DetailedStatisticsModel(s)));
//
//
//        for (int i = 0; i < detailedStatistics.size(); i++) {
//            for (int j = 0; j < visits.size(); j++) {
//                if (detailedStatistics.get(i).getDate().equals(visits.get(j).getName())) {
//                    detailedStatistics.get(i).setVisits(visits.get(j).getNumber());
//                }
//            }
//        }
//
//        for (int i = 0; i < detailedStatistics.size(); i++) {
//            for (int j = 0; j < feature1.size(); j++) {
//                if (detailedStatistics.get(i).getDate().equals(feature1.get(j).getName())) {
//                    detailedStatistics.get(i).setFeature1Quantity(feature1.get(j).getNumber());
//                }
//            }
//        }
//
//        for (int i = 0; i < detailedStatistics.size(); i++) {
//            for (int j = 0; j < feature2.size(); j++) {
//                if (detailedStatistics.get(i).getDate().equals(feature2.get(j).getName())) {
//                    detailedStatistics.get(i).setFeature2Quantity(feature2.get(j).getNumber());
//                }
//            }
//        }
//
//        for (int i = 0; i < detailedStatistics.size(); i++) {
//            for (int j = 0; j < feature3.size(); j++) {
//                if (detailedStatistics.get(i).getDate().equals(feature3.get(j).getName())) {
//                    detailedStatistics.get(i).setFeature3Quantity(feature3.get(j).getNumber());
//                }
//            }
//        }
//
//        for (int i = 0; i < detailedStatistics.size(); i++) {
//            for (int j = 0; j < feature4.size(); j++) {
//                if (detailedStatistics.get(i).getDate().equals(feature4.get(j).getName())) {
//                    detailedStatistics.get(i).setFeature4Quantity(feature4.get(j).getNumber());
//                }
//            }
//        }
//
//        return detailedStatistics;
//    }
//
//
//}
