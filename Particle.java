
public class Particle {
    private int index;
    public double x, y;
    public double radius;

    public Particle(int index, double x, double y, double radius) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%d: ", index)).append(String.format("%3f  %3f", x, y));
        return str.toString();
    }

}
