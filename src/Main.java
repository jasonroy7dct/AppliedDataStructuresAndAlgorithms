import assignment2.Searching;
import assignment3.Mergesort;
import assignment3.Quicksort;
import assignment4.City;
import assignment4.Graph;
import assignment5.RecursionAndBacktracking;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

  public static void main(String[] args) throws IOException {

    // Assignment2 Task-1
    /**
     * Example 1:
     * Input: nums = [4,9,10,13,17,17,19,21], target = 17
     * Output: [4,5]
     *
     * Example 2:
     * Input: nums = [2,4,6,8,10,14,16], target = 12
     * Output: [-1,-1]
     *
     * Example 3:
     * Input: nums = [], target = 0
     * Output: [-1,-1]
     */
    Searching searching = new Searching();
    int[] assignment2Task1ExampleOne = searching.task1(new int[]{4, 9, 10, 13, 17, 17, 19, 21}, -1);
    int[] assignment2Task1ExampleTwo = searching.task1(new int[]{2, 4, 6, 8, 10, 14, 16}, 12);
    int[] assignment2Task1ExampleThree = searching.task1(new int[]{}, 0);
    System.out.println("Assignment2 Task-1 Example 1 Output should be [4,5]: "
        + Arrays.toString(assignment2Task1ExampleOne));
    System.out.println("Assignment2 Task-1 Example 2 Output should be [-1,-1]: "
        + Arrays.toString(assignment2Task1ExampleTwo));
    System.out.println("Assignment2 Task-1 Example 3 Output should be [-1,-1]: "
        + Arrays.toString(assignment2Task1ExampleThree));

    // Assignment2 Task-2
    /**
     * Example 1:
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * Output: true
     *
     * Example 2:
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * Output: false
     */
    int[][] matrix = {
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 60}
    };
    boolean assignment2Task2ExampleOne = searching.task2(matrix, 3);
    boolean assignment2Task2ExampleSecond = searching.task2(matrix, 13);
    System.out.println("Assignment2 Task-2 Example 1 Output should be true: "
        + assignment2Task2ExampleOne);
    System.out.println("Assignment2 Task-2 Example 2 Output should be false: "
        + assignment2Task2ExampleSecond);

    // Assignment3 Task-1 Step-1
    /**
     * Input: strings = ["bucket","rat","mango","tango","ogtan","tar"]
     * Output: [["bucket"],["rat","tar"],["mango"],["tango","ogtan"]]
     */
    Mergesort mergesort = new Mergesort();
    List<List<String>> mergeSortOutput = mergesort.groupAnagram(
        Arrays.asList("bucket", "rat", "mango", "tango", "ogtan", "tar"));
    System.out.println(
        "Assignment3 Task-1 Mergesort Output should be [[\"bucket\"],[\"rat\",\"tar\"],[\"mango\"],[\"tango\",\"ogtan\"]]: "
            + mergeSortOutput);

    Quicksort quicksort = new Quicksort();
    List<List<String>> quickSortOutput = quicksort.groupAnagram(
        Arrays.asList("bucket", "rat", "mango", "tango", "ogtan", "tar"));
    System.out.println(
        "Assignment3 Task-1 Quicksort Output should be [[\"bucket\"],[\"rat\",\"tar\"],[\"mango\"],[\"tango\",\"ogtan\"]]: "
            + quickSortOutput);

    // Assignment3 Task-1 Step-2 compare the performance between Mergesort and Quicksort

    // initial the random string
    List<String> input = generateRandomStrings(1000);

    // 1. Leave the timestamp before doing the sort
    // 2. Leave the timestamp after doing the sort
    // 3. Calculate the time by minus the two timestamps

    long mergeSortStartTime = System.currentTimeMillis();
    mergesort.groupAnagram(input);
    long mergeSortEndTime = System.currentTimeMillis();
    long mergeSortTimeResult = mergeSortEndTime - mergeSortStartTime;
    System.out.println("Mergesort Time: " + mergeSortTimeResult + " ms");

    long quickSortStartTime = System.currentTimeMillis();
    quicksort.groupAnagram(input);
    long quickSortEndTime = System.currentTimeMillis();
    long quickSortTimeResult = quickSortEndTime - quickSortStartTime;
    System.out.println("Quicksort Time: " + quickSortTimeResult + " ms");

    if (mergeSortTimeResult < quickSortTimeResult) {
      System.out.println("Mergesort has a better performance than Quicksort in this case");
    } else {
      System.out.println("Quicksort has a better performance than Mergesort in this case");
    }

    // Assignment4 Task-2
    Graph graph = new Graph();
    Map<String, City> cityMap = graph.readCityPopulationToConstructGraphOfCities
        ("src/assignment4/textfile/city_population.txt");
    graph.readRoadNetworkToConnectCities("src/assignment4/textfile/road_network.txt");
    graph.printGraphOfCities();
    graph.printConnectedCitiesOfCity();

    // Assignment4 Task-3
    List<City> cityList = new ArrayList<>(cityMap.values());
    int numbers = graph.getTheNumberOfIslandsInTheArchipelago(cityList);
    System.out.println("Assignment4 Task-3: The Number Of Islands In The Archipelago: " + numbers);

    // Assignment4 Task-4
    Map<String, Integer> map = graph.calculateIslandPopulations(cityList);
    System.out.println("Assignment4 Task-4: Island Populations: " + map);

    // Assignment4 Task-5
    City city1 = graph.getCityByName("New York");
    City city2 = graph.getCityByName("Los Angeles");

    int minimum = graph.findMinimumHighways(city1, city2);
    System.out.println("Assignment4 Task-5: Minimum Highways: " + minimum);

    // Assignment5 Task-1
    RecursionAndBacktracking recursionAndBacktracking = new RecursionAndBacktracking();
    /**
     * Example 1:
     * Input: s1 = "ab", s2 = "eidbaooo"
     * Output: true
     * Explanation: s2 contains one permutation of s1 ("ba").
     */
    System.out.println("Assignment5 Task-1 Example 1 Output should be true: "
        + recursionAndBacktracking.checkPermutationsWithSlidingWindows("ab", "eidbaooo"));
    /**
     * Example 2:
     * Input: s1 = "ab", s2 = "eidboaoo"
     * Output: false
     */
    System.out.println("Assignment5 Task-1 Example 2 Output should be false: "
        + recursionAndBacktracking.checkPermutationsWithSlidingWindows("ab", "eidboaoo"));

    // Assignment5 Task-2
    /**
     * Example 1:
     * Input: 1 2 3 4 5 6 7 8
     * Output: 7
     */
    int[] exampleOne = {1, 2, 3, 4, 5, 6, 7, 8};
    System.out.println("Assignment5 Task-2 Example 1 Output should be 7: "
        + recursionAndBacktracking.findMinNumberOfMoves(exampleOne));
    /**
     *Example 2:
     * Input: 1 1 1 1 1 1 1 1
     * Output: 7
     */
    int[] exampleTwo = {1, 1, 1, 1, 1, 1, 1, 1};
    System.out.println("Assignment5 Task-2 Example 2 Output should be 7: "
        + recursionAndBacktracking.findMinNumberOfMoves(exampleTwo));
  }

  // in order to test the performance, randomly generate strings
  public static List<String> generateRandomStrings(int count) {
    List<String> strings = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      StringBuilder sb = new StringBuilder();
      int length = random.nextInt(10) + 1;
      for (int j = 0; j < length; j++) {
        char randomChar = (char) (random.nextInt(26) + 'a');
        sb.append(randomChar);
      }
      strings.add(sb.toString());
    }
    return strings;
  }
}