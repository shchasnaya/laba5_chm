package sample;

import org.mariuszgromada.math.mxparser.Argument;

import static java.lang.Math.*;

public class Linear {

    public static double pieceLinear(double[] x, double[] y, double xm) {
        int n = 10;
        if (xm < x[0]) {
            return y[0];
        }
        if (xm > x[n - 1])
            return x[n - 1];
        int il = 0;
        for (int i = 0; i <= n - 1; i++) {
            if (xm == x[i])
                return y[i];
            else if (xm < x[i]) {
                il = i;
                break;
            }
        }
        return (y[il - 1] + (xm - x[il - 1]) * (y[il] - y[il - 1]) / (x[il] - x[il - 1]));
    }

    public static double[][] linearTable(String f, double a, double b, int n) {
        double h = (b - a) * 0.1;
        double[] x = new double[n + 1];
        double[] y = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            x[i] = i * h + a;
            y[i] = function(f, x[i]);
        }
        int k = 0;
        double[] yr = new double[n + 1];
        double[][] result = new double[n + 1][2];
        for (int i = 0; i < n + 1; i++) {
            yr[i] = pieceLinear(x, y, x[i]);
            result[k][0] =(double)round((x[i])*10000d)/10000d;
            result[k][1] = (double)round((yr[i])*10000d)/10000d;
            k++;
        }
        return result;
    }


    public static double function(String expression, Double variable) {
        String line = "x= " + variable.toString();
        Argument var = new Argument(line);
        org.mariuszgromada.math.mxparser.Expression e = new org.mariuszgromada.math.mxparser.Expression(expression, var);
        return e.calculate();
    }

}
