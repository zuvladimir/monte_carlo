package monte.carlo.view;

/**
 * Интерфейс наблюдателей, следящих за изменением значения рисков.
 */
public interface ViewObserver {
    public void updateRiskOfWorkingHoursEncrease();
    public void updateRiskOfLackResources();
    public void updateRiskOfWorkloadEncrease();
    public void updateDecision();
}
