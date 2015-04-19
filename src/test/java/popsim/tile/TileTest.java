package popsim.tile;

import org.junit.*;
import static org.junit.Assert.*;

import popsim.entity.citizen.DefaultTestCitizen;
import popsim.entity.citizen.Citizen;

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
    defaultTile.addCitizen(new DefaultTestCitizen(otherTile));
    assertEquals(defaultTile.getNumCitizens(), 1);
    defaultTile.addCitizen(new DefaultTestCitizen(otherTile));
    defaultTile.addCitizen(new DefaultTestCitizen(otherTile));
    assertEquals(defaultTile.getNumCitizens(), 3);
  }

  @Test
  public void test_AddNullCitizen() {
    // Add a new citizen
    Citizen citizen = new DefaultTestCitizen(defaultTile);
    int tileCitizensSize = defaultTile.getNumCitizens();
    defaultTile.addCitizen(null);
    assertEquals(tileCitizensSize, defaultTile.getNumCitizens());
    // Try removing a null object
    // defaultTile.removeCitizen(null);
    assertEquals(tileCitizensSize, defaultTile.getNumCitizens());
    // Try removing a citizen from another tile
    Citizen newCitizen = new DefaultTestCitizen(otherTile);
    defaultTile.removeCitizen(newCitizen);
    assertEquals(tileCitizensSize, defaultTile.getNumCitizens());
  }

  @Test
  public void test_GetUtility() {
    Citizen citizen1 = new DefaultTestCitizen(otherTile);
    defaultTile.addCitizen(citizen1);
    defaultTile.update();
    assertEquals(citizen1.getUtility(), defaultTile.getAverageUtility(), DELTA);
    Citizen citizen2 = new DefaultTestCitizen(otherTile);
    Citizen citizen3 = new DefaultTestCitizen(otherTile);
    defaultTile.addCitizen(citizen2);
    defaultTile.addCitizen(citizen3);
    defaultTile.update();
    double averageUtility = (citizen1.getUtility() + citizen2.getUtility() + citizen3.getUtility())/3.0;
    assertEquals(averageUtility, defaultTile.getAverageUtility(), DELTA);
  }

  public void test_MoveCitizens() {
    int numDefaultCitizens = defaultTile.getNumCitizens();
    Citizen citizenToMove = new DefaultTestCitizen(defaultTile);
    defaultTile.addEntityToMove(citizenToMove);

    // Adding a citizen to the list of citizens to move
    // should not actually remove them
    assertEquals(numDefaultCitizens + 1, defaultTile.getNumCitizens());
    defaultTile.moveEntities();
    assertEquals(numDefaultCitizens, defaultTile.getNumCitizens());
  }

}
