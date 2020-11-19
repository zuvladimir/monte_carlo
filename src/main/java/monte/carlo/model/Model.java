package monte.carlo.model;

import monte.carlo.model.workinghoursencrease.RiskOfWorkingHoursEncrease;
import java.io.Serializable;
import java.util.ArrayList;
import monte.carlo.utils.Utils;
import monte.carlo.view.ViewObserver;

/**
 * Модель хранит состояние и содержит алгоритмы расчета всех рисков. При
 * изменении состояния, модель оповещает об этих изменениях зарегистрированных
 * наблюдателей, реализующих интерфейс RiskObserver. Таким образом реализуется
 * паттерн Наблюдатель.
 */
public class Model implements ModelInterface, Serializable {

    private ArrayList<ViewObserver> observers = new ArrayList<>();
    
    // Общее число испытаний
    private int totalTest;
    
    // Риск превышения трудозатрат
    private RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease;

    @Override
    public void initialize() {
        totalTest = 0;
        riskOfWorkingHoursEncrease = new RiskOfWorkingHoursEncrease();
        riskOfWorkingHoursEncrease.init();
        
    }

    /**
     * Алгоритм Расчет риска превышения трудозатрат
     */
    @Override
    public void calcRiskOfWorkingHoursEncrease() {
        // Числов испытаний с превышением трудозатрат
        int j = 0;
        riskOfWorkingHoursEncrease.getRiskOfWorkingHoursEncreaseList().clear();
        for (int i = 0; i < totalTest; i++) {
            RiskOfWorkingHoursEncrease item = new RiskOfWorkingHoursEncrease();
            item.setId( i + 1 );
            item.setTechnicalTask(Utils.getValueFromDistribution(riskOfWorkingHoursEncrease.getDistTechnicalTask()));
            item.setDevelopment(Utils.getValueFromDistribution(riskOfWorkingHoursEncrease.getDistDevelopment()));
            item.setTesting(Utils.getValueFromDistribution(riskOfWorkingHoursEncrease.getDistTesting()));
            // Фактические трудозатраты
            int totalSpentHours = item.getTechnicalTask() + item.getDevelopment() + item.getTesting();
            if (totalSpentHours > riskOfWorkingHoursEncrease.getPlannedWorkingHours()) {
                j++;
            }
            item.setChanceOfWorkingHoursEncrease(((double)j / (double)totalTest)  * 100D);
            riskOfWorkingHoursEncrease.getRiskOfWorkingHoursEncreaseList().add(item);
        }
        riskOfWorkingHoursEncrease.setChanceOfWorkingHoursEncrease(((double)j / (double)totalTest) * 100D);
        notifyRiskOfWorkingHoursEncreaseObservers();
    }
    
    public void notifyRiskOfWorkingHoursEncreaseObservers() {
        observers.forEach((observer) -> {
            observer.updateRiskOfWorkingHoursEncrease();
        });
    }
    
    public void notifyLaborResourcesObservers() {
        observers.forEach((observer) -> {
            observer.updateRiskOfLaborResources();
        });
    }
    
    public void notifyExcessWorkObservers() {
        observers.forEach((observer) -> {
            observer.updateRiskOfExcessWork();
        });
    }

    @Override
    public RiskOfWorkingHoursEncrease getRiskOfWorkingHoursEncrease() {
        return riskOfWorkingHoursEncrease;
    }
    
    @Override
    public void registerObserver(ViewObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ViewObserver o) {
        observers.remove(o);
    }
    
    @Override
    public void setTotalTests(int totalTest) {
        this.totalTest = totalTest;
    }
    
}
