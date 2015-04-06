package popsim.application;

import popsim.tile.TileController;

public class BasicApp {
  
  public static void main (String[] args) {
    TileController controller = new TileController(6); 
    for (int i = 0; i < 20; i ++) {
        
      controller.update();

      
      String utilities = "Step " + i + " Utilities:\t";
      for (double utility : controller.getTileUtilities()) {
        utilities += utility + "\t";
      }
      String numCitizensString = "Step " + i + " Num Citizens:\t";
      for (int numCitizens : controller.getTileNumCitizens()) {
        numCitizensString += numCitizens + "\t";
      }
//      System.out.println(utilities);
      System.out.println(numCitizensString);
    }
      
  }

}
