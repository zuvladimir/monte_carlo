package monte.carlo.ui.table;

import java.util.ArrayList;
import java.util.List;
import monte.carlo.model.RiskInterface;
import monte.carlo.model.RiskOfLackResources;

public class RiskOfLackResourcesTableModel extends AbstractTableModel<RiskInterface> {

    private static final long serialVersionUID = 1L;

    private static final String COL_NAME_0 = "№ исп.";
    private static final String COL_NAME_1 = "Ресурсы при болезни, кол-во чел.";
    private static final String COL_NAME_2 = "Ресурсы по квалификации, кол-во чел.";
    private static final String COL_NAME_3 = "Ресурсы по загруженности, кол-во чел.";
    private static final String COL_NAME_4 = "Вероятность, %";

    private List<RiskInterface> items;

    private final List<String> colNames = new ArrayList<>();

    private final List<Class<?>> colTypes = new ArrayList<>();

    public RiskOfLackResourcesTableModel() {
        {
            colNames.add(COL_NAME_0);
            colNames.add(COL_NAME_1);
            colNames.add(COL_NAME_2);
            colNames.add(COL_NAME_3);
            colNames.add(COL_NAME_4);
        }
        {
            colTypes.add(Integer.class);
            colTypes.add(Integer.class);
            colTypes.add(Integer.class);
            colTypes.add(Integer.class);
            colTypes.add(Double.class);
        }
    }

    @Override
    public void reload(List<RiskInterface> items) {
        this.items = items;
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return colTypes.get(columnIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colNames.get(columnIndex);
    }

    @Override
    public int getRowCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RiskOfLackResources item = (RiskOfLackResources)items.get(rowIndex);
        String colName = colNames.get(columnIndex);
        if (null != colName) {
            switch (colName) {
                case COL_NAME_0:
                    return item.getId();
                case COL_NAME_1:
                    return item.getResourcesByIllness();
                case COL_NAME_2:
                    return item.getResourcesBySkill();
                case COL_NAME_3:
                    return item.getResourcesByBusyness();
                case COL_NAME_4:
                    return item.getChanceOfLackResources();
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
