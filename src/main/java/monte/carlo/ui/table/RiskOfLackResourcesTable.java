package monte.carlo.ui.table;

import java.util.List;
import monte.carlo.model.RiskInterface;

/**
 * Таблица с результатами оценки риска нехватки трудовых ресурсов.
 */
public class RiskOfLackResourcesTable extends AbstractJTable<RiskInterface> {

    private RiskOfLackResourcesTableModel model;

    public RiskOfLackResourcesTable() {
        this.model = new RiskOfLackResourcesTableModel();
        setModel(model);
    }

    @Override
    public void reload(List<RiskInterface> items) {
        model.reload(items);
    }

    @Override
    public RiskOfLackResourcesTableModel getModel() {
        return model;
    }

}
