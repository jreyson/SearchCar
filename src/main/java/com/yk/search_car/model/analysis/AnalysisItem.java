package com.yk.search_car.model.analysis;

/**
 * Created by Satani on 2017/2/17.
 */
public class AnalysisItem {
    private double percent;// 百分比
    private String label;// 条目标签

    public AnalysisItem(double percent, String label) {
        this.percent = percent;
        this.label = label;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
