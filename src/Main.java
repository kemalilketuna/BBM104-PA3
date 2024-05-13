import java.io.*;
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application{
    // TODO: this variable can be changed
    private final int COLUMN_COUNT = 13;
    private final int ROW_COUNT = 10;
    private final int BLOCK_SIZE = 50;
    private final int SCREEN_WIDTH = BLOCK_SIZE * COLUMN_COUNT;
    private final int SCREEN_LENGTH = BLOCK_SIZE * ROW_COUNT;
    private final float VALUABLE_BLOCK_PROBABILITY = 0.15f;
    private final int skyRowCount = ROW_COUNT * 2 / 10;

    private final Rectangle[][] gridBlocks = new Rectangle[COLUMN_COUNT][ROW_COUNT];
    private final static HashMap <String, Integer[]> valuableBlockAttributes = new HashMap<>();
    
    private int drillX = COLUMN_COUNT / 2;
    private int drillY = 1;
    private int fuel = 10;
    private int collectedMoney = 0;
    private int weight = 0;

    private int idleFuelConsumption = 1;

    private Rectangle drillMachine;
    private Text fuelText;
    private Text collectedMoneyText;
    private Text weightText;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        pane.setPrefSize(SCREEN_WIDTH, SCREEN_LENGTH);
        
        GameBlockManager.setBlockAttributes(BLOCK_SIZE, valuableBlockAttributes);
        levelBuilder(pane);
        initializeDrillMachine(pane);
        initializeTexts(pane);

        // Create a Timeline
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            gravityFall();
            fuel -= idleFuelConsumption;
            updateTexts();
            if (fuel <= 0) {
                InfoScreens.showGreenScreen(primaryStage, pane, collectedMoney);
                timeline.stop();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_LENGTH);
        scene.setOnKeyPressed(event -> {
            processMove(event);
        });

        // Properties of the stage
        primaryStage.setTitle(TextCaptions.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void processMove(KeyEvent event){
        if (event.getCode() == KeyCode.UP && drillY > 0) fly();
        else if (event.getCode() == KeyCode.DOWN && drillY < ROW_COUNT - 1) drillY++;
        else if (event.getCode() == KeyCode.LEFT && drillX > 0) drillX--;
        else if (event.getCode() == KeyCode.RIGHT && drillX < COLUMN_COUNT - 1) drillX++;
        drillMachine.setX(drillX * BLOCK_SIZE);
        drillMachine.setY(drillY * BLOCK_SIZE);
    }

    private void drill(){

    }

    private void updateTexts(){
        fuelText.setText(TextCaptions.getFuelString(fuel));
        collectedMoneyText.setText(TextCaptions.getCollectedMoneyString(collectedMoney));
        weightText.setText(TextCaptions.getWeightString(weight));
    }

    private void fly(){
        if(drillY <= skyRowCount - 1){
            return;
        }
        Rectangle block = gridBlocks[drillX][drillY - 1];
        if (block instanceof DrilledBlock || block instanceof SkyBlock) {
            drillY--;
            drillMachine.setY(drillY * BLOCK_SIZE);
        }
    }

    private void gravityFall(){
        if (drillY == ROW_COUNT - 1) {
            return;
        }
        Rectangle block = gridBlocks[drillX][drillY + 1];
        if (block instanceof DrilledBlock) {
            drillY++;
            drillMachine.setY(drillY * BLOCK_SIZE);
        }
    }

    private void initializeDrillMachine(Pane pane){
        drillMachine = new DrillMachine(drillX, drillY, BLOCK_SIZE);
        pane.getChildren().add(drillMachine);
    }

    private void initializeTexts(Pane pane){
        fuelText = new TextElement(TextCaptions.getFuelString(fuel), 20, 20);
        pane.getChildren().add(fuelText);

        collectedMoneyText = new TextElement(TextCaptions.getCollectedMoneyString(collectedMoney), 20, BLOCK_SIZE/2 + 20);
        pane.getChildren().add(collectedMoneyText);

        weightText = new TextElement(TextCaptions.getWeightString(weight), 20, BLOCK_SIZE + 20);
        pane.getChildren().add(weightText);
    }

    private int[] getRandomXY(){
        int x = (int) ((Math.random() * (COLUMN_COUNT - 2)) + 1);
        int y = (int) ((Math.random() * (ROW_COUNT - (skyRowCount + 2))) + 3);

        // if the block is already occupied, try again
        while (gridBlocks[x][y] != null) {
            x = (int) ((Math.random() * (COLUMN_COUNT - 2)) + 1);
            y = (int) ((Math.random() * (ROW_COUNT - (skyRowCount + 2))) + 3);
        }
        return new int[]{x, y};
    }

    private void levelBuilder(Pane pane) {
        int lava_count = (int) (Math.random() * COLUMN_COUNT) + 1;
        
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
                } else if (i == skyRowCount){
                    block = GameBlockManager.getTopBlock(j, i);
                } else if (i == ROW_COUNT - 1){
                    block = GameBlockManager.getObstacleBlock(j, i);
                }
                else if (i > skyRowCount && (j == 0 || j == COLUMN_COUNT - 1)){
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
