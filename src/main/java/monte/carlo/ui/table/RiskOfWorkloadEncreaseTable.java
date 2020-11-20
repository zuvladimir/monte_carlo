package monte.carlo.ui.table;

import java.util.List;
import monte.carlo.model.RiskInterface;

/**
 * Таблица с результатами оценки риска превышения обьема работ.
 */
public class RiskOfWorkloadEncreaseTable extends AbstractJTable<RiskInterface> {

    private RiskOfWorkloadEncreaseTableModel model;

    public RiskOfWorkloadEncreaseTable() {
        this.model = new RiskOfWorkloadEncreaseTableModel();
        setModel(model);
    }

    @Override
    public void reload(List<RiskInterface> items) {
        model.reload(items);
    }

    @Override
    public RiskOfWorkloadEncreaseTableModel getModel() {
        return model;
    }

}
