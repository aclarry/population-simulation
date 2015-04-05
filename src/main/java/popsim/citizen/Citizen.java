package popsim.citizen;

import popsim.tile.Tile;
import java.util.Random;


public class Citizen {
  
  private Tile home;
  private double utility;
  private Random randomNumberGenerator;

  /**
   * The constructor for Citizen
   * @param home The home tile the citizen is initialized to
   */
  public Citizen(Tile home) {
    this.home = home;
    home.addCitizen(this);
    this.randomNumberGenerator = new Random();
  } 

  public Citizen(Tile home, Random randomNumberGenerator) {
    this(home);
    this.randomNumberGenerator = randomNumberGenerator;
  }

  public void update() {
    updateUtility();
    if (willMove()) {
      moveTile();
    }
  }

  public void changeHomeTile(Tile newHome) {
    home.removeCitizen(this);
    this.home = newHome;
    newHome.addCitizen(this);
  }

  public Tile getHomeTile() {
    return home;
  }

  public double getUtility() {
    return utility;
  }

  private void updateUtility() {
    utility = 0 - home.getNumCitizens();
  }

  private boolean willMove() {
    return getMoveProbability() < randomNumberGenerator.nextDouble();
  }

  /**
   * Returns a number between 0 and 1 indicating the probability that
   * the citizen will move next turn
   * This, in particular, is subject to change
   */
  private double getMoveProbability() {
    double moveProbability;
    double globalAverageUtil = home.getGlobalAverageUtility();
    if (globalAverageUtil <= utility) {
      moveProbability = 0.0;
    } else {
      moveProbability = (globalAverageUtil - utility)/globalAverageUtil;
    } 
    return moveProbability;
  }

  /**
   * Moves the citizen to a new tile, as given by the parent tile
   */
  private void moveTile() {
    Tile newHome;
    newHome = home.getNewTile();

    changeHomeTile(newHome);
  }

}
