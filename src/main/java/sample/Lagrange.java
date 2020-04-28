package sample;

public class Lagrange {
    /**
     * This method is used for counting by dichotomy method
     * @param function
     * @param firstlimit
     * @param secondlimit
     * @param n
     * @return
     */
    public static String Langrange(String function, Double firstlimit, Double secondlimit, int n){
        String result = "";
        double h = (double)Math.round(((secondlimit-firstlimit)/n)*10d)/10d;
        Double y[] = new Double[n+1];
        Double x[] = new Double[n+1];

        for(int i  = 0; i<=n; i++){
            x[i] = (double)Math.round(((i*h+firstlimit))*10d)/10d;
            y[i] =(double)Math.round((Graph.functions(function,x[i]))*10000d)/10000d;

        //    System.out.println(x[i]+ " " + y[i] + " " );
        }
        Double sum  = 0.0;
        Double proizvod = 1.0;
        Double L[] = new Double[n+1];

        for(int s = 0 ; s <= n; s++) {
            L[s] = firstlimit + (h * s);
        }
        int t = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    proizvod *= 1;
                } else proizvod *= (L[i] - x[j]) / (x[i] - x[j]);
            }
            x[i] = (double)Math.round(((i*h+firstlimit))*10d)/10d;
            y[i] =(double)Math.round((Graph.functions(function,x[i]))*10000d)/10000d;
            sum += proizvod * y[i];
            sum = (double)Math.round((sum)*10000d)/10000d;
            proizvod = 1.0;
            result += x[i] + " " + sum + "\n";
            sum = 0.0;

        }
        return result;
    }
}
