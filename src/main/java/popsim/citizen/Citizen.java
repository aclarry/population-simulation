package popsim.citizen;

import popsim.tile.Tile;


public class Citizen {
  
  private Tile home;
  private double utility;

  public Citizen(Tile home) {
    this.home = home;
    home.addCitizen(this);
  }
 
  public void update() {
    updateUtility();
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

   

}
