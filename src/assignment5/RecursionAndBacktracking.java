package assignment5;

import java.util.Arrays;

public class RecursionAndBacktracking {

  /**
   * Task Description: Complete the following tasks. Task-1:  Given two strings s1 and s2, return
   * true if s2 contains a permutation of s1, or false otherwise. In other words, return true if one
   * of s1's permutations is the substring of s2. Example 1: Input: s1 = "ab", s2 = "eidbaooo"
   * Output: true Explanation: s2 contains one permutation of s1 ("ba"). Example 2: Input: s1 =
   * "ab", s2 = "eidboaoo" Output: false Write sample test cases to validate your implementation.
   */

  // Change it to use sliding windows method
  // Time complexity is O(n)
  public boolean checkPermutationsWithSlidingWindows(String s1, String s2) {
    if (s1.length() > s2.length()) {
      return false;
    }
    // initial two arrays to count the characters in s1 and the current window in s2
    int[] s1Array = new int[26];
    int[] s2Array = new int[26];

    // count characters in the initial window of s2 (length equal to s1)
    for (int i = 0; i < s1.length(); i++) {
      s1Array[s1.charAt(i) - 'a']++;
      s2Array[s2.charAt(i) - 'a']++;
    }

    // slide the window through s2 for check permutations
    for (int i = 0; i < s2.length() - s1.length(); i++) {
      if (isMatched(s1Array, s2Array)) {
        return true;
      }
      // slide the window by adding the next character and removing the first character.
      s2Array[s2.charAt(i + s1.length()) - 'a']++;
      s2Array[s2.charAt(i) - 'a']--;
    }
    // check the character counts for the last window
    return isMatched(s1Array, s2Array);
  }

  public boolean isMatched(int[] s1Array, int[] s2Array) {
    for (int i = 0; i < 26; i++) {
      // compare the characters
      if (s1Array[i] != s2Array[i]) {
        return false;
      }
    }
    return true;
  }


  // my original sorting method
  public boolean checkPermutationsWithSorting(String s1, String s2) {
    s1 = sortTheOriginalString(s1);
    // compare the sub string
    for (int i = 0; i <= s2.length() - s1.length(); i++) {
      if (s1.equals(sortTheOriginalString(s2.substring(i, i + s1.length())))) {
        return true;
      }
    }
    return false;
  }

  private String sortTheOriginalString(String s) {
    char[] ch = s.toCharArray();
    Arrays.sort(ch);
    return new String(ch);
  }

  /**
   * Task-2:  You are given a chess board (of 8×8 dimension), and there are 8 queens placed randomly
   * on the board. Each of the 8 queens is in different columns, and that means no two queens are
   * attacking each other vertically. But some queens are attacking each other horizontally and/or
   * diagonally. You have to move the queens so that no two queens are attacking each other from any
   * direction. You are allowed to move the queens vertically, and thus, you can only change the row
   * positions of each queen and not the column. A move consists of moving a queen from (R1, C) to
   * (R2, C) where 1 ≤ R1, R2 ≤ 8 and R1 ̸= R2. You have to find the minimum number of moves
   * required to complete the task. Each input consists of a line containing 8 integers. All these
   * integers will be in the range [1, 8]. The i-th integer indicates the row position of a queen in
   * the i-th column. Example 1:
   * <p>
   * Input: 1 2 3 4 5 6 7 8
   * <p>
   * Output: 7
   * <p>
   * Example 2:
   * <p>
   * Input: 1 1 1 1 1 1 1 1
   * <p>
   * Output: 7
   * <p>
   * Write sample test cases to validate your implementation.
   */

  // new backtracking method
  public int findMinNumberOfMoves(int[] queenPositions) {
    // initial the minimum moves as maximum
    int[] moves = {Integer.MAX_VALUE};
    // to store the current position
    int[] currentPosition = new int[8];

    // check the initial configuration
    boolean initialConfigValid = true;

    for (int i = 0; i < 8; i++) {
      if (!isValid(queenPositions, i)) {
        initialConfigValid = false;
        break;
      }
    }

    if (initialConfigValid) {
      return 0;
    }

    // do backtracking to find the min moves
    backtracking(0, queenPositions, currentPosition, moves);

    return moves[0];
  }

  private void backtracking(int column, int[] initialPosition, int[] currentPosition,
      int[] minMoves) {
    if (column == 8) {
      int moves = 0;
      // count how many queens move from the original position
      for (int i = 0; i < 8; i++) {
        if (initialPosition[i] != currentPosition[i]) {
          moves++;
        }
      }
      // update the min moves if a better config founded
      minMoves[0] = Math.min(minMoves[0], moves);
      return;
    }

    // place a queen in each row of the current column
    for (int row = 1; row <= 8; row++) {
      currentPosition[column] = row;
      if (isValid(currentPosition, column)) {
        backtracking(column + 1, initialPosition, currentPosition, minMoves);
      }
    }
  }

  private boolean isValid(int[] queenPositions, int column) {
    for (int i = 0; i < column; i++) {
      // check if in the same row or previous row and current row is on a diagonal
      if (queenPositions[i] == queenPositions[column]
          || queenPositions[i] - i == queenPositions[column] - column
          || queenPositions[i] + i == queenPositions[column] + column) {
        return false;
      }
    }
    return true;
  }


  // my original brute method for finding min number of moves
  public int findMinNumberOfMovesWithBrute(int[] queenPositions) {
    // initial the moves to count
    int moves = 0;
    // initial the const of the 8×8 dimension chess board
    final int CHEST_BOARD_COLUMN = 8;

    for (int i = 0; i < CHEST_BOARD_COLUMN; i++) {
      for (int j = i + 1; j < CHEST_BOARD_COLUMN; j++) {
        // check if in the same row or previous row and current row is on a diagonal
        if (queenPositions[i] == queenPositions[j]
            || Math.abs(queenPositions[i] - queenPositions[j]) == Math.abs(i - j)) {
          moves++;
          break;
        }
      }
    }
    return moves;
  }
}
