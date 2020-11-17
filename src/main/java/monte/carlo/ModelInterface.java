package monte.carlo;

/**
 * Интерфейс модели.
 * 
 */
public interface ModelInterface {
    public void initialize();
    public void setTotalTests(int totalTest);
    public double getRiskOfLaborCosts();
    public double getRiskOfLaborResources();
    public double getRiskOfExcessWork();
    
    public void calcRiskOfLaborCosts();
    
    public void registerObserver(RiskOfExcessWorkObserver o);
    public void removeObserver(RiskOfExcessWorkObserver o);
    public void registerObserver(RiskOfLaborCostsObserver o);
    public void removeObserver(RiskOfLaborCostsObserver o);
    public void registerObserver(RiskOfLaborResourcesObserver o);
    public void removeObserver(RiskOfLaborResourcesObserver o);
}
