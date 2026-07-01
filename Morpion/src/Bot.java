import java.util.Scanner;
public class Bot {
    // bot niveau facile
    public static int mettreUnPionNiveauFacile(int taillePlateau) {
        int limite = (int) (Math.random() * taillePlateau);
        return limite;
    }

    //--------------------------------------------------------moyen---------------------------------------------------
    // enregistrement des coups
    public static int[][] enregistrementCoupJoueur(int[][] coupJoueur, int ligne, int colonne, int nbCoup) {
        return enregistrementCoup(coupJoueur, ligne, colonne, nbCoup);
    }

    public static int[][] enregistrementCoup(int[][] coup, int ligne, int colonne, int nbCoup) {
        coup[0][nbCoup] = ligne;
        coup[1][nbCoup] = colonne;
        return coup;
    }
    public static int retourneIndice(int[][] coup) {
        int ind=-1;
        for (int i = 0; i < coup[0].length; i++) {
            if (coup[0][i]==-1) {
                return i-1;
            }
        }
        return ind;
    }

    //   1 2 3
    // 1 . . .
    // 2 . O .
    // 3 . . .
    public static boolean mettreUnPionAutour(char[][] tab, int[][] Coup1, int nbcoup, char Symbole, int[][] Coup2, int nbcoup2) {
        int ligne = Coup1[0][nbcoup2 - 1];
        int colonne = Coup1[1][nbcoup2 - 1];
        boolean mettrelepion = false;
        int VariableLigne;
        int VariableColonne;

        // 20 essais pour trouver une case libre
        for (int tentative = 0; tentative < 20; tentative++) {
            VariableLigne = (int) (Math.random() * 3) - 1; // Valeur entre -1 et 1
            VariableColonne = (int) (Math.random() * 3) - 1; // Valeur entre -1 et 1

            int NouvelleLigne = ligne + VariableLigne;
            int NouvelleColonne = colonne + VariableColonne;

            // Vérifie que la case est dans les limites du tableau et qu'elle est libre
            if (NouvelleLigne >= 0 && NouvelleLigne < tab.length && NouvelleColonne >= 0 && NouvelleColonne < tab[NouvelleLigne].length) {
                if (!ModifPlateau.pionPresent(tab, NouvelleLigne, NouvelleColonne)) {
                    tab[NouvelleLigne][NouvelleColonne] = Symbole; // Place le pion
                    enregistrementCoup(Coup2, NouvelleLigne, NouvelleColonne, nbcoup);
                    mettrelepion = true;
                    break;
                }
            }
        }
        return mettrelepion;
    }


    public static void SupprimerCoordoneesPionCoup (int[][]coup, int nbcoup) {
        int indice=retourneIndice(coup);
        coup[0][indice]=-1;
        coup[1][indice]=-1;
    }

    public static void SupprimerPionPlateau(char[][]Plateau, int[][]coupbot) {
        int indice = Bot.retourneIndice(coupbot);
        int ligne = coupbot[0][indice];
        int colonne = coupbot[1][indice];

        Plateau[ligne][colonne]='.';
    }

    public static boolean bloqueAlignementHoriCombi(char[][] tab, int[][] Coup1, int nbcoup, boolean[][] J1Horizontal, char Symbole, int [][] Coup2) {
        boolean PresenceAlignement=false;
        int indice = retourneIndice(Coup1);
        int ligne = Coup1[0][indice];
        int colonne = Coup1[1][indice];
        if (colonne + 4 < tab[0].length && tab[ligne][colonne]==Symbole && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne + 4]) {
            if (tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 3] == Symbole && tab[ligne][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne + 1] = 'O'; // . O X X X
                enregistrementCoup(Coup2, ligne, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            if (!PresenceAlignement && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 4)) {
                tab[ligne][colonne + 4] = 'O'; // . X X X O
                enregistrementCoup(Coup2, ligne, colonne + 4, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 3)) {
                tab[ligne][colonne + 3] = 'O'; // . X X O X
                enregistrementCoup(Coup2, ligne, colonne + 3, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 3] == Symbole && tab[ligne][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 2)) {
                tab[ligne][colonne + 2] = 'O'; // . X O X X
                enregistrementCoup(Coup2, ligne, colonne + 2, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne + 3 < tab[0].length && colonne - 1 >= 0 && !J1Horizontal[ligne][colonne - 1] && !J1Horizontal[ligne][colonne + 3]) {
            if (tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 2)) {
                tab[ligne][colonne + 2] = 'O';// X . X O X
                enregistrementCoup(Coup2, ligne, colonne + 2, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne + 1] = 'O';// X . O X X
                enregistrementCoup(Coup2, ligne, colonne + 1, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 3)) {
                tab[ligne][colonne + 3] = 'O'; // X . X X O
                enregistrementCoup(Coup2, ligne, colonne + 3, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 1)) {
                tab[ligne][colonne - 1] = 'O'; // O . X X X
                enregistrementCoup(Coup2, ligne, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne + 2 < tab[0].length && colonne - 2 >= 0 && !J1Horizontal[ligne][colonne - 2] && !J1Horizontal[ligne][colonne + 2]) {
            if (tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 1)) {
                tab[ligne][colonne - 1] = 'O';// X O . X X
                enregistrementCoup(Coup2, ligne, colonne - 1, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne + 1] = 'O';// X X . O X
                enregistrementCoup(Coup2, ligne, colonne + 1, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 2)) {
                tab[ligne][colonne + 2] = 'O'; // X X . X O
                enregistrementCoup(Coup2, ligne, colonne + 2, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 2)) {
                tab[ligne][colonne - 2] = 'O'; // O X . X X
                enregistrementCoup(Coup2, ligne, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne - 3 >= 0 && colonne + 1 < tab[0].length && !J1Horizontal[ligne][colonne - 3] && J1Horizontal[ligne][colonne + 1]) {
            if (tab[ligne][colonne - 3] == Symbole && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 2)) {
                tab[ligne][colonne - 2] = 'O';// X O X . X
                enregistrementCoup(Coup2, ligne, colonne - 2, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 3] == Symbole && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 1)) {
                tab[ligne][colonne - 1] = 'O';// X X O . X
                enregistrementCoup(Coup2, ligne, colonne - 1, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 3] == Symbole && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne + 1] = 'O'; // X X X . O
                enregistrementCoup(Coup2, ligne, colonne + 1, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne - 1] == Symbole && tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 3)) {
                tab[ligne][colonne - 3] = 'O'; // O X X . X
                enregistrementCoup(Coup2, ligne, colonne - 3, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne - 4 >= 0 && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne - 4]) {
            if (tab[ligne][colonne - 4] == Symbole && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 3)) {
                tab[ligne][colonne - 3] = 'O';// X O X X .
                enregistrementCoup(Coup2, ligne, colonne - 3, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 4] == Symbole && tab[ligne][colonne - 3] == Symbole && tab[ligne][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 2)) {
                tab[ligne][colonne - 2] = 'O';// X X O X .
                enregistrementCoup(Coup2, ligne, colonne - 2, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 4] == Symbole && tab[ligne][colonne - 3] == Symbole && tab[ligne][colonne - 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 1)) {
                tab[ligne][colonne - 1] = 'O';// X X X O .
                enregistrementCoup(Coup2, ligne, colonne - 1, nbcoup);
                PresenceAlignement=true;
            } if (!PresenceAlignement && tab[ligne][colonne - 3] == Symbole && tab[ligne][colonne - 2] == Symbole && tab[ligne][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 4)) {
                tab[ligne][colonne - 4] = 'O';// O X X X .
                enregistrementCoup(Coup2, ligne, colonne - 4, nbcoup);
                PresenceAlignement=true;
            }
        }
        return PresenceAlignement;
    }

    public static boolean bloqueAlignementVertiConbi(char[][] tab, int[][] Coup1, int nbcoup, boolean[][] J1Vertical, char Symbole, int[][] Coup2) {
        boolean PresenceAlignement=false;
        int indice = retourneIndice(Coup1);
        int ligne = Coup1[0][indice];
        int colonne = Coup1[1][indice];
        if (ligne - 4 >= 0 && J1Vertical[ligne][colonne] && J1Vertical[ligne - 4][colonne]) {
            // X
            // X
            // X
            // O
            // .
            if (tab[ligne - 2][colonne] == Symbole && tab[ligne - 3][colonne] == Symbole && tab[ligne - 4][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne)) {
                tab[ligne + 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // X
            // O
            // X
            // .
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne - 3][colonne] == Symbole && tab[ligne - 4][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne)) {
                tab[ligne - 2][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // O
            // X
            // X
            // .
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne - 2][colonne] == Symbole && tab[ligne - 4][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne)) {
                tab[ligne - 3][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // O
            // X
            // X
            // X
            // .
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne - 2][colonne] == Symbole && tab[ligne - 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 4, colonne)) {
                tab[ligne - 4][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 4, colonne, nbcoup);
                PresenceAlignement=true;
            }
        } if (ligne - 3 >= 0 && ligne + 1 < tab.length && !J1Vertical[ligne - 3][colonne] && !J1Vertical[ligne + 1][colonne]) {
            // X
            // X
            // O
            // .
            // X
            if (tab[ligne - 3][colonne] == Symbole && tab[ligne - 2][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne)) {
                tab[ligne - 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // O
            // X
            // .
            // X
            if (!PresenceAlignement && tab[ligne - 3][colonne] == Symbole && tab[ligne - 1][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne)) {
                tab[ligne - 2][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // X
            // X
            // .
            // O
            if (!PresenceAlignement && tab[ligne - 3][colonne] == Symbole && tab[ligne - 2][colonne] == Symbole && tab[ligne - 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)) {
                tab[ligne + 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // O
            // X
            // X
            // .
            // X
            if (!PresenceAlignement && tab[ligne - 2][colonne] == Symbole && tab[ligne - 1][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne)) {
                tab[ligne - 3][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne, nbcoup);
                PresenceAlignement=true;
            }
        } if (ligne - 2 >= 0 && ligne + 2 < tab.length && !J1Vertical[ligne - 2][colonne] && !J1Vertical[ligne + 2][colonne]) {
            // X
            // X
            // .
            // O
            // X
            if (tab[ligne - 2][colonne] == Symbole && tab[ligne - 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)) {
                tab[ligne + 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // O
            // .
            // X
            // X
            if (!PresenceAlignement && tab[ligne - 2][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne)) {
                tab[ligne - 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // X
            // .
            // X
            // O
            if (!PresenceAlignement && tab[ligne - 2][colonne] == Symbole && tab[ligne - 1][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne)) {
                tab[ligne + 2][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // O
            // X
            // .
            // X
            // X
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne)) {
                tab[ligne - 2][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne, nbcoup);
                PresenceAlignement=true;
            }

        } if (ligne - 1 >= 0 && ligne + 3 < tab.length && !J1Vertical[ligne-1][colonne] && !J1Vertical[ligne + 3][colonne]) {
            // X
            // .
            // X
            // O
            // X
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne)) {
                tab[ligne + 2][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // .
            // O
            // X
            // X
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)) {
                tab[ligne + 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // X
            // .
            // X
            // X
            // O
            if (!PresenceAlignement && tab[ligne - 1][colonne] == Symbole && tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 3, colonne)) {
                tab[ligne + 3][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 3, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // O
            // .
            // X
            // X
            // X
            if (!PresenceAlignement && tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne)) {
                tab[ligne - 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
        } if (ligne + 4 < tab.length && !J1Vertical[ligne][colonne] && !J1Vertical[ligne + 4][colonne]) {
            // .
            // X
            // X
            // O
            // X
            if (!PresenceAlignement && tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && tab[ligne + 4][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 3, colonne)) {
                tab[ligne + 3][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 3, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // .
            // X
            // O
            // X
            // X
            if (!PresenceAlignement && tab[ligne + 1][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && tab[ligne + 4][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne)) {
                tab[ligne + 2][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne, nbcoup);
                PresenceAlignement=true;
            }

            // .
            // O
            // X
            // X
            // X
            if (!PresenceAlignement && tab[ligne + 2][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && tab[ligne + 4][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)) {
                tab[ligne + 1][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne, nbcoup);
                PresenceAlignement=true;
            }
            // .
            // X
            // X
            // X
            // O
            if (!PresenceAlignement && tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 4, colonne)) {
                tab[ligne + 4][colonne] = 'O';
                enregistrementCoup(Coup2, ligne + 4, colonne, nbcoup);
                PresenceAlignement=true;
            }
        }
        return PresenceAlignement;
    }

    public static boolean bloqueAlignementDiagoDroiteCombi(char[][] tab, int[][] Coup1, int nbcoup, boolean[][] J1DiagonaleDroite, char Symbole, int[][] Coup2) {
        boolean PresenceAlignement=false;
        int indice = retourneIndice(Coup1);
        int ligne = Coup1[0][indice];
        int colonne = Coup1[1][indice];
        if (colonne + 4 < tab[0].length && ligne + 4 < tab.length && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne + 4][colonne + 4]) {
            // .
            //   X
            //     X
            //       O
            //         X
            if (tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && tab[ligne + 4][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 3, colonne + 3)) {
                tab[ligne + 3][colonne + 3] = 'O';
                enregistrementCoup(Coup2, ligne + 3, colonne + 3, nbcoup);
                PresenceAlignement=true;

            }

            // .
            //   X
            //     O
            //       X
            //         X
            if (!PresenceAlignement && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && tab[ligne + 4][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne + 2)) {
                tab[ligne + 2][colonne + 2] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne + 2, nbcoup);
                PresenceAlignement = true;
            }
            // .
            //   X
            //     X
            //       X
            //         O
            if (!PresenceAlignement && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 4, colonne + 4)) {
                tab[ligne + 4][colonne + 4] = 'O';
                enregistrementCoup(Coup2, ligne + 4, colonne + 4, nbcoup);
                PresenceAlignement=true;
            }
            // .
            //   O
            //     X
            //       X
            //         X
            if (!PresenceAlignement && tab[ligne + 2][colonne + 2] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && tab[ligne + 4][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne + 1)) {
                tab[ligne + 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne + 3 < tab[0].length && ligne + 3 < tab.length && colonne - 1 >= 0 && ligne - 1 >= 0 && !J1DiagonaleDroite[ligne - 1][colonne - 1] && !J1DiagonaleDroite[ligne + 3][colonne + 3]) {
            // X
            //   .
            //     X
            //       O
            //         X
            if (!PresenceAlignement && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne + 2)) {
                tab[ligne + 2][colonne + 2] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne + 2, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   .
            //     O
            //       X
            //         X
            if (!PresenceAlignement && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne + 1)) {
                tab[ligne + 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   .
            //     X
            //       X
            //         O
            if (!PresenceAlignement && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 3, colonne + 3)) {
                tab[ligne + 3][colonne + 3] = 'O';
                enregistrementCoup(Coup2, ligne + 3, colonne + 3, nbcoup);
                PresenceAlignement=true;
            }
            // O
            //   .
            //     X
            //       X
            //         X
            if (!PresenceAlignement && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne - 1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne - 2 >= 0 && ligne - 2 >= 0 && colonne + 2 < tab[0].length && ligne + 2 < tab.length && !J1DiagonaleDroite[ligne - 2][colonne - 2] && !J1DiagonaleDroite[ligne + 2][colonne + 2]) {
            // X
            //   X
            //     .
            //       O
            //         X
            if (!PresenceAlignement && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne + 1)) {
                tab[ligne + 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   O
            //     .
            //       X
            //         X
            if (!PresenceAlignement && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne - 1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   X
            //     .
            //       X
            //         O
            if (!PresenceAlignement && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne + 2)) {
                tab[ligne + 2][colonne + 2] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne + 2, nbcoup);
                PresenceAlignement=true;
            }
            // O
            //   X
            //     .
            //       X
            //         X
            if (!PresenceAlignement && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne - 2)) {
                tab[ligne - 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne - 3 >= 0 && ligne - 3 >= 0 && colonne + 1 < tab[0].length && ligne + 1 < tab.length) {
            // X
            //   X
            //     O
            //       .
            //         X
            if (tab[ligne - 3][colonne - 3] == Symbole && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne - 1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   O
            //     X
            //       .
            //         X
            if (!PresenceAlignement && tab[ligne - 3][colonne - 3] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne - 2)) {
                tab[ligne - 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   X
            //     X
            //       .
            //         O
            if (!PresenceAlignement && tab[ligne - 3][colonne - 3] == Symbole && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne + 1)) {
                tab[ligne + 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            // O
            //   X
            //     X
            //       .
            //         X
            if (!PresenceAlignement && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne - 3)) {
                tab[ligne - 3][colonne - 3] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne - 3, nbcoup);
                PresenceAlignement=true;
            }

        } if (colonne - 4 >= 0 && ligne - 4 >= 0 && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne - 4][colonne - 4]) {
            // X
            //   X
            //     X
            //       O
            //         .
            if (!PresenceAlignement && tab[ligne - 4][colonne - 4] == Symbole && tab[ligne - 3][colonne - 3] == Symbole && tab[ligne - 2][colonne - 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne - 1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   X
            //     O
            //       X
            //         .
            if (!PresenceAlignement && tab[ligne - 4][colonne - 4] == Symbole && tab[ligne - 3][colonne - 3] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne - 2)) {
                tab[ligne - 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
            // X
            //   O
            //     X
            //       X
            //         .
            if (!PresenceAlignement && tab[ligne - 4][colonne - 4] == Symbole && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne - 3)) {
                tab[ligne - 3][colonne - 3] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne - 3, nbcoup);
                PresenceAlignement=true;
            }
            // O
            //   X
            //     X
            //       X
            //         .
            if (!PresenceAlignement && tab[ligne - 3][colonne - 3] == Symbole && tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 4, colonne - 4)) {
                tab[ligne - 4][colonne - 4] = 'O';
                enregistrementCoup(Coup2, ligne - 4, colonne - 4, nbcoup);
                PresenceAlignement=true;
            }
        }
        return PresenceAlignement;
    }

    public static boolean bloqueAlignementDiagoGaucheCombi(char[][] tab, int[][] Coup1, int nbcoup, boolean[][] J1DiagonaleGauche, char Symbole, int[][] Coup2) {
        boolean PresenceAlignement=false;
        int indice = retourneIndice(Coup1);
        int ligne = Coup1[0][indice];
        int colonne = Coup1[1][indice];
        if (colonne - 4 >= 0 && ligne + 4 < tab.length && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne + 4][colonne - 4]) {
            //        .
            //      X
            //    X
            //  O
            //X
            if (tab[ligne + 1][colonne - 1] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 4][colonne - 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 3, colonne - 3)) {
                tab[ligne + 3][colonne - 3] = 'O';
                enregistrementCoup(Coup2, ligne + 3, colonne - 3, nbcoup);
                PresenceAlignement=true;
            }
            //        .
            //      X
            //    O
            //  X
            //X
            if (!PresenceAlignement && tab[ligne + 4][colonne - 4] == Symbole && tab[ligne + 3][colonne - 3] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne - 2)) {
                tab[ligne + 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
            //        .
            //      O
            //    X
            //  X
            //X
            if (!PresenceAlignement && tab[ligne + 4][colonne - 4] == Symbole && tab[ligne + 3][colonne - 3] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne - 1)) {
                tab[ligne + 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
            //        .
            //      X
            //    X
            //  X
            //O
            if (!PresenceAlignement && tab[ligne + 3][colonne - 3] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 4, colonne - 4)) {
                tab[ligne + 4][colonne - 4] = 'O';
                enregistrementCoup(Coup2, ligne + 4, colonne - 4, nbcoup);
                PresenceAlignement=true;
            }
        } if (ligne + 3 < tab.length && colonne - 3 >= 0 && colonne + 1 < tab[0].length && ligne - 1 >= 0 && !J1DiagonaleGauche[ligne + 3][colonne - 3] && !J1DiagonaleGauche[ligne - 1][colonne + 1]) {
            //        X
            //      .
            //    X
            //  O
            //X
            if (!PresenceAlignement && tab[ligne + 3][colonne - 3] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne - 2)) {
                tab[ligne + 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      .
            //    O
            //  X
            //X
            if (!PresenceAlignement && tab[ligne + 3][colonne - 3] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne - 1)) {
                tab[ligne + 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
            //        O
            //      .
            //    X
            //  X
            //X
            if (!PresenceAlignement && tab[ligne + 3][colonne - 3] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne + 1)) {
                tab[ligne - 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      .
            //    X
            //  X
            //O
            if (!PresenceAlignement && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 3, colonne - 3)) {
                tab[ligne + 3][colonne - 3] = 'O';
                enregistrementCoup(Coup2, ligne + 3, colonne - 3, nbcoup);
                PresenceAlignement=true;
            }
        } if (ligne + 2 < tab.length && colonne - 2 >= 0 && colonne + 2 < tab[0].length && ligne - 2 >= 0 && !J1DiagonaleGauche[ligne + 2][colonne - 2] && !J1DiagonaleGauche[ligne - 2][colonne + 2]) {
            //        X
            //      X
            //    .
            //  O
            //X
            if (!PresenceAlignement && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne - 1)) {
                tab[ligne + 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      O
            //    .
            //  X
            //X
            if (!PresenceAlignement && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne + 1)) {
                tab[ligne - 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            //        O
            //      X
            //    .
            //  X
            //X
            if (!PresenceAlignement && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne + 2)) {
                tab[ligne - 2][colonne + 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne + 2, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      X
            //    .
            //  X
            //O
            if (!PresenceAlignement && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne - 2)) {
                tab[ligne + 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne + 2, colonne - 2, nbcoup);
                PresenceAlignement=true;
            }
        } if (ligne + 1 < tab.length && colonne - 1 >= 0 && colonne + 3 < tab[0].length && ligne - 3 >= 0 && !J1DiagonaleGauche[ligne + 1][colonne - 1] && !J1DiagonaleGauche[ligne - 3][colonne + 3]) {
            //        X
            //      X
            //    O
            //  .
            //X
            if (!PresenceAlignement && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne + 1)) {
                tab[ligne - 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      O
            //    X
            //  .
            //X
            if (!PresenceAlignement && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne + 2)) {
                tab[ligne - 2][colonne + 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne + 2, nbcoup);
                PresenceAlignement=true;
            }
            //        O
            //      X
            //    X
            //  .
            //X
            if (!PresenceAlignement && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne + 3)) {
                tab[ligne - 3][colonne + 3] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne + 3, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      X
            //    X
            //  .
            //O
            if (!PresenceAlignement && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne - 1)) {
                tab[ligne + 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne - 1, nbcoup);
                PresenceAlignement=true;
            }
        } if (colonne + 4 < tab[0].length && ligne - 4 >= 0 && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne - 4][colonne + 4]) {
            //        X
            //      X
            //    X
            //  O
            //.
            if (!PresenceAlignement && tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && tab[ligne - 4][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne + 1)) {
                tab[ligne - 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne + 1, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      X
            //    O
            //  X
            //.
            if (!PresenceAlignement && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && tab[ligne - 4][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne + 2)) {
                tab[ligne - 2][colonne + 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne + 2, nbcoup);
                PresenceAlignement=true;
            }
            //        X
            //      O
            //    X
            //  X
            //.
            if (!PresenceAlignement && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 4][colonne + 4] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne + 3)) {
                tab[ligne - 3][colonne + 3] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne + 3, nbcoup);
                PresenceAlignement=true;
            }
            //        O
            //      X
            //    X
            //  X
            //.
            if (!PresenceAlignement && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 4, colonne + 4)) {
                tab[ligne - 4][colonne + 4] = 'O';
                enregistrementCoup(Coup2, ligne - 4, colonne + 4, nbcoup);
                PresenceAlignement=true;
            }
        }
        return PresenceAlignement;
    }










    //------------------------------------------ dificille ---------------------------------------------------------------------------------------
    // defense + attaque si possible sinon attaque


    // ------------------------------------------hori alignement 3 check et block-----------------------------------------------------------------
    public static boolean checkAlignement3Hori (char[][] tab,int ligne, int colonne,boolean[][] J1Horizontal, char Symbole) {
        boolean alignementDe3 = false;

        if (colonne + 2 < tab.length && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne + 2]) {
            if (tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 1] == Symbole) {
                // . O O
                alignementDe3 = true;
            }
        }
        if (colonne + 1 < tab.length && colonne - 1 >= 0 && !J1Horizontal[ligne][colonne+1] && !J1Horizontal[ligne][colonne - 1]) {
            if (tab[ligne][colonne - 1] == 'O' && tab[ligne][colonne + 1] == Symbole) {
                // O . O
                alignementDe3 = true;
            }
        }
        if (colonne-2>=0 && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne-2]){
            if (tab[ligne][colonne-1]=='O' && tab[ligne][colonne - 2] == Symbole){
                // O O .
                alignementDe3 = true;
            }
        }
        return alignementDe3;
    }
    public static boolean Blockalignement3Hori (char[][] tab,int ligne, int colonne,int[][] Coup2,int nbcoup, boolean[][] J1Horizontal, char Symbole) {
        boolean alignementDe3 = false;

        if (colonne + 2 < tab.length && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne + 2]) {
            if (tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 2)) {
                tab[ligne][colonne+2]='O';
                enregistrementCoup(Coup2, ligne, colonne+2, nbcoup); // . X O
                alignementDe3 = true;
            } else if (tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne+1]='O';
                enregistrementCoup(Coup2, ligne, colonne+1, nbcoup); // . O X
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && colonne + 1 < tab.length && colonne - 1 >= 0 && !J1Horizontal[ligne][colonne+1] && !J1Horizontal[ligne][colonne - 1]) {
            if (tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 1)) {
                tab[ligne][colonne-1]='O';
                enregistrementCoup(Coup2, ligne, colonne-1, nbcoup); // O . X
                alignementDe3 = true;
            } else if (tab[ligne][colonne -1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne+1]='O';
                enregistrementCoup(Coup2, ligne, colonne+1, nbcoup); // X . O
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && colonne-2>=0 && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne-2]){
            if (tab[ligne][colonne-2]== Symbole && !ModifPlateau.pionPresent(tab, ligne,colonne-1)){
                tab[ligne][colonne-1]='O';
                enregistrementCoup(Coup2, ligne, colonne-1, nbcoup); // X O .
                // X O .
                alignementDe3 = true;
            }
            else if (tab[ligne][colonne-1]==Symbole && !ModifPlateau.pionPresent(tab, ligne,colonne-2)){
                tab[ligne][colonne-2]='O';
                enregistrementCoup(Coup2, ligne, colonne-2, nbcoup);// O X .
                alignementDe3 = true;
            }
        }
        return alignementDe3;
    }

    //----------------------------------verti check block------------------------------------------------------------------------
    public static boolean checkAlignement3Verti (char[][] tab,int ligne , int colonne, boolean[][] J1Vertical) {
        boolean alignementDe3 = false;
        if (ligne + 2 < tab.length && !J1Vertical[ligne][colonne] && !J1Vertical[ligne + 2][colonne]) {
            // .
            // O
            // O
            if (tab[ligne + 1][colonne] == 'O' && tab[ligne + 2][colonne] == 'O')
                alignementDe3 = true;
        }
        if (ligne + 1 < tab.length && ligne - 1 >= 0 && !J1Vertical[ligne+1][colonne] && !J1Vertical[ligne-1][colonne]) {
            // O
            // .
            // O
            if (tab[ligne - 1][colonne] == 'O' && tab[ligne + 1][colonne] == 'O')
                alignementDe3 = true;
        }
        if (ligne-2>=0 && !J1Vertical[ligne][colonne] && !J1Vertical[ligne-2][colonne]){
            // O
            // O
            // .
            if (tab[ligne-2][colonne]=='O' && tab[ligne - 1][colonne] == 'O')
                alignementDe3 = true;
        }
        return alignementDe3;
    }
    public static boolean blockAlignement3Verti (char[][] tab,int ligne , int colonne,int[][] Coup2,int nbcoup, boolean[][] J1Vertical, char Symbole) {
        boolean alignementDe3 = false;
        if (ligne + 2 < tab.length && !J1Vertical[ligne][colonne] && !J1Vertical[ligne + 2][colonne]) {
            // .
            // O
            // X
            if (tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)){
                tab[ligne+1][colonne]='O';
                enregistrementCoup(Coup2,ligne+1, colonne, nbcoup );
                alignementDe3 = true;
            }
            // .
            // X
            // O
            else if (tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne)){
                tab[ligne+2][colonne] ='O';
                enregistrementCoup(Coup2,ligne+2, colonne, nbcoup );
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && ligne + 1 < tab.length && ligne - 1 >= 0 && !J1Vertical[ligne+1][colonne] && !J1Vertical[ligne-1][colonne]) {
            // X
            // .
            // O
            if (tab[ligne - 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)){
                tab[ligne+1][colonne]='O';
                enregistrementCoup(Coup2, ligne +1,colonne, nbcoup);
                alignementDe3 = true;
            }
            // O
            // .
            // X
            else if (tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne)){
                tab[ligne-1][colonne]='O';
                enregistrementCoup(Coup2, ligne -1,colonne, nbcoup);
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && ligne-2>=0 && !J1Vertical[ligne][colonne] && !J1Vertical[ligne-2][colonne]){
            // X
            // O
            // .
            if (tab[ligne-2][colonne]==Symbole && !ModifPlateau.pionPresent(tab, ligne-1,colonne)){
                tab[ligne-1][colonne]='O';
                enregistrementCoup(Coup2,ligne-1,colonne,nbcoup);
                alignementDe3 = true;
            }
            // O
            // X
            // .
            else if (tab[ligne-1][colonne]==Symbole && !ModifPlateau.pionPresent(tab, ligne-2,colonne)){
                tab[ligne-2][colonne]='O';
                enregistrementCoup(Coup2,ligne-2,colonne,nbcoup);
                alignementDe3 = true;
            }
        }
        return alignementDe3;
    }
    // -----------------------------------------diago gauche check block------------------------------------------------------------
    public static boolean checkAlignement3DiagoGauche (char[][] tab,int ligne , int colonne, boolean[][] J1DiagonaleGauche) {
        boolean alignementDe3 = false;
        if (ligne + 2 < tab.length && colonne + 2 < tab.length && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne+2][colonne+2]) {
            // .
            //   O
            //     O
            if (tab[ligne+1][colonne+1] == 'O' && tab[ligne+2][colonne+2] == 'O')
                alignementDe3 = true;
        }
        if (ligne + 1 < tab.length && colonne+1 < tab.length && ligne-1>=0 && colonne-1>=0 && !J1DiagonaleGauche[ligne-1][colonne-1] && !J1DiagonaleGauche[ligne+1][colonne+1]) {
            // O
            //   .
            //     O
            if (tab[ligne-1][colonne-1] == 'O' && tab[ligne+1][colonne+1] == 'O')
                alignementDe3 = true;
        }
        if (ligne -2 >=0 && colonne-2>= 0 && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne-2][colonne-2]) {
            // O
            //   O
            //     .
            if (tab[ligne-1][colonne-1] == 'O' && tab[ligne-2][colonne-2] == 'O')
                alignementDe3 = true;
        }
        return alignementDe3;
    }
    public static boolean blockAlignement3DiagoGauche (char[][] tab,int ligne , int colonne,int[][] Coup2,int nbcoup, boolean[][] J1DiagonaleGauche, char Symbole) {
        boolean alignementDe3 = false;
        if (ligne + 2 < tab.length && colonne + 2 < tab.length && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne+2][colonne+2]) {
            // .
            //   X
            //     O
            if (tab[ligne+1][colonne+1] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne+2)){
                tab[ligne+2][colonne+2]='O';
                enregistrementCoup(Coup2, ligne+2,colonne+2,nbcoup);
                alignementDe3 = true;
            }
            // .
            //   O
            //     X
            else if (tab[ligne + 2][colonne+ 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne+1)){
                tab[ligne+1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne+1,colonne+1,nbcoup);
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && ligne + 1 < tab.length && colonne+1 < tab.length && ligne-1>=0 && colonne-1>=0 && !J1DiagonaleGauche[ligne-1][colonne-1] && !J1DiagonaleGauche[ligne+1][colonne+1]) {
            // X
            //   .
            //     O
            if (tab[ligne-1][colonne-1] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne+1)){
                tab[ligne+1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne+1,colonne+1,nbcoup);
                alignementDe3 = true;
            }

            // O
            //   .
            //     X
            else if (tab[ligne +1][colonne+1] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne-1)){
                tab[ligne-1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne-1,colonne-1,nbcoup);
                alignementDe3 = true;
            }

        }
        if (!alignementDe3 && ligne -2 >=0 && colonne-2>= 0 && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne-2][colonne-2]) {
            // O
            //   X
            //     .
            if (tab[ligne-1][colonne-1] == Symbole && !ModifPlateau.pionPresent(tab, ligne-2, colonne-2)){
                tab[ligne-2][colonne-2]='O';
                enregistrementCoup(Coup2, ligne-2,colonne-2,nbcoup);
                alignementDe3 = true;
            }

            // X
            //   O
            //     .
            else if (tab[ligne -2][colonne-2] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne-1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                alignementDe3 = true;
            }
        }
        return alignementDe3;
    }
    //--------------------------------------------------diago groite check block-------------------------------------------------------------------
    public static boolean checkAlignement3DiagoDroite (char[][] tab,int ligne , int colonne, boolean[][] J1DiagonaleDroite) {
        boolean alignementDe3 = false;
        if (ligne +2 < tab.length && colonne-2>= 0 && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne+2][colonne-2]) {
            //     .
            //   O
            // O
            if (tab[ligne+1][colonne-1] =='O'  && tab[ligne+2][colonne-2] == 'O')
                alignementDe3 = true;
        }
        if (ligne +1 < tab.length && colonne-1>=0 && ligne-1>=0 && colonne+1< tab.length && !J1DiagonaleDroite[ligne+1][colonne-1] && !J1DiagonaleDroite[ligne-1][colonne+1]) {
            //     O
            //   .
            //O
            if (tab[ligne-1][colonne+1] == 'O' && tab[ligne+1][colonne-1] == 'O')
                alignementDe3 = true;
        }
        if (ligne -2 >=0 && colonne+2 < tab.length && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne-2][colonne+2]) {
            //     O
            //   O
            // .
            if (tab[ligne-2][colonne+2] == 'O' && tab[ligne-1][colonne+1] == 'O')
                alignementDe3 = true;
        }
        return alignementDe3;
    }
    public static boolean blockAlignement3DiagoDroite (char[][] tab,int ligne , int colonne,int[][] Coup2,int nbcoup, boolean[][] J1DiagonaleDroite, char Symbole) {
        boolean alignementDe3 = false;
        if (ligne +2 < tab.length && colonne-2>= 0 && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne+2][colonne-2]) {
            //     .
            //   X
            // O
            if (tab[ligne+1][colonne-1] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne-2)){ // Ici symbole c'est celui du bot ou joueur
                tab[ligne+2][colonne-2]='O';
                enregistrementCoup(Coup2, ligne+2, colonne-2, nbcoup);
                alignementDe3 = true;
            }
            //     .
            //   O
            // X
            else if (tab[ligne +2][colonne-2] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne-1)){
                tab[ligne+1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne+1, colonne-1, nbcoup);
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && ligne +1 < tab.length && colonne-1>=0 && ligne-1>=0 && colonne+1< tab.length && !J1DiagonaleDroite[ligne+1][colonne-1] && !J1DiagonaleDroite[ligne-1][colonne+1]) {
            //     X
            //   .
            // O
            if (tab[ligne-1][colonne+1] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne-1)){
                tab[ligne+1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne+1, colonne-1, nbcoup);
                alignementDe3 = true;
            }
            //     O
            //   .
            // X
            else if (tab[ligne +1][colonne-1] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne+1)){
                tab[ligne-1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne-1, colonne+1, nbcoup);
                alignementDe3 = true;
            }
        }
        if (!alignementDe3 && ligne -2 >=0 && colonne+2 < tab.length && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne-2][colonne+2]) {
            //     X
            //   O
            // .
            if (tab[ligne-2][colonne+2] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne+1)){
                tab[ligne-1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne-1, colonne+1, nbcoup);
                alignementDe3 = true;
            }
            //     O
            //   X
            // .
            else if (tab[ligne -1][colonne+1] == Symbole && !ModifPlateau.pionPresent(tab, ligne-2, colonne+2)){
                tab[ligne-2][colonne+2]='O';
                enregistrementCoup(Coup2, ligne-2, colonne-2, nbcoup);
                alignementDe3 = true;
            }

        }
        return alignementDe3;
    }
    // ------------------------------------------hori alignement 4 check et block-----------------------------------------------------------------
    public static boolean checkAlignement4Hori (char[][] tab,int ligne, int colonne,boolean[][] J1Horizontal, char Symbole) {
        boolean alignementDe4 = false;

        if (colonne + 3 < tab.length && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne + 3]) {
            if (tab[ligne][colonne + 3] == Symbole && tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 1] == Symbole) {
                // . O O O
                alignementDe4 = true;
            }
        }
        if (colonne + 2 < tab.length && colonne - 1 >= 0 && !J1Horizontal[ligne][colonne+2] && !J1Horizontal[ligne][colonne - 1]) {
            if (tab[ligne][colonne - 1] == 'O' && tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole) {
                // O . O O
                alignementDe4 = true;
            }
        }
        if (colonne-2>=0 && colonne+1 <tab.length &&!J1Horizontal[ligne][colonne+1] && !J1Horizontal[ligne][colonne-2]){
            if (tab[ligne][colonne -2] == Symbole && tab[ligne][colonne-1]=='O' && tab[ligne][colonne +1] == Symbole){
                // O O . O
                alignementDe4 = true;
            }
        }
        if (colonne-3>=0 &&!J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne-3]){
            if (tab[ligne][colonne -3] == Symbole && tab[ligne][colonne -2] == Symbole && tab[ligne][colonne-1]=='O' ){
                // O O O .
                alignementDe4 = true;
            }
        }
        return alignementDe4;
    }
    public static boolean Blockalignement4Hori (char[][] tab,int ligne, int colonne,int[][] Coup2,int nbcoup, boolean[][] J1Horizontal, char Symbole) {
        boolean alignementDe4 = false;

        if (colonne + 3 < tab.length && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne + 3]) {
            if (tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 2)) {
                tab[ligne][colonne+2]='O';
                enregistrementCoup(Coup2, ligne, colonne+2, nbcoup); // . X O X
                alignementDe4 = true;
            } else if (tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne+1]='O';
                enregistrementCoup(Coup2, ligne, colonne+1, nbcoup); // . O X X
                alignementDe4 = true;
            } else if (tab[ligne][colonne + 2] == Symbole && tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 3)) {
                tab[ligne][colonne+3]='O';
                enregistrementCoup(Coup2, ligne, colonne+3, nbcoup); // . X X O
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && colonne + 2 < tab.length && colonne - 1 >= 0 && !J1Horizontal[ligne][colonne+2] && !J1Horizontal[ligne][colonne - 1]) {
            if (tab[ligne][colonne + 1] == Symbole && tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne - 1)) {
                tab[ligne][colonne-1]='O';
                enregistrementCoup(Coup2, ligne, colonne-1, nbcoup); // O . X X
                alignementDe4 = true;
            } else if (tab[ligne][colonne -1] == Symbole && tab[ligne][colonne + 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 1)) {
                tab[ligne][colonne+1]='O';
                enregistrementCoup(Coup2, ligne, colonne+1, nbcoup); // X . O X
                alignementDe4 = true;
            } else if (tab[ligne][colonne -1] == Symbole && tab[ligne][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne, colonne + 2)) {
                tab[ligne][colonne+2]='O';
                enregistrementCoup(Coup2, ligne, colonne+2, nbcoup); // X . X O
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && colonne-2>=0 && colonne+1<tab.length && !J1Horizontal[ligne][colonne+1] && !J1Horizontal[ligne][colonne-2]){
            if (tab[ligne][colonne-2]== Symbole && tab[ligne][colonne-1]== Symbole && !ModifPlateau.pionPresent(tab, ligne,colonne+1)){
                tab[ligne][colonne+1]='O';
                enregistrementCoup(Coup2, ligne, colonne+1, nbcoup); // X X . O
                alignementDe4 = true;
            }
            else if (tab[ligne][colonne-2]==Symbole && tab[ligne][colonne+1]== Symbole &&!ModifPlateau.pionPresent(tab, ligne,colonne-1)){
                tab[ligne][colonne-1]='O';
                enregistrementCoup(Coup2, ligne, colonne-1, nbcoup);// X O . X
                alignementDe4 = true;
            }
            else if (tab[ligne][colonne-1]==Symbole && tab[ligne][colonne+1]== Symbole && !ModifPlateau.pionPresent(tab, ligne,colonne-2)){
                tab[ligne][colonne-2]='O';
                enregistrementCoup(Coup2, ligne, colonne-2, nbcoup);// O X . X
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && colonne-3>=0 && !J1Horizontal[ligne][colonne] && !J1Horizontal[ligne][colonne-3]){
            if (tab[ligne][colonne-3]== Symbole && tab[ligne][colonne-2]== Symbole && !ModifPlateau.pionPresent(tab, ligne,colonne-1)){
                tab[ligne][colonne-1]='O';
                enregistrementCoup(Coup2, ligne, colonne-1, nbcoup); // X X O .
                alignementDe4 = true;
            }
            else if (tab[ligne][colonne-3]==Symbole && tab[ligne][colonne-1]== Symbole &&!ModifPlateau.pionPresent(tab, ligne,colonne-2)){
                tab[ligne][colonne-2]='O';
                enregistrementCoup(Coup2, ligne, colonne-2, nbcoup);// X O X .
                alignementDe4 = true;
            }
            else if (tab[ligne][colonne-2]==Symbole && tab[ligne][colonne-1]== Symbole && !ModifPlateau.pionPresent(tab, ligne,colonne-3)){
                tab[ligne][colonne-3]='O';
                enregistrementCoup(Coup2, ligne, colonne-3, nbcoup);// O X X .
                alignementDe4 = true;
            }
        }
        return alignementDe4;
    }

    //----------------------------------verti check block------------------------------------------------------------------------
    public static boolean checkAlignement4Verti (char[][] tab,int ligne , int colonne, boolean[][] J1Vertical) {
        boolean alignementDe4 = false;
        if (ligne + 3 < tab.length && !J1Vertical[ligne][colonne] && !J1Vertical[ligne + 3][colonne]) {
            // .
            // O
            // O
            // O
            if (tab[ligne + 1][colonne] == 'O' && tab[ligne + 2][colonne] == 'O' && tab[ligne + 3][colonne] == 'O')
                alignementDe4 = true;
        }
        if (ligne + 2 < tab.length && ligne - 1 >= 0 && !J1Vertical[ligne+2][colonne] && !J1Vertical[ligne-1][colonne]) {
            // O
            // .
            // O
            // O
            if (tab[ligne - 1][colonne] == 'O' && tab[ligne + 1][colonne] == 'O' && tab[ligne + 2][colonne] == 'O')
                alignementDe4 = true;
        }
        if (ligne-2>=0 && ligne +1 <tab.length && !J1Vertical[ligne-2][colonne] && !J1Vertical[ligne+1][colonne]){
            // O
            // O
            // .
            // O
            if (tab[ligne-2][colonne]=='O' && tab[ligne - 1][colonne] == 'O' && tab[ligne + 1][colonne] == 'O')
                alignementDe4 = true;
        }
        if (ligne-3>=0 && !J1Vertical[ligne-3][colonne] && !J1Vertical[ligne+1][colonne]){
            // O
            // O
            // O
            // .
            if (tab[ligne -3][colonne] == 'O' && tab[ligne-2][colonne]=='O' && tab[ligne - 1][colonne] == 'O')
                alignementDe4 = true;
        }
        return alignementDe4;
    }
    public static boolean blockAlignement4Verti (char[][] tab,int ligne , int colonne,int[][] Coup2,int nbcoup, boolean[][] J1Vertical, char Symbole) {
        boolean alignementDe4 = false;
        if (ligne + 3 < tab.length && !J1Vertical[ligne][colonne] && !J1Vertical[ligne + 3][colonne]) {
            // .
            // O
            // X
            // X
            if (tab[ligne + 2][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne)){
                tab[ligne+1][colonne]='O';
                enregistrementCoup(Coup2,ligne+1, colonne, nbcoup );
                alignementDe4 = true;
            }
            // .
            // X
            // O
            // X
            else if (tab[ligne + 1][colonne] == Symbole && tab[ligne + 3][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne)){
                tab[ligne+2][colonne] ='O';
                enregistrementCoup(Coup2,ligne+2, colonne, nbcoup );
                alignementDe4 = true;
            }
            // .
            // X
            // X
            // O
            else if (tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne+3, colonne)){
                tab[ligne+3][colonne] ='O';
                enregistrementCoup(Coup2,ligne+3, colonne, nbcoup );
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && ligne + 2 < tab.length && ligne - 1 >= 0 && !J1Vertical[ligne+2][colonne] && !J1Vertical[ligne-1][colonne]) {
            // X
            // .
            // O
            // X
            if (tab[ligne - 1][colonne] == Symbole && tab[ligne +2][colonne] == Symbole &&  !ModifPlateau.pionPresent(tab, ligne + 1, colonne)){
                tab[ligne+1][colonne]='O';
                enregistrementCoup(Coup2, ligne +1,colonne, nbcoup);
                alignementDe4 = true;
            }
            // O
            // .
            // X
            // X
            else if (tab[ligne + 1][colonne] == Symbole && tab[ligne + 2][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne)){
                tab[ligne-1][colonne]='O';
                enregistrementCoup(Coup2, ligne -1,colonne, nbcoup);
                alignementDe4 = true;
            }
            // X
            // .
            // X
            // O
            else if (tab[ligne + 1][colonne] == Symbole && tab[ligne - 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 2, colonne)){
                tab[ligne+2][colonne]='O';
                enregistrementCoup(Coup2, ligne +2,colonne, nbcoup);
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && ligne-2>=0 && ligne+1<tab.length && !J1Vertical[ligne+1][colonne] && !J1Vertical[ligne-2][colonne]){
            // X
            // O
            // .
            // X
            if (tab[ligne-2][colonne]==Symbole && tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1,colonne)){
                tab[ligne-1][colonne]='O';
                enregistrementCoup(Coup2,ligne-1,colonne,nbcoup);
                alignementDe4 = true;
            }
            // O
            // X
            // .
            // X
            else if (tab[ligne-1][colonne]==Symbole && tab[ligne + 1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne-2,colonne)){
                tab[ligne-2][colonne]='O';
                enregistrementCoup(Coup2,ligne-2,colonne,nbcoup);
                alignementDe4 = true;
            }
            // X
            // X
            // .
            // O
            else if (tab[ligne-2][colonne]==Symbole && tab[ligne -1][colonne] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1,colonne)){
                tab[ligne+1][colonne]='O';
                enregistrementCoup(Coup2,ligne+1,colonne,nbcoup);
                alignementDe4 = true;
            }
        }
        return alignementDe4;
    }
    // -----------------------------------------diago gauche check block------------------------------------------------------------
    public static boolean checkAlignement4DiagoGauche (char[][] tab,int ligne , int colonne, boolean[][] J1DiagonaleGauche) {
        boolean alignementDe4 = false;
        if (ligne + 3 < tab.length && colonne + 3 < tab.length && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne+3][colonne+3]) {
            // .
            //   O
            //     O
            //       O
            if (tab[ligne+1][colonne+1] == 'O' && tab[ligne+2][colonne+2] == 'O'&& tab[ligne+3][colonne+3] == 'O')
                alignementDe4 = true;
        }
        if (ligne + 2 < tab.length && colonne+2 < tab.length && ligne-1>=0 && colonne-1>=0 && !J1DiagonaleGauche[ligne-1][colonne-1] && !J1DiagonaleGauche[ligne+2][colonne+2]) {
            // O
            //   .
            //     O
            //       O
            if (tab[ligne-1][colonne-1] == 'O' && tab[ligne+1][colonne+1] == 'O' && tab[ligne+2][colonne+2] == 'O')
                alignementDe4 = true;
        }
        if (ligne -2 >=0 && colonne-2>= 0 && ligne +1<tab.length && colonne+1<tab.length && !J1DiagonaleGauche[ligne+1][colonne+1] && !J1DiagonaleGauche[ligne-2][colonne-2]) {
            // O
            //   O
            //     .
            //       O
            if (tab[ligne-1][colonne-1] == 'O' && tab[ligne-2][colonne-2] == 'O'&& tab[ligne+1][colonne+1] == 'O')
                alignementDe4 = true;
        }
        if (ligne -3 >=0 && colonne-3>= 0 && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne-3][colonne-3]) {
            // O
            //   O
            //     O
            //       .
            if (tab[ligne-1][colonne-1] == 'O' &&tab[ligne-2][colonne-2] == 'O' && tab[ligne-3][colonne-3] == 'O')
                alignementDe4 = true;
        }
        return alignementDe4;
    }
    public static boolean blockAlignement4DiagoGauche (char[][] tab,int ligne , int colonne,int[][] Coup2,int nbcoup, boolean[][] J1DiagonaleGauche, char Symbole) {
        boolean alignementDe4 = false;
        if (ligne + 3 < tab.length && colonne + 3 < tab.length && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne+3][colonne+3]) {
            // .
            //   X
            //     O
            //       X
            if (tab[ligne+1][colonne+1] == Symbole && tab[ligne+3][colonne+3] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne+2)){
                tab[ligne+2][colonne+2]='O';
                enregistrementCoup(Coup2, ligne+2,colonne+2,nbcoup);
                alignementDe4 = true;
            }
            // .
            //   O
            //     X
            //       X
            else if (tab[ligne + 2][colonne+ 2] == 'O' && tab[ligne + 3][colonne+ 3] == 'O' && !ModifPlateau.pionPresent(tab, ligne+1, colonne+1)){
                tab[ligne+1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne+1,colonne+1,nbcoup);
                alignementDe4 = true;
            }
            // .
            //   X
            //     X
            //       O
            else if (tab[ligne + 1][colonne+ 1] == 'O' && tab[ligne + 2][colonne+ 2] == 'O' && !ModifPlateau.pionPresent(tab, ligne+3, colonne+3)){
                tab[ligne+3][colonne+3]='O';
                enregistrementCoup(Coup2, ligne+3,colonne+3,nbcoup);
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && ligne + 2 < tab.length && colonne+2 < tab.length && ligne-1>=0 && colonne-1>=0 && !J1DiagonaleGauche[ligne-1][colonne-1] && !J1DiagonaleGauche[ligne+2][colonne+2]) {
            // X
            //   .
            //     O
            //       X
            if (tab[ligne-1][colonne-1] == 'O' && tab[ligne+2][colonne+2] == 'O' && !ModifPlateau.pionPresent(tab, ligne+1, colonne+1)){
                tab[ligne+1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne+1,colonne+1,nbcoup);
                alignementDe4 = true;
            }
            // O
            //   .
            //     X
            //       X
            else if (tab[ligne +1][colonne+1] == 'O' && tab[ligne +2][colonne+2] == 'O' && !ModifPlateau.pionPresent(tab, ligne-1, colonne-1)){
                tab[ligne-1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne-1,colonne-1,nbcoup);
                alignementDe4 = true;
            }
            // X
            //   .
            //     X
            //       O
            else if (tab[ligne -1][colonne-1] == 'O' && tab[ligne +1][colonne+1] == 'O' && !ModifPlateau.pionPresent(tab, ligne+2, colonne+2)){
                tab[ligne+2][colonne+2]='O';
                enregistrementCoup(Coup2, ligne+2,colonne+2,nbcoup);
                alignementDe4 = true;
            }

        }
        if (!alignementDe4 && ligne -2 >=0 && colonne-2>= 0 && ligne+1<tab.length && colonne+1 <tab.length && !J1DiagonaleGauche[ligne+1][colonne+1] && !J1DiagonaleGauche[ligne-2][colonne-2]) {
            // O
            //   X
            //     .
            //       X
            if (tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne - 2)) {
                tab[ligne - 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne - 2, nbcoup);
                alignementDe4 = true;
            }

            // X
            //   O
            //     .
            //       X
            else if (tab[ligne - 2][colonne - 2] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne - 1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                alignementDe4 = true;
            }
            // X
            //   X
            //     .
            //       O
            else if (tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 1][colonne - 1] == Symbole && !ModifPlateau.pionPresent(tab, ligne + 1, colonne + 1)) {
                tab[ligne + 1][colonne + 1] = 'O';
                enregistrementCoup(Coup2, ligne + 1, colonne + 1, nbcoup);
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && ligne -3 >=0 && colonne-3>= 0 && !J1DiagonaleGauche[ligne][colonne] && !J1DiagonaleGauche[ligne-3][colonne-3]) {
            // O
            //   X
            //     X
            //       .
            if (tab[ligne - 1][colonne - 1] == Symbole && tab[ligne - 2][colonne - 2] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 3, colonne - 3)) {
                tab[ligne - 3][colonne - 3] = 'O';
                enregistrementCoup(Coup2, ligne - 3, colonne - 3, nbcoup);
                alignementDe4 = true;
            }
            // X
            //   O
            //     X
            //       .
            else if (tab[ligne - 1][colonne - 1] == Symbole && tab[ligne - 3][colonne - 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 2, colonne - 2)) {
                tab[ligne - 2][colonne - 2] = 'O';
                enregistrementCoup(Coup2, ligne - 2, colonne - 2, nbcoup);
                alignementDe4 = true;
            }
            // X
            //   X
            //     O
            //       .
            else if (tab[ligne - 2][colonne - 2] == Symbole && tab[ligne - 3][colonne - 3] == Symbole && !ModifPlateau.pionPresent(tab, ligne - 1, colonne - 1)) {
                tab[ligne - 1][colonne - 1] = 'O';
                enregistrementCoup(Coup2, ligne - 1, colonne - 1, nbcoup);
                alignementDe4 = true;
            }
        }
        return alignementDe4;
    }
    //--------------------------------------------------diago groite check block-------------------------------------------------------------------
    public static boolean checkAlignement4DiagoDroite (char[][] tab,int ligne , int colonne, boolean[][] J1DiagonaleDroite) {
        boolean alignementDe4 = false;
        if (ligne +3 < tab.length && colonne-3>= 0 && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne+3][colonne-3]) {
            //     .
            //   O
            // O
            //O
            if (tab[ligne+1][colonne-1] == 'O' && tab[ligne+2][colonne-2] == 'O' && tab[ligne+3][colonne-3] == 'O')
                alignementDe4 = true;
        }
        if (ligne +2 < tab.length && colonne-2>=0 && ligne-1>=0 && colonne+1< tab.length && !J1DiagonaleDroite[ligne+2][colonne-2] && !J1DiagonaleDroite[ligne-1][colonne+1]) {
            //     O
            //   .
            // O
            //O
            if (tab[ligne-1][colonne+1] == 'O' && tab[ligne+1][colonne-1] == 'O' && tab[ligne+2][colonne-2] == 'O')
                alignementDe4 = true;
        }
        if (ligne -2 >=0 && colonne+2 < tab.length && ligne+1<tab.length && colonne-1>=0 && !J1DiagonaleDroite[ligne+1][colonne-1] && !J1DiagonaleDroite[ligne-2][colonne+2]) {
            //     O
            //   O
            // .
            //O
            if (tab[ligne-2][colonne+2] == 'O' && tab[ligne-1][colonne+1] == 'O' && tab[ligne+1][colonne-1] == 'O')
                alignementDe4 = true;
        }
        if (ligne -3 >=0 && colonne+3 < tab.length && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne-3][colonne+3]) {
            //     O
            //   O
            // O
            //.
            if (tab[ligne-2][colonne+2] == 'O' && tab[ligne-1][colonne+1] == 'O' && tab[ligne-3][colonne+3] == 'O')
                alignementDe4 = true;
        }

        return alignementDe4;
    }
    public static boolean blockAlignement4DiagoDroite (char[][] tab,int ligne , int colonne,int[][] Coup2,int nbcoup, boolean[][] J1DiagonaleDroite, char Symbole) {
        boolean alignementDe4 = false;
        if (ligne +3 < tab.length && colonne-3>= 0 && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne+3][colonne-3]) {
            //     .
            //   X
            // O
            //X
            if (tab[ligne+1][colonne-1] == Symbole && tab[ligne+3][colonne-3] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne-2)){
                tab[ligne+2][colonne-2]='O';
                enregistrementCoup(Coup2, ligne+2, colonne-2, nbcoup);
                alignementDe4 = true;
            }
            //     .
            //   O
            // X
            //X
            else if (tab[ligne +2][colonne-2] == Symbole && tab[ligne+3][colonne-3] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne-1)){
                tab[ligne+1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne+1, colonne-1, nbcoup);
                alignementDe4 = true;
            }
            //     .
            //   X
            // X
            //O
            else if (tab[ligne+1][colonne-1] == Symbole && tab[ligne +2][colonne-2] == Symbole && !ModifPlateau.pionPresent(tab, ligne+3, colonne-3)){
                tab[ligne+3][colonne-3]='O';
                enregistrementCoup(Coup2, ligne+3, colonne-3, nbcoup);
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && ligne +2 < tab.length && colonne-2>=0 && ligne-1>=0 && colonne+1< tab.length && !J1DiagonaleDroite[ligne+2][colonne-2] && !J1DiagonaleDroite[ligne-1][colonne+1]) {
            //     X
            //   .
            // O
            //X
            if (tab[ligne-1][colonne+1] == Symbole && tab[ligne+2][colonne-2] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne-1)){
                tab[ligne+1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne+1, colonne-1, nbcoup);
                alignementDe4 = true;
            }
            //     O
            //   .
            // X
            //X
            else if (tab[ligne +1][colonne-1] == Symbole && tab[ligne +2][colonne-2] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne+1)){
                tab[ligne-1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne-1, colonne+1, nbcoup);
                alignementDe4 = true;
            }
            //     X
            //   .
            // X
            //O
            else if (tab[ligne +1][colonne-1] == Symbole && tab[ligne -1][colonne+1] == Symbole && !ModifPlateau.pionPresent(tab, ligne+2, colonne-2)){
                tab[ligne+2][colonne-2]='O';
                enregistrementCoup(Coup2, ligne+2, colonne-2, nbcoup);
                alignementDe4 = true;
            }
        }
        if (!alignementDe4 && ligne -2 >=0 && colonne+2 < tab.length && ligne+1<tab.length && colonne-1>=0 &&!J1DiagonaleDroite[ligne+1][colonne-1] && !J1DiagonaleDroite[ligne-2][colonne+2]) {
            //     X
            //   O
            // .
            //X
            if (tab[ligne-2][colonne+2] == Symbole && tab[ligne+1][colonne-1] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne+1)){
                tab[ligne-1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne-1, colonne+1, nbcoup);
                alignementDe4 = true;
            }
            //     O
            //   X
            // .
            //X
            else if (tab[ligne -1][colonne+1] == Symbole && tab[ligne+1][colonne-1] == Symbole && !ModifPlateau.pionPresent(tab, ligne-2, colonne+2)){
                tab[ligne-2][colonne+2]='O';
                enregistrementCoup(Coup2, ligne-2, colonne+2, nbcoup);
                alignementDe4 = true;
            }
            //     X
            //   X
            // .
            //O
            else if (tab[ligne -1][colonne+1] == Symbole && tab[ligne-2][colonne+2] == Symbole && !ModifPlateau.pionPresent(tab, ligne+1, colonne-1)){
                tab[ligne+1][colonne-1]='O';
                enregistrementCoup(Coup2, ligne+1, colonne-1, nbcoup);
                alignementDe4 = true;
            }

        }
        if (!alignementDe4 && ligne -3 >=0 && colonne+3 < tab.length && !J1DiagonaleDroite[ligne][colonne] && !J1DiagonaleDroite[ligne-3][colonne+3]) {
            //     X
            //   O
            // X
            //.
            if (tab[ligne-1][colonne+1] == Symbole && tab[ligne-3][colonne+3] == Symbole && !ModifPlateau.pionPresent(tab, ligne-2, colonne+2)){
                tab[ligne-2][colonne+2]='O';
                enregistrementCoup(Coup2, ligne-2, colonne+2, nbcoup);
                alignementDe4 = true;
            }
            //     O
            //   X
            // X
            //.
            else if (tab[ligne -1][colonne+1] == Symbole && tab[ligne-2][colonne+2] == Symbole && !ModifPlateau.pionPresent(tab, ligne-3, colonne+3)){
                tab[ligne-3][colonne+3]='O';
                enregistrementCoup(Coup2, ligne-3, colonne+3, nbcoup);
                alignementDe4 = true;
            }
            //     X
            //   X
            // O
            //.
            else if (tab[ligne -2][colonne+2] == Symbole && tab[ligne-3][colonne+3] == Symbole && !ModifPlateau.pionPresent(tab, ligne-1, colonne+1)){
                tab[ligne-1][colonne+1]='O';
                enregistrementCoup(Coup2, ligne-1, colonne+1, nbcoup);
                alignementDe4 = true;
            }
        }
        return alignementDe4;
    }
}