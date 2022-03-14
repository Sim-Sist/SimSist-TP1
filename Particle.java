
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

    public double distanceTo(Particle p) {
        double deltaX = this.x - p.x;
        double deltaY = this.y - p.y;
        return (Math.sqrt(deltaX * deltaX + deltaY * deltaY)) - (this.radius + p.radius);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Particle))
            return false;
        Particle p = (Particle) o;
        return p.x == x && p.y == y && p.index == index && p.radius == radius;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%d: ", index)).append(String.format("r:%.3f x:%.3f  y:%.3f", radius, x, y));
        return str.toString();
    }

}
