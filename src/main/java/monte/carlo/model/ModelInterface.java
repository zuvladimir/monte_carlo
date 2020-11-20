package monte.carlo.model;

import monte.carlo.view.ViewObserver;

/**
 * Интерфейс модели.
 */
public interface ModelInterface {
    public void initialize();
    public void setTotalTests(int totalTest);    
    public void calcRiskOfWorkingHoursEncrease();
    public RiskOfWorkingHoursEncrease getRiskOfWorkingHoursEncrease();
    public void calcRiskOfLackResources();
    public RiskOfLackResources getRiskOfLackResources();
    public void calcRiskOfWorkloadEncrease();
    public RiskOfWorkloadEncrease getRiskOfWorkloadEncrease();
    public void registerObserver(ViewObserver o);
    public void removeObserver(ViewObserver o);
}
