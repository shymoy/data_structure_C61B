public class PlotFilter {

    public static void main(String[] args) {

        // read in bounding box and rescale
        double xmin = StdIn.readDouble();
        double ymin = StdIn.readDouble();
        double xmax = StdIn.readDouble();
        double ymax = StdIn.readDouble();
        StdDraw.setXscale(xmin, xmax);
        StdDraw.setYscale(ymin, ymax);

        // to speed up performance, defer displaying points
        StdDraw.enableDoubleBuffering();

        // plot points, one at a time
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            StdDraw.point(x, y);
        }

        // display all the points
        StdDraw.show();

    }
}
