public class Main {
    private static boolean outOfBoundaries(int origin, int target) {
        int gridSize = 3;
        return Math.abs(origin % gridSize - target % gridSize) > 1;
    }

    public static void main(String[] args) {
        // for (int i = 0; i < 9; i++) {
        // System.out.println("\nFor " + i + ":");
        // for(int k = 0; k < 9; k++){
        // System.out.println(k + ": " + outOfBoundaries(i, k));
        // }
        // }

        Space s = new Space(100, 40);
        s.outputInitialState();
        s.calculateCells();
        s.outputNeighbours();
    }
}
