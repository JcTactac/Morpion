import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JeuTest {
    @Test
    public final void alignementHorizontalConsecutifTest() {
        char[][] tabligne0 =
                {
                        {'X', 'X', 'X', 'X', 'X','X'},
                };
        boolean[][] JHorizontal =
                {
                        {false, true, true, true, true, true},
                };
        boolean[][] JHorizontal2 =
                {
                        {false, false, false, false, false, false},
                };
        assertFalse(Alignements.alignementHorizontalConsecutif(tabligne0, 0, 0,  JHorizontal, 'X'));
        assertTrue(Alignements.alignementHorizontalConsecutif(tabligne0, 0, 0,  JHorizontal2, 'X'));
    }
    @Test
    public final void alignementHorizontalConsecutifInverse() {
        // joueur 1
        char[][] tabligne0 =
                {
                        {'X', 'X', 'X', 'X', 'X', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {true, true, true, true, true, false},
                };
        boolean[][] J2Horizontal =
                {
                        {false, false, false, false, false, false},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifInverse(tabligne0, 0, 5,J1Horizontal,'X'));
        assertTrue(Alignements.alignementHorizontalConsecutifInverse(tabligne0, 0, 5,J2Horizontal, 'X'));
    }
    @Test
    public final void alignementHorizontalConsecutifCombiTest() {
        char[][] tabligne0 =
                {
                        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {false, false, false, true, true, true, true, true},

                };
        boolean[][] J2Horizontal =
                {
                        {false, false, false, false, false, false, false, false},
                };
        assertFalse(Alignements.alignementHorizontalConsecutifCombi(tabligne0, 0, 2,J1Horizontal,'X'));
        assertTrue(Alignements.alignementHorizontalConsecutifCombi(tabligne0, 0, 2,J2Horizontal,'X'));
    }
    @Test
    public final void alignementVertivalConsecutifTest() {
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
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {false, false, false, false, false},
                };
        boolean[][] J2Horizontal =
                {
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                };
        assertFalse(Alignements.alignementVertivalConsecutif(tab0, 4, 0, J1Horizontal, 'X'));
        assertTrue(Alignements.alignementVertivalConsecutif(tab0, 4, 0, J2Horizontal,'X'));
    }
    @Test
    public final void alignementVerticalConsecutifInverseTest() {
        // joueur 1
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                };
        boolean[][] J2Horizontal =
                {
                        {false, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                };
        assertFalse(Alignements.alignementVerticalConsecutifInverse(tab0, 0, 0, J1Horizontal, 'X'));
        assertFalse(Alignements.alignementVerticalConsecutifInverse(tab0, 0, 0, J2Horizontal, 'X'));
    }
    @Test
    public final void alignementVerticalConsecutifCombiTest() {
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                        {'X', '.', '.', '.', '.'},
                };
        boolean[][] J1Horizontal =
                {
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                };
        boolean[][] J2Horizontal =
                {
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {false, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                        {true, false, false, false, false},
                };
        assertFalse(Alignements.alignementVerticalConsecutifCombi(tab0, 1, 0,J1Horizontal, 'X' ));
        assertTrue(Alignements.alignementVerticalConsecutifCombi(tab0, 1, 0,J2Horizontal, 'X'));
    }
    @Test
    public final void alignementDiagoGauchePresentTest() {
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', 'X', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', 'X', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', 'X', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', 'X', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {true, false, false, false, false, false, false, false, false},
                        {false, true, false, false, false, false, false, false, false},
                        {false, false, true, false, false, false, false, false, false},
                        {false, false, false, true, false, false, false, false, false},
                        {false, false, false, false, true, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                };
        boolean[][] J2Horizontal =
                {
                        {true, false, false, false, false, false, false, false, false},
                        {false, true, false, false, false, false, false, false, false},
                        {false, false, true, false, false, false, false, false, false},
                        {false, false, false, true, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                };
        assertFalse(Alignements.alignementDiagoGauchePresent(tab0, 8, 8, J1Horizontal, 'X'));
        assertTrue(Alignements.alignementDiagoGauchePresent(tab0, 8, 8, J2Horizontal, 'X'));
    }
    @Test
    public final void alignementDiagoGaucheInversePresentTest() {
        // joueur 1
        char[][] tab0 =
                {
                        {'X', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', 'X', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', 'X', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', 'X', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', 'X', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', 'X', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', 'X', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', 'X', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', 'X'},
                };
        boolean[][] J1Horizontal =
                {
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, true, false, false, false, false},
                        {false, false, false, false, false, true, false, false, false},
                        {false, false, false, false, false, false, true, false, false},
                        {false, false, false, false, false, false, false, true, false},
                        {false, false, false, false, false, false, false, false, true},
                };
        boolean[][] J2Horizontal =
                {
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, true, false, false, false},
                        {false, false, false, false, false, false, true, false, false},
                        {false, false, false, false, false, false, false, true, false},
                        {false, false, false, false, false, false, false, false, true},
                };
        assertFalse(Alignements.alignementDiagoGaucheInversePresent(tab0, 0, 0, J1Horizontal, 'X'));
        assertTrue(Alignements.alignementDiagoGaucheInversePresent(tab0, 0, 0, J2Horizontal, 'X'));

    }
}