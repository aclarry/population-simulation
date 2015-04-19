package popsim.entity.citizen;

import popsim.entity.Entity;
import popsim.tile.Tile;
import java.util.Random;


public abstract class Citizen extends Entity {
  
  private double utility;

  /**
   * The constructor for Citizen
   * @param home The home tile the citizen is initialized to
   */
  public Citizen(Tile home) {
      super(home);
  } 

  /**
   * Additional constructor for citizen
   * @param home The home tile the citizen is initialized to
   * @param randomNumberGenerator An instance of Random for the
   *            Citizen to use
   */
  public Citizen(Tile home, Random randomNumberGenerator) {
      super(home, randomNumberGenerator);
  }


  public void update() {
    updateUtility();
    if (willMove()) {
      home.addEntityToMove(this);
    }  
  }

  public double getUtility() {
    return utility;
  }

  protected void setUtility(double utility) {
    this.utility = utility; 
  }

  protected abstract void updateUtility(); 

  public boolean willMove() {
    return getMoveProbability() >= getRandom();
  }

  /**
   * Returns a number between 0 and 1 indicating the probability that
   * the citizen will move next turn
   * This, in particular, is subject to change
   */
  protected abstract double getMoveProbability(); 

}
