import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/** A Human */
public class Human extends FarmAnimal {

  public static Egg eggTarget = null;

  public static ArrayList<Egg> myBasket = new ArrayList<Egg>();

  /** Constructs a new Human. */
  public Human() {
    appearance = "human";
    colour = Color.DARKGOLDENROD;
    goingRight = true;
  }

  /** Causes human to drop down 4 piece s of food all around. */
  protected void dropFood() {
    int a1[] = {x - 1, y - 1, x - 1, y + 1, x + 1, y - 1, x + 1, y + 1};
    for (int i = 0; i < 8; i += 2) {
      AnimalFood newFood = new AnimalFood();
      newFood.setLocation(a1[i + 1], a1[i]);
      Grid.myFarmAnimals[a1[i + 1]][a1[i]] = newFood;
    }
  }

  /** Causes this item to take its turn in the farm-pensimulation. */
  @Override
  public void move() {

    if (eggTarget == null) {
      eggTarget = eggTarget.getEgg();
    }
    // Check if there's an egg to pick up.
    if (eggTarget != null) {
      this.pickupEgg();
    }
    // no egg to pick up
    // Move one spot to the right or left.
    else {
      if (goingRight) {
        if (x + 1 < Grid.xMax) {
          x += 1;
        } else {
          if (0 < x - 1) {
            x -= 1;
          }
        }
      } else {
        // Figure out whether to move up or down, or neither.
        if (random() < 0.1) {
          y += 1;
        } else if (random() < 0.2) {
          y -= 1;
        }
      }
    }

    // Figure out whether I should drop food.
    if (random() < 0.005) {
      dropFood();
    }

    // Figure out whether I turn around.
    if (random() < 0.1 || x - 1 <= 0) {
      turnAround();
    }
  }

  private void pickupEgg() {
    // Check if I am on  an egg
    if (x == eggTarget.x && y == eggTarget.y) {
      this.myBasket.add(eggTarget);
      eggTarget = null;
      Grid.myEggs[y][x] = null;
    } else {

      // move toward the egg
      if (x < eggTarget.getX()) {
        x += 1;
      } else if (x > eggTarget.getX()) {
        x -= 1;
      }
      if (y < eggTarget.getY()) {
        y += 1;
      } else if (y > eggTarget.getY()) {
        y -= 1;
      }
    }
  }

  @Override
  protected String reverseAppearance() {
    return "human";
  }
}
