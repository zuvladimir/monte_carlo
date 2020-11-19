package monte.carlo.controller;

import monte.carlo.view.View;
import monte.carlo.model.Model;
import monte.carlo.model.ModelInterface;
import monte.carlo.model.workinghoursencrease.RiskOfWorkingHoursEncrease;

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
    public void calcRiskOfLaborResources() {
        
    }

    /**
     * Расчет риска превышения объема работ
     */
    @Override
    public void calcRiskOfExcessWork() {
        
    }

    

}
