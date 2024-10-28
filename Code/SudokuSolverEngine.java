import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SudokuSolverEngine {
    private List<DeductionRule> rules; // Liste des règles à appliquer
    private int[][] grid; // La grille 9x9
    private boolean isSolvedByDR1 = false;
    private boolean isSolvedByDR2 = false;
    private boolean isSolvedByDR3 = false;
    private ResolutionStrategy strategy;

    private Stack<Command> commandStack = new Stack<>(); // Pile pour les commandes

    private ObservableSolver observableSolver = new ObservableSolver(); // Utilisation d'ObservableSolver


    public SudokuSolverEngine(int[][] grid) {
        this.grid = grid;
        rules = new ArrayList<>();
        // Ajouter les règles avec la grille initiale
        rules.add(DeductionRuleFactory.createRule("DR1", grid));
        rules.add(DeductionRuleFactory.createRule("DR2", grid));
        rules.add(DeductionRuleFactory.createRule("DR3", grid));
    }

    public void addObserver(RegleObserver observer) {
        observableSolver.addObserver(observer);
    }

    public void placeNumber(int row, int col, int value) {
        // Créer une nouvelle commande pour placer le nombre
        PlaceNumberCommand command = new PlaceNumberCommand(SudokuSolver.getInstance(), row, col, value);
        command.execute(); // Exécuter la commande
        commandStack.push(command); // Empiler la commande
    }

    // Méthode pour annuler la dernière action
    public void undoLastAction() {
        if (!commandStack.isEmpty()) {
            Command lastCommand = commandStack.pop(); // Retirer la dernière commande
            lastCommand.undo(); // Annuler la dernière action
        } else {
            System.out.println("Aucune action à annuler.");
        }
    }

    
    

    // Méthode pour résoudre la grille en appliquant les règles
    public void solve() {
        boolean changed;
        System.out.println("Grille initiale :");
        rules.get(0).afficherGrille(grid);
        
        // Appliquer chaque règle de déduction
        for (DeductionRule rule : rules) {
            rule.appliquer();
            // Afficher la grille après application de chaque règle
            String ruleName = rule.getClass().getSimpleName();
            observableSolver.notifyObservers(grid, ruleName);

            //rule.afficherGrille(grid);

            // Vérifier si la grille est résolue après application de la règle
            if (isSolved()) {
                if (rule instanceof DR1) {
                    isSolvedByDR1 = true;
                } else if (rule instanceof DR2) {
                    isSolvedByDR2 = true;
                } else if (rule instanceof DR3) {
                    isSolvedByDR3 = true;
                }
                break; // Si une règle a résolu la grille, on n'applique pas les autres règles
            }
        }

        // Afficher le résultat final
        if (isSolved()) {
            System.out.println("La grille a été résolue.");
            if (isSolvedByDR1) {
                System.out.println("La grille est facile.");
            } else if (isSolvedByDR2) {
                System.out.println("La grille est de niveau moyen.");
            } else if (isSolvedByDR3) {
                System.out.println("La grille est difficile.");
            } 
        
        } 
        
        else{
            if (!isSolved()) {
                System.out.println("La grille est trop difficile. Veuillez placer des chiffres manuellement.");
        
                // Demander à l'utilisateur de remplir la grille
                remplirGrilleManuellement();
            }
        
            // Afficher la grille finale après que l'utilisateur a ajouté des chiffres
            System.out.println("Grille finale après ajout des chiffres :");
            rules.get(0).afficherGrille(grid);
        
            // Recommencer la résolution après l'ajout manuel
            //solve(); // Relancer la résolution après ajout manuel7=
        }
    }


    // Vérifier si la grille est résolue
    private boolean isSolved() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) { // Si une case est encore vide
                    return false;
                }
            }
        }
        return true;
    }

    private void remplirGrilleManuellement() {
    Scanner scanner = new Scanner(System.in);

    do{
        System.out.println("Entrez la ligne (1-9), la colonne (1-9), et le chiffre (1-9) à placer (ou '0 0 0' pour terminer) :");
        int ligne = scanner.nextInt() - 1;  // Conversion vers l'index 0
        int colonne = scanner.nextInt() - 1;  // Conversion vers l'index 0
        int chiffre = scanner.nextInt();

        // Si l'utilisateur entre '0 0 0', on arrête l'ajout manuel
        if (ligne == -1 && colonne == -1 && chiffre == 0) {
            break;
        }

        // Vérifier si l'emplacement est vide et si le chiffre est valide
        if (grid[ligne][colonne] == 0 && chiffre >= 1 && chiffre <= 9 && rules.get(0).peutEtrePlace(ligne, colonne, chiffre)) {
            grid[ligne][colonne] = chiffre;
            //placeNumber(ligne, colonne, chiffre); // Placer le chiffre
            System.out.println("Chiffre placé avec succès !");

            } 
        else {
            System.out.println("Position ou chiffre invalide. Réessayez.");
            }
        
        
        // Afficher la grille après chaque ajout
        rules.get(0).afficherGrille(grid);  

    } while(!isSolved());

    System.out.println("Grille Résolue ! ");
    
    
    }
    
}
