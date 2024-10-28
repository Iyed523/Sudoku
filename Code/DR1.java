import java.util.ArrayList;
import java.util.List;

public class DR1 extends DeductionRule implements ResolutionStrategy {

    public DR1(int[][] grid) {
        super(grid);
    }

    @Override
    public void appliquer() {
        boolean changement;
        
        // Continue jusqu'à ce qu'il n'y ait plus de changements
        do {
            changement = false;
            for (int ligne = 0; ligne < 9; ligne++) {
                for (int colonne = 0; colonne < 9; colonne++) {
                    if (grid[ligne][colonne] == 0) { // Cellule vide
                        int[] candidats = trouverCandidats(ligne, colonne);
                        if (candidats.length == 1) {
                            grid[ligne][colonne] = candidats[0];
                            changement = true; // Indiquer qu'un changement a été effectué
                        }
                    }
                }
            }
        } while (changement); // Continue tant qu'il y a des changements
    }

    // Trouver les candidats possibles pour une cellule donnée
    private int[] trouverCandidats(int ligne, int colonne) {
        List<Integer> candidats = new ArrayList<>();
        
        for (int chiffre = 1; chiffre <= 9; chiffre++) {
            if (peutEtrePlace(ligne, colonne, chiffre)) {
                candidats.add(chiffre);
            }
        }

        // Convertir la liste en tableau
        return candidats.stream().mapToInt(i -> i).toArray();
    }

 
}
