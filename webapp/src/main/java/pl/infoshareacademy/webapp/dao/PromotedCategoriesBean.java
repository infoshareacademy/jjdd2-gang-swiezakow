package pl.infoshareacademy.webapp.dao;

import pl.infoshareacademy.webapp.entities.PromotedCategories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PromotedCategoriesBean {
    @PersistenceContext(unitName = "zakupyunit")
    private EntityManager em;

    public void savePromotedCategories(List<Integer> catId) {
        List<PromotedCategories> categories = catId.stream().map(PromotedCategories::new)
                .collect(Collectors.toList());
        for (PromotedCategories category : categories) {
            em.persist(category);
        }
    }

    public List<Integer> getPromotedCategories() {
        Query query = em.createQuery("SELECT pc FROM PromotedCategories pc");
        List<PromotedCategories> categories = query.getResultList();

        return categories.stream().map(PromotedCategories::getCatId)
                .collect(Collectors.toList());
    }

    public void deletePromotedCategories(List<Integer> categories) {
        Query query = em.createQuery("DELETE FROM PromotedCategories pc WHERE pc.catId IN :categories");
        query.setParameter("categories", categories);
        query.executeUpdate();
    }

}
