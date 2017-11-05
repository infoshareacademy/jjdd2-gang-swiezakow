package pl.infoshareacademy.reportService.ModelsStore;

public class SumDetailedStaticsModel {

    private final Integer visitSum;
    private final Integer feature1Sum;
    private final Integer feature2Sum;
    private final Integer feature3Sum;
    private final Integer feature4Sum;

    public SumDetailedStaticsModel(Integer visitSum, Integer feature1Sum, Integer feature2Sum, Integer feature3Sum, Integer feature4Sum) {
        this.visitSum = visitSum;
        this.feature1Sum = feature1Sum;
        this.feature2Sum = feature2Sum;
        this.feature3Sum = feature3Sum;
        this.feature4Sum = feature4Sum;
    }

    public Integer getVisitSum() {
        return visitSum;
    }

    public Integer getFeature1Sum() {
        return feature1Sum;
    }

    public Integer getFeature2Sum() {
        return feature2Sum;
    }

    public Integer getFeature3Sum() {
        return feature3Sum;
    }

    public Integer getFeature4Sum() {
        return feature4Sum;
    }

    @Override
    public String toString() {
        return "SumDetailedStaticsModel{" +
                "visitSum=" + visitSum +
                ", feature1Sum=" + feature1Sum +
                ", feature2Sum=" + feature2Sum +
                ", feature3Sum=" + feature3Sum +
                ", feature4Sum=" + feature4Sum +
                '}';
    }

}
