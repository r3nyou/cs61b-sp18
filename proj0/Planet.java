public class Planet {
    /** Its current x position */
    public double xxPos;
    
    /** Its current y position */
    public double yyPos;

    /** Its current velocity in the x direction */
    public double xxVel;

    /** Its current velocity in the y direction */
    public double yyVel;
    
    public double mass;
    
    /** The name of the file that corresponds to the image that depicts the body */
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = (xxPos - p.xxPos) * (xxPos - p.xxPos);
        double dy = (yyPos - p.yyPos) * (yyPos - p.yyPos);

        return Math.sqrt(dx + dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double f = G * mass * p.mass / (r * r);
        
        return f;
    }

    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);

        double fx = f * (p.xxPos - xxPos) / r;
        
        return fx;
    }

    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);

        double fy = f * (p.yyPos - yyPos) / r;
        
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        
        for (int i = 0; i < allPlanets.length; ++i) {
            if (!this.equals(allPlanets[i])) {
                netForceX += calcForceExertedByX(allPlanets[i]);
            }
        }

        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        
        for (int i = 0; i < allPlanets.length; ++i) {
            if (!this.equals(allPlanets[i])) {
                netForceY += calcForceExertedByY(allPlanets[i]);
            }
        }

        return netForceY;
    }
}