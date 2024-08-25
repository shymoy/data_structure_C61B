

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public double calcDistance(Planet b1) {
        double Dis_x = (xxPos - b1.xxPos);
        double Dis_y = (yyPos - b1.yyPos);
        return Math.sqrt(Dis_x * Dis_x + Dis_y * Dis_y);
    }

    public double calcForceExertedBy(Planet b1) {
        final double F = (6.67e-11 * mass * b1.mass) / Math.pow(calcDistance(b1), 2);
        return F;
    }

    public double calcForceExertedByX(Planet b1) {
        final double Fx = calcForceExertedBy(b1) * (b1.xxPos - this.xxPos) / calcDistance(b1);
        return Fx;
    }

    public double calcForceExertedByY(Planet b1) {
        final double Fy = calcForceExertedBy(b1) * (b1.yyPos - this.yyPos) / calcDistance(b1);
        return Fy;
    }

    public boolean equals(Planet b1) {
        return b1 == this;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double NetFx = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i]))
                continue;
            else {
                NetFx += calcForceExertedByX(allPlanets[i]);
            }
        }
        return NetFx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double NetFy = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i]))
                continue;
            else {
                NetFy += calcForceExertedByY(allPlanets[i]);
            }
        }
        return NetFy;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public void draw() {
        StdDraw.picture(xxPos,yyPos,"./proj0/images/"+ imgFileName);
    }
}