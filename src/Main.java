import java.io.*;
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application{
    // TODO: this variable can be changed
    private static final int COLUMN_COUNT = 13;
    private static final int ROW_COUNT = 10;
    private static final int BLOCK_SIZE = 50;
    private static final int SCREEN_WIDTH = BLOCK_SIZE * COLUMN_COUNT;
    private static final int SCREEN_LENGTH = BLOCK_SIZE * ROW_COUNT;
    private static final float VALUABLE_BLOCK_PROBABILITY = 0.15f;

    private final Rectangle[][] gridBlocks = new Rectangle[COLUMN_COUNT][ROW_COUNT];

    private static HashMap <String, Integer[]> valuableBlockAttributes = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        
        GameBlockManager.setBlockAttributes(BLOCK_SIZE, valuableBlockAttributes);
        pane.setPrefSize(SCREEN_WIDTH, SCREEN_LENGTH);
        
        levelBuilder(pane);

        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_LENGTH);

        primaryStage.setTitle(Texts.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private int[] getRandomXY(){
        int x = (int) ((Math.random() * (COLUMN_COUNT - 2)) + 1);
        int y = (int) ((Math.random() * (ROW_COUNT - 3)) + 3);

        // if the block is already occupied, try again
        while (gridBlocks[x][y] != null) {
            x = (int) ((Math.random() * (COLUMN_COUNT - 2)) + 1);
            y = (int) ((Math.random() * (ROW_COUNT - 3)) + 3);
        }
        return new int[]{x, y};
    }

    private void levelBuilder(Pane pane) {
        final int skyRowCount = ROW_COUNT * 2 / 10;
        final int topRowNumber = skyRowCount;
        final int lava_count = (int) (Math.random() * COLUMN_COUNT / 2) + 1;
        
        // Initialize lava blocks
        for (int i = 0; i < lava_count; i++) {
            int[] xy = getRandomXY();
            int x = xy[0];
            int y = xy[1];
            Rectangle block = GameBlockManager.getLavaBlock(x, y);
            gridBlocks[x][y] = block;
            pane.getChildren().add(block);
        }
        
        // Ensure at least tree different valuable blocks are present
        int[] xy = getRandomXY();
        gridBlocks[xy[0]][xy[1]] = GameBlockManager.getAmozoniteBlock(xy[0], xy[1]);
        pane.getChildren().add(gridBlocks[xy[0]][xy[1]]);
        xy = getRandomXY();
        gridBlocks[xy[0]][xy[1]] = GameBlockManager.getBronziumBlock(xy[0], xy[1]);
        pane.getChildren().add(gridBlocks[xy[0]][xy[1]]);
        xy = getRandomXY();
        gridBlocks[xy[0]][xy[1]] = GameBlockManager.getDiamondBlock(xy[0], xy[1]);
        pane.getChildren().add(gridBlocks[xy[0]][xy[1]]);

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (gridBlocks[j][i] != null) {
                    continue;
                }
                Rectangle block;
                if(i < skyRowCount){
                    block = GameBlockManager.getSkyBlock(j, i);
                } else if (i == topRowNumber){
                    block = GameBlockManager.getTopBlock(j, i);
                } else if (i > topRowNumber && (j == 0 || j == COLUMN_COUNT - 1)){
                    block = GameBlockManager.getObstacleBlock(j, i);
                }else {
                    if(Math.random() < VALUABLE_BLOCK_PROBABILITY){
                        block = GameBlockManager.getRandomValuableBlock(j, i);
                    } else {
                        block = GameBlockManager.getSoilBlock(j, i);
                    }
                }
                gridBlocks[j][i] = block;
                pane.getChildren().add(block);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        // read attirbutes from file
        File file = new File(Paths.ATTRIBUTE_FILE_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\t");
            String name = parts[0].trim().toLowerCase();
            int worth = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            valuableBlockAttributes.put(name, new Integer[]{worth, weight});
        }
        reader.close();

        launch(args);
    }
}
