import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application{
    // TODO: this variable can be changed
    private static final int COLUMN_COUNT = 13;
    private static final int ROW_COUNT = 9;
    private static final int BLOCK_SIZE = 50;
    private static final int SCREEN_WIDTH = BLOCK_SIZE * COLUMN_COUNT;
    private static final int SCREEN_LENGTH = BLOCK_SIZE * ROW_COUNT;

    private final Rectangle[][] gridBlocks = new Rectangle[COLUMN_COUNT][ROW_COUNT];

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(SCREEN_WIDTH, SCREEN_LENGTH);
        GameBlockManager.BLOCK_SIZE = BLOCK_SIZE;
        levelBuilder(pane);

        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_LENGTH);

        primaryStage.setTitle(Texts.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void levelBuilder(Pane pane) {
        for (int i = 0; i < COLUMN_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                Rectangle rect = GameBlockManager.getSkyBlock(i, j);
                pane.getChildren().add(rect);
                gridBlocks[i][j] = rect;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
