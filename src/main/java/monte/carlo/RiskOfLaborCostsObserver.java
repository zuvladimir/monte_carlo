package monte.carlo;

/**
 * Интерфейс наблюдателей, следящих за изменением значения риска превышения трудозатрат.
 * 
 */
public interface RiskOfLaborCostsObserver {
    public void updateRiskOfLaborCosts();
}
