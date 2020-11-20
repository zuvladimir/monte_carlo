package monte.carlo.model;

import java.util.ArrayList;
import java.util.List;
import monte.carlo.utils.RandomUtils;

/**
 * Риск превышения трудозатрат
 */
public class RiskOfWorkingHoursEncrease implements RiskInterface {

    private static final long serialVersionUID = 1L;

    // ID испытания
    private int id;
    // Распределение трудозатрат при составлении ТЗ
    private int[] distTechnicalTask;
    // Распределение трудозатрат при разработке кода
    private int[] distDevelopment;
    // Распределение трудозатрат при тестировании
    private int[] distTesting;
    // Планируемые суммарные трудозатраты
    private int plannedWorkingHours;

    // трудозатраты при составлении ТЗ
    private int technicalTask;
    // трудозатраты при разработке кода
    private int development;
    // трудозатраты при тестировании
    private int testing;
    // вероятность превышения трудозатрат
    private double chanceOfWorkingHoursEncrease;
    // Результаты испытаний при оценки риска превышения трудозатрат
    private List<RiskInterface> riskOfWorkingHoursEncreaseList = new ArrayList<>();

    /**
     * Значения для генерирования статистических данных выбраны на основе
     * экспертных мнений.
     */
    @Override
    public void init() {
        distTechnicalTask = RandomUtils.generateStatisticData(20, 70);
        distDevelopment = RandomUtils.generateStatisticData(110, 180);
        distTesting = RandomUtils.generateStatisticData(150, 220);
    }

    /**
     * Алгоритм Расчет риска превышения трудозатрат
     * 
     * @param totalTest число испытаний
     */
    @Override
    public void calculate(int totalTest) {
        // Числов испытаний с превышением трудозатрат
        int j = 0;
        getRiskOfWorkingHoursEncreaseList().clear();
        for (int i = 0; i < totalTest; i++) {
            RiskOfWorkingHoursEncrease item = new RiskOfWorkingHoursEncrease();
            item.setId(i + 1);
            item.setTechnicalTask(RandomUtils.getValueFromDistribution(distTechnicalTask));
            item.setDevelopment(RandomUtils.getValueFromDistribution(distDevelopment));
            item.setTesting(RandomUtils.getValueFromDistribution(distTesting));
            // Фактические трудозатраты
            int totalSpentHours = item.getTechnicalTask() + item.getDevelopment() + item.getTesting();
            if (totalSpentHours > getPlannedWorkingHours()) {
                j++;
            }
            item.setChanceOfWorkingHoursEncrease(((double) j / (double) totalTest) * 100D);
            getRiskOfWorkingHoursEncreaseList().add(item);
        }
        setChanceOfWorkingHoursEncrease(((double)j / (double)totalTest) * 100D);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevelopment() {
        return development;
    }

    public void setDevelopment(int development) {
        this.development = development;
    }

    public int getTesting() {
        return testing;
    }

    public void setTesting(int testing) {
        this.testing = testing;
    }

    public int getTechnicalTask() {
        return technicalTask;
    }

    public void setTechnicalTask(int technicalTask) {
        this.technicalTask = technicalTask;
    }

    public double getChanceOfWorkingHoursEncrease() {
        return chanceOfWorkingHoursEncrease;
    }

    public void setChanceOfWorkingHoursEncrease(double chanceOfWorkingHoursEncrease) {
        this.chanceOfWorkingHoursEncrease = chanceOfWorkingHoursEncrease;
    }

    public int getPlannedWorkingHours() {
        return plannedWorkingHours;
    }

    public void setPlannedWorkingHours(int plannedWorkingHours) {
        this.plannedWorkingHours = plannedWorkingHours;
    }

    public List<RiskInterface> getRiskOfWorkingHoursEncreaseList() {
        return riskOfWorkingHoursEncreaseList;
    }

    public void setRiskOfWorkingHoursEncreaseList(List<RiskInterface> riskOfWorkingHoursEncreaseList) {
        this.riskOfWorkingHoursEncreaseList = riskOfWorkingHoursEncreaseList;
    }

}
