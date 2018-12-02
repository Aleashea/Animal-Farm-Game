import javafx.scene.paint.Color;

/** Constructs some Animal Food. */
public class AnimalFood extends FarmObj {

  /**
   * Indicates whether the wind is moving vertically or not. -1 if the wind is blowing up. 1 if the
   * wind is blowing down. 0 if the wind isn't blowing.
   */
  private static int lastUp = 0;

  /**
   * Indicates whether the wind is moving horizontally or not. -1 if the wind is blowing left. 1 if
   * the wind is blowing right. 0 if the wind isn't blowing.
   */
  private static int lastLeft = 0;

  public AnimalFood() {
    colour = Color.GRAY.darker().darker().darker();
    // start off with o as the appearance cause all animal food looks the same
    appearance = "%";
  }

  /**
   * Causes this item to take its turn in the farm-pen simulation, blown due to strong winds. Up,
   * Down, Right and Left in this case
   */
  public void move() {

    windBlowingUp();
    windBlowingLeft();
    if (lastUp == -1) {
      // Move upwards
      if (y + 1 < Grid.yMax) {
        y += 1;
      }
    } else if (lastUp == 1) {
      // Move downwards
      if (y - 1 > 0) {
        y -= 1;
      }
    }

    if (lastLeft == -1) {
      // Move Left
      if (x - 1 > 0) {
        x -= 1;
      } //
    } else if (lastLeft == 1) {
      // Move Right
      if (x + 1 < Grid.xMax) {
        x = x + 1;
      }
    }
  }

  /**
   * This method determines the vertical wind direction. -1 if the wind is blowing up. 1 if the wind
   * is blowing down. 0 if the wind isn't blowing. Keep blowing the same direction 30% the time.
   * Turn around 10% of the time. Otherwise no wind.
   */
  public static int windBlowingUp() {

    if (lastUp != 0)
      if (Math.random() < 0.3) {
        lastUp = lastUp;
      } else if (Math.random() < 0.1) {
        lastUp = -lastUp;
        return lastUp;
      } else lastUp = 0;
    else
    // lastUp was zero. Change wind 10% updown each.
    if (Math.random() < 0.1) {
      lastUp = -1;

    } else if (Math.random() < 0.1) {
      lastUp = 1;
    }
    return lastUp;
  }

  /**
   * This method determines the vertical wind direction. -1 if the wind is blowing up. 1 if the wind
   * is blowing down. 0 if the wind isn't blowing. Keep blowing the same direction 30% the time.
   * Turn around 10% of the time. Otherwise no wind.
   */
  public static int windBlowingLeft() {
    if (lastLeft != 0)
      if (Math.random() < 0.3) {
        lastLeft = lastLeft;
      } else if (Math.random() < 0.1) {
        lastLeft = -lastLeft;
        return lastUp;
      } else lastLeft = 0;
    else
    // lastUp was zero. Change wind 10% updown each.
    if (Math.random() < 0.1) {
      lastLeft = -1;

    } else if (Math.random() < 0.1) {
      lastLeft = 1;
    }
    return lastLeft;
  }
}
