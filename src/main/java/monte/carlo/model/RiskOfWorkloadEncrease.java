package monte.carlo.model;

import java.util.ArrayList;
import java.util.List;
import monte.carlo.utils.RandomUtils;

/**
 * Риск превышения обьема работ.
 */
public class RiskOfWorkloadEncrease implements RiskInterface {

    private static final long serialVersionUID = 1L;

    // ID испытания
    private int id;
    // Распределение работ по побочным задачам
    private int[] distWorkloadBySideTasks;
    // Распределение работ по анализу ТЗ
    private int[] distWorkloadByAnalysis;
    // Распределение работ по новым задачам
    private int[] distWorkloadByNewTasks;
    // Планируемый обьем работ (кол-во задач)
    private int plannedWorkload;

    // работы над побочными задачами
    private int workloadBySideTasks;
    // работы по анализу ТЗ
    private int workloadByAnalysis;
    // работы по новым задачам
    private int workloadByNewTasks;
    // вероятность превышения обьемов работ
    private double chanceOfWorkloadEncrease;
    // Результаты испытаний при оценке риска превышения обьемов работ
    private List<RiskInterface> riskOfWorkloadEncreaseList = new ArrayList<>();

    @Override
    public void init() {
        distWorkloadBySideTasks = RandomUtils.generateStatisticData(10, 90);
        distWorkloadByAnalysis = RandomUtils.generateStatisticData(50, 125);
        distWorkloadByNewTasks = RandomUtils.generateStatisticData(5, 100);
    }

    /**
     * Алгоритм Расчет риска превышения обьема работ
     * 
     * @param totalTest кол-во испытаний
     */
    @Override
    public void calculate(int totalTest) {
        // Числов испытаний с превышением обьмов работ
        int j = 0;
        getRiskOfWorkloadEncreaseList().clear();
        for (int i = 0; i < totalTest; i++) {
            RiskOfWorkloadEncrease item = new RiskOfWorkloadEncrease();
            item.setId(i + 1);
            item.setWorkloadBySideTasks(RandomUtils.getValueFromDistribution(distWorkloadBySideTasks));
            item.setWorkloadByAnalysis(RandomUtils.getValueFromDistribution(distWorkloadByAnalysis));
            item.setWorkloadByNewTasks(RandomUtils.getValueFromDistribution(distWorkloadByNewTasks));
            // Фактические обьем работ
            int totalSpentHours = item.getWorkloadBySideTasks() + item.getWorkloadByAnalysis() + item.getWorkloadByNewTasks();
            if (totalSpentHours > getPlannedWorkload()) {
                j++;
            }
            item.setChanceOfWorkloadEncrease(((double) j / (double) totalTest) * 100D);
            getRiskOfWorkloadEncreaseList().add(item);
        }
        setChanceOfWorkloadEncrease(((double) j / (double) totalTest) * 100D);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlannedWorkload() {
        return plannedWorkload;
    }

    public void setPlannedWorkload(int plannedWorkload) {
        this.plannedWorkload = plannedWorkload;
    }

    public int getWorkloadBySideTasks() {
        return workloadBySideTasks;
    }

    public void setWorkloadBySideTasks(int workloadBySideTasks) {
        this.workloadBySideTasks = workloadBySideTasks;
    }

    public int getWorkloadByAnalysis() {
        return workloadByAnalysis;
    }

    public void setWorkloadByAnalysis(int workloadByAnalysis) {
        this.workloadByAnalysis = workloadByAnalysis;
    }

    public int getWorkloadByNewTasks() {
        return workloadByNewTasks;
    }

    public void setWorkloadByNewTasks(int workloadByNewTasks) {
        this.workloadByNewTasks = workloadByNewTasks;
    }

    public double getChanceOfWorkloadEncrease() {
        return chanceOfWorkloadEncrease;
    }

    public void setChanceOfWorkloadEncrease(double chanceOfWorkloadEncrease) {
        this.chanceOfWorkloadEncrease = chanceOfWorkloadEncrease;
    }

    public List<RiskInterface> getRiskOfWorkloadEncreaseList() {
        return riskOfWorkloadEncreaseList;
    }

    public void setRiskOfWorkloadEncreaseList(List<RiskInterface> riskOfWorkloadEncreaseList) {
        this.riskOfWorkloadEncreaseList = riskOfWorkloadEncreaseList;
    }

}
