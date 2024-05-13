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

/*
 * This class is the main class of the game, responsible for game logic, game loop,
 * initialization, user input handling, game screen display, and game over condition. 
*/
public class Main extends Application{
    // This variable can be changed if game is too easy or too hard
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
    private int fuel = 1000;
    private int collectedMoney = 0;
    private int weight = 0;

    private int idleFuelConsumption = 3;
    private int moveFuelConsumption = 10;

    private DrillMachine drillMachine;
    private Text fuelText;
    private Text collectedMoneyText;
    private Text weightText;
    private Stage stage;
    private Boolean gameOver = false;
    private Pane pane;
    private Boolean isFlying = false;

    /**
     * Starts the game by setting up the game window, initializing game elements,
     * and creating a game loop.
     *
     * <p>This method is called when the game starts. It performs the following steps:</p>
     * <ol>
     *   <li>Sets up the game window by creating a {@link Pane} and setting its background color and size.</li>
     *   <li>Initializes game elements such as the game blocks, drill machine, and text displays.</li>
     *   <li>Creates a game loop using a {@link Timeline} that updates the game state every 0.5 seconds.</li>
     *   <li>Handles gravity and fuel consumption in each iteration of the game loop.</li>
     *   <li>Sets up a {@link KeyEvent} listener to process user input for moving the drill machine.</li>
     *   <li>Sets the title, scene, and other properties of the primary stage.</li>
     *   <li>Shows the primary stage to display the game window.</li>
     * </ol>
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        pane = new Pane();

        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        pane.setPrefSize(SCREEN_WIDTH, SCREEN_LENGTH);
        
        GameBlockManager.setBlockAttributes(BLOCK_SIZE, valuableBlockAttributes);
        levelBuilder();
        initializeDrillMachine();
        initializeTexts();

        // Create a Timeline
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), event -> {
            if (gameOver) {
                timeline.stop();
            }
            gravityFall();
            useFuel(idleFuelConsumption);
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

    /*
     * This method handles the user input and moves the drill machine accordingly.
     * 
     * @param event the key event to be processed
     */
    private void processMove(KeyEvent event){
        if (event.getCode() == KeyCode.UP) fly();
        else if (event.getCode() == KeyCode.DOWN) move(drillX, drillY+1);
        else if (event.getCode() == KeyCode.LEFT) {
            drillMachine.lookLeft();
            move(drillX-1, drillY);
        }
        else if (event.getCode() == KeyCode.RIGHT){
            drillMachine.lookRight();
            move(drillX+1, drillY);
        }
    }


    /**
     * Moves the drill to the specified position on the grid.
     *
     * @param x The x-coordinate of the target position.
     * @param y The y-coordinate of the target position.
     *
     * <p>
     * The method checks the validity of the target position and performs actions based on the encountered block type:
     * </p>
     *
     * <ul>
     *   <li>If the block below the drill is a {@link DrilledBlock}, the drill can move to a {@link DrilledBlock} or {@link SkyBlock}.</li>
     *   <li>If the target block is an {@link ObstacleBlock}, no action is performed.</li>
     *   <li>If the target block is a {@link LavaBlock}, the game is over.</li>
     *   <li>If the target block is a {@link ValuableBlock}, collected money and weight are updated.</li>
     * </ul>
     *
     * <p>
     * The drill position is updated, and the target block is replaced with a {@link DrilledBlock}.
     * Game texts are updated to reflect the changes.
     * </p>
     */
    private void move(int x, int y){
        if (x < 0 || x >= COLUMN_COUNT || y < 0 || y >= ROW_COUNT) {
            return;
        }

        Rectangle floor = gridBlocks[drillX][drillY+1];
        Rectangle block = gridBlocks[x][y];

        isFlying = false;

        if (floor instanceof DrilledBlock) {
            if (block instanceof DrilledBlock || block instanceof SkyBlock) {
                updateDrillPosition(x, y);
                drillMachine.idle();
            }
            return;
        }

        if (block instanceof ObstacleBlock) {
            return;
        } else if (block instanceof LavaBlock) {
            InfoScreens.showRedScreen(stage, SCREEN_LENGTH, SCREEN_WIDTH);
            gameOver = true;
            return;
        } else{
            if(drillY < y || (block instanceof SkyBlock) || (block instanceof DrilledBlock)){
                drillMachine.idle();
            }else{
                drillMachine.drill();
                SoundPlayer.playDrillSound();
            }
            if(block instanceof ValuableBlock){
                ValuableBlock valuableBlock = (ValuableBlock) block;
                collectedMoney += valuableBlock.getWorth();
                weight += valuableBlock.getWeight();
            }
            updateDrillPosition(x, y);
            if(block instanceof SkyBlock) {
                return;
            }
            pane.getChildren().remove(block);
            Rectangle drilledBlock = GameBlockManager.getDrilledBlock(x, y);
            gridBlocks[x][y] = drilledBlock;
            pane.getChildren().add(drilledBlock);
            drilledBlock.toBack();
            updateTexts();
        }
    }

    /**
     * Updates the drill's position to the specified coordinates and consumes fuel.
     *
     * @param x The new x-coordinate of the drill.
     * @param y The new y-coordinate of the drill.
     */
    private void updateDrillPosition(int x, int y){
        useFuel(moveFuelConsumption);
        drillX = x;
        drillY = y;
        drillMachine.setX(drillX * BLOCK_SIZE);
        drillMachine.setY(drillY * BLOCK_SIZE);
    }

    /**
     * Consumes fuel and updates the fuel text.
     *
     * @param amount The amount of fuel to be consumed.
     */
    private void useFuel(int amount){
        fuel -= amount;
        if(fuel <= 0){
            gameOver = true;
            InfoScreens.showGreenScreen(stage, SCREEN_LENGTH, SCREEN_WIDTH, collectedMoney);
        }
        updateTexts();
    }

    /**
     * Updates the fuel, collected money, and weight texts.
     */
    private void updateTexts(){
        fuelText.setText(TextCaptions.getFuelString(fuel));
        collectedMoneyText.setText(TextCaptions.getCollectedMoneyString(collectedMoney));
        weightText.setText(TextCaptions.getWeightString(weight));
    }

    /**
     * Checks if the drill machine can fly and moves it up if possible.
     */
    private void fly(){
        if(drillY <= skyRowCount - 1){
            return;
        }
        Rectangle block = gridBlocks[drillX][drillY - 1];
        if (block instanceof DrilledBlock || block instanceof SkyBlock) {
            isFlying = true;
            drillMachine.fly();
            updateDrillPosition(drillX, drillY - 1);
        }
    }

    /**
     * Checks if the drill machine can fall due to gravity and moves it down if possible.
     */
    private void gravityFall(){
        if (isFlying) {
            return;
        }
        if (drillY == ROW_COUNT - 1) {
            return;
        }
        Rectangle block = gridBlocks[drillX][drillY + 1];
        if (block instanceof DrilledBlock) {
            updateDrillPosition(drillX, drillY + 1);
        }
    }

    /**
     * Initializes the drill machine and adds it to the game screen.
     */
    private void initializeDrillMachine(){
        drillMachine = new DrillMachine(drillX, drillY, BLOCK_SIZE);
        pane.getChildren().add(drillMachine);
    }

    /**
     * Initializes the fuel, collected money, and weight texts and adds them to the game screen.
     */
    private void initializeTexts(){
        fuelText = new TextElement(TextCaptions.getFuelString(fuel), 20, 20);
        pane.getChildren().add(fuelText);

        collectedMoneyText = new TextElement(TextCaptions.getCollectedMoneyString(collectedMoney), 20, BLOCK_SIZE/2 + 20);
        pane.getChildren().add(collectedMoneyText);

        weightText = new TextElement(TextCaptions.getWeightString(weight), 20, BLOCK_SIZE + 20);
        pane.getChildren().add(weightText);
    }

    /**
     * Generates random x and y coordinates for placing blocks.
     *
     * @return An array containing the x and y coordinates.
     */
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

    /**
     * Builds the game level by randomly placing blocks on the grid.
     * 
     * <p>
     * The method generates a random number of lava blocks and places them at random positions.
     * It ensures that at least three different types of valuable blocks (Amazonite, Bronzium, and Diamond)
     * are present on the grid.
     * </p>
     * 
     * <p>
     * The remaining blocks are filled based on their row positions:
     * - Rows above the sky row count are filled with sky blocks.
     * - The top row is filled with top blocks.
     * - The bottom row and the leftmost and rightmost columns are filled with obstacle blocks.
     * - Other positions are filled with either valuable blocks (based on a probability) or soil blocks.
     * </p>
     * 
     * <p>
     * The generated blocks are added to the `gridBlocks` array and the `pane` for display.
     * </p>
     */
    private void levelBuilder() {
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

    /**
     * The main method of the game.
     * It reads the attributes of the valuable blocks from a file and launches the game.
     * 
     * @param args the command line arguments
     */
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
