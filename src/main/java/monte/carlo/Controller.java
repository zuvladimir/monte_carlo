package monte.carlo;

/**
 * Класс контроллера
 * 
 */
public class Controller implements ControllerInterface {
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
        model.initialize();
    }

    /**
     * Расчет риска превышения трудозатрат
     * 
     */
    @Override
    public void calcRiskOfLaborCosts() {
        model.setTotalTests(view.getTotalTest());
        model.calcRiskOfLaborCosts();
    }

    /**
     * Расчет риска недостатка трудовых ресурсов
     * 
     */
    @Override
    public void calcRiskOfLaborResources() {
        
    }

    /**
     * Расчет риска превышения объема работ
     * 
     */
    @Override
    public void calcRiskOfExcessWork() {
        
    }

    
}
