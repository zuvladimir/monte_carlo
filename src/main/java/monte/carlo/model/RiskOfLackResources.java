package monte.carlo.model;

import java.util.ArrayList;
import java.util.List;
import monte.carlo.utils.RandomUtils;

/**
 * Риск нехватки трудовых ресурсов.
 */
public class RiskOfLackResources implements RiskInterface {
    private static final long serialVersionUID = 1L;
    
    // ID испытания
    private int id;
    // Распределение трудовых ресурсов по болезни сотрудников
    private int[] distResourcesByIllness;
    // Распределение трудовых ресурсов по квалификации
    private int[] distResourcesBySkill;
    // Распределение трудовых ресурсов по загруженности
    private int[] distResourcesByBusyness;
    // Планируемые трудовые ресурсы
    private int plannedResources;

    // трудовые ресурсы во время болезни сотрудников
    private int resourcesByIllness;
    // трудовые ресурсы в зависимости от квалификации сотрудников
    private int resourcesBySkill;
    // трудовые ресурсы в зависимости от загруженности
    private int resourcesByBusyness;
    // вероятность нехватки трудовых ресурсов
    private double chanceOfLackResources;
    // Результаты испытаний при оценке риска нехватки трудовых ресурсов
    private List<RiskInterface> riskOfLackResourcesList = new ArrayList<>();
    
    @Override
    public void init() {
        distResourcesByIllness = RandomUtils.generateStatisticData(15, 50);
        distResourcesBySkill = RandomUtils.generateStatisticData(5, 25);
        distResourcesByBusyness = RandomUtils.generateStatisticData(5, 15);
    }

    /**
     * Алгоритм Расчет риска нехватки трудовых ресурсов
     * 
     * @param totalTest кол-во испытаний
     */
    @Override
    public void calculate(int totalTest) {
        // Числов испытаний с нехваткой ресурсов
        int j = 0;
        getRiskOfLackResourcesList().clear();
        for (int i = 0; i < totalTest; i++) {
            RiskOfLackResources item = new RiskOfLackResources();
            item.setId(i + 1);
            item.setResourcesByIllness(RandomUtils.getValueFromDistribution(distResourcesByIllness));
            item.setResourcesBySkill(RandomUtils.getValueFromDistribution(distResourcesBySkill));
            item.setResourcesByBusyness(RandomUtils.getValueFromDistribution(distResourcesByBusyness));
            // Фактические трудовые ресурсы
            int totalSpentHours = item.getResourcesByIllness() + item.getResourcesBySkill() + item.getResourcesByBusyness();
            if (totalSpentHours < getPlannedResources()) {
                j++;
            }
            item.setChanceOfLackResources(((double) j / (double) totalTest) * 100D);
            getRiskOfLackResourcesList().add(item);
        }
        setChanceOfLackResources(((double)j / (double)totalTest) * 100D);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlannedResources() {
        return plannedResources;
    }

    public void setPlannedResources(int plannedResources) {
        this.plannedResources = plannedResources;
    }

    public int getResourcesByIllness() {
        return resourcesByIllness;
    }

    public void setResourcesByIllness(int resourcesByIllness) {
        this.resourcesByIllness = resourcesByIllness;
    }

    public int getResourcesBySkill() {
        return resourcesBySkill;
    }

    public void setResourcesBySkill(int resourcesBySkill) {
        this.resourcesBySkill = resourcesBySkill;
    }

    public int getResourcesByBusyness() {
        return resourcesByBusyness;
    }

    public void setResourcesByBusyness(int resourcesByBusyness) {
        this.resourcesByBusyness = resourcesByBusyness;
    }

    public double getChanceOfLackResources() {
        return chanceOfLackResources;
    }

    public void setChanceOfLackResources(double chanceOfLackResources) {
        this.chanceOfLackResources = chanceOfLackResources;
    }

    public List<RiskInterface> getRiskOfLackResourcesList() {
        return riskOfLackResourcesList;
    }

    public void setRiskOfLackResourcesList(List<RiskInterface> riskOfLackResourcesList) {
        this.riskOfLackResourcesList = riskOfLackResourcesList;
    }

}
