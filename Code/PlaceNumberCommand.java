public class PlaceNumberCommand implements Command {
    private SudokuSolver solver;
    private int[][] grid; // La grille actuelle
    private int row, col, value;
    private int previousValue; // Pour stocker l'ancienne valeur et l'annuler

    public PlaceNumberCommand(SudokuSolver solver, int row, int col, int value) {
        this.solver = solver;
        this.grid = solver.getGrid(); // Obtenir la grille actuelle
        this.row = row;
        this.col = col;
        this.value = value;
        this.previousValue = grid[row][col]; // Mémoriser la valeur précédente
    }

    @Override
    public void execute() {
        grid[row][col] = value; // Mettre à jour la grille directement
        solver.setGrid(grid); // Mettre à jour la grille dans l'objet SudokuSolver
    }

    @Override
    public void undo() {
        grid[row][col] = previousValue; // Rétablir la valeur précédente
        solver.setGrid(grid); // Mettre à jour la grille dans l'objet SudokuSolver
    }
}
