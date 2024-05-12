import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Try3 extends Application {

    private static final int GRID_SIZE = 300;
    private static final int SQUARE_SIZE = GRID_SIZE / 3;

    private int squareX = 1;  // Column (0-2)
    private int squareY = 1;  // Row (0-2)

    private final Rectangle[][] gridBlocks = new Rectangle[3][3];

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(GRID_SIZE, GRID_SIZE);

        // Initialize the grid and color blocks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rectangle rect = new Rectangle(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                pane.getChildren().add(rect);
                gridBlocks[i][j] = rect;
            }
        }

        // Red square to move around
        Rectangle redSquare = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.TRANSPARENT);
        redSquare.setFill(Color.RED);
        redSquare.setX(squareX * SQUARE_SIZE);
        redSquare.setY(squareY * SQUARE_SIZE);
        pane.getChildren().add(redSquare);

        // Move the square and toggle block color when arrow key is pressed
        Scene scene = new Scene(pane, GRID_SIZE, GRID_SIZE);
        scene.setOnKeyPressed(event -> {
            int prevX = squareX;
            int prevY = squareY;
            if (event.getCode() == KeyCode.UP && squareY > 0) squareY--;
            else if (event.getCode() == KeyCode.DOWN && squareY < 2) squareY++;
            else if (event.getCode() == KeyCode.LEFT && squareX > 0) squareX--;
            else if (event.getCode() == KeyCode.RIGHT && squareX < 2) squareX++;

            if (prevX != squareX || prevY != squareY) { // If the square moved
                toggleBlockColor(prevX, prevY); // Toggle the color of the block it just left
            }
            redSquare.setX(squareX * SQUARE_SIZE);
            redSquare.setY(squareY * SQUARE_SIZE);
        });

        primaryStage.setTitle("Red Square Block Color Toggler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void toggleBlockColor(int x, int y) {
        Rectangle rect = gridBlocks[x][y];
        if (rect.getFill().equals(Color.WHITE)) {
            rect.setFill(Color.BLACK);
        } else {
            rect.setFill(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
