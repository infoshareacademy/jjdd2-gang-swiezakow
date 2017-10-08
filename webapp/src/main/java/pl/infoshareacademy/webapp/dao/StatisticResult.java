package pl.infoshareacademy.webapp.dao;

public class StatisticResult {

    private String name;
    private Integer number;

    public StatisticResult(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }
}
