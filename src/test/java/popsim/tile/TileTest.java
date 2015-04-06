package popsim.tile;

import org.junit.*;
import static org.junit.Assert.*;

import popsim.citizen.*;

public class TileTest {
  
  private final double DELTA = 0.001;
  private Tile defaultTile, otherTile;

  @Before
  public void create_otherTile() {
    defaultTile = new Tile();
    otherTile = new Tile();
  }

  @Test
  public void test_AddCitizen() {
    assertEquals(defaultTile.getNumCitizens(), 0);
    defaultTile.addCitizen(new Citizen(otherTile));
    assertEquals(defaultTile.getNumCitizens(), 1);
    defaultTile.addCitizen(new Citizen(otherTile));
    defaultTile.addCitizen(new Citizen(otherTile));
    assertEquals(defaultTile.getNumCitizens(), 3);
  }

  @Test
  public void test_AddNullCitizen() {
    // Add a new citizen
    Citizen citizen = new Citizen(defaultTile);
    int tileCitizensSize = defaultTile.getNumCitizens();
    defaultTile.addCitizen(null);
    assertEquals(tileCitizensSize, defaultTile.getNumCitizens());
    // Try removing a null object
    // defaultTile.removeCitizen(null);
    assertEquals(tileCitizensSize, defaultTile.getNumCitizens());
    // Try removing a citizen from another tile
    Citizen newCitizen = new Citizen(otherTile);
    defaultTile.removeCitizen(newCitizen);
    assertEquals(tileCitizensSize, defaultTile.getNumCitizens());
  }

  @Test
  public void test_GetUtility() {
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

  public void test_MoveCitizens() {
    int numDefaultCitizens = defaultTile.getNumCitizens();
    Citizen citizenToMove = new Citizen(defaultTile);
    defaultTile.addCitizenToMove(citizenToMove);

    // Adding a citizen to the list of citizens to move
    // should not actually remove them
    assertEquals(numDefaultCitizens + 1, defaultTile.getNumCitizens());
    defaultTile.moveCitizens();
    assertEquals(numDefaultCitizens, defaultTile.getNumCitizens());
    
  }

}
