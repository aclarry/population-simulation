package popsim.entity.citizen;

import java.util.Random;
import popsim.tile.Tile;


public class DefaultTestCitizen extends Citizen {

  public DefaultTestCitizen(Tile home) {
    super(home);
  }

  public DefaultTestCitizen(Tile home, Random random) {
    super(home, random);
  }

  protected void updateUtility() {
    setUtility(0 - home.getNumCitizens());
  }

  protected double getMoveProbability() {
    double moveProbability;
    double utility = getUtility();
    double globalAverageUtil = home.getGlobalAverageUtility();
    if (utility < globalAverageUtil) {
      moveProbability = (globalAverageUtil - utility)/Math.abs(globalAverageUtil + utility);
    } else {
      moveProbability = 0.0;
    }
    return moveProbability;
  }

}
