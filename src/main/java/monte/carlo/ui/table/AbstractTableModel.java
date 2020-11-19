package monte.carlo.ui.table;

import java.util.List;

public abstract class AbstractTableModel<T> extends javax.swing.table.AbstractTableModel {
    public abstract void reload(List<T> attributes);
}
