import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class AlignementTest {
    //-------------------------------ALIGNEMENT HORIZONTAL ----------------------------------------------------------------
    @Test
    public final void alignementHorizontalConsecutifTest(){
        char[][]tabligne0=
                {
                        {'X','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementHorizontalConsecutif(tabligne0, 0,0,J1Horizontal,'X'));

        char[][]tabligne3marchepas=
                {
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'X', 'X', 'X', 'X', '.'},
                        {'.', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutif(tabligne3marchepas,3,0,J1Horizontal, 'X'));
        char[][]tabligne3marchepas2=
                {
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'X','X','.','X', '.'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutif(tabligne3marchepas2,3,0, J1Horizontal, 'X'));
        char[][]tabligne3marchepas3=
                {
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutif(tabligne3marchepas3,3,1,J1Horizontal, 'X'));
        char[][]tabligne3marchepas4=
                {
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'O','X','X','O', 'X'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutif(tabligne3marchepas4,3,1,J1Horizontal, 'X'));
    }


    @Test
    public final void alignementHorizontalConsecutifInverse(){
        // joueur 1
        char[][]tabligne0=
                {
                        {'X','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementHorizontalConsecutifInverse(tabligne0,0,4,J1Horizontal, 'X'));
        char[][]tabligne0marchepas1=
                {
                        {'O','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifInverse(tabligne0marchepas1,0,4,J1Horizontal, 'X'));
        char[][]tabligne0marchepas2=
                {
                        {'X','X','O','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifInverse(tabligne0marchepas2,0,4,J1Horizontal, 'X'));
    }
    @Test
    public final void alignementHorizontalConsecutifCombiTest(){
        char[][]tabligne0=
                {
                        {'X','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementHorizontalConsecutifCombi(tabligne0,0,2,J1Horizontal, 'X'));
        char[][]tabligne0marchepas=
                {
                        {'X','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifCombi(tabligne0marchepas,0,4,J1Horizontal, 'X'));
        char[][]tabligne0marchepas2=
                {
                        {'O','X','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifCombi(tabligne0marchepas2,0,3,J1Horizontal, 'X'));
        char[][]tabligne0marchepas3=
                {
                        {'X','O','X','X','X'},
                        {'.', '.', '.', '.','.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifCombi(tabligne0marchepas3,0,3,J1Horizontal, 'X'));
    }

    //-------------------------------ALIGNEMENT VERTICAL ----------------------------------------------------------------
    @Test
    public final void alignementVertivalConsecutifTest(){
        char[][]tab0=
                {
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.','.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementVertivalConsecutif(tab0,4,0,J1Horizontal, 'X'));
        char[][]tab1=
                {
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementVertivalConsecutif(tab1,0,0,J1Horizontal, 'X'));
        char[][]tab3=
                {
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'O', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementVertivalConsecutif(tab3,0,0,J1Horizontal, 'X'));
    }
    @Test
    public final void alignementVerticalConsecutifInverseTest(){
        // joueur 1
        char[][]tab0=
                {
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.','.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementVerticalConsecutifInverse(tab0,0,0,J1Horizontal, 'X'));
        char[][]tab1=
                {
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementVerticalConsecutifInverse(tab1,4,0,J1Horizontal, 'X'));
        char[][]tab3=
                {
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.','.'},
                        {'X', '.', '.', '.', '.'},
                        {'O', '.', '.', '.', '.'},
                        {'X', '.', '.', '.','.'},
                };
        assertFalse(Alignements.alignementVerticalConsecutifInverse(tab3,0,0,J1Horizontal, 'X'));
    }
    @Test
    public final void alignementVerticalConsecutifCombiTest(){
        // joueur 1
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementVerticalConsecutifCombi(tab0, 1, 0,J1Horizontal, 'X'));
        char[][] tab3 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'O', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementVerticalConsecutifCombi(tab3, 0, 0,J1Horizontal, 'X'));
        char[][] tab4 =
                {
                        {'O', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'O', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'O', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementVerticalConsecutifCombi(tab4, 1, 0,J1Horizontal, 'X'));
    }
    //-------------------------------ALIGNEMENT DIAGO GAUCHE------------------------------------------------------------
    @Test
    public final void alignementDiagoGauchePresentTest() {
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementDiagoGauchePresent(tab0,4 , 4,J1Horizontal, 'X'));
        char[][] tab1 =
                {
                        {'O', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'X'},
                };
        assertFalse(Alignements.alignementDiagoGauchePresent(tab1,4 , 4,J1Horizontal, 'X'));
        char[][] tab2 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'O', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'X'},
                };
        assertFalse(Alignements.alignementDiagoGauchePresent(tab2,4 , 4, J1Horizontal, 'X'));
    }
    @Test
    public final void alignementDiagoGaucheInversePresentTest() {
        // joueur 1
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementDiagoGaucheInversePresent(tab0,0 , 0, J1Horizontal, 'X'));
        char[][] tab1 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'O'},
                };
        assertFalse(Alignements.alignementDiagoGaucheInversePresent(tab1,0 , 0,J1Horizontal, 'X'));
        char[][] tab2 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'O'},
                };
        assertFalse(Alignements.alignementDiagoGaucheInversePresent(tab2,0 , 0,J1Horizontal, 'X'));
    }
    @Test
    public final void alignementDiagoGaucheInverseCombiPresent() {
        // joueur 1
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementDiagoGaucheInverseCombiPresent(tab0,3 , 3,J1Horizontal, 'X'));
        char[][] tab3 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', '.', '.', 'O', '.'},
                        {'.', '.', '.', '.', 'X'},
                };
        assertFalse(Alignements.alignementDiagoGaucheInverseCombiPresent(tab3,2 , 2,J1Horizontal, 'X'));
    }
    //-------------------------------ALIGNEMENT DIAGO DROITE -----------------------------------------------------------
    @Test
    public final void alignementDiagoDroitePresentTest() {
        // joueur 1
        char[][] tab0 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertFalse(Alignements.alignementDiagoDroitePresent(tab0,0 , 4, J1Horizontal, 'X'));
        char[][] tab1 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        assertTrue(Alignements.alignementDiagoDroitePresent(tab1,4 , 0, J1Horizontal, 'X'));
        char[][] tab2 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'O', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementDiagoDroitePresent(tab2,4 , 0, J1Horizontal, 'X'));
    }
    @Test
    public final void alignementDiagoDroiteInversePresentTest() {
        // joueur 1
        char[][] tab0 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementDiagoDroiteInversePresent(tab0,0 , 4, J1Horizontal, 'X'));
        char[][] tab4 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementDiagoDroiteInversePresent(tab4,4 , 0, J1Horizontal, 'X'));
        char[][] tab1 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'O', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementDiagoDroiteInversePresent(tab1,0 , 4, J1Horizontal, 'X'));
        char[][] tab2 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'O', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementDiagoDroiteInversePresent(tab2,0 , 4,J1Horizontal, 'X'));
    }
    @Test
    public final void alignementDiagoDroiteCombiPresentTest() {
        // joueur 1
        char[][] tab0 =
                {
                        {'.', '.', '.', '.', 'X'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                        {false,false,false,false,false},
                };
        assertTrue(Alignements.alignementDiagoDroiteCombiPresent(tab0, 3, 1, J1Horizontal, 'X'));
        char[][] tab3 =
                {
                        {'.', '.', '.', '.', 'O'},
                        {'.', '.', '.', 'X', '.'},
                        {'.', '.', 'X', '.', '.'},
                        {'.', 'X', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        assertFalse(Alignements.alignementDiagoDroiteCombiPresent(tab3, 1, 3, J1Horizontal, 'X'));
    }
}