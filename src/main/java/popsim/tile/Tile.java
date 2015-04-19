package popsim.tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import popsim.entity.Entity;
import popsim.entity.citizen.Citizen;
import popsim.entity.citizen.CrowdPreferringCitizen;
import popsim.entity.citizen.OpenPreferringCitizen;
import popsim.entity.firm.Firm;

public class Tile {

  private TileController controller;
  private List<Citizen> citizens;
  private List<Firm> firms;
  private Set<Entity> entitiesToMove;
  private Random randomNumberGenerator;

  private double averageUtility;
  private double averageProfit;

  /**
   * Default constructor for tile
   * Initializes a new array of citizens
   */
  public Tile() {
    citizens = new ArrayList<Citizen>();
    firms = new ArrayList<Firm>();
    averageUtility = 0.0;
    averageProfit = 0.0;
    resetEntitiesToMove();
  }

  /**
   * Constructor for tile
   * @param controller
   *        The controller parent of the tile
   */
  public Tile(TileController controller) {
    this();
    this.controller = controller;
  }

  /**
   * Constructor for tile
   * @param controller
   *        The controller parent of the tile
   * @param randomNumberGenerator
   */
  public Tile(TileController controller, Random randomNumberGenerator) {
    this(controller);
    this.randomNumberGenerator = randomNumberGenerator;
  }

  public void addCitizens(int numCitizens) {
    for (int i = 0; i < 3*numCitizens/4; i++) {
      new CrowdPreferringCitizen(this);
    }
    for (int i = 0; i < numCitizens/4; i++) {
      new OpenPreferringCitizen(this);
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

  /**
   * Removes a citizen to the tile
   * @param citizen
   *            The citizen to remove to the tile
   */
  public void removeCitizen(Citizen citizen) {
    citizens.remove(citizen);
  }

  /**
   * Adds an entity to this tile
   * @param entity
   *          The entity to add to the tile
   */
  public void addEntity(Entity entity) {
    if (entity instanceof Citizen) {
      citizens.add((Citizen) entity);
    } else if (entity instanceof Firm) {
      firms.add((Firm) entity);
    }
    // Else, do nothing
  }

  /**
   * Removes an entity from this tile
   * @param entity
   *          The entity to remove from this tile
   */
  public void removeEntity(Entity entity) {
    if (entity instanceof Citizen) {
      citizens.remove(entity);
    } 
    if (entity instanceof Firm) {
      firms.remove(entity);
    }
    // Else, do nothing
  }
  
  /**
   * Returns the average utility of citizens in the tile
   * @return
   *          The average utility of the tile
   */
  public double getAverageUtility() {
    return averageUtility;
  }

  public double getAverageProfit() {
    return averageProfit;
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

  public int getNumFirms() {
    return firms.size();
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
    for (Firm firm : firms) {
      firm.update();
    }
    moveEntities();
    updateAverageUtility();
  }

  public void moveEntities() {
    for (Entity entity : entitiesToMove) {
      entity.moveTile();
    }
  }

  public void addEntityToMove(Entity entity) {
    entitiesToMove.add(entity);
  }

  private void resetEntitiesToMove() {
    entitiesToMove = new HashSet<Entity>();
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

  private void updateAverageProfit() {
    double total = 0.0;
    if (firms.size() == 0) {
      averageProfit = 0.0;
    } else {
      for (Firm firm : firms) {
        total += firm.getProfit();
      }
      averageProfit = total / firms.size();
    }
  }

}
