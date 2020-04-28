package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.mariuszgromada.math.mxparser.Argument;

import javax.swing.*;

public class Graph {
    public static void show(String expression, Double a, Double b, int n) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series_Linear = new XYSeries("Linear");
        XYSeries series_Runge_Square = new XYSeries("Square");
        XYSeries series_Lagrange = new XYSeries("Lagrange");
        XYSeries series_x_y = new XYSeries("Y(x)");
        double h = (double)Math.round(((b-a)/n)*10d)/10d;

        double[] x = new double[10];
        double[] y = new double[10];
        for (int i = 0; i < 10; i++) {
            x[i] = i * h + a;
            y[i] = functions(expression, x[i]);
            series_x_y.add(x[i], y[i]);
        }
        //series_x_y.setDescription("fghjklkjhg");
        dataset.addSeries(series_x_y);


        double[][] result = Linear.linearTable(expression, a, b, n);
        //XYSeries series = new XYSeries("Graph");
        for (int i = 0; i < 10; i++) {
            series_Linear.add(result[i][0], result[i][1]);
        }
        dataset.addSeries(series_Linear);

        double[][] result_Lagrange = Linear.linearTable(expression, a, b, n);
        //XYSeries series = new XYSeries("Graph");
        for (int i = 0; i < 10; i++) {
            series_Lagrange.add(result_Lagrange[i][0], result_Lagrange[i][1]);
        }
        dataset.addSeries(series_Lagrange);

        JFreeChart chart = ChartFactory
                .createXYLineChart("function", "x", "y",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame jFrame = new JFrame("MinimalStaticChart");
        jFrame.getContentPane()
                .add(new ChartPanel(chart));
        jFrame.setSize(800, 600);
        jFrame.show();

    }

    static double f(String expression, Double variablex,Double variabley){// расчёт нашей функции первый- выражение, второй - переменная.
        String line = "x= "+variablex.toString();// Создаю стринг для аргумента.
        String line2 = "y= "+variabley.toString();//Создаю стринг для у
        Argument varx=new Argument(line);
        Argument vary= new Argument(line2);
        org.mariuszgromada.math.mxparser.Expression e = new org.mariuszgromada.math.mxparser.Expression(expression,varx,vary);
        return e.calculate() ;// вернула значение функции со значением аргумента
    }

    static double functions(String expression, Double variable) {
        String line = "x= " + variable.toString();
        Argument var = new Argument(line);
        org.mariuszgromada.math.mxparser.Expression e = new org.mariuszgromada.math.mxparser.Expression(expression, var);
        return e.calculate();
    }
}
