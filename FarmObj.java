import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class FarmObj {
  /** How this object appears on the screen. */
  protected String appearance;

  /** What color the object appears on the screen as. */
  protected Color colour;

  /** This object's horizontal coordinate on the screen */
  protected int x;

  /** This object's vertical coordinate on the screen */
  protected int y;

  /**
   * Set this item's location.
   *
   * @param x the first coordinate.
   * @param y the second coordinate.
   */
  public void setLocation(int y, int x) {
    // set x to x
    this.y = y;
    // set y to y
    this.x = x;
  }

  /** Returns this object's horizontal coordinate. */
  public int getX() {
    return x;
  }
  /** Returns this object's vertical coordinate. */
  public int getY() {
    return y;
  }

  /**
   * Draws the given string in the given graphics context at at the given cursor location.
   *
   * @param g the graphics context in which to draw the string.
   * @param s the string to draw.
   * @param x the x-coordinate of the string's cursor location.
   * @param y the y-coordinate of the string's cursor location.
   */
  public void drawString(GraphicsContext g, String s, int y, int x) {
    g.setFill(colour);
    g.fillText(s, x * Grid.charWidth, y * Grid.charHeight);
  }

  /**
   * Draws this farm pen item.
   *
   * @param g the graphics context in which to draw this item.
   */
  public void draw(GraphicsContext g) {
    drawString(g, appearance, y, x);
  }

  /** Returns a random number for this object. */
  public double random() {
    return Math.random();
  }
}
