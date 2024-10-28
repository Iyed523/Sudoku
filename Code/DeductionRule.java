public abstract class DeductionRule {

    protected int[][] grid;

    public DeductionRule(int[][] grid) {
        this.grid = grid;
    }

    // Méthode abstraite que chaque classe spécifique implémentera
    public abstract void appliquer();

    public void afficherGrille(int[][] grille) {
        // Boucle pour afficher chaque ligne avec des séparateurs
        for (int i = 0; i < 9; i++) {
            // Afficher une ligne horizontale toutes les 3 lignes (pour un effet de section)
            if (i % 3 == 0) {
                System.out.println(" - - - - - - - - - - - - ");
            }

            for (int j = 0; j < 9; j++) {
                // Ajouter un séparateur vertical toutes les 3 colonnes
                if (j % 3 == 0) {
                    System.out.print("| ");
                }

                // Afficher la valeur de la cellule (remplacer 0 par un espace pour plus de clarté)
                if (grille[i][j] == 0) {
                    System.out.print("  ");  // Afficher un espace pour les cases vides
                } else {
                    System.out.print(grille[i][j] + " ");
                }
            }
            System.out.println("|");  // Terminer chaque ligne avec un séparateur vertical
        }
        // Afficher une ligne de séparation en bas de la grille
        System.out.println(" - - - - - - - - - - - - ");
    }

    // Méthode utilitaire : vérifie si un chiffre peut être placé dans une case donnée
    protected boolean peutEtrePlace(int ligne, int colonne, int chiffre) {
        // Vérifie la ligne
        for (int j = 0; j < 9; j++) {
            if (grid[ligne][j] == chiffre) {
                return false;
            }
        }

        // Vérifie la colonne
        for (int i = 0; i < 9; i++) {
            if (grid[i][colonne] == chiffre) {
                return false;
            }
        }

        // Vérifie la région 3x3
        int regionLigne = (ligne / 3) * 3;
        int regionColonne = (colonne / 3) * 3;
        for (int i = regionLigne; i < regionLigne + 3; i++) {
            for (int j = regionColonne; j < regionColonne + 3; j++) {
                if (grid[i][j] == chiffre) {
                    return false;
                }
            }
        }

        // Si aucune des conditions ci-dessus n'est violée, le chiffre peut être placé
        return true;
    }

    // Méthode pour afficher la grille
   
    protected boolean estDansLigne(int ligne, int chiffre) {
        for (int j = 0; j < 9; j++) {
            if (grid[ligne][j] == chiffre) {
                return true;
            }
        }
        return false;
    }

    // Vérifier si un chiffre est présent dans une colonne
    protected boolean estDansColonne(int colonne, int chiffre) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][colonne] == chiffre) {
                return true;
            }
        }
        return false;
    }

    // Vérifier si un chiffre est présent dans une région 3x3
    protected boolean estDansRegion(int ligne, int colonne, int chiffre) {
        int debutLigne = (ligne / 3) * 3;
        int debutColonne = (colonne / 3) * 3;
        for (int i = debutLigne; i < debutLigne + 3; i++) {
            for (int j = debutColonne; j < debutColonne + 3; j++) {
                if (grid[i][j] == chiffre) {
                    return true;
                }
            }
        }
        return false;
    }

   

   
}
