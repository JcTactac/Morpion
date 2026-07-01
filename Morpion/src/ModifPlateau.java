public class ModifPlateau {

    public static final String RED = "\u001B[41m";
    public static final String BLUE = "\u001B[44m";
    public static final String RESET = "\u001B[0m";

    public static void afficherPlateau(char[][] t, int tailleLignePlateau, int tailleColonnePlateau) {

        // Affiche les numéros de colonnes
        System.out.print("  ");
        for (int ChiffresColonne = 0; ChiffresColonne < tailleColonnePlateau; ChiffresColonne++) {
            System.out.print(" | " + ChiffresColonne);
        }
        System.out.println(" |");

        // Affiche la ligne de séparation
        for (int j = 0; j < tailleColonnePlateau; j++) {
            System.out.print("-----");
        }
        System.out.println();

        // Affiche le plateau
        for (int i = 0; i < tailleLignePlateau; i++) {
            // Affichage du numéro de ligne et des cases
            System.out.print((i) + " | "); // Affiche le numéro de ligne (commence à 1)
            for (int j = 0; j < tailleColonnePlateau; j++) {
                if (t[i][j]=='X') {
                    System.out.print(RED + t[i][j] + RESET + " | ");
                }
                else {
                    if (t[i][j]=='O') {
                        System.out.print(BLUE + t[i][j] + RESET + " | ");
                    }
                    else {
                        System.out.print(t[i][j] + " | ");
                    }
                }
            }
            System.out.println();

            // Ligne de séparation entre les rangées
            for (int j = 0; j < tailleColonnePlateau; j++) {
                System.out.print("-----");
            }
            System.out.println();
        }
    }


    public static boolean pionvalide(char[][] tab, int LigneJoueur, int ColonneJoueur, int tailleLignePlateau, int tailleColonnePlateau) {
        boolean Valide = true;
        if (LigneJoueur < 0 || LigneJoueur >= tailleLignePlateau || ColonneJoueur < 0 || ColonneJoueur >= tailleColonnePlateau) {
            Valide = false;
        }
        return Valide;
    }


    public static boolean pionPresent (char[][] tab, int ligne, int colonne) {
        boolean estPresent = false;
        if (tab[ligne][colonne] !='.') {
            estPresent = true;
        }
        return estPresent;
    }

    public static void saisirUnPion(char[][] tab, int ligne, int colonne,int joueur) {
        if (joueur==1) {
            tab[ligne][colonne] ='X';
        }
        else {
            tab[ligne][colonne] = 'O';
        }
    }

    public static boolean pointGagneHorizontal(char[][] tab, int ligne, int colonne, boolean[][] JHorizontal, char Symbole) {
        // Alignement Horizontal
        boolean AlignementRealise=false;
        if (Alignements.alignementHorizontalConsecutifInverse(tab, ligne, colonne, JHorizontal, Symbole)) {
            AlignementRealise=true;
        }
        else {
            if (Alignements.alignementHorizontalConsecutif(tab, ligne, colonne, JHorizontal, Symbole)) {
                AlignementRealise=true;
            }
            else {
                if (Alignements.alignementHorizontalConsecutifCombi(tab, ligne, colonne, JHorizontal, Symbole)) {
                    AlignementRealise=true;
                }
            }
        }
        return AlignementRealise;
    }

    public static boolean pointGagneVertical(char[][] tab, int ligne, int colonne,boolean[][] JVertical, char Symbole) {
        // Alignement Vertical
        boolean AlignementRealise=false;
        if (Alignements.alignementVerticalConsecutifInverse(tab, ligne, colonne, JVertical,Symbole)) {
            AlignementRealise=true;
        }
        else {
            if (Alignements.alignementVertivalConsecutif(tab, ligne, colonne, JVertical,Symbole)) {
                AlignementRealise=true;
            }
            else {
                if (Alignements.alignementVerticalConsecutifCombi(tab, ligne, colonne, JVertical,Symbole)) {
                    AlignementRealise=true;
                }
            }
        }
        return AlignementRealise;
    }

    public static boolean pointGagneDiagoGauche(char[][] tab, int ligne, int colonne,boolean[][] JDiagonaleGauche, char Symbole) {
        boolean AlignementRealise=false;
        if (Alignements.alignementDiagoGauchePresent(tab, ligne, colonne,JDiagonaleGauche,Symbole)) {
            AlignementRealise=true;
        }
        else {
            if (Alignements.alignementDiagoGaucheInversePresent(tab, ligne, colonne, JDiagonaleGauche,Symbole)) {
                AlignementRealise=true;
            }
            else {
                if (Alignements.alignementDiagoGaucheInverseCombiPresent(tab, ligne, colonne, JDiagonaleGauche,Symbole)) {
                    AlignementRealise=true;
                }
            }
        }
        return AlignementRealise;
    }

    public static boolean pointGagneDiagoDroite(char[][] tab, int ligne, int colonne, boolean[][] JDiagonaleDroite, char Symbole) {
        boolean AlignementRealise=false;
        if (Alignements.alignementDiagoDroitePresent(tab, ligne, colonne,JDiagonaleDroite,Symbole)) {
            AlignementRealise=true;
        }
        else {
            if (Alignements.alignementDiagoDroiteInversePresent(tab, ligne, colonne, JDiagonaleDroite,Symbole)) {
                AlignementRealise=true;
            }
            else {
                if (Alignements.alignementDiagoDroiteCombiPresent(tab, ligne, colonne, JDiagonaleDroite,Symbole)) {
                    AlignementRealise=true;
                }
            }
        }
        return AlignementRealise;
    }
}