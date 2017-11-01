package pl.infoshareacademy.reportService.ModelsStore;

public class RushHourModel {

    private Integer hour;
    private Long quantity;

    public RushHourModel(Integer hour, Long quantity) {
        this.hour = hour;
        this.quantity = quantity;
    }

    public Integer getHour() {
        return hour;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RushHourModel{" +
                "hour=" + hour +
                ", quantity=" + quantity +
                '}';
    }

}
