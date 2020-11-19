package monte.carlo.ui.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import monte.carlo.model.workinghoursencrease.RiskOfWorkingHoursEncrease;
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
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
/**
 * Адаптер для рисования графиков распределения риска превышения трудозатрат
 */
public class WorkingHoursEncreaseRendererAdapter implements DisributionChartRenderer {
    private RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease;
    
    public WorkingHoursEncreaseRendererAdapter(RiskOfWorkingHoursEncrease riskOfWorkingHoursEncrease) {
        this.riskOfWorkingHoursEncrease = riskOfWorkingHoursEncrease;
    }
    
    @Override
    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<RiskOfWorkingHoursEncrease> items = riskOfWorkingHoursEncrease.getRiskOfWorkingHoursEncreaseList();
        // Конвертируем список в массив значений вероятностей
        double[] arr = new double[items.size()];
        double sum = 0;
        for (int i = 0; i < items.size(); i++) {
            arr[i] = items.get(i).getChanceOfWorkingHoursEncrease();
            sum += arr[i];
        }
        // Среднее арифметическое
        double mean = sum / (double)items.size();
        // Стандартное отклонение
        StandardDeviation standardDeviation = new StandardDeviation();
        double sd = standardDeviation.evaluate(arr);
        // Заполняем dataset для графика
        items.forEach((item) -> {
            double probability = item.getChanceOfWorkingHoursEncrease();
            dataset.addValue(new Gaussian(mean, sd).value(probability), "Число попыток", String.valueOf(item.getId()));
        });
        return dataset;
    }

    @Override
    public JFreeChart createChart(CategoryDataset dataset) {
        boolean rootPaneCheckingEnabled = true;
        JFreeChart chart = ChartFactory.createBarChart(
                "Распределение вероятности превышения трудозатрат",
                null,
                "Вероятность",
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

    @Override
    public JPanel createChartPanel() {
        JFreeChart chart = createChart(createDataset());
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(800, 800));
        return panel;
    }
    
}
