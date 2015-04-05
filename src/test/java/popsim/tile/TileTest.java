package popsim.tile;

import org.junit.*;
import static org.junit.Assert.*;

import popsim.citizen.*;

public class TileTest {
  
  private final double DELTA = 0.001;
  private Tile defaultTile, otherTile;

  @Before
  public void create_otherTile() {
    System.out.println("Resetting test objects");

    defaultTile = new Tile();
    otherTile = new Tile();
  }

  @Test
  public void test_AddCitizen() {
    System.out.println("Testing adding consumers");

    assertEquals(defaultTile.getNumCitizens(), 0);
    defaultTile.addCitizen(new Citizen(otherTile));
    assertEquals(defaultTile.getNumCitizens(), 1);
    defaultTile.addCitizen(new Citizen(otherTile));
    defaultTile.addCitizen(new Citizen(otherTile));
    assertEquals(defaultTile.getNumCitizens(), 3);
  }

  @Test
  public void test_GetUtility() {
    System.out.println("Testing utility getting");

    Citizen citizen1 = new Citizen(otherTile);
    defaultTile.addCitizen(citizen1);
    defaultTile.update();
    assertEquals(citizen1.getUtility(), defaultTile.getAverageUtility(), DELTA);
    Citizen citizen2 = new Citizen(otherTile);
    Citizen citizen3 = new Citizen(otherTile);
    defaultTile.addCitizen(citizen2);
    defaultTile.addCitizen(citizen3);
    defaultTile.update();
    double averageUtility = (citizen1.getUtility() + citizen2.getUtility() + citizen3.getUtility())/3.0;
    assertEquals(averageUtility, defaultTile.getAverageUtility(), DELTA);
    
  }

}
