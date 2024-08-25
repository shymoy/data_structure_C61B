public class SimpleAttractors {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        double[] xxPop = new double[num];
        double[] yyPop = new double[num];
        double[] xxVel = new double[num];
        double[] yyVel = new double[num];
        double dt =0.5;
        double mass = 1.0;
        double acStrength = 0.01;
        double drag = 0.05;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setScale(-1,1);

        StdDraw.enableDoubleBuffering();

    while (true) {
        if(StdDraw.isMousePressed()) {
            for (int i = 0; i < num; i++) {
                xxVel[i] = 0.2 * Math.random() - 0.1;
                yyVel[i] = 0.2 * Math.random() - 0.1;
            }
        }

            double[] fx = new double[num];
            double[] fy = new double[num];
            for (int i = 0; i < num; i++) {
               double dx = StdDraw.mouseX() - xxPop[i];
                double dy = StdDraw.mouseY() - yyPop[i];
                fx[i] = acStrength * dx - drag * xxVel[i];
                fy[i] = acStrength * dy - drag * yyVel[i];
            }

            for (int i = 0; i < num; i++) {
                xxVel[i] += fx[i]/mass*dt;
                yyVel[i] += fy[i]/mass*dt;
                xxPop[i] += xxVel[i]*dt;
                yyPop[i] += yyVel[i]*dt;
            }
            StdDraw.clear();

            for (int i = 0; i < num; i++) {
                StdDraw.filledCircle(xxPop[i],yyPop[i],0.01);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}