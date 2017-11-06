package pl.infoshareacademy.webapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTED_CATEGORIES")
public class PromotedCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer catId;
//
//    public PromotedCategories() {
//    }

    public PromotedCategories(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
}
