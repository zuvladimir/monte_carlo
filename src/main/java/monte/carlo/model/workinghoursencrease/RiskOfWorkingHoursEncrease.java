package monte.carlo.model.workinghoursencrease;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import monte.carlo.utils.Utils;

/**
 * Риск превышения трудозатрат
 */
public class RiskOfWorkingHoursEncrease implements Serializable {
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
    private List<RiskOfWorkingHoursEncrease> riskOfWorkingHoursEncreaseList = new ArrayList<>();

    public RiskOfWorkingHoursEncrease() {
    }
    
    public void init() {
        distTechnicalTask = Utils.generateStatisticData(20, 70);
        distDevelopment = Utils.generateStatisticData(110, 180);
        distTesting = Utils.generateStatisticData(150, 220);
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

    public int[] getDistTechnicalTask() {
        return distTechnicalTask;
    }

    public void setDistTechnicalTask(int[] distTechnicalTask) {
        this.distTechnicalTask = distTechnicalTask;
    }

    public int[] getDistDevelopment() {
        return distDevelopment;
    }

    public void setDistDevelopment(int[] distDevelopment) {
        this.distDevelopment = distDevelopment;
    }

    public int[] getDistTesting() {
        return distTesting;
    }

    public void setDistTesting(int[] distTesting) {
        this.distTesting = distTesting;
    }

    public int getPlannedWorkingHours() {
        return plannedWorkingHours;
    }

    public void setPlannedWorkingHours(int plannedWorkingHours) {
        this.plannedWorkingHours = plannedWorkingHours;
    }

    public List<RiskOfWorkingHoursEncrease> getRiskOfWorkingHoursEncreaseList() {
        return riskOfWorkingHoursEncreaseList;
    }

    public void setRiskOfWorkingHoursEncreaseList(List<RiskOfWorkingHoursEncrease> riskOfWorkingHoursEncreaseList) {
        this.riskOfWorkingHoursEncreaseList = riskOfWorkingHoursEncreaseList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
