package popsim.citizen;

import org.junit.*;
import static org.junit.Assert.*;

import popsim.tile.Tile;

public class CitizenTest {

  private Tile defaultHome;
  private Citizen defaultCitizen;

  @Before
  public void before_ResetDefaultObjects() {
    System.out.println("Resetting default objects");

    defaultHome = new Tile();
    defaultCitizen = new Citizen(defaultHome);
  }

  @Test
  public void test_ChangeTile() {
    System.out.println("Testing tile updating");
    int defaultHomeStartSize = defaultHome.getNumCitizens();

    assertSame(defaultHome, defaultCitizen.getHomeTile());
    Tile newHome = new Tile();
    defaultCitizen.changeHomeTile(newHome);
    assertSame(newHome, defaultCitizen.getHomeTile());
    assertEquals(defaultHomeStartSize - 1, defaultHome.getNumCitizens() );
    assertEquals(1, newHome.getNumCitizens());
  }

}
