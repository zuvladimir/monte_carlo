package monte.carlo.ui.table;

import java.util.List;
import javax.swing.JTable;

public abstract class AbstractJTable<T> extends JTable {
    public abstract void reload(List<T> items);
}
