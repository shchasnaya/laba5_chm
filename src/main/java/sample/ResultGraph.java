package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class ResultGraph {
    public static void show(String expression, Double a, Double b, int n) {
        double[][] result = Linear.linearTable(expression, a, b, n);
        XYSeries series = new XYSeries("Graph");
        for (int i = 0; i < 10; i++) {
            series.add(result[i][0], result[i][1]);
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("function", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame jFrame = new JFrame("MinimalStaticChart");
        jFrame.getContentPane()
                .add(new ChartPanel(chart));
        jFrame.setSize(800, 600);
        jFrame.show();
    }
}
