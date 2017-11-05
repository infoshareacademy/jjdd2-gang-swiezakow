package pl.infoshareacademy.webapp.dao;

public class StatisticResult {

    private final String name;
    private Integer number = 0;

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

    @Override
    public String toString() {
        return "StatisticResult{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
