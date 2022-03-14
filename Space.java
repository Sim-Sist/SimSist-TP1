import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Space {
    private double height, width;
    private Particle[] particles;
    private double criticalRadius = 50;
    private Set<Integer>[] neighbours;
    private final double DEFAULT_MIN_RADIUS = 1, DEFAULT_MAX_RADIUS = 10;
    private double minRadius = DEFAULT_MIN_RADIUS, maxRadius = DEFAULT_MAX_RADIUS;
    private final String INIT_STATE_DEFAULT_FILENAME = "output/particles.txt";
    private final String NEIGHBOURS_DEFAULT_FILENAME = "output/neighbours.txt";

    private Space(double height, double width, int particlesAmount, Double particleRadius) {
        this.height = height;
        this.width = width;
        particles = new Particle[particlesAmount];
        generateSystem(particlesAmount, particleRadius);
    }

    // Particles with random radiuses
    public Space(double size, int particlesAmount) {
        this(size, size, particlesAmount, null);
    }

    // Particles with defined constant radius
    public Space(double size, int particlesAmount, double particleRadius) {
        this(size, size, particlesAmount, particleRadius);
    }

    public void setCriticalRadius(double radius) {
        this.criticalRadius = radius;
    }

    public void setRadiusLimits(double min, double max) {
        if (min < 0 || min > max)
            throw new RuntimeException("Invalid values for radius' limits");
        minRadius = min;
        maxRadius = max;
    }

    private void generateSystem(int particlesAmount, Double particleRadius) {
        Random rnd = new Random();
        for (int i = 0; i < particlesAmount; i++) {
            double radius = (particleRadius == null) ? (rnd.nextDouble() * (maxRadius - minRadius) + minRadius)
                    : particleRadius;
            particles[i] = new Particle(i, rnd.nextDouble() * width, rnd.nextDouble() * height, radius);
        }
    }

    public void calculateCells() {
        neighbours = CellIndexMethod.apply(height, criticalRadius, particles);
    }

    public void calculateCells(int gridSize) {
        neighbours = CellIndexMethod.apply(height, criticalRadius, particles, gridSize);
    }

    public boolean outputInitialState() {
        return this.outputInitialState(INIT_STATE_DEFAULT_FILENAME);
    }

    public boolean outputInitialState(String filename) {
        FileWriter fw;
        try {
            fw = new FileWriter(filename);

            /**
             * Header stile goes like:
             * - Number of particles
             * - Size of Space
             * - Length of critical radius
             * - One free line to put a comment/name. This can also be left blank.
             */
            fw.append(Integer.toString(particles.length)).append('\n');
            fw.append(Double.toString(height)).append('\n');
            fw.append(Double.toString(criticalRadius)).append('\n');
            fw.append('\n');

            /**
             * Body of textfile consists of one line for each particle
             * with its radius and then its xy coordinates, all separated by a spaces
             */
            for (Particle p : particles) {
                fw.append(String.format("%f %f %f\n", p.radius, p.x, p.y));
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean outputNeighbours() {
        return this.outputNeighbours(NEIGHBOURS_DEFAULT_FILENAME);
    }

    public boolean outputNeighbours(String filename) {
        if (neighbours == null) {
            return false;
        }
        FileWriter fw;
        int defaultTarget = 1;
        try {
            fw = new FileWriter(filename);

            /**
             * Header stile goes like:
             * - One free line to put a comment/name. This can also be left blank.
             */
            fw.append('\n');

            /**
             * Body of textfile consists of one line for each particle
             * with a list of all its neighbours separated by a space
             */
            for (Set<Integer> s : neighbours) {
                StringBuilder sb = new StringBuilder();
                for (Integer n : s) {
                    sb.append(n).append(" ");
                }
                if (sb.length() > 0)
                    sb.deleteCharAt(sb.length() - 1); // Delete last space
                sb.append('\n');
                fw.append(sb.toString());
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Space:\n");
        for (Particle p : particles) {
            str.append(p).append('\n');
        }
        return str.toString();
    }
}
