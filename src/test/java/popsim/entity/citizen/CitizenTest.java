package popsim.entity.citizen;

import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

import popsim.tile.Tile;

public class CitizenTest {

  private Tile defaultHome;
  private int defaultHomeStartSize;
  private Citizen defaultCitizen;

  @Before
  public void before_ResetDefaultObjects() {
    defaultHome = new Tile();
    defaultCitizen = new DefaultTestCitizen(defaultHome);

    defaultHomeStartSize = defaultHome.getNumCitizens();
  }

  @Test
  public void test_ChangeTile() {
    // The size of the home tile before adding any new citizens
    assertSame(defaultHome, defaultCitizen.getHomeTile());
    Tile newHome = new Tile();
    defaultCitizen.changeHomeTile(newHome);
    assertSame(newHome, defaultCitizen.getHomeTile());
    assertEquals(defaultHomeStartSize - 1, defaultHome.getNumCitizens() );
    assertEquals(1, newHome.getNumCitizens());
  }

  @Test
  public void test_moveTile() {

    Random zeroGenerator = new Random() {
      @Override
      public double nextDouble() {
        return 0.0;
      }
    }; 

    Citizen testCitizen1 = new DefaultTestCitizen(defaultHome, zeroGenerator);
    assertTrue(testCitizen1.willMove());
    defaultHome.addEntityToMove(testCitizen1);
    defaultHome.moveEntities();
    assertNotSame(defaultHome, testCitizen1.getHomeTile());
    Citizen testCitizen2 = new DefaultTestCitizen(defaultHome, zeroGenerator);
    defaultHome.update();
    assertNotSame(defaultHome, testCitizen2.getHomeTile());
  }

}
