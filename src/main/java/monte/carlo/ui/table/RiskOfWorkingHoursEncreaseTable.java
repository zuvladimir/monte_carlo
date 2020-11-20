package monte.carlo.ui.table;

import java.util.List;
import monte.carlo.model.RiskInterface;

/**
 * Таблица с результатами оценки риска превышения трудозатрат
 */
public class RiskOfWorkingHoursEncreaseTable extends AbstractJTable<RiskInterface> {

    private RiskOfWorkingHoursEncreaseTableModel model;

    public RiskOfWorkingHoursEncreaseTable() {
        model = new RiskOfWorkingHoursEncreaseTableModel();
        setModel(model);
    }

    @Override
    public void reload(List<RiskInterface> items) {
        model.reload(items);
    }

    @Override
    public RiskOfWorkingHoursEncreaseTableModel getModel() {
        return model;
    }
}
