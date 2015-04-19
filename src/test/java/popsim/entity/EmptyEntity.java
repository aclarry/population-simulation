package popsim.entity;

import popsim.tile.Tile;
import java.util.Random;
public class EmptyEntity extends Entity {

  public EmptyEntity(Tile home) {
    super(home);
  }

  public EmptyEntity(Tile home, Random randomNumberGenerator) {
    super(home, randomNumberGenerator);
  }

  public void update() {

  }

}

