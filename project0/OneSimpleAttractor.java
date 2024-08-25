public class OneSimpleAttractor {
    public static void main(String[] args) {
        double xxPop = 0;
        double yyPop = 0;
        double xxVel = 0;
        double yyVel = 0;
        double mass = 1.0;
        double drag = 0.1;
        double dt = 0.5;
        double acStrength = 0.01;

        StdDraw.enableDoubleBuffering();

        while (true) {
                double dx = StdDraw.mouseX() - xxPop;
                double dy = StdDraw.mouseY() - yyPop;

                double fx = acStrength*dx - drag*xxVel;
                double fy = acStrength*dy - drag*yyVel;

                xxVel = xxVel + fx*dt/mass;
                yyVel = yyVel + fy*dt/mass;

                xxPop += xxVel*dt;
                yyPop += yyVel*dt;
                StdDraw.clear();
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(xxPop,yyPop,0.08);

                StdDraw.show();
                StdDraw.pause(20);
        }
    }
}