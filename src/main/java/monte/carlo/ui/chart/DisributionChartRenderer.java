package monte.carlo.ui.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import monte.carlo.model.RiskInterface;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

/**
 * Класс, содержащий поведение для отрисовки графиков распределений.
 */
public abstract class DisributionChartRenderer {
    
    protected abstract double getRiskProbability(RiskInterface risk);
    
    protected abstract List<RiskInterface> getRiskItems();
    
    /**
     * Шаблонный метод для расчета координат графика нормального распределения
     * 
     * @return CategoryDataset координаты точек для графика
     */
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<RiskInterface> items = getRiskItems();
        // Конвертируем список в массив значений вероятностей
        double[] arr = new double[items.size()];
        double sum = 0;
        for (int i = 0; i < items.size(); i++) {
            arr[i] = getRiskProbability(items.get(i));
            sum += arr[i];
        }
        // Среднее арифметическое
        double mean = sum / (double)items.size();
        // Стандартное отклонение
        StandardDeviation standardDeviation = new StandardDeviation();
        double sd = standardDeviation.evaluate(arr);
        // Заполняем dataset для графика
        items.forEach((item) -> {
            double probability = getRiskProbability(item);
            dataset.addValue(new Gaussian(mean, sd).value(probability), "Число попыток", String.valueOf(item.getId()));
        });
        return dataset;
    }

    protected abstract String getChartTitle();
    
    protected abstract String getChartTitleY();
    
    private JFreeChart createChart(CategoryDataset dataset) {
        boolean rootPaneCheckingEnabled = true;
        JFreeChart chart = ChartFactory.createBarChart(
                getChartTitle(),
                null,
                getChartTitleY(),
                dataset,
                PlotOrientation.VERTICAL,
                rootPaneCheckingEnabled,
                rootPaneCheckingEnabled,
                rootPaneCheckingEnabled);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }

    public JPanel createChartPanel() {
        JFreeChart chart = createChart(createDataset());
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 400));
        return panel;
    }
}
