public class FunctionGraph {
    public static void main(String[] args) {
        StdDraw.setFront()
        int n = Integer.parseInt(args[0]);
        double[] x = new double[n+1];
        double[] y = new double[n+1];
        for(int i = 0; i<n+1;i++)
        {
            x[i] = Math.PI*i/n;
            y[i] = Math.sin(4*x[i]) + Math.sin(20*x[i]);
        }
        StdDraw.setXscale(0, Math.PI);
        StdDraw.setYscale(-2.1, +2.1);
        for(int i = 0; i<n;i++)
        {
            StdDraw.line(x[i],y[i],x[i+1],y[i+1]);
        }
    }
}