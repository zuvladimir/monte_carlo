package monte.carlo.controller;

import monte.carlo.view.View;
import monte.carlo.model.Model;
import monte.carlo.model.ModelInterface;
import monte.carlo.model.RiskOfLackResources;
import monte.carlo.model.RiskOfWorkingHoursEncrease;
import monte.carlo.model.RiskOfWorkloadEncrease;

/**
 * Класс контроллера
 * 
 */
public class Controller implements ControllerInterface {
    private ModelInterface model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
        model.initialize();
    }

    /**
     * Расчет риска превышения трудозатрат
     */
    @Override
    public void calcRiskOfWorkingHoursEncrease() {
        model.setTotalTests(view.getQuantityOfTests());
        RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease = model.getRiskOfWorkingHoursEncrease();
        riskOfWorkingHoursEncrease.setPlannedWorkingHours(view.getPlannedWorkingHours());
        model.calcRiskOfWorkingHoursEncrease();
    }

    /**
     * Расчет риска недостатка трудовых ресурсов
     */
    @Override
    public void calcRiskOfLackResources() {
        model.setTotalTests(view.getQuantityOfTests());
        RiskOfLackResources riskOfLackResources = model.getRiskOfLackResources();
        riskOfLackResources.setPlannedResources(view.getPlannedResources());
        model.calcRiskOfLackResources();
    }

    /**
     * Расчет риска превышения объема работ
     */
    @Override
    public void calcRiskOfWorkloadEncrease() {
        model.setTotalTests(view.getQuantityOfTests());
        RiskOfWorkloadEncrease riskOfWorkloadEncrease = model.getRiskOfWorkloadEncrease();
        riskOfWorkloadEncrease.setPlannedWorkload(view.getPlannedWorkload());
        model.calcRiskOfWorkloadEncrease();
    }

    @Override
    public void makeDecisionAlgorithm() {
        model.makeDecisionAlgorithm();
    }

}
