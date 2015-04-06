package popsim.tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import popsim.citizen.Citizen;

public class Tile {

  private TileController controller;
  private List<Citizen> citizens;
  private Set<Citizen> citizensToMove;
  private Random randomNumberGenerator;

  private double averageUtility;

  /**
   * Default constructor for tile
   * Initializes a new array of citizens
   */
  public Tile() {
    citizens = new ArrayList<Citizen>();
    averageUtility = 0.0;
    resetCitizensToMove();
  }

  public Tile(TileController controller) {
    this();
    this.controller = controller;
  }

  public Tile(TileController controller, Random randomNumberGenerator) {
    this(controller);
    this.randomNumberGenerator = randomNumberGenerator;
  }

  public void addCitizens(int numCitizens) {
    for (int i = 0; i < numCitizens; i++) {
      new Citizen(this);
    }
  }
  
  /**
   * Adds a citizen to the tile
   * @param citizen
   *            The citizen to add to the tile
   */
  public void addCitizen(Citizen citizen) {
    if (citizen != null) {
      citizens.add(citizen);
    }
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

  public double getGlobalAverageUtility() {
    if (controller != null) {
      return controller.getGlobalAverageUtility(); 
    } else {
      return averageUtility;
    }
  }

  /**
   * Gets a random tile from the controller which is not equal
   * to this tile
   * @return 
   *          A tile not equal to this tile
   */
  public Tile getNewTile() {
    Tile newTile;
    if (controller != null) {
      do {
         newTile = controller.getTile();
       } while (newTile == this);
    } else {
      newTile = new Tile();
    }
    return newTile;
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
   * The average utility should be one of the last variables
   * to update
   */
  public void update() {
    for (Citizen citizen : citizens) {
      citizen.update();
    }
    moveCitizens();
    updateAverageUtility();
  }

  public void moveCitizens() {
    for (Citizen citizen : citizensToMove) {
      citizen.moveTile();
    }
  }

  public void addCitizenToMove(Citizen citizen) {
    citizensToMove.add(citizen);
  }

  private void resetCitizensToMove() {
    citizensToMove = new HashSet<Citizen>();
  }

  /**
   * Updates the average utility by taking the average of all
   * utilities of citizens in the tile
   */
  private void updateAverageUtility() {
    double total = 0.0;
    if (citizens.size() == 0) {
      averageUtility = 0.0;
    } else {
      for (Citizen citizen : citizens) {
        total += citizen.getUtility();
      }
      averageUtility = total / citizens.size();
    }
  }

}
