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
    
    // Принятое решение
    private String decision;
    
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
    
    public void notifyDecisionObservers() {
        observers.forEach((observer) -> {
            observer.updateDecision();
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

    /**
     * Алгоритм выбора вариантов обработки рисков для поддержки принятия решений 
     */
    @Override
    public void makeDecisionAlgorithm() {
        double chanceOfWorkingHoursEncrease = riskOfWorkingHoursEncrease.getChanceOfWorkingHoursEncrease();
        double chanceOfLackResources = riskOfLackResources.getChanceOfLackResources();
        double chanceOfWorkloadEncrease = riskOfWorkloadEncrease.getChanceOfWorkloadEncrease();
        
        if (chanceOfWorkingHoursEncrease > 0D) {
            if (chanceOfWorkingHoursEncrease > 50D) {
                decision = Decision.DO_NOT_CONTINUE.getLabel();
            } else if (chanceOfWorkingHoursEncrease <= 10D) {
                decision = Decision.START.getLabel();
            } else if (chanceOfWorkingHoursEncrease > 10D && chanceOfWorkingHoursEncrease <= 50D) {
                decision = Decision.ENCREASE_WORKING_HOURS.getLabel();
            } else {
                decision = Decision.DO_NOT_CONTINUE.getLabel();
            }
        } else if (chanceOfLackResources > 0D) {
            if (chanceOfLackResources > 70D) {
                decision = Decision.DO_NOT_START.getLabel();
            } else if (chanceOfLackResources <= 30D) {
                decision = Decision.START.getLabel();
            } else if (chanceOfLackResources > 30 && chanceOfLackResources <= 70) {
                decision = Decision.ENCREASE_RESOURCES.getLabel();
            } else {
                decision = Decision.DO_NOT_START.getLabel();
            }
        } else if (chanceOfWorkloadEncrease > 0D) {
            if (chanceOfWorkloadEncrease > 70D) {
                decision = Decision.DO_NOT_CONTINUE.getLabel();
            } else if (chanceOfWorkloadEncrease <= 30D) {
                decision = Decision.START.getLabel();
            } else if (chanceOfWorkloadEncrease > 30D && chanceOfWorkloadEncrease <= 70D 
                    && chanceOfWorkingHoursEncrease <= 10) {
                decision = Decision.START.getLabel();
            } else {
                decision = Decision.DO_NOT_CONTINUE.getLabel();
            }
        } else {
            decision = Decision.START.getLabel();
        }
        notifyDecisionObservers();
    }
    
    /**
     * Варианты решений
     */
    private enum Decision {
        START("Принять риск и начать деятельность."),
        DO_NOT_START("Непримелимый риск. Не начинать деятельность."),
        DO_NOT_CONTINUE("Непримелимый риск. Не продолжать деятельность."),
        ENCREASE_WORKING_HOURS("Пересмотреть модель данных и увеличить запланированные трудозатраты."),
        ENCREASE_RESOURCES("Принять риск. Привлечь к работам подрядные организации с целью разделить риск.");
        
        private String label;
        
        Decision(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    @Override
    public String getDecision() {
        return decision;
    }

}
