package monte.carlo.model;

import java.io.Serializable;

/**
 * Общий интерфейс для любых рисков.
 */
public interface RiskInterface extends Serializable {
    public int getId();
    public void init();
    /**
     * Выполнить оценку риска.
     * @param totalTest кол-во испытаний
     */
    public void calculate(int totalTest);
}
