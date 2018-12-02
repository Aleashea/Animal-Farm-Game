import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;

public class Grid {

  final static public Image background = new Image("/BackgroundImages/backgroundmain.png");

  /** The width of the screen. */
  public static final int screenWidth = 800;

  /** The height of the screen. */
  public static final int screenHeight = 600;

  /** The width of a character. */
  public static final int charWidth = 6;
  /** The height of a character. */
  public static final int charHeight = 10;

  /** The maximum that a vertical coordinate can be on the screen. */
  public static int yMax = screenHeight / charHeight;

  /** The maximum that a horizontal coordinate can be on the screen. */
  public static int xMax = screenWidth / charWidth;

  /** Keeps track of all the farmAnimals and AnimalFood on the screen. */
  public static Object[][] myFarmAnimals = new Object[Grid.yMax][Grid.xMax];

  /** Keeps track of all the eggs on the screen. */
  public static Object[][] myEggs = new Object[Grid.yMax][Grid.xMax];

  public static Object[][] myVenom = new Object[Grid.yMax][Grid.xMax];

  /** Keeps track of all the manure on the screen. */
  public static Object[][] myManure = new Object[Grid.yMax][Grid.xMax];

  /** Keeps track of all the manure's ages on the screen. */
  public static Long[][] animal_manure_age = new Long[Grid.yMax][Grid.xMax];

  /** The time when the application starts running. */
  public static long timeStart;

  /** The current time in the game. */
  public static double t = (System.currentTimeMillis() - timeStart) / 1000.0;

  /** Initializes the animals in the game. */
  public static void initializeAnimals() throws IOException {
    Grid.myFarmAnimals[0][0] = new Chicken();
    ((Chicken) Grid.myFarmAnimals[0][0]).setLocation(23, 18);
    Grid.myFarmAnimals[6][12] = new Chicken();
    ((Chicken) Grid.myFarmAnimals[6][12]).setLocation(6, 12);
    Grid.myFarmAnimals[16][22] = new Chicken();
    ((Chicken) Grid.myFarmAnimals[16][22]).setLocation(16, 18);
    Grid.myFarmAnimals[23][18] = new Chicken();
    ((Chicken) Grid.myFarmAnimals[23][18]).setLocation(23, 18);
    Grid.myFarmAnimals[6][12] = new Chicken();
    ((Chicken) Grid.myFarmAnimals[6][12]).setLocation(6, 12);
    Grid.myFarmAnimals[10][20] = new Pig();
    ((Pig) Grid.myFarmAnimals[10][20]).setLocation(10, 20);
    Grid.myFarmAnimals[20][10] = new Pig();
    ((Pig) Grid.myFarmAnimals[20][10]).setLocation(20, 10);
    Grid.myFarmAnimals[15][15] = new Cow();
    ((Cow) Grid.myFarmAnimals[15][15]).setLocation(15, 15);
    Grid.myFarmAnimals[30][30] = new Human();
    ((Human) Grid.myFarmAnimals[30][30]).setLocation(30, 30);
  }

  public Image getBackground() {
    return background;
  }

  public static void drawBackground(GraphicsContext g) {
    g.drawImage(background, 0, 0);
  }

  /**
   * Draws the egg counter on the upper left corner of the screen.
   *
   * @param g the graphics context in which to draw this item.
   */
  public static void drawEggCounter(GraphicsContext g) {
    Rectangle rectangle = new Rectangle(20, 20 * Grid.charHeight, 10, 10);
    g.fillRect(50, 50 * Grid.charHeight, 50, 50);
    g.fillText("Eggs: " + Human.myBasket.size(), 10 * Grid.charWidth, 20 * Grid.charHeight);
  }



  /**
   * Clears the Manure off the screen that has been sitting for longer than 10 seconds of game time.
   */
  public static void clearManure() {
    for (int a = 0; a != Grid.yMax; a++) {
      for (int b = 0; b != Grid.xMax; b++) {
        Long currentManureAge = Grid.animal_manure_age[a][b];
        if (currentManureAge != null) {
          t = (System.currentTimeMillis() - timeStart) / 1000;
          // Check if manure is older than 10 seconds.
          if ((Grid.t - currentManureAge) > 30) {
            // Clear the manure.
            Grid.animal_manure_age[a][b] = null;
            Grid.myManure[a][b] = null;
          }
        }
      }
    }
  }

  /** Move all the characters currently existing on the grid. */
  public static void move() {
    for (int y = 0; y != Grid.yMax; y++) {
      for (int x = 0; x != Grid.xMax; x++) {
        if (Grid.myFarmAnimals[y][x] != null) {
          if (Grid.myFarmAnimals[y][x] instanceof FarmAnimal) {
            ((FarmAnimal) Grid.myFarmAnimals[y][x]).move();
          } else if (Grid.myFarmAnimals[y][x] instanceof AnimalFood) {
            ((AnimalFood) Grid.myFarmAnimals[y][x]).move();
          }
          Grid.clearManure();
        }
      }
    }
  }
  /** Draw the current characters, objects and egg counter on the Grid. */
  public static void drawShapes(GraphicsContext gc) {
    // Tell all the farmyard items to draw themselves.
    Grid.drawEggCounter(gc);
    Grid.drawBackground(gc);
    for (int y = 0; y != Grid.yMax; y++) {
      for (int x = 0; x != Grid.yMax; x++) {
        if (Grid.myFarmAnimals[y][x] != null) {
          if (Grid.myFarmAnimals[y][x] instanceof Chicken) {
            ((Chicken) Grid.myFarmAnimals[y][x]).draw(gc);
          }
          else {
            ((FarmObj) Grid.myFarmAnimals[y][x]).draw(gc);
          }
        }
        if (Grid.myManure[y][x] != null) {
          ((FarmObj) Grid.myManure[y][x]).draw(gc);
        }
        if (Grid.myEggs[y][x] != null) {
          ((FarmObj) Grid.myEggs[y][x]).draw(gc);
        }
        if (Grid.myVenom[y][x] != null) {
          ((FarmObj) Grid.myVenom[y][x]).draw(gc);
        }
      }
    }
  }
}
