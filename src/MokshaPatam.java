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
            snakeLadderStarts[ladders[i][0]] = ladders[i][1];
        }
    }
    public static int tryBFS(int lastSquare, int currentSquare, int numRolls, Queue<Integer> bfsQueue) {
        // Base case - if lastSquare is reached, return number of rolls used
        if (currentSquare == lastSquare) {
            return numRolls;
        }
        for (int i = 1; i < 7; i++) {
            if (isSnake()) {
                // Do stuff
            }
            else if (isLadder()) {

            }
            // Add next possible roll to queue
            bfsQueue.add(currentSquare + i);
        }
        // If there are no nodes left in the queue, return -1 signaling we are in a loop
        if (bfsQueue.peek() == null) {
            return -1;
        }
        // Increment rolls count by 1
        numRolls += 1;
        int nextNode = bfsQueue.remove();
        return tryBFS(lastSquare, nextNode, numRolls, bfsQueue);
    }
    public static boolean isSnake() {
    }
    public static boolean isLadder() {

    }
}
