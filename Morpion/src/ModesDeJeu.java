import java.util.Scanner;

// Contient toutes les différentes modes de jeu que l'utilisateur peut choisir
public class ModesDeJeu {

    public static void JoueurVsJoueur (char[][] Plateau, boolean ArreterPartie, int CompteurCasesLibres, int tailleLignePlateau, int tailleColonnePlateau, int tour, int PointJ1, int PointJ2, boolean[][] J1Horizontal, boolean[][] J2Horizontal, boolean[][] J1Vertical,
                                       boolean[][] J2Vertical, boolean[][] J1DiagonaleGauche, boolean[][] J2DiagonaleGauche, boolean[][] J1DiagonaleDroite, boolean[][] J2DiagonaleDroite) {
        Scanner sc = new Scanner(System.in);
        char SymboleX='X';
        char SymboleO='O';
        while (!ArreterPartie && CompteurCasesLibres != 0) {
            int Joueur = 1;
            int VeutQuitter = 0;
            while (Joueur < 3 && CompteurCasesLibres != 0) {
                int LigneJoueur = 0;
                int ColonneJoueur = 0;
                ModifPlateau.afficherPlateau(Plateau, tailleLignePlateau, tailleColonnePlateau);
                System.out.println();


                System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                LigneJoueur = sc.nextInt();
                if (LigneJoueur == -1) {
                    VeutQuitter++;
                } else if (VeutQuitter == 0) {
                    System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                    ColonneJoueur = sc.nextInt();

                    while (!ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau) || ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur)) {
                        System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                        LigneJoueur = sc.nextInt();
                        System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                        ColonneJoueur = sc.nextInt();
                    }
                }
                if (VeutQuitter == 1) {
                    ArreterPartie = true;
                }

                if (VeutQuitter == 0 && ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau) && !ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur)) {
                    ModifPlateau.saisirUnPion(Plateau, LigneJoueur, ColonneJoueur, Joueur);
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J1Horizontal, SymboleX)) {
                            PointJ1++;
                        }
                        if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J1Vertical, SymboleX)) {
                            PointJ1++;
                        }
                        if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleGauche, SymboleX)) {
                            PointJ1++;
                        }
                        if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleDroite, SymboleX)) {
                            PointJ1++;
                        }
                    }
                    if (Joueur == 2) {
                        if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J2Horizontal, SymboleO)) {
                            PointJ2++;
                        }
                        if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J2Vertical, SymboleO)) {
                            PointJ2++;
                        }
                        if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J2DiagonaleGauche, SymboleO)) {
                            PointJ2++;
                        }
                        if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J2DiagonaleDroite, SymboleO)) {
                            PointJ2++;
                        }
                    }
                }
                System.out.println();
                System.out.println("Joueur 1 : " + PointJ1 + " points");
                System.out.println("Joueur 2 : " + PointJ2 + " points");
                System.out.println();
                CompteurCasesLibres--;
                Joueur++;
            }
            tour++;
            if (ArreterPartie && VeutQuitter == 1) {
                System.out.println();
                System.out.println("Un joueur a décidé d'arrêter la partie : Partie abandonnée");
                System.out.println("Merci d'avoir joué !");
            } else if (ArreterPartie && VeutQuitter == 2) {
                System.out.println();
                System.out.println("Les deux joueurs ont décidé d'arrêter la partie : Joueur 1 a " + PointJ1 + " points et " + " Joueur 2 a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("Le joueur 2 gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println("Merci d'avoir joué !");
            } else if (CompteurCasesLibres == 0) {
                System.out.println();
                System.out.println("La partie est terminée : Joueur 1 a " + PointJ1 + " points et " + " Joueur 2 a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("Le joueur 2 gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println();
                System.out.println("Merci d'avoir joué !");
            }
        }
            sc.close();
    }


    public static void JoueurVsIaFacile (char[][] Plateau, boolean ArreterPartie, int CompteurCasesLibres, int tailleLignePlateau, int tailleColonnePlateau, int tour, int PointJ1, int PointJ2, boolean[][] J1Horizontal, boolean[][] J2Horizontal, boolean[][] J1Vertical,
                                         boolean[][] J2Vertical, boolean[][] J1DiagonaleGauche, boolean[][] J2DiagonaleGauche, boolean[][] J1DiagonaleDroite, boolean[][] J2DiagonaleDroite) {
        Scanner sc = new Scanner(System.in);
        char SymboleX='X';
        char SymboleO='O';
        while (!ArreterPartie && CompteurCasesLibres != 0) {
            int Joueur = 1;
            int VeutQuitter = 0;
            while (Joueur < 3 && CompteurCasesLibres != 0) {
                int LigneJoueur = 0;
                int ColonneJoueur = 0;

                ModifPlateau.afficherPlateau(Plateau, tailleLignePlateau, tailleColonnePlateau);
                System.out.println();

                if (Joueur==1) {
                    System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                    LigneJoueur = sc.nextInt();
                    if (LigneJoueur == -1) {
                        VeutQuitter++;
                    }
                    else if (VeutQuitter == 0) {
                        System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                        ColonneJoueur = sc.nextInt();

                        while (!ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau) || ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur)) {
                            System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                            LigneJoueur = sc.nextInt();
                            System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                            ColonneJoueur = sc.nextInt();
                        }
                    }
                }
                if (Joueur==2) {
                    do {
                        LigneJoueur = Bot.mettreUnPionNiveauFacile(tailleLignePlateau);
                        ColonneJoueur = Bot.mettreUnPionNiveauFacile(tailleColonnePlateau);
                    } while (ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur));
                }

                if (VeutQuitter == 1) {
                    ArreterPartie = true;
                }

                if (VeutQuitter == 0 && ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau) && !ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur)) {
                    ModifPlateau.saisirUnPion(Plateau, LigneJoueur, ColonneJoueur, Joueur);
                        if (Joueur == 1) {
                            if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J1Horizontal, SymboleX)) {
                                PointJ1++;
                            }
                        } else {
                            if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J2Horizontal, SymboleO)) {
                                PointJ2++;
                            }
                        }
                        if (Joueur == 1) {
                            if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J1Vertical, SymboleX)) {
                                PointJ1++;
                            }
                        } else {
                            if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J2Vertical, SymboleO)) {
                                PointJ2++;
                            }
                        }
                        if (Joueur == 1) {
                            if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleGauche, SymboleX)) {
                                PointJ1++;
                            }
                        } else {
                            if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J2DiagonaleGauche, SymboleO)) {
                                PointJ2++;
                            }
                        }
                        if (Joueur == 1) {
                            if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleDroite, SymboleX)) {
                                PointJ1++;
                            }
                        } else {
                            if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J2DiagonaleDroite, SymboleX)) {
                                PointJ2++;
                            }
                        }
                    System.out.println();
                    System.out.println("Joueur 1 : " + PointJ1 + " points");
                    System.out.println("IA Facile : " + PointJ2 + " points");
                    System.out.println();
                    CompteurCasesLibres--;
                }
                Joueur++;
            }
            tour++;
            if (ArreterPartie && VeutQuitter == 1) {
                System.out.println();
                System.out.println("Les deux joueurs ont décidé d'arrêter la partie : Joueur 1 a " + PointJ1 + " points et " + " IA Facile a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("Le IA Facile gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println("Merci d'avoir joué !");
            }
            else if (CompteurCasesLibres == 0) {
                System.out.println();
                System.out.println("La partie est terminée : Joueur 1 a " + PointJ1 + " points et " + " Joueur 2 a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("Le IA Facile gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println();
                System.out.println("Merci d'avoir joué !");
            }
        }
        sc.close();
    }

    public static void JoueurVsIaMoyen(char[][] Plateau, boolean ArreterPartie, int CompteurCasesLibres, int tailleLignePlateau, int tailleColonnePlateau
            , int tour, int[][] CoupJoueur, int nbCoupJoueur, int [][] coupbot, int nbCoupBot, boolean[][] J1Horizontal, boolean[][] J2Horizontal,
                                       boolean[][] J1Vertical, boolean[][] J2Vertical, boolean[][] J1DiagonaleGauche, boolean[][] J2DiagonaleGauche, boolean[][] J1DiagonaleDroite,
                                       boolean[][] J2DiagonaleDroite, int PointJ1, int PointJ2) {
        Scanner sc = new Scanner(System.in);
        while (!ArreterPartie && CompteurCasesLibres != 0) {
            int Joueur = 1;
            int VeutQuitter = 0;
            char SymboleX='X';
            char SymboleO='O';
            boolean RentreDansIf=false;
            int LigneJoueurBot=-1;
            int ColonneJoueurBot=-1;
            int LigneJoueurJoueur;
            int ColonneJoueurJoueur;
            while (Joueur < 3 && CompteurCasesLibres != 0) {
                int LigneJoueur = 0;
                int ColonneJoueur = 0;
                if (Joueur==1) {
                    ModifPlateau.afficherPlateau(Plateau, tailleLignePlateau, tailleColonnePlateau);
                    System.out.println();
                }

                if (Joueur==1) {
                    System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                    LigneJoueur = sc.nextInt();
                    if (LigneJoueur == -1) {
                        VeutQuitter++;
                    }
                    else if (VeutQuitter == 0) {
                        System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                        ColonneJoueur = sc.nextInt();

                        while (!ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau) || ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur)) {
                            System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                            LigneJoueur = sc.nextInt();
                            System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                            ColonneJoueur = sc.nextInt();
                        }
                    }
                }
                if (Joueur==2) {
                    int indiceJoueur = Bot.retourneIndice(CoupJoueur);
                    LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                    ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                    if (nbCoupBot>0) {
                        int indiceBot = Bot.retourneIndice(coupbot);
                        LigneJoueurBot = coupbot[0][indiceBot];
                        ColonneJoueurBot = coupbot[1][indiceBot];
                    }
                    // Commencer par bloquer le joueur --> Vérifier si y'a un alignement de 4 pions
                    if (Bot.bloqueAlignementHoriCombi(Plateau, CoupJoueur, nbCoupBot, J1Horizontal, SymboleX, coupbot)){
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && Bot.bloqueAlignementVertiConbi(Plateau, CoupJoueur, nbCoupBot, J1Vertical, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && Bot.bloqueAlignementDiagoDroiteCombi(Plateau, CoupJoueur, nbCoupBot, J1DiagonaleDroite, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && Bot.bloqueAlignementDiagoGaucheCombi(Plateau, CoupJoueur, nbCoupBot, J1DiagonaleGauche, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA place un pion si l'IA a un alignement de 4 pions
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementHoriCombi(Plateau, coupbot, nbCoupBot, J2Horizontal, SymboleO, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementVertiConbi(Plateau, coupbot, nbCoupBot, J2Vertical, SymboleO, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementDiagoDroiteCombi(Plateau, coupbot, nbCoupBot, J2DiagonaleDroite, SymboleO, coupbot)){
                        RentreDansIf=true;
                        nbCoupBot++;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementDiagoGaucheCombi(Plateau, coupbot, nbCoupBot, J2DiagonaleGauche, SymboleO,coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA Bloque le joueur s'il a un alignement de 3 pions
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement4Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // IA Place un pion si il a un alignement de 3 pions
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement4Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Horizontal, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Vertical, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleGauche, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleDroite, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA Bloque le joueur s'il a un alignement de 2 pions
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement3Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // IA Place un pion si il a un alignement de 2 pions
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement3Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Horizontal, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Vertical, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleGauche, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleDroite, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA place un pion autour du pion du joueur
                    if(!RentreDansIf && Bot.mettreUnPionAutour(Plateau, CoupJoueur,nbCoupBot, SymboleO,coupbot, nbCoupJoueur)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA place un pion autour du pion de l'IA
                    if(!RentreDansIf && nbCoupBot>0 && Bot.mettreUnPionAutour(Plateau, coupbot, nbCoupBot, SymboleO,coupbot, nbCoupBot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA
                    else if (!RentreDansIf){
                        do {
                            LigneJoueur = Bot.mettreUnPionNiveauFacile(tailleLignePlateau);
                            ColonneJoueur = Bot.mettreUnPionNiveauFacile(tailleColonnePlateau);
                        } while (ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur));
                        ModifPlateau.saisirUnPion(Plateau, LigneJoueur, ColonneJoueur, Joueur);
                        coupbot[0][nbCoupBot] = LigneJoueur;
                        coupbot[1][nbCoupBot] = ColonneJoueur;
                        nbCoupBot++;
                    }
                }

                if (VeutQuitter == 1) {
                    ArreterPartie = true;
                }
                if (Joueur==2) {
                    int indice = Bot.retourneIndice(coupbot);
                    LigneJoueur = coupbot[0][indice];
                    ColonneJoueur = coupbot[1][indice];
                }

                if (VeutQuitter == 0 && ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau)) {
                    if (Joueur==1) {
                        ModifPlateau.saisirUnPion(Plateau, LigneJoueur, ColonneJoueur, Joueur);
                        Bot.enregistrementCoupJoueur(CoupJoueur, LigneJoueur, ColonneJoueur, nbCoupJoueur);
                        nbCoupJoueur++;
                    }
                    if (Joueur == 1) {
                            if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J1Horizontal, SymboleX)) {
                                PointJ1++;
                            }
                    }
                    else if (Joueur==2){
                        if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J2Horizontal, SymboleO)) {
                            PointJ2++;
                        }
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J1Vertical, SymboleX)) {
                            PointJ1++;
                        }
                    } else if (Joueur==2){
                        if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J2Vertical, SymboleO)) {
                            PointJ2++;
                        }
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleGauche, SymboleX)) {
                            PointJ1++;
                        }
                    } else if (Joueur==2){
                        if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J2DiagonaleGauche, SymboleO)) {
                            PointJ2++;
                        }
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleDroite, SymboleX)) {
                            PointJ1++;
                        }
                    } else if (Joueur==2){
                        if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleDroite, SymboleX)) {
                            PointJ2++;
                        }
                    }
                    CompteurCasesLibres--;
                }
                Joueur++;
            }
            System.out.println();
            System.out.println("Joueur 1 : " + PointJ1 + " points");
            System.out.println("IA Moyen : " + PointJ2 + " points");
            System.out.println();
            tour++;
            if (ArreterPartie && VeutQuitter == 1) {
                System.out.println();
                System.out.println("Le joueur a décidé d'arrêter la partie : Joueur a " + PointJ1 + " points et " + " l'IA Moyen a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("L'IA Moyen gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println("Merci d'avoir joué !");
            }
            else if (CompteurCasesLibres == 0) {
                System.out.println();
                System.out.println("La partie est terminée : Joueur a " + PointJ1 + " points et " + " l'IA Moyen a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("l'IA Moyen gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println();
                System.out.println("Merci d'avoir joué !");
            }
        }
        sc.close();
    }

    public static void JoueurVsIaDifficile(char[][] Plateau, boolean ArreterPartie, int CompteurCasesLibres, int tailleLignePlateau, int tailleColonnePlateau
            , int tour, int[][] CoupJoueur, int nbCoupJoueur, int [][] coupbot, int nbCoupBot, boolean[][] J1Horizontal, boolean[][] J2Horizontal,
                                       boolean[][] J1Vertical, boolean[][] J2Vertical, boolean[][] J1DiagonaleGauche, boolean[][] J2DiagonaleGauche, boolean[][] J1DiagonaleDroite,
                                       boolean[][] J2DiagonaleDroite, int PointJ1, int PointJ2) {
        Scanner sc = new Scanner(System.in);
        while (!ArreterPartie && CompteurCasesLibres != 0) {
            int Joueur = 1;
            int VeutQuitter = 0;
            char SymboleX = 'X';
            char SymboleO = 'O';
            boolean RentreDansIf = false;
            int LigneJoueurJoueur;
            int ColonneJoueurJoueur;
            int LigneJoueurBot=-1;
            int ColonneJoueurBot=-1;
            int VariableIndice;

            ModifPlateau.afficherPlateau(Plateau, tailleLignePlateau, tailleColonnePlateau);
            System.out.println();

            while (Joueur < 3 && CompteurCasesLibres != 0) {
                int LigneJoueur = 0;
                int ColonneJoueur = 0;

                if (Joueur == 1) {
                    System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                    LigneJoueur = sc.nextInt();
                    if (LigneJoueur == -1) {
                        VeutQuitter++;
                    } else if (VeutQuitter == 0) {
                        System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                        ColonneJoueur = sc.nextInt();

                        while (!ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau) || ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur)) {
                            System.out.print("Tour " + tour + ", Joueur " + (Joueur) + " : Entrez -1 pour quitter ou choisissez la ligne où mettre la pièce : ");
                            LigneJoueur = sc.nextInt();
                            System.out.print("Tour " + tour + ",Joueur " + (Joueur) + " : Choisissez la colonne où mettre la pièce : ");
                            ColonneJoueur = sc.nextInt();
                        }
                    }
                } // C'est le tout premier
                if (Joueur==2) {
                    int indiceJoueur = Bot.retourneIndice(CoupJoueur);
                    LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                    ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                    VariableIndice=indiceJoueur;
                    if (nbCoupBot>0) {
                        int indiceBot = Bot.retourneIndice(coupbot);
                        LigneJoueurBot = coupbot[0][indiceBot];
                        ColonneJoueurBot = coupbot[1][indiceBot];
                    }
                    //  Bloquer le joueur --> Vérifier si y'a un alignement de 4 pions
                    if (Bot.bloqueAlignementHoriCombi(Plateau, CoupJoueur, nbCoupBot, J1Horizontal, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && Bot.bloqueAlignementVertiConbi(Plateau, CoupJoueur, nbCoupBot, J1Vertical, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && Bot.bloqueAlignementDiagoDroiteCombi(Plateau, CoupJoueur, nbCoupBot, J1DiagonaleDroite, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && Bot.bloqueAlignementDiagoGaucheCombi(Plateau, CoupJoueur, nbCoupBot, J1DiagonaleGauche, SymboleX, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // IA place un pion si l'IA a un alignement de 4 pions
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementHoriCombi(Plateau, coupbot, nbCoupBot, J2Horizontal, SymboleO, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementVertiConbi(Plateau, coupbot, nbCoupBot, J2Vertical, SymboleO, coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementDiagoDroiteCombi(Plateau, coupbot, nbCoupBot, J2DiagonaleDroite, SymboleO, coupbot)){
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.bloqueAlignementDiagoGaucheCombi(Plateau, coupbot, nbCoupBot, J2DiagonaleGauche, SymboleO,coupbot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // Bloquer un alignement de 4 du joueur et faire un alignement 4 du bot

                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement4Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        if (Bot.checkAlignement4Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Horizontal, SymboleX)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.Blockalignement4Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche,SymboleO)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        if (Bot.checkAlignement4Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Vertical)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }


                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        if (Bot.checkAlignement4DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }


                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        if (Bot.checkAlignement4DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleDroite)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    // Bloquer un alignement de 3 du joueur et faire un alignement de 4 du bot
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement3Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        if (Bot.checkAlignement4Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Horizontal, SymboleO)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.Blockalignement3Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche, SymboleO)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }


                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        if (Bot.checkAlignement4Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Vertical)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        if (Bot.checkAlignement4DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        if (Bot.checkAlignement4DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleDroite)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement4DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    // Bloquer un alignement de 4 du joueur et faire un alignement de 3 du bot
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement4Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        if (Bot.checkAlignement3Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Horizontal, SymboleO)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.Blockalignement4Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche,SymboleO)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        if (Bot.checkAlignement3Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Vertical)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        if (Bot.checkAlignement3DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        if (Bot.checkAlignement3DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleDroite)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    // Bloquer un alignement de 3 du joueur et faire un alignement de 3 du bot
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement3Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        if (Bot.checkAlignement3Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Horizontal, SymboleO)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.Blockalignement3Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche, SymboleO)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        if (Bot.checkAlignement3Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2Vertical)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement3Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        if (Bot.checkAlignement3DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        if (Bot.checkAlignement3DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleDroite)) {
                            nbCoupBot++;
                            RentreDansIf=true;
                        }
                        else {
                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                            if (nbCoupJoueur>1) {
                                for (int i=0; VariableIndice+i>=0; i--) {
                                    LigneJoueurJoueur = CoupJoueur[0][VariableIndice+i];
                                    ColonneJoueurJoueur = CoupJoueur[1][VariableIndice+i];
                                    if (Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                                        if (Bot.checkAlignement3DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, J2DiagonaleGauche)) {
                                            nbCoupBot++;
                                            RentreDansIf=true;
                                        }
                                        else {
                                            Bot.SupprimerPionPlateau(Plateau, coupbot);
                                            Bot.SupprimerCoordoneesPionCoup(coupbot, nbCoupBot);
                                        }
                                    }
                                }
                                if (nbCoupBot>0) {
                                    int indiceBot = Bot.retourneIndice(coupbot);
                                    LigneJoueurBot = coupbot[0][indiceBot];
                                    ColonneJoueurBot = coupbot[1][indiceBot];
                                }
                                indiceJoueur = Bot.retourneIndice(CoupJoueur);
                                LigneJoueurJoueur = CoupJoueur[0][indiceJoueur];
                                ColonneJoueurJoueur = CoupJoueur[1][indiceJoueur];
                            }
                        }
                    }

                    // Faire un alignement de 4 du bot
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement4Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Horizontal, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Vertical, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleGauche, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleDroite, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // Bloquer un alignement de 4 du joueur
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement4Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, CoupJoueur, nbCoupJoueur, J1Horizontal, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, CoupJoueur, nbCoupJoueur, J1Vertical, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, CoupJoueur, nbCoupJoueur, J1DiagonaleGauche, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement4DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, CoupJoueur, nbCoupJoueur, J1DiagonaleDroite, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // Faire un alignement de 3 du bot
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement3Hori(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Horizontal, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3Verti(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2Vertical, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleGauche, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurBot, ColonneJoueurBot, coupbot, nbCoupBot, J2DiagonaleDroite, SymboleO)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // Bloquer un alignement de 3 du joueur
                    if (!RentreDansIf && nbCoupBot>0 && Bot.Blockalignement3Hori(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Horizontal, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3Verti(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1Vertical, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoGauche(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleGauche, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    if (!RentreDansIf && nbCoupBot>0 && Bot.blockAlignement3DiagoDroite(Plateau, LigneJoueurJoueur, ColonneJoueurJoueur, coupbot, nbCoupBot, J1DiagonaleDroite, SymboleX)) {
                        nbCoupBot++;
                        RentreDansIf=true;
                    }

                    // IA place un pion autour du pion du joueur
                    if(!RentreDansIf && Bot.mettreUnPionAutour(Plateau, CoupJoueur,nbCoupBot, SymboleO,coupbot, nbCoupJoueur)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA place un pion autour du pion de l'IA
                    if(!RentreDansIf && nbCoupBot>0 && Bot.mettreUnPionAutour(Plateau, coupbot, nbCoupBot, SymboleO,coupbot, nbCoupBot)){
                        nbCoupBot++;
                        RentreDansIf=true;
                    }
                    // IA met au pif
                    if (!RentreDansIf){
                        do {
                            LigneJoueur = Bot.mettreUnPionNiveauFacile(tailleLignePlateau);
                            ColonneJoueur = Bot.mettreUnPionNiveauFacile(tailleColonnePlateau);
                        } while (ModifPlateau.pionPresent(Plateau, LigneJoueur, ColonneJoueur));
                        ModifPlateau.saisirUnPion(Plateau, LigneJoueur, ColonneJoueur, Joueur);
                        coupbot[0][nbCoupBot] = LigneJoueur;
                        coupbot[1][nbCoupBot] = ColonneJoueur;
                    }
                }

                if (VeutQuitter == 1) {
                    ArreterPartie = true;
                }
                if (Joueur==2) {
                    int indice = Bot.retourneIndice(coupbot);
                    LigneJoueur = coupbot[0][indice];
                    ColonneJoueur = coupbot[1][indice];
                }

                if (VeutQuitter == 0 && ModifPlateau.pionvalide(Plateau, LigneJoueur, ColonneJoueur, tailleLignePlateau, tailleColonnePlateau)) {
                    if (Joueur==1) {
                        ModifPlateau.saisirUnPion(Plateau, LigneJoueur, ColonneJoueur, Joueur);
                        Bot.enregistrementCoupJoueur(CoupJoueur, LigneJoueur, ColonneJoueur, nbCoupJoueur);
                        nbCoupJoueur++;
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J1Horizontal, SymboleX)) {
                            PointJ1++;
                        }
                    }
                    else if (Joueur==2){ // En fait là ça disait qu'il y a un alignement diagonale de 3 du bot
                        if (ModifPlateau.pointGagneHorizontal(Plateau, LigneJoueur, ColonneJoueur, J2Horizontal, SymboleO)) {
                            PointJ2++;
                        }
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J1Vertical, SymboleX)) {
                            PointJ1++;
                        }
                    } else if (Joueur==2){
                        if (ModifPlateau.pointGagneVertical(Plateau, LigneJoueur, ColonneJoueur, J2Vertical, SymboleO)) {
                            PointJ2++;
                        }
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleGauche, SymboleX)) {
                            PointJ1++;
                        }
                    } else if (Joueur==2){
                        if (ModifPlateau.pointGagneDiagoGauche(Plateau, LigneJoueur, ColonneJoueur, J2DiagonaleGauche, SymboleO)) {
                            PointJ2++;
                        }
                    }
                    if (Joueur == 1) {
                        if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleDroite, SymboleX)) {
                            PointJ1++;
                        }
                    } else if (Joueur==2){
                        if (ModifPlateau.pointGagneDiagoDroite(Plateau, LigneJoueur, ColonneJoueur, J1DiagonaleDroite, SymboleX)) {
                            PointJ2++;
                        }
                    }
                    CompteurCasesLibres--;
                }
                Joueur++;
            }
            System.out.println();
            System.out.println("Joueur 1 : " + PointJ1 + " points");
            System.out.println("IA Difficile : " + PointJ2 + " points");
            System.out.println();
            tour++;

            if (Joueur == 2) {
                ModifPlateau.afficherPlateau(Plateau, tailleLignePlateau, tailleColonnePlateau);
                System.out.println();
            }

            if (ArreterPartie && VeutQuitter == 1) {
                System.out.println();
                System.out.println("Le joueur a décidé d'arrêter la partie : Joueur a " + PointJ1 + " points et " + " l'IA Difficile a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("L'IA Difficile gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println("Merci d'avoir joué !");
            }
            else if (CompteurCasesLibres == 0) {
                System.out.println();
                System.out.println("La partie est terminée : Joueur a " + PointJ1 + " points et " + " l'IA Moyen a " + PointJ2 + " points");
                if (PointJ1 > PointJ2) {
                    System.out.println("Le joueur 1 gagne la partie!");
                } else {
                    if (PointJ1 < PointJ2) {
                        System.out.println("l'IA Difficile gagne la partie!");
                    } else {
                        System.out.println("Egalité!");
                    }
                }
                System.out.println();
                System.out.println("Merci d'avoir joué !");
            }
        }
        sc.close();
    }
}

