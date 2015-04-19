package popsim.entity.firm;

import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

import popsim.tile.Tile;

public class FirmTest {

  private Tile defaultHome;
  private int defaultHomeStartSize;
  private Firm defaultFirm;

  @Before
  public void before_ResetDefaultObjects() {
    defaultHome = new Tile();
    defaultFirm = new DefaultTestFirm(defaultHome);

    defaultHomeStartSize = defaultHome.getNumFirms();
  }

  @Test
  public void test_ChangeTile() {
    // The size of the home tile before adding any new firms
    assertSame(defaultHome, defaultFirm.getHomeTile());
    Tile newHome = new Tile();
    defaultFirm.changeHomeTile(newHome);
    assertSame(newHome, defaultFirm.getHomeTile());
    assertEquals(defaultHomeStartSize - 1, defaultHome.getNumFirms() );
    assertEquals(1, newHome.getNumFirms());
  }

  @Test
  public void test_moveTile() {

    Random zeroGenerator = new Random() {
      @Override
      public double nextDouble() {
        return 0.0;
      }
    }; 

    Firm testFirm1 = new DefaultTestFirm(defaultHome, zeroGenerator);
    assertTrue(testFirm1.willMove());
    defaultHome.addEntityToMove(testFirm1);
    defaultHome.moveEntities();
    assertNotSame(defaultHome, testFirm1.getHomeTile());
    Firm testFirm2 = new DefaultTestFirm(defaultHome, zeroGenerator);
    defaultHome.update();
    assertNotSame(defaultHome, testFirm2.getHomeTile());
  }

}
