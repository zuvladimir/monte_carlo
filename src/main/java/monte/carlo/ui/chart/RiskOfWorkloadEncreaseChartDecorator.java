package monte.carlo.ui.chart;

import java.util.List;
import monte.carlo.model.RiskInterface;
import monte.carlo.model.RiskOfWorkloadEncrease;

/**
 * Декоратор для риска превышения обьемов работ.
 * Добавляет поведение для отрисовки диаграммы нормального распределения.
 */
public class RiskOfWorkloadEncreaseChartDecorator  extends DisributionChartRenderer {
    private RiskOfWorkloadEncrease riskOfWorkloadEncrease;

    public RiskOfWorkloadEncreaseChartDecorator(RiskOfWorkloadEncrease riskOfWorkloadEncrease) {
        this.riskOfWorkloadEncrease = riskOfWorkloadEncrease;
    }
    
    @Override
    public String getChartTitle() {
        return "Распределение вероятности превышения обьёмов работ";
    }

    @Override
    public String getChartTitleY() {
        return "Вероятность";
    }

    @Override
    public double getRiskProbability(RiskInterface risk) {
        return ((RiskOfWorkloadEncrease)risk).getChanceOfWorkloadEncrease();
    }

    @Override
    public List<RiskInterface> getRiskItems() {
        return riskOfWorkloadEncrease.getRiskOfWorkloadEncreaseList();
    }
}
