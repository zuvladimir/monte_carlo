package monte.carlo;

/**
 * Интерфейс наблюдателей, следящих за изменением значения риска превышения объема работ.
 */
public interface RiskOfExcessWorkObserver {
    public void updateRiskOfExcessWork();
}
