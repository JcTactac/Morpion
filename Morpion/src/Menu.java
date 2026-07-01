import java.util.Scanner;
public class Menu {

    // Contient toute la structure du menu
    public static void PreparationJeu() {
        Scanner sc = new Scanner(System.in);
        int tailleLignePlateau = 0;
        int tailleColonnePlateau = 0;
        int choix;
        int PointJ1 = 0;
        int PointJ2 = 0;
        int tour = 1;


        String menu = "1. Partie VS Entre 2 Joueurs \n2. Partie VS IA Facile \n3. Partie VS IA Moyen \n4. Partie VS IA Difficile";

        System.out.println("Bienvenue au jeu de morpion taille variable ! Choisissez le mode de jeu parmi les suivants : ");
        System.out.println(menu);

        do {
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
        } while (choix < 1 || choix > 4);


            do {
                System.out.print("Entrez le nombre de lignes souhaité pour le plateau : ");
                tailleLignePlateau = sc.nextInt();
                System.out.print("Entrez le nombre de colonnes pour le plateau : ");
                tailleColonnePlateau = sc.nextInt();
                System.out.println();
            } while (tailleColonnePlateau < 5 || tailleLignePlateau < 5);


        char[][] Plateau = new char[tailleLignePlateau][tailleColonnePlateau];

        boolean[][] J1Horizontal = new boolean[tailleLignePlateau][tailleColonnePlateau];
        boolean[][] J1Vertical = new boolean[tailleLignePlateau][tailleColonnePlateau];
        boolean[][] J1DiagonaleGauche = new boolean[tailleLignePlateau][tailleColonnePlateau];
        boolean[][] J1DiagonaleDroite = new boolean[tailleLignePlateau][tailleColonnePlateau];

        boolean[][] J2Horizontal = new boolean[tailleLignePlateau][tailleColonnePlateau];
        boolean[][] J2Vertical = new boolean[tailleLignePlateau][tailleColonnePlateau];
        boolean[][] J2DiagonaleGauche = new boolean[tailleLignePlateau][tailleColonnePlateau];
        boolean[][] J2DiagonaleDroite = new boolean[tailleLignePlateau][tailleColonnePlateau];

        for (int i = 0; i < tailleLignePlateau; i++) {
            for (int j = 0; j < tailleColonnePlateau; j++) {
                Plateau[i][j] = '.';
            }
        }

        int[][] CoupJoueur;
        int[][] coupbot;
        CoupJoueur = new int[2][(tailleColonnePlateau * tailleLignePlateau)];
        coupbot = new int[2][(tailleColonnePlateau * tailleLignePlateau)];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < CoupJoueur[i].length; j++) {
                CoupJoueur[i][j] = -1;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < coupbot[i].length; j++) {
                coupbot[i][j] = -1;
            }
        }

        int nbCoupJoueur = 0;
        int nbCoupBot = 0;
        int CompteurCasesLibres = tailleLignePlateau * tailleColonnePlateau;
        boolean ArreterPartie = false;


        switch (choix) {

            // Joueur vs Joueur
            case 1:
                ModesDeJeu.JoueurVsJoueur(Plateau, ArreterPartie, CompteurCasesLibres, tailleLignePlateau, tailleColonnePlateau, tour, PointJ1, PointJ2, J1Horizontal, J2Horizontal, J1Vertical, J2Vertical, J1DiagonaleGauche,
                        J2DiagonaleGauche, J1DiagonaleDroite, J2DiagonaleDroite);
                break;

            // IA Facile
            case 2:
                ModesDeJeu.JoueurVsIaFacile(Plateau, ArreterPartie, CompteurCasesLibres, tailleLignePlateau, tailleColonnePlateau, tour, PointJ1, PointJ2, J1Horizontal, J2Horizontal, J1Vertical, J2Vertical, J1DiagonaleGauche,
                        J2DiagonaleGauche, J1DiagonaleDroite, J2DiagonaleDroite);
                break;

            // IA Moyen
            case 3:
                ModesDeJeu.JoueurVsIaMoyen(Plateau, ArreterPartie, CompteurCasesLibres, tailleLignePlateau, tailleColonnePlateau, tour,
                        CoupJoueur, nbCoupJoueur, coupbot, nbCoupBot, J1Horizontal, J2Horizontal, J1Vertical, J2Vertical,
                        J1DiagonaleGauche, J2DiagonaleGauche, J1DiagonaleDroite, J2DiagonaleDroite, PointJ1, PointJ2);
                break;

            // Quitter sans avoir joué
            case 4:
                ModesDeJeu.JoueurVsIaDifficile(Plateau, ArreterPartie, CompteurCasesLibres, tailleLignePlateau, tailleColonnePlateau, tour,
                        CoupJoueur, nbCoupJoueur, coupbot, nbCoupBot, J1Horizontal, J2Horizontal, J1Vertical, J2Vertical,
                        J1DiagonaleGauche, J2DiagonaleGauche, J1DiagonaleDroite, J2DiagonaleDroite, PointJ1, PointJ2);
                break;
        }
        sc.close();
    }
}