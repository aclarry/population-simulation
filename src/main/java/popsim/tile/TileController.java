package popsim.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileController {

  private List<Tile> tileList;
  private Random randomNumberGenerator;

  private double globalAverageUtility = 0.0;

  public TileController() {
    tileList = new ArrayList<Tile>();
    randomNumberGenerator = new Random();
  }

  public TileController(int numTiles) {
    this();
    for (int i = 0; i < numTiles; i++) {
      Tile newTile = new Tile(this, randomNumberGenerator);
      newTile.addCitizens(randomNumberGenerator.nextInt(20));
      tileList.add(newTile);
    }
  }

  public void update() {
    double totalUtilityAverage = 0.0;
    for (Tile tile : tileList) {
      tile.update();
      totalUtilityAverage += tile.getAverageUtility();
    }
    globalAverageUtility = totalUtilityAverage / tileList.size();

  }

  public Tile getTile() {
    int randomIndex = randomNumberGenerator.nextInt(tileList.size());
    return tileList.get(randomIndex);
  }

  public List<Tile> getTileList() {
    return tileList;
  }

  public List<Double> getTileUtilities() {
    List<Double> utilityList = new ArrayList<Double>();
    for (Tile tile : tileList) {
      utilityList.add(tile.getAverageUtility());
    }
    return utilityList;
  }
  
  public List<Integer> getTileNumCitizens() {
    List<Integer> citizensList = new ArrayList<Integer>();
    for (Tile tile : tileList) {
      citizensList.add(tile.getNumCitizens());
    }
    return citizensList;
  }

  public double getGlobalAverageUtility() {
    return globalAverageUtility;
  }

}
