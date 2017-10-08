package pl.infoshareacademy.webapp.dao;

import pl.infoshareacademy.webapp.entities.Statistics;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StatisticsBean {

    @PersistenceContext(unitName = "zakupyunit")
    private EntityManager em;

    public void addStatistics(Statistics stats) {
        em.persist(stats);
    }
}
