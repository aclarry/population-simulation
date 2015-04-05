package popsim.tile;

import java.util.List;
import java.util.ArrayList;

import popsim.citizen.Citizen;

public class Tile {

  List<Citizen> citizens;

  double averageUtility;

  /**
   * Default constructor for tile
   * Initializes a new array of citizens
   */
  public Tile() {
    citizens = new ArrayList<Citizen>();
  }
  
  /**
   * Adds a citizen to the tile
   * @param citizen
   *            The citizen to add to the tile
   */
  public void addCitizen(Citizen citizen) {
    citizens.add(citizen);
  }

  public void removeCitizen(Citizen citizen) {
    citizens.remove(citizen);
  }
  
  /**
   * Returns the average utility of citizens in the tile
   * @return
   *          The average utility of the tile
   */
  public double getAverageUtility() {
    return averageUtility;
  }

  /**
   * Returns the number of citizens in the tile
   * @return
   *          The number of citizens in the tile
   */
  public int getNumCitizens() {
    return citizens.size();
  }

  /**
   * Updates the tile, updating every citizen and
   * updating the tile's private variables
   */
  public void update() {
    for (Citizen citizen : citizens) {
      citizen.update();
    }
    updateAverageUtility();
  }

  /**
   * Updates the average utility by taking the average of all
   * utilities of citizens in the tile
   */
  private void updateAverageUtility() {
    double total = 0.0;
    for (Citizen citizen : citizens) {
      total += citizen.getUtility();
    }
    averageUtility = total / citizens.size();
  }
  
}
