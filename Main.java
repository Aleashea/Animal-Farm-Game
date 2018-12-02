import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

/** Our take on the "classical" game Farm Ville */
public class Main extends Application implements EventHandler {

  public static void main(String[] args) {
    launch(args);
  }
  StackPane holder = new StackPane();
  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("FarmVille");

    Group root = new Group();
    Scene theScene = new Scene(root);
    primaryStage.setScene(theScene);
    Canvas canvas = new Canvas(Grid.screenWidth, Grid.screenHeight);
    root.getChildren().add(canvas);
    Pane root2 = new Pane();
    StackPane holder = new StackPane();
    Button button = new Button();
    holder.getChildren().add(canvas);
    root.getChildren().add(holder);
    Rectangle rectangle2 = new Rectangle(650, 5, 100, 20);
    rectangle2.setFill(Gre);
    rectangle2.setArcWidth(20.0);
    rectangle2.setArcHeight(10.0);
    root.getChildren().add(rectangle2);

    GraphicsContext gc = canvas.getGraphicsContext2D();

    Grid.initializeAnimals();
    Grid.drawShapes(gc);
    Timeline gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);
    final long timeStart = System.currentTimeMillis();
    Grid.timeStart = timeStart;

    KeyFrame kf =
        new KeyFrame(
            Duration.seconds(0.5),
            new EventHandler<ActionEvent>() {
              public void handle(ActionEvent ae) {
                Grid.move();
                // Clear the canvas
                gc.clearRect(0, 0, 1024, 720);
                Grid.drawShapes(gc);
                //Image img2 = new Image(getClass().getResourceAsStream("/AnimalImages/chicken1.png"));
                //gc.drawImage(img2, 200, 200, 70, 70);
              }
            });

    gameLoop.getKeyFrames().add(kf);
    gameLoop.play();
    primaryStage.show();
  }

  @Override
  public void handle(Event event) {

  }
}
