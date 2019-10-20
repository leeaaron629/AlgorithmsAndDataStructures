package unsolved;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *  https://leetcode.com/problems/stone-game/
 */
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int beg = 0, end = piles.length - 1;
        System.out.println(Arrays.toString(alexTake(piles, beg, end)));
        int[] scores = alexTake(piles, beg, end);
        return scores[0] >= scores[1];
    }

    private int[] leeTake(int[] piles, int beg, int end) {

        if (beg >= end) {
            return new int[]{0, piles[beg]}; // Alex is the first one
        }

        int[] scoresFromBeg = alexTake(piles, beg+1, end);
        int[] scoresFromEnd = alexTake(piles, beg, end-1);

        if (scoresFromBeg[1] > scoresFromEnd[1]) {
            scoresFromBeg[1] += piles[beg];
            System.out.println("Lee takes from the beginning: " + piles[beg] + " | " + Arrays.toString(scoresFromBeg));
            return scoresFromBeg;
        } else {
            scoresFromEnd[1] += piles[end];
            System.out.println("Lee takes from the end: " + piles[end] + " | " + Arrays.toString(scoresFromEnd));
            return scoresFromEnd;
        }
    }

    private int[] alexTake(int[] piles, int beg, int end) {

        if (beg >= end) {
            return new int[]{piles[beg], 0}; // Alex is the first one
        }

        int[] scoresFromBeg = leeTake(piles, beg+1, end);
        int[] scoresFromEnd = leeTake(piles, beg, end-1);

        if (scoresFromBeg[0] > scoresFromEnd[0]) {
            scoresFromBeg[0] += piles[beg];
            System.out.println("Alex takes from the beginning: " + piles[beg] + " | " + Arrays.toString(scoresFromBeg));
            return scoresFromBeg;
        } else {
            scoresFromEnd[0] += piles[end];
            System.out.println("Alex takes from the end: " + piles[end] + " | " + Arrays.toString(scoresFromEnd));
            return scoresFromEnd;
        }
    }

    @Test
    public void tests() {
        int[] piles = new int[]{10, 1, 4, 3, 100, 2}; // 16, 104
        stoneGame(piles);

        piles = new int[] {3,4};
//        stoneGame(piles);

        piles = new int[]{5,3,4,5}; // 9, 8
//        stoneGame(piles);


    }
}
