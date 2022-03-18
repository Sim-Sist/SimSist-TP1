import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static double SIZE = 35;
    private static int PARTICLES = 350;
    private static double RADIUS = 3;
    static int x = 0;
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            test();
        } else {
        Space s = new Space(SIZE);
        s.setCriticalRadius(RADIUS);
        // s.setRadiusLimits(1, 4);
        s.initialize(PARTICLES, .5);
        s.calculateCells();
        s.outputInitialState();
        s.outputNeighbours();
    }
    }

    public static void test() {
        final double size = 20, radius = 1, particleRadius = .25;
        int gridSize, particles;
        Map<Integer, Double> elapsedTimes = new TreeMap<>();

        System.out.println("Starting performance test\n");

        System.out.println("CELL INDEX METHOD");
        // Test performance with fixed grid size as 13
        System.out.println("Test performance with fixed grid size as 13");
        gridSize = 13;
        Space s;
        for (particles = 0; particles <= 1000; particles += 100) {
            List<Double> times = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                s = new Space(size);
                s.setOutputElapsedTime(false);
                s.setCriticalRadius(radius);
                s.initialize(particles, particleRadius);
                long start = System.nanoTime();
                s.calculateCells(gridSize);
                long end = System.nanoTime();
                double elapsed = (end - start) / Math.pow(10, 6);
                times.add(elapsed);
            }
            System.out.print(".");
            elapsedTimes.put(particles, times.stream().mapToDouble(x -> x).average().getAsDouble());
        }
        System.out.println();
        System.out.println("Performance with increasing particles for grid size of " + gridSize + ":");
        System.out.println("Particles   Elapsed Time [ms]");
        elapsedTimes.forEach((k, v) -> System.out.println(String.format("%9d   %12f", k, v)));

        elapsedTimes.clear();

        // Test performance with fixed particles as 1000
        System.out.println("\n\nTest performance with fixed particles as 1000");
        particles = 1000;
        int maxGridSize = (int) (size / (radius + 2 * particleRadius));
        for (gridSize = 1; gridSize <= maxGridSize; gridSize++) {
            List<Double> times = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                s = new Space(size);
                s.setOutputElapsedTime(false);
                s.setCriticalRadius(radius);
                s.initialize(particles, particleRadius);
                long start = System.nanoTime();
                s.calculateCells(gridSize);
                long end = System.nanoTime();
                double elapsed = (end - start) / Math.pow(10, 6);
                times.add(elapsed);
            }
            System.out.print(".");
            elapsedTimes.put(gridSize, times.stream().mapToDouble(x -> x).average().getAsDouble());
        }
        System.out.println();
        System.out.println("Performance with increasing grid size for " + particles + " particles:");
        System.out.println("Grid Size   Elapsed Time [ms]");
        elapsedTimes.forEach((k, v) -> System.out.println(String.format("%9d   %12f", k, v)));

        /*
         * Brute Force Method
         */
        elapsedTimes.clear();
        System.out.println("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("BRUTE FORCE METHOD");
        // Test performance with fixed grid size as 13
        System.out.println("Test performance with fixed grid size as 13");
        for (particles = 0; particles <= 1000; particles += 100) {
            List<Double> times = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                s = new Space(size);
                s.setOutputElapsedTime(false);
                s.setCriticalRadius(radius);
                s.initialize(particles, particleRadius);
                long start = System.nanoTime();
                s.calculateCellsBruteForce();
                long end = System.nanoTime();
                double elapsed = (end - start) / Math.pow(10, 6);
                times.add(elapsed);
            }
            System.out.print(".");
            elapsedTimes.put(particles, times.stream().mapToDouble(x -> x).average().getAsDouble());
        }
        System.out.println();
        System.out.println("Performance with increasing particles for grid size of " + gridSize + ":");
        System.out.println("Particles   Elapsed Time [ms]");
        elapsedTimes.forEach((k, v) -> System.out.println(String.format("%9d   %12f", k, v)));

    }

}
