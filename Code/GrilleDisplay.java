public class GrilleDisplay implements RegleObserver {
    @Override
    public void onRegleAppliquee(int[][] grille, String regle) {
        System.out.println("La règle " + regle + " a été appliquée. Grille actuelle :");
        afficherGrille(grille);
    }

    // Méthode pour afficher la grille
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

}
