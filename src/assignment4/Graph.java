package assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {

  /**
   * Task-2: Read the text file and construct a graph of cities. Use the objects of a class City to
   * model a city and the graph. Note that conceptually, this is similar to the adjacency list
   * representation of the graph.
   */

  // create a simple graph using a conceptually adjacency list.
  private final Map<String, City> cityMap = new HashMap<>();

  public Map<String, City> readCityPopulationToConstructGraphOfCities(String fileName)
      throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      String[] strArray = line.split(" : ");
      String name = strArray[0];
      int population = Integer.parseInt(strArray[1]);
      City city = new City(name, population);
      cityMap.put(name, city);
    }
    bufferedReader.close();
    return cityMap;
  }

  public void readRoadNetworkToConnectCities(String fileName) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      String[] strArray = line.split(" : ");
      String name1 = strArray[0];
      String name2 = strArray[1];
      City city1 = cityMap.get(name1);
      City city2 = cityMap.get(name2);
      if (city1 != null && city2 != null) {
        city1.addConnectedCity(city2);
        city2.addConnectedCity(city1);
      }
    }
    bufferedReader.close();
  }

  public void printGraphOfCities() {
    for (City city : cityMap.values()) {
      String name = city.getName();
      int population = city.getPopulation();
      System.out.println("City: " + name + " has " + population + " population");
    }
  }

  public void printConnectedCitiesOfCity() {
    for (City city : cityMap.values()) {
      String name = city.getName();
      int population = city.getPopulation();
      System.out.print(
          "City " + name + " has " + population + " population" + ", connected to: ");
      for (City connectedCity : city.getConnectedCities()) {
        System.out.print(connectedCity.getName() + ", ");
      }
      System.out.println();
    }
  }


  /**
   * Task-3:  Given the list of City objects, write a function to return the number of islands in
   * the archipelago. Note that this function would require finding the number of connected
   * components in the graph.
   *
   * @param cityList List<City>
   * @return int
   */
  public int getTheNumberOfIslandsInTheArchipelago(List<City> cityList) {
    int numberOfIslands = 0;
    Set<City> visitedCitySet = new HashSet<>();

    for (City city : cityList) {
      if (!visitedCitySet.contains(city)) {
        depthFirstSearch(city, visitedCitySet);
        numberOfIslands++;
      }
    }
    return numberOfIslands;
  }

  // traversing and exploring connected components in a graph
  private void depthFirstSearch(City city, Set<City> visitedCitySet) {
    visitedCitySet.add(city);
    for (City connectedCity : city.getConnectedCities()) {
      if (!visitedCitySet.contains(connectedCity)) {
        depthFirstSearch(connectedCity, visitedCitySet);
      }
    }
  }

  /**
   * Task-4:  Given the list of City objects, write a function that would return the population of
   * each island in the island archipelago. Note that this function would require you to find the
   * population of each connected component in the graph.
   *
   * @param cityList List<City>
   * @return Map<String, Integer>
   */
  public Map<String, Integer> calculateIslandPopulations(List<City> cityList) {
    Map<String, Integer> islandPopulations = new HashMap<>();
    Set<City> visitedCitySet = new HashSet<>();

    for (City city : cityList) {
      if (!visitedCitySet.contains(city)) {
        int islandPopulation = depthFirstSearchReturnInteger(city, visitedCitySet);
        islandPopulations.put(city.getName(), islandPopulation);
      }
    }
    return islandPopulations;
  }

  private int depthFirstSearchReturnInteger(City city, Set<City> visitedCities) {
    visitedCities.add(city);
    int population = city.getPopulation();
    for (City connectedCity : city.getConnectedCities()) {
      if (!visitedCities.contains(connectedCity)) {
        population += depthFirstSearchReturnInteger(connectedCity, visitedCities);
      }
    }
    return population;
  }

  /**
   * Task-5: Given two City objects, write a function that would return the minimum number of unique
   * highways you can take to reach from one city to another. Note that this function requires you
   * to find the distance, i.e., the number of unique highways between two cities.
   *
   * @param city1 City1
   * @param city2 City2
   * @return int
   */
  public int findMinimumHighways(City city1, City city2) {
    if (city1 == null || city2 == null) {
      throw new IllegalArgumentException("City1 and city2 must not be null.");
    }

    // implement bfs so use queue
    Set<City> visitedCities = new HashSet<>();
    Queue<City> queue = new LinkedList();

    // search from city1
    queue.offer(city1);
    visitedCities.add(city1);
    int highways = 0;

    while (!queue.isEmpty()) {
      int citiesInCurrentLevel = queue.size();
      for (int i = 0; i < citiesInCurrentLevel; i++) {
        City currentCity = queue.poll();
        for (City connectedCity : currentCity.getConnectedCities()) {
          // check if the connected city has not been visited
          if (!visitedCities.contains(connectedCity)) {
            if (connectedCity == city2) {
              // return the number of highways when city2 is found
              return highways + 1;
            }
            // add the connected city to th queue
            queue.offer(connectedCity);
            visitedCities.add(connectedCity);
          }
        }
      }
      highways++;
    }
    return 0;
  }

  public City getCityByName(String cityName) {
    for (City city : cityMap.values()) {
      if (city.getName().equals(cityName)) {
        return city;
      }
    }
    return null;
  }
}
