package sample;

import static java.lang.Math.*;

public class Linear {
    /**
     * This method is used for counting by dichotomy method
     * @param function
     * @param firstlimit
     * @param secondlimit
     * @param n
     * @return
     */

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

        public static double[][] linearTable() {
            int a = 0;
            int b = 2;
            double h = (b - a)*0.1;
            double[] x = new double[10];
            double[] y = new double[10];
            for (int i = 0; i < 10; i++) {
                x[i] = i * h + a;
                y[i] = function(x[i]);
            }
            int k = 0;
            int n = 10;
            double[] yr = new double[n + 1];
            double[] xm = new double[n + 1];
            double[][] result = new double[n + 1][2];
            for (int i = 0; i <= n; i++) {
                xm[i] = a + (h * i);
            }
            for (int i = 0; i < n; i++) {
                yr[i] = pieceLinear(x, y, xm[i]);
                result[k][0] = x[i];
                result[k][1] = yr[i];
                k++;
            }
            return result;
        }

        public static double function(double x) {
            return cos(2*x + pow(x, 2));
        }

}
