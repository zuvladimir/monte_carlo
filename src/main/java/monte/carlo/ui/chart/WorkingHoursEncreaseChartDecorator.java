package monte.carlo.ui.chart;

import java.util.List;
import monte.carlo.model.RiskInterface;
import monte.carlo.model.RiskOfWorkingHoursEncrease;

/**
 * Декоратор для риска превышения трудозатрат.
 * Добавляет поведение для отрисовки диаграммы нормального распределения.
 */
public class WorkingHoursEncreaseChartDecorator extends DisributionChartRenderer {
    private RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease;

    public WorkingHoursEncreaseChartDecorator(RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease) {
        this.riskOfWorkingHoursEncrease = riskOfWorkingHoursEncrease;
    }

    @Override
    public String getChartTitle() {
        return "Распределение вероятности превышения трудозатрат";
    }

    @Override
    public String getChartTitleY() {
        return "Вероятность";
    }

    @Override
    protected double getRiskProbability(RiskInterface risk) {
        return ((RiskOfWorkingHoursEncrease)risk).getChanceOfWorkingHoursEncrease();
    }

    @Override
    protected List<RiskInterface> getRiskItems() {
        return riskOfWorkingHoursEncrease.getRiskOfWorkingHoursEncreaseList();
    }

}
