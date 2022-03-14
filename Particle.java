
public class Particle {
    private int index;
    public double x, y;

    public Particle(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
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
