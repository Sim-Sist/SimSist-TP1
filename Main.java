public class Main {
    public static void main(String[] args) {
        Space s = new Space(120, 40);
        s.outputInitialState();
        s.calculateCells();
        s.outputNeighbours();
    }
}
