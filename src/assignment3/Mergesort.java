package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Mergesort {

  /**
   * Task Description
   * <p>
   * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
   * typically using all the original letters exactly once. For example, “mango” and “ogman” are
   * anagrams.
   * <p>
   * Task-1: Given an array of strings Strings, group the words that are anagrams to each other. You
   * can return the answer in any order.
   * <p>
   * For example,
   * <p>
   * Input: strings = ["bucket","rat","mango","tango","ogtan","tar"]
   * <p>
   * Output: [["bucket"],["rat","tar"],["mango"],["tango","ogtan"]]
   * <p>
   * For this assignment, complete the following steps -
   * <p>
   * Step-1: Implement a function List<List<String>> groupAnagram(List<String> strings) that takes a
   * list of strings and returns a list of lists of strings
   * <p>
   * Step-2: To group the anagrams, you would need to sort all the given strings by the characters,
   * i.e., the ASCII code of the characters. After sorting the strings that converge to the same
   * string, they are grouped together in the same anagram bucket. For example, after sorting, both
   * mango and ogman become agmno; therefore, they are in the same bucket. Implement a function
   * String sortString(String str) that sorts a string. You can’t use sorting API for this. Please
   * use two of the following sorting algorithms and compare their performance. Write sample test
   * cases to validate your implementation.
   * <p>
   * Mergesort Quicksort Heapsort Radix sort
   *
   * @return List<List < String>>
   */
  public List<List<String>> groupAnagram(List<String> strings) {
    List<List<String>> result = new ArrayList<>();
    List<String> sortedLists = new ArrayList<>();

    for (String string : strings) {
      String sortedString = sortStringWithMergesort(string);

      int index = sortedLists.indexOf(sortedString);
      // if sorted string not in sortedLists
      if (index == -1) {
        // add the original string in
        sortedLists.add(sortedString);
        List<String> list = new ArrayList<>();
        list.add("\"" + string + "\"");
        result.add(list);
      } else {
        // sorted string already in sortedLists, add the original string in
        result.get(index).add("\"" + string + "\"");
      }
    }
    return result;
  }

  private String sortStringWithMergesort(String str) {
    if (str == null) {
      return null;
    }

    char[] charArray = str.toCharArray();
    mergeSort(charArray);
    return new String(charArray);
  }

  // Time complexity of mergesort in average case and worst case is O(N * log2N)
  private void mergeSort(char[] charArray) {
    int n = charArray.length;
    if (n < 2) {
      return;
    }

    // split it into n/2
    int mid = n / 2;
    char[] left = new char[mid];
    char[] right = new char[n - mid];

    System.arraycopy(charArray, 0, left, 0, mid);
    System.arraycopy(charArray, mid, right, 0, n - mid);

    mergeSort(left);
    mergeSort(right);

    merge(charArray, left, right);
  }

  private void merge(char[] charArray, char[] left, char[] right) {
    int leftLength = left.length;
    int rightLength = right.length;
    int i = 0, j = 0, k = 0;

    while (i < leftLength && j < rightLength) {
      if (left[i] <= right[j]) {
        charArray[k++] = left[i++];
      } else {
        charArray[k++] = right[j++];
      }
    }

    while (i < leftLength) {
      charArray[k++] = left[i++];
    }

    while (j < rightLength) {
      charArray[k++] = right[j++];
    }
  }
}
