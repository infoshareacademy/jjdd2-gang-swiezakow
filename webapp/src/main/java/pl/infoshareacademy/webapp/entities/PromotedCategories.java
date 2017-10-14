package pl.infoshareacademy.webapp.entities;

import javax.persistence.Column;
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
    private Integer id;

    @Column(name = "category_id")
    private Integer catId;

    public PromotedCategories() {
    }

    public Integer getCatId() {
        return catId;
    }

    public PromotedCategories(Integer catId) {

        this.catId = catId;
    }
}
