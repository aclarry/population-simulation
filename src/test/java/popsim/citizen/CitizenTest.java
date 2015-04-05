package popsim.citizen;

import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

import popsim.tile.Tile;

public class CitizenTest {

  private Tile defaultHome;
  private Citizen defaultCitizen;

  @Before
  public void before_ResetDefaultObjects() {
    defaultHome = new Tile();
    defaultCitizen = new Citizen(defaultHome);
  }

  @Test
  public void test_ChangeTile() {
    // The size of the home tile before adding any new citizens
    int defaultHomeStartSize = defaultHome.getNumCitizens();

    assertSame(defaultHome, defaultCitizen.getHomeTile());
    Tile newHome = new Tile();
    defaultCitizen.changeHomeTile(newHome);
    assertSame(newHome, defaultCitizen.getHomeTile());
    assertEquals(defaultHomeStartSize - 1, defaultHome.getNumCitizens() );
    assertEquals(1, newHome.getNumCitizens());
  }

//  @Test
//    public void test_moveTile() {
//      Random oneGenerator = new Random() {
//        @Override
//        nextDouble() {
//        return 1.0;
//        }
//      }     
//      Citizen testCitizen = new Citizen(defaultHome, oneGenerator);
//      assertSame(defaultHome, testCitizen.getHomeTile());
//      assertNotSame(defaultHome, testCitizen.getHomeTile());
//    }

}
