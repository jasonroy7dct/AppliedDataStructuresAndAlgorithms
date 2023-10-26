package assignment2;

import java.util.List;

public class Searching {

  /**
   * Task Description:  Complete the following tasks:
   * <p>
   * Task-1: Given an array of integer nums sorted in non-decreasing order, find a given target
   * value's first and last position. Suppose the target is not found in the array; return [-1, -1].
   * You must write an algorithm with O(log n) runtime complexity. Also, write additional sample
   * test cases to validate your implementation.
   * <p>
   * Example 1:
   * <p>
   * Input: nums = [4,9,10,13,17,17,19,21], target = 17
   * <p>
   * Output: [4,5]
   * <p>
   * Example 2:
   * <p>
   * Input: nums = [2,4,6,8,10,14,16], target = 12
   * <p>
   * Output: [-1,-1]
   * <p>
   * Example 3:
   * <p>
   * Input: nums = [], target = 0
   * <p>
   * Output: [-1,-1]
   */
  public int[] task1(int[] nums, int target) {
    // initial the int array
    int[] result = new int[2];
    // both use binary search
    result[0] = findFirst(nums, target);
    result[1] = findLast(nums, target);
    return result;
  }

  private int findFirst(int[] nums, int target) {
    int index = -1;
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        index = mid;
        // in order to find the first position before the mid index ([0...mid-1])
        high = mid - 1;
      } else if (nums[mid] > target) {
        // set the high to mid - 1
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return index;
  }

  private int findLast(int[] nums, int target) {
    int index = -1;
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        index = mid;
        // in order to find the last position after the mid index ([mid+1...high])
        low = mid + 1;
      } else if (nums[mid] > target) {
        // set the high to mid - 1
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return index;
  }

  /**
   * Task-2:
   * <p>
   * You are given an m x n integer matrix with the following two properties:
   * <p>
   * Each row is sorted in non-decreasing order. The first integer of each row is greater than the
   * last integer of the previous row. Given an integer target, return true if the target is in the
   * matrix or false otherwise. Write a solution in O(log(m * n)) time complexity. Also, write
   * additional sample test cases to validate your implementation.
   * <p>
   * Example 1:
   * <p>
   * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
   * <p>
   * Output: true
   * <p>
   * Example 2:
   * <p>
   * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
   * <p>
   * Output: false
   *
   * @return boolean
   */
  public boolean task2(int[][] matrix, int target) {
    // initial row and column
    int row = matrix.length;
    int col = matrix[0].length;

    // if the first element exceeds target number, it means that the target number is not in the matrix
    // or if target number exceeds last element, it means that the target number is out of the matrix
    if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
      return false;
    }

    // use binary search to achieve O(log(m * n)) time complexity
    int low = 0;
    int high = row * col - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      // check row and column is equal target or not
      if (matrix[mid / col][mid % col] == target) {
        return true;
      } else if (matrix[mid / col][mid % col] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }
}
