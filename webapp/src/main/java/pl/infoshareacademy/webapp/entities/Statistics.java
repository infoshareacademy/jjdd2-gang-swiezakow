package pl.infoshareacademy.webapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "STATISTICS")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "parametr")
    private String parametr;

    @Column(name = "date")
    private Date date;

    public Statistics () {
    }

    public Statistics(String name, String parametr, Date date) {
        this.name = name;
        this.parametr = parametr;
        this.date = date;
    }

    public Statistics(String name, String parametr) {
        this.name = name;
        this.parametr = parametr;
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParametr() {
        return parametr;
    }

    public Date getDate() {
        return date;
    }
}
