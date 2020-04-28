package sample;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
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

        double x[]  = new double[n+1];
        double y[]  = new double [n+1];


        double h = (double)Math.round(((b-a)/n)*10d)/10d;

        Double [][]result_Eyler = new Double[n+1][2];
        //result_Eyler = Eyler.method(expression, n, x, y, h);

        Double [][]result_Runge_Kutta = new Double[n+1][2];
        //result_Runge_Kutta = Runge_Kutta.method(expression, n, x, y, h);

        Double [][]result_Adams = new Double[n+1][2];
        //result_Adams = Adams.method(expression, n, x, y, h, a);

        //int j = 0;
        //for(int i=0; i < n; i++){
            //series_Eyler.add(result_Eyler[i][j] , result_Eyler[i][j+1]);
        //}
        //dataset.addSeries(series_Eyler);

        //j = 0;
        //for(int i=0; i < n; i++){
            //series_Adams.add(result_Adams[i][j] , result_Adams[i][j+1]);
        //}
        //dataset.addSeries(series_Adams);

        //j = 0;
        //for(int i=0; i < n; i++){
            //series_Runge_Kutta.add(result_Runge_Kutta[i][j] , result_Runge_Kutta[i][j+1]);
        //}
        //dataset.addSeries(series_Runge_Kutta);


        for (double i = 0; i < n; i++) {
            double xi = i*h + h;
            series_x_y.add(functions(expression, xi), i);
        }
        dataset.addSeries(series_x_y);

        //XYDataset xyDataset = new XYSeriesCollection(series);
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
