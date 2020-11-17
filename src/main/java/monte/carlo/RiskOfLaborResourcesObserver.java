package monte.carlo;

/**
 * Интерфейс наблюдателей, следящих за изменением значения риска недостатка трудовых ресурсов.
 */
public interface RiskOfLaborResourcesObserver {
    public void updateRiskOfLaborResources();
}
