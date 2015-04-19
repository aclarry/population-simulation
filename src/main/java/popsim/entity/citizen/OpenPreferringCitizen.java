package popsim.entity.citizen;

import popsim.tile.Tile;
import java.util.Random;

public class OpenPreferringCitizen extends Citizen {

  /**
   * The constructor for Citizen
   * @param home The home tile the citizen is initialized to
   */
  public OpenPreferringCitizen(Tile home) {
      super(home);
  } 

  /**
   * Additional constructor for citizen
   * @param home The home tile the citizen is initialized to
   * @param randomNumberGenerator An instance of Random for the
   *            Citizen to use
   */
  public OpenPreferringCitizen(Tile home, Random randomNumberGenerator) {
      super(home, randomNumberGenerator);
  }

  protected void updateUtility() {
    setUtility(0 - home.getNumCitizens());
  }

  protected double getMoveProbability() {
    double moveProbability;
    double globalAverageUtil = home.getGlobalAverageUtility();
    double utility = getUtility();
    if (utility < globalAverageUtil) {
      moveProbability = (globalAverageUtil - utility)/Math.abs(globalAverageUtil + utility);
    } else {
      moveProbability = 0.0;
    }
    return moveProbability;
  }

}
