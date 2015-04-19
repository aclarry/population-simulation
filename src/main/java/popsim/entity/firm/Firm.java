package popsim.entity.firm;

import popsim.entity.Entity;
import popsim.tile.Tile;
import java.util.Random;


public abstract class Firm extends Entity {

  private double profit;

  public Firm(Tile home) {
    super(home);
  }

  public Firm(Tile home, Random randomNumberGenerator) {
    super(home, randomNumberGenerator);
  }

  public void update() {
    updateProfit();
    if (willMove()) {
      home.addEntityToMove(this);
    }  
  }

  public double getProfit() {
    return profit;
  }

  protected void setProfit(double profit) {
    this.profit = profit; 
  }

  protected abstract void updateProfit(); 

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
