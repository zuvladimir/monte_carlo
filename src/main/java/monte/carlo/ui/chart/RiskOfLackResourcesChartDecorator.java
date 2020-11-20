package monte.carlo.ui.chart;

import java.util.List;
import monte.carlo.model.RiskInterface;
import monte.carlo.model.RiskOfLackResources;

/**
 * Декоратор для риска нехватки трудовых ресурсов.
 * Добавляет поведение для отрисовки диаграммы нормального распределения.
 */
public class RiskOfLackResourcesChartDecorator extends DisributionChartRenderer {
    private RiskOfLackResources riskOfLackResources;

    public RiskOfLackResourcesChartDecorator(RiskOfLackResources riskOfLackResources) {
        this.riskOfLackResources = riskOfLackResources;
    }
    
    @Override
    public String getChartTitle() {
        return "Распределение вероятности нехватки трудовых ресурсов";
    }

    @Override
    public String getChartTitleY() {
        return "Вероятность";
    }

    @Override
    protected double getRiskProbability(RiskInterface risk) {
        return ((RiskOfLackResources)risk).getChanceOfLackResources();
    }

    @Override
    protected List<RiskInterface> getRiskItems() {
        return riskOfLackResources.getRiskOfLackResourcesList();
    }
}
