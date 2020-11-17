package monte.carlo;

import java.util.ArrayList;

/**
 * Модель хранит состояние и содержит алгоритмы расчета всех рисков. При
 * изменении состояния, модель оповещает об этих изменениях зарегистрированных
 * наблюдателей, реализующих интерфейсы RiskOfExcessWorkObserver,
 * RiskOfLaborCostsObserver, RiskOfLaborResourcesObserver.
 *
 */
public class Model implements ModelInterface {

    private ArrayList<RiskOfExcessWorkObserver> excessWorkObservers = new ArrayList<>();
    private ArrayList<RiskOfLaborResourcesObserver> laborResourcesObservers = new ArrayList<>();
    private ArrayList<RiskOfLaborCostsObserver> laborCostsObservers = new ArrayList<>();
    private int totalTest;
    private double riskOfLaborCosts;
    private double riskOfLaborResources;
    private double riskOfExcessWork;

    @Override
    public void initialize() {
        totalTest = 0;
        riskOfLaborCosts = 0;
        riskOfLaborResources = 0;
        riskOfExcessWork = 0;
    }

    @Override
    public void registerObserver(RiskOfExcessWorkObserver o) {
        excessWorkObservers.add(o);
    }

    @Override
    public void removeObserver(RiskOfExcessWorkObserver o) {
        excessWorkObservers.remove(o);
    }

    @Override
    public void registerObserver(RiskOfLaborCostsObserver o) {
        laborCostsObservers.add(o);
    }

    @Override
    public void removeObserver(RiskOfLaborCostsObserver o) {
        laborCostsObservers.remove(o);
    }

    @Override
    public void registerObserver(RiskOfLaborResourcesObserver o) {
        laborResourcesObservers.add(o);
    }

    @Override
    public void removeObserver(RiskOfLaborResourcesObserver o) {
        laborResourcesObservers.remove(o);
    }

    @Override
    public void setTotalTests(int totalTest) {
        this.totalTest = totalTest;
    }

    @Override
    public void calcRiskOfLaborCosts() {
        // Алгоритм Расчет риска превышения трудозатрат
        riskOfLaborCosts = 100;
        notifyLaborCostsObservers();
    }

    public void notifyExcessWorkObservers() {
        excessWorkObservers.forEach((observer) -> {
            observer.updateRiskOfExcessWork();
        });
    }

    public void notifyLaborResourcesObservers() {
        laborResourcesObservers.forEach((observer) -> {
            observer.updateRiskOfLaborResources();
        });
    }

    public void notifyLaborCostsObservers() {
        laborCostsObservers.forEach((observer) -> {
            observer.updateRiskOfLaborCosts();
        });
    }

    @Override
    public double getRiskOfLaborCosts() {
        return riskOfLaborCosts;
    }

    @Override
    public double getRiskOfLaborResources() {
        return riskOfLaborResources;
    }

    @Override
    public double getRiskOfExcessWork() {
        return riskOfExcessWork;
    }

    
}
