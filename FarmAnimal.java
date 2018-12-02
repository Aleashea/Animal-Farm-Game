import java.io.IOException;

public abstract class FarmAnimal extends FarmObj {
  /** Indicates whether this FarmAnimal is moving right. */
  boolean goingRight = true;

  /** Indicates whether this FarmAnimal is moving up. */
  boolean goingUp = false;

  /** Returns this object's reveased appearance. */
  protected String reverseAppearance() {
    String reverse_list = '\\' + "/\\)()><>}{}[][";
    String reverse = "";
    for (int i = appearance.length() - 1; i >= 0; i--) {
      int chIndex = reverse_list.indexOf(appearance.charAt(i));
      if (chIndex >= 0) {
        reverse += reverse_list.charAt(chIndex + 1);
      } else {
        reverse += appearance.charAt(i);
      }
    }
    return reverse;
  }

  /** Turns this FarmAnimal around, causing it to reverse direction. */
  public void turnAround() {
    goingRight = !goingRight;
    appearance = reverseAppearance();
  }
  /** This method causes the animal to release some manure on the grid. */
  public void clearStomach() {
    AnimalManure newManure = new AnimalManure();
    newManure.setLocation(y, x);
    newManure.setLocation(y, x);
    Grid.myManure[y][x] = newManure;
    Grid.animal_manure_age[y][x] = (System.currentTimeMillis() - Grid.timeStart) / 1000;
  }

  /** Causes this item to take its turn in the farm-pen simulation. */
  public void move() {}
}
