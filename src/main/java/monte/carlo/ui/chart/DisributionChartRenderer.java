package monte.carlo.ui.chart;

import javax.swing.JPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

/**
 * Интерфейс для отрисовки графиков распределений
 */
public interface DisributionChartRenderer {
    public CategoryDataset createDataset();
    public JFreeChart createChart(CategoryDataset dataset);
    public JPanel createChartPanel();
}
