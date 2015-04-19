package popsim.entity;

import popsim.tile.Tile;
import java.util.Random;


public abstract class Entity {
  
  protected Tile home;
  protected Random randomNumberGenerator;

  /**
   * The constructor for Entity
   * @param home The home tile the entity is initialized to
   */
  public Entity(Tile home) {
    this.home = home;
    home.addEntity(this);
    this.randomNumberGenerator = new Random();
  } 

  /**
   * Additional constructor for Entity
   * @param home The home tile the entity is initialized to
   * @param randomNumberGenerator An instance of Random for the
   *            Entity to use
   */
  public Entity(Tile home, Random randomNumberGenerator) {
    this(home);
    this.randomNumberGenerator = randomNumberGenerator;
  }

  /**
   * Updates the entity
   */
  public abstract void update();

  /**
   * Moves the entity to a new tile, as given by the parent tile
   */
  public void moveTile() {
    Tile newHome = home.getNewTile();
    changeHomeTile(newHome);
  }

  /**
   * Change the home tile of the entity to a new tile
   * @param newHome A new home tile
   */
  public void changeHomeTile(Tile newHome) {
    home.removeEntity(this);
    this.home = newHome;
    newHome.addEntity(this);
  }

  /**
   * Gets the entity's home tile
   * @return
   *          The entity's home tile
   */
  public Tile getHomeTile() {
    return home;
  }

  protected double getRandom() {
    return randomNumberGenerator.nextDouble();
  }
}
