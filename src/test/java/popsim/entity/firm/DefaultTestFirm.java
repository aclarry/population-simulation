package popsim.entity.firm;

import java.util.Random;
import popsim.tile.Tile;


public class DefaultTestFirm extends Firm {

  public DefaultTestFirm(Tile home) {
    super(home);
  }

  public DefaultTestFirm(Tile home, Random random) {
    super(home, random);
  }

  protected void updateProfit() {
    setProfit(0 - home.getNumCitizens());
  }

  protected double getMoveProbability() {
    double moveProbability;
    double profit = getProfit();
    double globalAverageProfit = home.getAverageProfit();
    if (profit < globalAverageProfit) {
      moveProbability = (globalAverageProfit - profit)/Math.abs(globalAverageProfit + profit);
    } else {
      moveProbability = 0.0;
    }
    return moveProbability;
  }

}
