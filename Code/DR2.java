public class DR2 extends DeductionRule implements ResolutionStrategy {

    public DR2(int[][] grid) {
        super(grid);
    }

    @Override
    public void appliquer() {
        boolean changement = true;

        // Continue tant qu'il y a des changements
        while (changement) {
            changement = false;

            // Vérifier chaque ligne
            for (int i = 0; i < 9; i++) {
                for (int chiffre = 1; chiffre <= 9; chiffre++) {
                    int possiblePosition = -1;
                    int countPossible = 0;

                    // Cherche les cases où le chiffre peut être placé
                    for (int j = 0; j < 9; j++) {
                        if (grid[i][j] == 0 && peutEtrePlace(i, j, chiffre)) {
                            countPossible++;
                            possiblePosition = j; // Enregistre la position possible
                        }
                    }

                    // Si une seule case est possible pour ce chiffre, on le place
                    if (countPossible == 1) {
                        grid[i][possiblePosition] = chiffre;
                        changement = true; // On note un changement
                    }
                }
            }

            // Vérifier chaque colonne
            for (int j = 0; j < 9; j++) {
                for (int chiffre = 1; chiffre <= 9; chiffre++) {
                    int possiblePosition = -1;
                    int countPossible = 0;

                    // Cherche les cases où le chiffre peut être placé
                    for (int i = 0; i < 9; i++) {
                        if (grid[i][j] == 0 && peutEtrePlace(i, j, chiffre)) {
                            countPossible++;
                            possiblePosition = i; // Enregistre la position possible
                        }
                    }

                    // Si une seule case est possible pour ce chiffre, on le place
                    if (countPossible == 1) {
                        grid[possiblePosition][j] = chiffre;
                        changement = true; // On note un changement
                    }
                }
            }

            // Vérifier chaque région 3x3
            for (int regionLigne = 0; regionLigne < 3; regionLigne++) {
                for (int regionColonne = 0; regionColonne < 3; regionColonne++) {
                    for (int chiffre = 1; chiffre <= 9; chiffre++) {
                        int possibleLigne = -1;
                        int possibleColonne = -1;
                        int countPossible = 0;

                        // Cherche les cases où le chiffre peut être placé dans le bloc 3x3
                        for (int i = regionLigne * 3; i < regionLigne * 3 + 3; i++) {
                            for (int j = regionColonne * 3; j < regionColonne * 3 + 3; j++) {
                                if (grid[i][j] == 0 && peutEtrePlace(i, j, chiffre)) {
                                    countPossible++;
                                    possibleLigne = i;
                                    possibleColonne = j;
                                }
                            }
                        }

                        // Si une seule case est possible dans ce bloc 3x3, on place le chiffre
                        if (countPossible == 1) {
                            grid[possibleLigne][possibleColonne] = chiffre;
                            changement = true;
                        }
                    }
                }
            }
        }
    }

   
}
