import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/** Constructs some Animal Manure. */
public class AnimalManure extends FarmObj {
  Image manureappearance;

  public Image img1, img2, img3, img4, img5, img6;
  int counter = 0;

  public AnimalManure() {
    colour = Color.BLACK.darker().darker().darker();
    img1 = new Image(getClass().getResourceAsStream("/AnimalImages/test/Coin1.png"));
    img2 = new Image(getClass().getResourceAsStream("/AnimalImages/test/Coin2.png"));
    img3 = new Image(getClass().getResourceAsStream("/AnimalImages/test/Coin3.png"));
    img4 = new Image(getClass().getResourceAsStream("/AnimalImages/test/Coin4.png"));
    img5 = new Image(getClass().getResourceAsStream("/AnimalImages/test/Coin5.png"));
    img6 = new Image(getClass().getResourceAsStream("/AnimalImages/test/Coin6.png"));
    manureappearance = new Image(getClass().getResourceAsStream("/AnimalImages/coin2.png"));
    appearance = ".";
  }
  @Override
  public void drawString(GraphicsContext g, String s, int y, int x) {
      if (counter >= 20) {
          counter = 0;
          g.drawImage(img6,x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.6, img2.getHeight()*.6);
      }
      if (counter == 0) {
          g.drawImage(img1, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.6, img2.getHeight()*.6);
      }
      if (counter == 1) {
          g.drawImage(img2, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.6, img2.getHeight()*.6);
      }
      if (counter == 2) {
          g.drawImage(img3, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.6, img2.getHeight()*.6);
      }
      if (counter == 3) {
          g.drawImage(img4, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.6, img2.getHeight()*.6);
      }
      if (counter == 4) {
          g.drawImage(img5, x * Grid.charWidth, y*Grid.charHeight, img2.getWidth()*.6, img2.getHeight()*.6);
      }
      counter++;
  }
  /** Set the appearance of this object on the screen */
  public void setAppearance(String newAppearance) {
    appearance = newAppearance;
  }
}
