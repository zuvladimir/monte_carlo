package monte.carlo.model;

import java.io.Serializable;
import java.util.ArrayList;
import monte.carlo.view.ViewObserver;

/**
 * Модель хранит состояние и содержит алгоритмы расчета всех рисков. При
 * изменении состояния, модель оповещает об этих изменениях зарегистрированных
 * наблюдателей, реализующих интерфейс RiskObserver. Таким образом реализуется
 * паттерн Наблюдатель.
 */
public class Model implements ModelInterface, Serializable {

    private final ArrayList<ViewObserver> observers = new ArrayList<>();
    
    // Общее число испытаний
    private int totalTest;
    
    // Риск превышения трудозатрат
    private RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease;
    
    // Риск недостатка трудовых ресурсов
    private RiskOfLackResources riskOfLackResources;
    
    // Риск превышения обьема работ
    private RiskOfWorkloadEncrease riskOfWorkloadEncrease;

    @Override
    public void initialize() {
        totalTest = 0;
        riskOfWorkingHoursEncrease = new RiskOfWorkingHoursEncrease();
        riskOfWorkingHoursEncrease.init();
        riskOfLackResources = new RiskOfLackResources();
        riskOfLackResources.init();
        riskOfWorkloadEncrease = new RiskOfWorkloadEncrease();
        riskOfWorkloadEncrease.init();
    }

    @Override
    public void calcRiskOfWorkingHoursEncrease() {
        riskOfWorkingHoursEncrease.calculate(totalTest);
        notifyRiskOfWorkingHoursEncreaseObservers();
    }
    
    @Override
    public void calcRiskOfLackResources() {
        riskOfLackResources.calculate(totalTest);
        notifyRiskOfLackResourcesObservers();
    }
    
    @Override
    public void calcRiskOfWorkloadEncrease() {
        riskOfWorkloadEncrease.calculate(totalTest);
        notifyRiskOfWorkloadEncreaseObservers();
    }

    public void notifyRiskOfWorkingHoursEncreaseObservers() {
        observers.forEach((observer) -> {
            observer.updateRiskOfWorkingHoursEncrease();
        });
    }

    public void notifyRiskOfLackResourcesObservers() {
        observers.forEach((observer) -> {
            observer.updateRiskOfLackResources();
        });
    }
    
    public void notifyRiskOfWorkloadEncreaseObservers() {
        observers.forEach((observer) -> {
            observer.updateRiskOfWorkloadEncrease();
        });
    }

    @Override
    public RiskOfWorkingHoursEncrease getRiskOfWorkingHoursEncrease() {
        return riskOfWorkingHoursEncrease;
    }
    
    @Override
    public RiskOfLackResources getRiskOfLackResources() {
        return riskOfLackResources;
    }
    
    @Override
    public RiskOfWorkloadEncrease getRiskOfWorkloadEncrease() {
        return riskOfWorkloadEncrease;
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
