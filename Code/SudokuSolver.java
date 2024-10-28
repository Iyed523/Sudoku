import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {

    private static final int GRID_SIZE = 9; // Taille de la grille (9x9)
    private static SudokuSolver instance = null; // Instance unique de SudokuSolver
    private int[][] grid; // La grille 9x9

    // Constructeur privé pour empêcher l'instanciation directe
    private SudokuSolver() {
        grid = new int[GRID_SIZE][GRID_SIZE]; // Initialiser la grille 9x9 vide
    }

    // Méthode statique pour obtenir l'unique instance de SudokuSolver
    public static SudokuSolver getInstance() {
        if (instance == null) {
            instance = new SudokuSolver(); // Créer l'instance si elle n'existe pas
        }
        return instance;
    }

    // Méthode pour charger la grille à partir d'un tableau 2D
    public void setGrid(int[][] newGrid) {
        this.grid = newGrid;
    }

    // Méthode pour obtenir la grille actuelle
    public int[][] getGrid() {
        return grid;
    }

  
   

    // Méthode pour convertir un tableau linéaire de 81 éléments en une grille 9x9
    public static int[][] convertTo2DGrid(int[] linearGrid) {
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            grid[i / GRID_SIZE][i % GRID_SIZE] = linearGrid[i]; // Calcul des indices 2D
        }
        return grid;
    }

    // Méthode pour lire la grille depuis un fichier
    public static int[] readGridFromFile(String fileName) throws FileNotFoundException {
        int[] grid = new int[GRID_SIZE * GRID_SIZE]; // Tableau de 81 éléments
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        int index = 0;
        while (scanner.hasNextInt() && index < GRID_SIZE * GRID_SIZE) {
            grid[index] = scanner.nextInt();
            index++;
        }
        scanner.close();
        return grid;
    }


    public static void main(String[] args) {
    String fileName = "../Grille/grilleM.txt"; // Nom du fichier contenant la grille
    int[] linearGrid = new int[GRID_SIZE * GRID_SIZE]; // Tableau linéaire de 81 éléments

    // Lecture de la grille depuis un fichier
    try {
        linearGrid = SudokuSolver.readGridFromFile(fileName);
    } catch (FileNotFoundException e) {
        System.out.println("Fichier non trouvé: " + e.getMessage());
        return;
    }

    // Conversion en une grille 9x9
    int[][] grid = SudokuSolver.convertTo2DGrid(linearGrid);
   

    SudokuSolverEngine solverEngine = new SudokuSolverEngine(grid);

    solverEngine.addObserver(new GrilleDisplay()); // Ajout de l'observateur

    solverEngine.solve();



    }
}


