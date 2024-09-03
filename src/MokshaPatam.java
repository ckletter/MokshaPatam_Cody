import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class MokshaPatam {

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        int currentSquare = 1;
        int numRolls = 0;
        // Calculate last square
        int lastSquare = boardsize * boardsize;
        ArrayList<Integer> finalRolls = new ArrayList<Integer>();
        tryLadder(lastSquare, ladders, snakes, currentSquare, numRolls, finalRolls);
        // Calculate minimum roll of all possible combinations
        int minRolls = Integer.MAX_VALUE;
        for (int roll : finalRolls) {
            System.out.println(roll);
            if (roll < minRolls) {
                minRolls = roll;
            }
        }
        return minRolls;
    }
    public static void tryLadder(int lastSquare, int[][] ladders, int[][] snakes, int currentSquare, int numRolls, ArrayList<Integer> finalRolls) {
        int numLadders = ladders.length;
        boolean laddersLeft = false;
        // Loop through each ladder on the board
        for (int i = 0; i < numLadders; i++) {
            int currLadder = ladders[i][0];
            // If already past ladder, skip (can't try that ladder)
            if (currentSquare > currLadder) {
                continue;
            }
            // Calculate number of rolls from current square to get to ladder and add to numRolls
            int rollsToLadder = (int) Math.ceil((currLadder - currentSquare) / 6.0);

            numRolls += rollsToLadder;
            // Update current square once moved
            currentSquare = ladders[i][1];
            // Set laddersLeft equal to true (possible to move to another ladder)
            laddersLeft = true;
            tryLadder(lastSquare, ladders, snakes, currentSquare, numRolls, finalRolls);
        }
        // If not possible to move to another ladder,
        // calculates rolls to the end and adds final number of rolls to final rolls array list, then returns
        if (!laddersLeft) {
            int rollsToEnd = (int) Math.ceil((lastSquare - currentSquare) / 6.0);
            numRolls += rollsToEnd;
            finalRolls.add(numRolls);
            return;
        }
    }
}
