public class Alignements {
    // gauche -> droite
    // X X X X X
    public static boolean alignementHorizontalConsecutif(char[][]tab, int ligne, int colonne, boolean[][] JHorizontal, char Symbole) {
        int constUn = 1;
        int compteurDroite = 0;
        boolean alignementHorizontal = false;
        boolean trouve = true;
            if (colonne + 4 < tab[ligne].length) {
                for (int i = 0; i <= 3 && trouve && !JHorizontal[ligne][colonne + constUn]; i++) {
                    if (tab[ligne][colonne + constUn] == Symbole) {
                        constUn++;
                        compteurDroite++;
                    } else {
                        trouve = false;
                    }
                }
                if (compteurDroite == 4) {
                    alignementHorizontal = true;
                    for (int i=0; i<=4 ; i++) {
                        JHorizontal[ligne][colonne+i]=true;
                    }
                }
            }
        return alignementHorizontal;
    }
    // droite -> gauche
    // X X X X X
    public static boolean alignementHorizontalConsecutifInverse(char[][]tab, int ligne, int colonne, boolean[][] JHorizontal, char Symbole) {
        int constUn=1;
        int compteurGauche = 0;
        boolean alignementHorizontalInverse = false;
        boolean trouve = true;
            if (colonne-4>=0) {
                for (int i = 0; i <= 3 && trouve && !JHorizontal[ligne][colonne-constUn]; i++) {
                    if (tab[ligne][colonne - constUn] == Symbole) {
                        compteurGauche++;
                        constUn++;
                    } else {
                        trouve = false;
                    }
                }
                if (compteurGauche == 4) {
                    alignementHorizontalInverse = true;
                    for (int i=0; i<=4 ; i++) {
                        JHorizontal[ligne][colonne-i]=true;
                    }
                }
            }
        return alignementHorizontalInverse;
    }
    // X . X X X
    public static boolean alignementHorizontalConsecutifCombi(char[][]tab, int ligne, int colonne, boolean[][] JHorizontal, char Symbole) {
        boolean alignementHorizontalCombi=false;
            if (colonne +2 < tab[ligne].length && colonne-2>=0) {
                if( (tab[ligne][colonne-1]==Symbole && tab[ligne][colonne-2] ==Symbole && tab[ligne][colonne+1]==Symbole && tab[ligne][colonne+2]==Symbole && !JHorizontal[ligne][colonne - 1] && !JHorizontal[ligne][colonne - 2] &&
                !JHorizontal[ligne][colonne] && !JHorizontal[ligne][colonne + 1] && !JHorizontal[ligne][colonne + 2])){
                    alignementHorizontalCombi=true;
                    for (int i=0; i<=2 ; i++) {
                        JHorizontal[ligne][colonne-i]=true;
                    }
                    for (int i=0; i<=2 ; i++) {
                        JHorizontal[ligne][colonne+i]=true;
                    }
                }
            }
            else if (colonne+3 < tab[ligne].length && colonne-1>=0) {
                if (tab[ligne][colonne-1]==Symbole && tab[ligne][colonne+1]==Symbole && tab[ligne][colonne+2]==Symbole && tab[ligne][colonne+3] ==Symbole && !JHorizontal[ligne][colonne-1] && !JHorizontal[ligne][colonne] && !JHorizontal[ligne][colonne+1]
                        && !JHorizontal[ligne][colonne+2] && !JHorizontal[ligne][colonne+3]) {
                    alignementHorizontalCombi=true;
                    for (int i=0; i<=1 ; i++) {
                        JHorizontal[ligne][colonne-i]=true;
                    }
                    for (int i=0; i<=3 ; i++) {
                        JHorizontal[ligne][colonne+i]=true;
                    }
                }
            }
            else if (colonne+1 < tab[ligne].length && colonne-1>=0) {
                if (tab[ligne][colonne-3]==Symbole && tab[ligne][colonne-2]==Symbole&&tab[ligne][colonne-1]==Symbole && tab[ligne][colonne+1] ==Symbole && !JHorizontal[ligne][colonne+1] && !JHorizontal[ligne][colonne] && !JHorizontal[ligne][colonne-1]
                && !JHorizontal[ligne][colonne-2] && !JHorizontal[ligne][colonne-3]) {
                    alignementHorizontalCombi=true;
                    for (int i=0; i<=1 ; i++) {
                        JHorizontal[ligne][colonne+i]=true;
                    }
                    for (int i=0; i<=3 ; i++) {
                        JHorizontal[ligne][colonne-i]=true;
                    }
                }
            }
        return alignementHorizontalCombi;
    }
    //bas vers haut
    // X
    // X
    // X
    // X
    // X
    public static boolean alignementVertivalConsecutif(char[][]tab, int ligne, int colonne, boolean[][] JVertical, char Symbole){
        int constUn=1;
        int compteurHaut = 0;
        boolean alignementVertical=false;
        boolean trouve=true;
            if (ligne-4>=0) {
                for (int i = 0; i <= 3 && trouve && !JVertical[ligne-constUn][colonne]; i++) {
                    if ( tab[ligne - constUn][colonne] == Symbole) {
                        compteurHaut++;
                        constUn++;
                    } else {
                        trouve = false;
                    }
                }
                if (compteurHaut == 4) {
                    alignementVertical = true;
                    for (int i=0; i<=4; i++) {
                        JVertical[ligne-i][colonne]=true;
                    }
                }
            }
        return alignementVertical;
    }
    //haut vers bas
    // X
    // X
    // X
    // X
    // X
    public static boolean alignementVerticalConsecutifInverse(char[][]tab, int ligne, int colonne, boolean[][] JVertical, char Symbole){
        int constUn=1;
        int competeurBas = 0;
        boolean alignementVertical=false;
        boolean trouve=true;
            if (ligne+4< tab.length) {
                for (int i = ligne; i <= 3 && trouve && !JVertical[ligne+constUn][colonne]; i++) {
                    if (tab[ligne + constUn][colonne] == Symbole) {
                        competeurBas++;
                        constUn++;
                    } else {
                        trouve = false;
                    }
                }
                if (competeurBas == 4) {
                    alignementVertical = true;
                    for (int i=0; i<=4; i++) {
                        JVertical[ligne+i][colonne]=true;
                    }
                }
            }
        return alignementVertical;
    }

    // marche pas trop
    // X
    // X
    // .
    // X
    // X
    public static boolean alignementVerticalConsecutifCombi(char[][]tab, int ligne, int colonne, boolean[][] JVertical, char Symbole){
        boolean alignementVerticalCombi=false;
            if(ligne -2>=0 && ligne +2< tab.length){
                if (!JVertical[ligne-2][colonne] && !JVertical[ligne-1][colonne] && !JVertical[ligne][colonne] && !JVertical[ligne+1][colonne] && !JVertical[ligne+2][colonne] && tab[ligne-1][colonne] ==Symbole && tab[ligne-2][colonne] == Symbole && tab[ligne+1][colonne] == Symbole && tab[ligne+2][colonne] == Symbole){
                    alignementVerticalCombi = true;
                    for (int i=0; i<=2 ; i++) {
                        JVertical[ligne+i][colonne]=true;
                    }
                    for (int i=0; i<=2; i++) {
                        JVertical[ligne-i][colonne]=true;
                    }
                }
            }
            else if (ligne+3<tab.length && ligne-1>=0) {
                if(tab[ligne-1][colonne] ==Symbole && tab[ligne+1][colonne] == Symbole && tab[ligne+2][colonne] == Symbole && tab[ligne+3][colonne] == Symbole && !JVertical[ligne-1][colonne] && !JVertical[ligne][colonne] &&
                        !JVertical[ligne+1][colonne] && !JVertical[ligne+2][colonne] && !JVertical[ligne+3][colonne]){
                    alignementVerticalCombi = true;
                    for (int i=0; i<=3 ; i++) {
                        JVertical[ligne+i][colonne]=true;
                    }
                    for (int i=0; i<=1; i++) {
                        JVertical[ligne-i][colonne]=true;
                    }
                }
            }
            else if (ligne-3>=0 && ligne+1<tab.length ){
                if(tab[ligne-3][colonne]== Symbole && tab[ligne-2][colonne] == Symbole && tab[ligne-1][colonne] == Symbole && tab[ligne+1][colonne] == Symbole && !JVertical[ligne+1][colonne] && !JVertical[ligne][colonne] &&
                        !JVertical[ligne-1][colonne] && !JVertical[ligne-2][colonne] && !JVertical[ligne-3][colonne]){
                    alignementVerticalCombi= true;
                    for (int i=0; i<=3 ; i++) {
                        JVertical[ligne-i][colonne]=true;
                    }
                    for (int i=0; i<=1; i++) {
                        JVertical[ligne+i][colonne]=true;
                    }
                }
            }
        return alignementVerticalCombi;
    }
    //bas droite -> haut gauche
    //X
    //  X
    //    X
    //      X
    //        X
    public static boolean alignementDiagoGauchePresent(char[][]tab, int ligne, int colonne, boolean[][] JDiagonaleGauche, char Symbole) {
        int compteurGauchehaut = 0;
        int constGaucheHaut =1;
        boolean alignementDiagoGauche = false;
        boolean trouve = true;
            if (ligne-4>=0 && colonne-4>=0) {
                for (int i = 0; i <=3 && trouve && !JDiagonaleGauche[ligne-constGaucheHaut][colonne-constGaucheHaut]; i++) {
                    if (tab[ligne-constGaucheHaut][colonne-constGaucheHaut] == Symbole) {
                        compteurGauchehaut++;
                        constGaucheHaut++;
                    }
                    else{
                        trouve =false;
                    }
                }
            }
            if(compteurGauchehaut==4){
                alignementDiagoGauche=true;
                for (int i=0; i<=4; i++) {
                    JDiagonaleGauche[ligne-i][colonne-i]=true;
                }
            }
        return alignementDiagoGauche;
    }
    //haut gauche -> bas droit
    //X
    //  X
    //    X
    //      X
    //        X
    //jsp pk ca bug
    public static boolean alignementDiagoGaucheInversePresent(char[][]tab, int ligne, int colonne, boolean[][] JDiagonaleGauche, char Symbole) {
        int compteurGaucheBas = 0;
        int constGaucheBas = 1;
        boolean alignementDiagoGaucheInverse = false;
        boolean trouve = true;
        if (ligne + 4 < tab.length && colonne + 4 < tab[ligne].length) {
            for (int i = 0; i <= 3 && trouve && !JDiagonaleGauche[ligne + constGaucheBas][colonne + constGaucheBas]; i++) {
                if (ligne + i < tab.length && colonne + i < tab.length) {
                    if (tab[ligne + constGaucheBas][colonne + constGaucheBas] == Symbole) {
                        compteurGaucheBas++;
                        constGaucheBas++;
                    } else {
                        trouve = false;
                    }
                }
            }
            if (compteurGaucheBas == 4) {
                alignementDiagoGaucheInverse = true;
                for (int i = 0; i <= 4; i++) {
                    JDiagonaleGauche[ligne + i][colonne + i] = true;
                }
            }
        }
        return alignementDiagoGaucheInverse;
    }
    //bas gauche -> haut droit
    //haut gauche -> bas droit
    //X
    //  X
    //    X
    //      X
    //        X
    public static boolean alignementDiagoGaucheInverseCombiPresent (char[][]tab, int ligne, int colonne, boolean[][] JDiagonaleGauche, char Symbole) {
        boolean alignementGaucheCombi=false;
            if(ligne-2>=0 && colonne+2 < tab[ligne].length && ligne+2< tab.length && colonne-2>=0){
                if(tab[ligne-2][colonne-2]==Symbole && tab[ligne-1][colonne-1]==Symbole && tab[ligne+1][colonne+1]==Symbole && tab[ligne+2][colonne+2]==Symbole && !JDiagonaleGauche[ligne-2][colonne-2] &&
                        !JDiagonaleGauche[ligne-1][colonne-1] && !JDiagonaleGauche[ligne][colonne] && !JDiagonaleGauche[ligne+1][colonne+1] && !JDiagonaleGauche[ligne+2][colonne+2]){
                    alignementGaucheCombi = true;
                    for (int i=0; i<=2; i++) {
                        JDiagonaleGauche[ligne-i][colonne-i]=true;
                    }
                    for (int i=0; i<=2; i++) {
                        JDiagonaleGauche[ligne+i][colonne+i]=true;
                    }
                }
            }
            else if (ligne-3>=0 && colonne+1< tab[ligne].length && ligne+1< tab.length && colonne-3>=0){
                if(tab[ligne-3][colonne-3]==Symbole && tab[ligne-2][colonne-2]==Symbole && tab[ligne-1][colonne-1]==Symbole && tab[ligne+1][colonne+1]==Symbole && !JDiagonaleGauche[ligne+1][colonne+1] &&
                        !JDiagonaleGauche[ligne][colonne] && !JDiagonaleGauche[ligne-1][colonne-1] && !JDiagonaleGauche[ligne-2][colonne-2] && !JDiagonaleGauche[ligne-3][colonne-3]){
                    alignementGaucheCombi= true;
                    for (int i=0; i<=3; i++) {
                        JDiagonaleGauche[ligne-i][colonne-i]=true;
                    }
                    for (int i=0; i<=1; i++) {
                        JDiagonaleGauche[ligne+i][colonne+i]=true;
                    }
                }
            }
            else if(ligne-1>=0 && colonne+3< tab[ligne].length && ligne+3< tab.length && colonne-1>=0) {
                if (tab[ligne - 1][colonne - 1] == Symbole && tab[ligne + 1][colonne + 1] == Symbole && tab[ligne + 2][colonne + 2] == Symbole && tab[ligne + 3][colonne + 3] == Symbole && !JDiagonaleGauche[ligne - 1][colonne - 1] &&
                        !JDiagonaleGauche[ligne][colonne] && !JDiagonaleGauche[ligne + 1][colonne + 1] && !JDiagonaleGauche[ligne + 2][colonne + 2] && !JDiagonaleGauche[ligne + 3][colonne + 3]) {
                    alignementGaucheCombi = true;
                    for (int i = 0; i <= 1; i++) {
                        JDiagonaleGauche[ligne - i][colonne - i] = true;
                    }
                    for (int i = 0; i <= 3; i++) {
                        JDiagonaleGauche[ligne + i][colonne + i] = true;
                    }
                }
            }
        return alignementGaucheCombi;
    }
    //bas gauche -> haut droite
    //        X
    //      X
    //    X
    //  X
    //X
    public static boolean alignementDiagoDroitePresent(char[][]tab, int ligne , int colonne , boolean[][] JDiagonaleDroite, char Symbole) {
        int compteurDroiteHaut = 0;
        int constDroiteHaut =1;
        boolean alignementDiagoDroite = false;
        boolean trouve = true;
            if (ligne-4>=0 && colonne+4 < tab[ligne].length) {
                for (int i = 0; i <=3 && trouve && !JDiagonaleDroite[ligne-constDroiteHaut][colonne+constDroiteHaut]; i++) {
                    if (tab[ligne -constDroiteHaut][colonne +constDroiteHaut] == Symbole) {
                        compteurDroiteHaut++;
                        constDroiteHaut++;
                    } else {
                        trouve = false;
                    }
                }
                if (compteurDroiteHaut == 4) {
                    alignementDiagoDroite = true;
                    for (int i=0; i<=4; i++) {
                        JDiagonaleDroite[ligne-i][colonne+i]=true;
                    }
                }
            }
        return alignementDiagoDroite;
    }
    // haut droit -> bas gauche
    //        X
    //      X
    //    X
    //  X
    //X
    public static boolean alignementDiagoDroiteInversePresent(char[][]tab, int ligne, int colonne, boolean[][] JDiagonaleDroite, char Symbole) {
        int compteurGaucheBas = 0;
        int constGauchebas =1;
        boolean alignementDiagoGaucheInverse = false;
        boolean trouve = true;
            if (ligne+4 < tab.length && colonne-4 >=0) {
                for (int i = 0; i <=3 && trouve && !JDiagonaleDroite[ligne+constGauchebas][colonne-constGauchebas]; i++) {
                    if (tab[ligne+constGauchebas][colonne-constGauchebas] == Symbole) {
                        compteurGaucheBas++;
                        constGauchebas++;
                    }
                    else{
                        trouve=false;
                    }
                }
                if(compteurGaucheBas==4){
                    alignementDiagoGaucheInverse=true;
                    for (int i=0; i<=4; i++) {
                        JDiagonaleDroite[ligne+i][colonne-i]=true;
                    }
                }
            }
        return alignementDiagoGaucheInverse;
    }
    //     X
    //    X
    //   X
    //  .
    // X
    public static boolean alignementDiagoDroiteCombiPresent(char[][]tab, int ligne, int colonne, boolean[][] JDiagonaleDroite, char Symbole) {
        boolean alignementDroiteCombi = false;
            if (ligne - 2 >= 0 && colonne + 2 < tab[ligne].length && ligne + 2 < tab.length && colonne - 2 >= 0) {
                if (tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && !JDiagonaleDroite[ligne - 2][colonne + 2] &&
                        !JDiagonaleDroite[ligne - 1][colonne + 1] && !JDiagonaleDroite[ligne][colonne] && !JDiagonaleDroite[ligne + 1][colonne - 1] && !JDiagonaleDroite[ligne + 2][colonne - 2]) {
                    alignementDroiteCombi = true;
                    for (int i = 0; i <= 2; i++) {
                        JDiagonaleDroite[ligne - i][colonne + i] = true;
                    }
                    for (int i = 0; i <= 2; i++) {
                        JDiagonaleDroite[ligne + i][colonne - i] = true;
                    }
                }
            } else if (ligne - 1 >= 0 && ligne + 3 < tab.length && colonne + 1 < tab[ligne].length && colonne - 3 >= 0) {
                if (tab[ligne - 1][colonne + 1] == Symbole && tab[ligne + 1][colonne - 1] == Symbole && tab[ligne + 2][colonne - 2] == Symbole && tab[ligne + 3][colonne - 3] == Symbole && !JDiagonaleDroite[ligne-1][colonne+1] &&
                        !JDiagonaleDroite[ligne][colonne] && !JDiagonaleDroite[ligne+1][colonne-1] && !JDiagonaleDroite[ligne+2][colonne-2] && !JDiagonaleDroite[ligne+3][colonne-3]) {
                    alignementDroiteCombi = true;
                    for (int i=0; i<=1; i++) {
                        JDiagonaleDroite[ligne-i][colonne+i]=true;
                    }
                    for (int i=0; i<=3; i++) {
                        JDiagonaleDroite[ligne+i][colonne-i]=true;
                    }
                }
            } else if (ligne + 1 < tab.length && ligne - 3 >= 0 && colonne - 1 >= 0 && colonne + 3 < tab[ligne].length) {
                if (tab[ligne + 1][colonne - 1] == Symbole && tab[ligne - 1][colonne + 1] == Symbole && tab[ligne - 2][colonne + 2] == Symbole && tab[ligne - 3][colonne + 3] == Symbole && !JDiagonaleDroite[ligne+1][colonne-1] &&
                        !JDiagonaleDroite[ligne][colonne] && !JDiagonaleDroite[ligne-1][colonne+1] && !JDiagonaleDroite[ligne-2][colonne+2] && !JDiagonaleDroite[ligne-3][colonne+3]) {
                    alignementDroiteCombi = true;
                    for (int i=0; i<=1; i++) {
                        JDiagonaleDroite[ligne+i][colonne-i]=true;
                    }
                    for (int i=0; i<=3; i++) {
                        JDiagonaleDroite[ligne-i][colonne+i]=true;
                    }
                }
            }
        return alignementDroiteCombi;
    }
}

