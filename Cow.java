import javafx.scene.paint.Color;

public class Cow extends FarmAnimal {

  /** Constructs a new Cow. */
  public Cow() {
    colour = Color.PINK.darker();
    appearance = "MOO";
    goingRight = false;
  }

  /** Causes this Cow to take its turn in the farm-pen simulation. */
  @Override
  public void move() {

    if (random() < 0.1 || (x - 1) <= 0 || (x + 1) >= Grid.xMax) {
      turnAround();
    }
    // Move one spot to the right or left.
    if (goingRight) {
      if (x + 1 < Grid.xMax) {
        x += 1;
      }
    } else {
      if (0 <= x - 1) {
        x -= 1;
      }
    }

    // Sometimes we digest.
    if (random() < 0.2) {
      clearStomach();
    }
  }

  @Override
  protected String reverseAppearance() {
    return "MOO";
  }

  @Override
  public final void clearStomach() {
    AnimalManure newManure = new AnimalManure();
    newManure.setAppearance("&");
    newManure.setLocation(y, x);
    Grid.myManure[y][x] = newManure;
    Grid.animal_manure_age[y][x] = (System.currentTimeMillis() - Grid.timeStart) / 1000;
  }
}
