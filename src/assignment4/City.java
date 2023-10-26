package assignment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Task-1: Implement a class City that will include the following fields - [4 pt] Name of the city
 * The population of the city List of cities that are connected to this particular city.
 */
public class City {

  // Name of the city
  private String name;
  // The population of the city
  private int population;
  // List of cities that are connected to this particular city.
  List<City> connectedCities;

  public City(String name, int population) {
    this.name = name;
    this.population = population;
    this.connectedCities = new ArrayList<>();
  }

  public void addConnectedCity(City city) {
    connectedCities.add(city);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public List<City> getConnectedCities() {
    return connectedCities;
  }

  public void setConnectedCities(List<City> connectedCities) {
    this.connectedCities = connectedCities;
  }
}
