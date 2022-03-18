import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class BruteForceMethod {
    private static boolean periodicBoundary = false;
    private static double size;
    @SuppressWarnings("unchecked")
    public static Set<Integer>[] apply(Particle[] particles, double criticalRadius, double spaceSize) {
        size = spaceSize;
        BiFunction<Particle, Particle, Double> computeDistance = periodicBoundary
                ? (p1, p2) -> getPeriodicDistance(p1, p2)
                : (p1, p2) -> p1.distanceTo(p2);
        List<Particle> particlesList = new ArrayList<>(Arrays.asList(particles));
        Set<Integer>[] neighbours;
        neighbours = (Set<Integer>[]) (new HashSet[particles.length]);
        Arrays.setAll(neighbours, (i) -> setsProvider());
        for (Particle p : particlesList) {
            particlesList.remove(p);
            for (Particle q : particlesList) {
                double distance = computeDistance.apply(p, q);
                if (distance <= criticalRadius) {
                    neighbours[p.getIndex()].add(q.getIndex());
                    neighbours[q.getIndex()].add(p.getIndex());
                }
            }
        }
        return neighbours;
    }

    private static Set<Integer> setsProvider() {
        return new HashSet<>();
    }

    private static double getPeriodicDistance(Particle p1, Particle p2) {
        double dx = Math.abs(p1.x - p2.x);
        double dy = Math.abs(p1.y - p2.y);
        if (dx > 0.5 * size)
            dx = size - dx;
        if (dy > 0.5 * size)
            dy = size - dy;

        return (Math.sqrt(dx * dx + dy * dy)) - (p1.radius + p2.radius);
    }
}
