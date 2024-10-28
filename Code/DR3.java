import java.util.HashSet;
import java.util.Set;

public class DR3 extends DeductionRule implements ResolutionStrategy {


    int ligne;
    int colonne;
    public DR3(int[][] grid) {
        super(grid);
    }

    @Override
    public void appliquer() {
        boolean changement;
        

        do {
            changement = false;

            // Appliquer la stratégie de candidats pour chaque case vide
            for ( ligne = 0; ligne < 9; ligne++) {
                for (colonne = 0; colonne < 9; colonne++) {
                    if (grid[ligne][colonne] == 0) {
                        Set<Integer> candidats = obtenirCandidats(ligne, colonne);

                        // Si un seul candidat, placer ce chiffre
                        if (candidats.size() == 1) {
                            int chiffre = candidats.iterator().next();
                            grid[ligne][colonne] = chiffre;
                            changement = true;
                            //afficherGrille(grid); // Afficher la grille après modification
                        }
                    }
                }
            }

            // Réduction des candidats pour chaque ligne, colonne, et région 3x3
            for (int chiffre = 1; chiffre <= 9; chiffre++) {
                for (int regionLigne = 0; regionLigne < 3; regionLigne++) {
                    for (int regionColonne = 0; regionColonne < 3; regionColonne++) {
                        reductionCandidatsRegion(regionLigne, regionColonne, chiffre);
                    }
                }

                reductionCandidatsLigne(chiffre);
                reductionCandidatsColonne(chiffre);
            }

        } while (changement); // Répéter tant qu'il y a des changements
    }

    private Set<Integer> obtenirCandidats(int ligne, int colonne) {
        Set<Integer> candidats = new HashSet<>();

        // Vérifier tous les chiffres possibles (1 à 9)
        for (int chiffre = 1; chiffre <= 9; chiffre++) {
            if (peutEtrePlace(ligne, colonne, chiffre)) {
                candidats.add(chiffre);
            }
        }

        return candidats;
    }

    private void reductionCandidatsRegion(int regionLigne, int regionColonne, int chiffre) {
        int debutLigne = regionLigne * 3;
        int debutColonne = regionColonne * 3;

        int countLigne = 0;
        int countColonne = 0;
        boolean ligneUnique = false;
        boolean colonneUnique = false;
        
        

        for (int i = debutLigne; i < debutLigne + 3; i++) {
            for (int j = debutColonne; j < debutColonne + 3; j++) {
                if (grid[i][j] == 0) {
                    if (peutEtrePlace(i, j, chiffre)) {
                        countLigne++;
                        if (i == ligne) ligneUnique = true;
                        countColonne++;
                        if (j == colonne) colonneUnique = true;
                    }
                }
            }
        }

        if (countLigne == 1 && ligneUnique) {
            grid[ligne][colonne] = chiffre;
            //afficherGrille(grid); // Afficher la grille après modification
        }

        if (countColonne == 1 && colonneUnique) {
            grid[ligne][colonne] = chiffre;
            //afficherGrille(grid); // Afficher la grille après modification
        }
    }

    private void reductionCandidatsLigne(int chiffre) {
        for (int ligne = 0; ligne < 9; ligne++) {
            Set<Integer> colonnesPossibles = new HashSet<>();
            for (int colonne = 0; colonne < 9; colonne++) {
                if (grid[ligne][colonne] == 0 && peutEtrePlace(ligne, colonne, chiffre)) {
                    colonnesPossibles.add(colonne);
                }
            }

            if (colonnesPossibles.size() == 1) {
                int colonne = colonnesPossibles.iterator().next();
                grid[ligne][colonne] = chiffre;
                //afficherGrille(grid); // Afficher la grille après modification
            }
        }
    }

    private void reductionCandidatsColonne(int chiffre) {
        for (int colonne = 0; colonne < 9; colonne++) {
            Set<Integer> lignesPossibles = new HashSet<>();
            for (int ligne = 0; ligne < 9; ligne++) {
                if (grid[ligne][colonne] == 0 && peutEtrePlace(ligne, colonne, chiffre)) {
                    lignesPossibles.add(ligne);
                }
            }

            if (lignesPossibles.size() == 1) {
                int ligne = lignesPossibles.iterator().next();
                grid[ligne][colonne] = chiffre;
                //afficherGrille(grid); // Afficher la grille après modification
            }
        }
    }
}
