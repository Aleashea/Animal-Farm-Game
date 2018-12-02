import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Chicken extends FarmAnimal {

  BufferedImage appearance2;
  Image img;
  int counter = 0;
  Image img2;
  Image img3;
  /** Constructs a new Chicken. */
  public Chicken() {
    if (goingRight) {
      img2 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken1right.png"));
      img3 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken2right.png"));
    }
    else {
      img2 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken1.png"));
      img3 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken2.png"));
    }
    colour = Color.RED;
    goingRight = true;
    goingUp = true;
  }

  @Override
  public void drawString(GraphicsContext g, String s, int y, int x) {
    if (counter >= 2) {
      counter = 0;
    }
    if (counter == 0) {
    g.drawImage(img2, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.2, img2.getHeight()*.2);
  }
    if (counter == 1) {
      g.drawImage(img3, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.2, img2.getHeight()*.2);
    }
    counter++;
  }

  @Override
  public String reverseAppearance() {
    if (goingRight) {
      img2 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken1right.png"));
      img3 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken2right.png"));
    }
    else {
      img2 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken1.png"));
      img3 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken2.png"));
    }
    return "";
  }
  /** Turns this chicken around, causing it to reverse direction. */
  protected void turnAroundVertical() {
    goingUp = !goingUp;
  }

  /** Causes this Chicken to take its turn in the farm-pen simulation. */
  @Override
  public void move() {

    if (random() < 0.1) {
      clearStomach();
    }
    // Move one spot to the right or left.
    if (goingRight) {
      if (x + 1 < Grid.xMax) {
        x += 1;
      } else {
        turnAround();
      }
    } else {
      if (0 < x - 1) {
        x -= 1;
      } else {
        turnAround();
      }
    }
    // Move one spot to the up or down.
    if (goingUp) {
      if (y + 1 < Grid.yMax) {
        y += 1;
      }
    } else {
      if (0 < y - 1) {
        y -= 1;
      }
    }

    // Every now and then lay an egg.
    if (random() < 0.03) {
      layEgg();
      turnAround();
    }
    if (random() > 0.8 || (y - 1) <= 0) {
      turnAroundVertical();
    }
  }

  /** Lay an egg. */
  private void layEgg() {
    Egg egg = new Egg();
    egg.setLocation(y, x);
    Grid.myEggs[y][x] = egg;
  }
}
