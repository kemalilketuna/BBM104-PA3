import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Try1 extends Application {

    // Grid and square sizes
    private static final int GRID_SIZE = 300;
    private static final int SQUARE_SIZE = GRID_SIZE / 3;

    // Starting position of the square
    private int squareX = 1; // Column (0-2)
    private int squareY = 1; // Row (0-2)

    // Rectangle representing the moving square
    private final Rectangle redSquare = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.RED);

    @Override
    public void start(Stage primaryStage) {
        // // Path to your music file
        // String musicFile = "fireloop.mp3";
        // // Creating a Media object
        // Media sound = new Media(getClass().getResource(musicFile).toString());
        // // Creating a MediaPlayer object
        // MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // // Play the music
        // mediaPlayer.play();


        // Create a pane to hold the grid and the square
        Pane pane = new Pane();
        pane.setPrefSize(GRID_SIZE, GRID_SIZE);

        // Draw the 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rectangle rect = new Rectangle(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                // add image to inside of rectangle
                rect.setFill(new ImagePattern(new Image("a.png")));
                // rect.setFill(null); // Uncomment this line to see the grid
                // rect.setFill(Color.PALEGREEN);
                rect.setStroke(Color.BLACK);


                pane.getChildren().add(rect);


            }
        }

        // Set initial position of the red square and add to pane
        updateSquarePosition();
        pane.getChildren().add(redSquare);

        // Handling keyboard events to move the square
        Scene scene = new Scene(pane, GRID_SIZE, GRID_SIZE);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && squareY > 0) squareY--;
            else if (event.getCode() == KeyCode.DOWN && squareY < 2) squareY++;
            else if (event.getCode() == KeyCode.LEFT && squareX > 0) squareX--;
            else if (event.getCode() == KeyCode.RIGHT && squareX < 2) squareX++;

            updateSquarePosition();
        });

        Image icon = new Image("b.png"); 
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Move Red Square");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Update the position of the red square based on the grid
    private void updateSquarePosition() {
        redSquare.setX(squareX * SQUARE_SIZE);
        redSquare.setY(squareY * SQUARE_SIZE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
