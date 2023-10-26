package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Quicksort {

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
      String sortedString = sortStringWithQuicksort(string);

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

  private String sortStringWithQuicksort(String str) {
    if (str == null) {
      return null;
    }

    char[] charArray = str.toCharArray();
    int low = 0;
    int high = charArray.length - 1;

    quicksort(charArray, low, high);

    return new String(charArray);
  }

  // Time complexity of quicksort in average case is O(N * log2N), while O(N^2) in the worst case
  private void quicksort(char[] charArray, int low, int high) {
    if (low < high) {
      // find the pivot index
      int pivotIndex = partition(charArray, low, high);
      // set the high, low by recursion
      quicksort(charArray, low, pivotIndex - 1);
      quicksort(charArray, pivotIndex + 1, high);
    }
  }

  // partition around the pivot element
  private int partition(char[] charArray, int low, int high) {
    // initial last element as the pivot
    char pivot = charArray[high];
    // initial the smaller element as the index
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (charArray[j] <= pivot) {
        i++;
        // swap i, j elements
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
      }
    }
    //swap the high pivot element and the i + 1 element
    char temp = charArray[i + 1];
    charArray[i + 1] = charArray[high];
    charArray[high] = temp;

    return i + 1;
  }
}
