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

  public Tile getHomeTile() {
    return home;
  }

  public void update() {
    updateUtility();
    if (willMove()) {
      home.addCitizenToMove(this);
    }  
  }

  /**
   * Moves the citizen to a new tile, as given by the parent tile
   */
  public void moveTile() {
    Tile newHome = home.getNewTile();
    changeHomeTile(newHome);
  }

  public void changeHomeTile(Tile newHome) {
    home.removeCitizen(this);
    this.home = newHome;
    newHome.addCitizen(this);
  }

  public double getUtility() {
    return utility;
  }

  private void updateUtility() {
    utility = 0 - home.getNumCitizens();
  }

  public boolean willMove() {
    return getMoveProbability() >= randomNumberGenerator.nextDouble();
  }

  /**
   * Returns a number between 0 and 1 indicating the probability that
   * the citizen will move next turn
   * This, in particular, is subject to change
   */
  protected double getMoveProbability() {
    double moveProbability;
    double globalAverageUtil = home.getGlobalAverageUtility();
    if (utility < globalAverageUtil) {
      moveProbability = (globalAverageUtil - utility)/Math.abs(globalAverageUtil + utility);
    } else {
      moveProbability = 0.0;
    } 
    return moveProbability;
  }

}
