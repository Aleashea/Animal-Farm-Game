import javafx.scene.paint.Color;

/** An egg that a farmer collects. */
public class Egg extends FarmObj {

  public Egg() {
    appearance = "" + 'o';
    colour = Color.ROSYBROWN;
  }

  /** Returns an egg on the farm */
  public static Egg getEgg() {
    for (int y = 0; y != Grid.myEggs.length; y++) {
      for (int x = 0; x != Grid.myEggs[0].length; x++)
        if (Grid.myEggs[y][x] instanceof Egg) {
          return (Egg) Grid.myEggs[y][x];
        }
    }
    return null;
  }

  public static Egg getEgg2() {
    for (int y = Grid.myEggs.length - 1; y != 0; y--) {
      for (int x = Grid.myEggs[0].length - 1; x != 0; x--)
        if (Grid.myEggs[y][x] instanceof Egg) {
          return (Egg) Grid.myEggs[y][x];
        }
    }
    return null;
  }
}
