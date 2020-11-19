package monte.carlo.riskofworkinghoursencrease.ui.table;

import monte.carlo.model.workinghoursencrease.RiskOfWorkingHoursEncrease;
import java.util.List;
import monte.carlo.ui.table.AbstractJTable;

/**
 * Таблица с результатами оценки риска превышения трудозатрат
 */
public class RiskOfWorkingHoursEncreaseTable extends AbstractJTable<RiskOfWorkingHoursEncrease> {
    private RiskOfWorkingHoursEncreaseTableModel model;
    
    public RiskOfWorkingHoursEncreaseTable() {
        model = new RiskOfWorkingHoursEncreaseTableModel();
        setModel(model);
    }

    @Override
    public void reload(List<RiskOfWorkingHoursEncrease> items) {
        model.reload(items);
    }

    @Override
    public RiskOfWorkingHoursEncreaseTableModel getModel() {
        return model;
    }
}
