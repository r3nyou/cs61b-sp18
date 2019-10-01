public class NBody {
    /**
     * Draw the initial universe state
     */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);

        /**
         * Drawing the Background
         */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /**
         * Drawing All of the Planets
         */
        Planet[] planets = readPlanets(fileName);
        for (Planet planet : planets) {
            planet.draw();
        }

        /**
         * Creating an Animation
         */
        StdDraw.enableDoubleBuffering();

        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

    }

    public static double readRadius(String file) {
        In in = new In(file);
        int n = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int n = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[n];
        int i = 0;
        while (i < n) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i++] = new Planet(xP, yP, xV, yV, m, img);
        }

        return planets;
    }
}