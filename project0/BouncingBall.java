public class BouncingBall {
    public static void main(String[] args) {
        //define the arguments of ball
        double xxPop = 0.480, yyPop = 0.860;     // position
        double xxVel = 0.015, yyVel = 0.023;     // velocity
        double radius = 0.05;

        //set the scale
        StdDraw.setXscale(-1.0,1.0);
        StdDraw.setYscale(-1.0,1.0);

        //enable doublebuffering
        StdDraw.enableDoubleBuffering();

        while(true) {

            //bountary elastic collision
            if(Math.abs(xxPop+xxVel) > 1.0-radius) xxVel = -xxVel;
            if(Math.abs(yyPop+yyVel) > 1.0-radius) yyVel = -yyVel;

            //refresh the position
            xxPop += xxVel;
            yyPop += yyVel;

            StdDraw.clear(StdDraw.LIGHT_GRAY);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(xxPop, yyPop, radius);

            StdDraw.show();

            StdDraw.pause(20);

        }

    }
}