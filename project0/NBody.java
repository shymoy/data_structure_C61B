
public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] allplanets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();


            allplanets[i] = new Planet(xp, yp, xv, yv, mass, image);
        }
        return allplanets;
    }

    public static void main(String[] args) {

        //read command line
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        //get radius to set scale
        double radius = readRadius(filename);

        //define the planets
	    Planet[] allPlanets = readPlanets(filename);

        //set scale
        StdDraw.setScale(-radius,radius);
        StdDraw.enableDoubleBuffering();

        //to draw dynamic graphic
        for(double t = 0; t < T; t += dt) {

            //store the F
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];


            //calculate the F
            for (int j = 0; j < allPlanets.length; j++) {
                xForces[j] = allPlanets[j].calcNetForceExertedByX(allPlanets);
                yForces[j] = allPlanets[j].calcNetForceExertedByY(allPlanets);
            }


            //update argument of plantes
            for (int j = 0; j < allPlanets.length; j++) {
                allPlanets[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.clear();

            //draw the background
            StdDraw.picture(0,0,"proj0/images/starfield.jpg");


            //draw planets on offscreen
            for (int j = 0; j < allPlanets.length; j++) {
                allPlanets[j].draw();
            }
            //copy to onscreen
            StdDraw.show();
            StdDraw.pause(30);
        }
    }
}

