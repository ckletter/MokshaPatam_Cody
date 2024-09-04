import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        int lastSquare = boardsize * boardsize;
        int ladderLength = ladders.length;
        int snakesLength = snakes.length;
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        ArrayList<Integer> alreadyExplored = new ArrayList<Integer>();
        // initialize new array lookup table to find starts of snakes and ladders
        int[] snakeLadderStarts = new int[boardsize];
        for (int i = 0; i < ladders.length; i++) {
            snakeLadderStarts[ladders[i][0] - 1] = ladders[i][1];
        }
        for (int j = 0; j < snakes.length; j++) {
            snakeLadderStarts[snakes[j][0] - 1] = snakes[j][1];
        }
        int rolls = tryBFS(lastSquare, currentSquare, 0, bfsQueue, snakeLadderStarts, alreadyExplored);
        return rolls;
    }
    public static int tryBFS(int lastSquare, int currentSquare, int numRolls, Queue<Integer> bfsQueue, int[] snakeLadderStarts, ArrayList<Integer> alreadyExplored) {
        // Base case - if lastSquare is reached or surpassed, return number of rolls used
        if (currentSquare >= lastSquare) {
            return numRolls;
        }
        for (int i = 1; i < 7; i++) {
            int nextSquare;
            // Check to make sure new square not out of bounds
            if (currentSquare + i >= 100) {
                return numRolls + 1;
            }
            if (snakeLadderStarts[currentSquare + i - 1] != 0) {
                nextSquare = snakeLadderStarts[currentSquare + i];
            }
            else {
                nextSquare = currentSquare + i;
            }
            // Add next square to queue only if it is not already explored and not in queue already
            // Add next square to list of already explored squares
            if (!alreadyExplored.contains(nextSquare)) {
                // Add next possible roll to queue
                bfsQueue.add(nextSquare);
                alreadyExplored.add(nextSquare);
            }
        }
        // If there are no nodes left in the queue, return -1 signaling we are in a loop
        if (bfsQueue.peek() == null) {
            return -1;
        }
        // Increment rolls count by 1
        numRolls += 1;
        int nextNode = bfsQueue.remove();
        return tryBFS(lastSquare, nextNode, numRolls, bfsQueue, snakeLadderStarts, alreadyExplored);
    }
}
