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
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        int currentSquare = 1;
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        // initialize new association array lookup table to find starts of snakes and ladders
        int[] snakeLadderStarts = new int[boardsize + 1];
        // fill snake ladder association array with ladders start/end pairs
        for (int i = 0; i < ladders.length; i++) {
            snakeLadderStarts[ladders[i][0]] = ladders[i][1];
        }
        // fill snake ladder association array with snake start/end pairs
        for (int j = 0; j < snakes.length; j++) {
            snakeLadderStarts[snakes[j][0]] = snakes[j][1];
        }
        // initialize association array lookup tables for roll counts and visited squares
        int[] rollCounts = new int[boardsize + 1];
        boolean[] isVisited = new boolean[boardsize + 1];
        // add first square to queue
        bfsQueue.add(currentSquare);
        // loop until the queue is empty
        while (!bfsQueue.isEmpty()) {
            // If last square has been reached, return rolls made
            if (currentSquare == boardsize) {
                return rollCounts[currentSquare];
            }
            // Loop through all 6 rolls
            for (int i = 1; i < 7; i++) {
                int nextSquare;
                // If we are 1 roll away from the final square, return one more than rolls to get to current square
                if (currentSquare + i == boardsize) {
                    return rollCounts[currentSquare] + 1;
                }
                // If the square to roll to is the start of a snake or ladder, change the corresponding square to its end
                else if (snakeLadderStarts[currentSquare + i] != 0) {
                    nextSquare = snakeLadderStarts[currentSquare + i];
                }
                // The next square to go to for the specific roll (not rolling to a snake or ladder)
                else {
                    nextSquare = currentSquare + i;
                }
                // Add next square to queue only if it is not already explored and not in queue already
                if (!isVisited[nextSquare]) {
                    // Add next possible roll to queue
                    bfsQueue.add(nextSquare);
                    // Change value of nextSquare in visited array to 1 indicating that it has been seen
                    isVisited[nextSquare] = true;
                    // Increment rolls count of the next square to one more than its parent square (current square)
                    rollCounts[nextSquare] = rollCounts[currentSquare] + 1;
                }
            }
            // If there are no nodes left in the queue, return -1 signaling we are in a loop
            if (bfsQueue.peek() == null) {
                return -1;
            }
            currentSquare = bfsQueue.remove();
        }
        // If there are no nodes left in the queue, return -1 signaling we are in a loop
        return -1;
    }
}
