import java.util.HashMap;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/*
 * This class is responsible for creating the blocks in the game.
 */
class GameBlock extends Rectangle {
    private int worth;
    private int weight;

    /*
     * This constructor creates a game block at the given position.
     * 
     * @param x the x-coordinate of the game block
     * @param y the y-coordinate of the game block
     * @param size the size of the game block
     * @param worth the worth of the game block
     * @param weight the weight of the game block
     */
    GameBlock(int x, int y, int size, int worth, int weight) {
        super(x * size, y * size, size, size);
        setStroke(null);
        this.weight = weight;
        this.worth = worth;
    }

    /*
     * This method returns the worth of the game block.
     * 
     * @return the worth of the game block
     */
    public int getWorth() {
        return worth;
    }

    /*
     * This method returns the weight of the game block.
     * 
     * @return the weight of the game block
     */
    public int getWeight() {
        return weight;
    }
}

/*
 * This class is responsible for creating the sky block in the game.
 */
class SkyBlock extends GameBlock {
    /*
     * This constructor creates a sky block at the given position.
     * 
     * @param x the x-coordinate of the sky block
     * @param y the y-coordinate of the sky block
     * @param size the size of the sky block
     */
    SkyBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        setFill(Color.LIGHTBLUE);
    }
}

/*
 * This class is responsible for creating the drilled block in the game.
 */
class DrilledBlock extends GameBlock {
    /*
     * This constructor creates a drilled block at the given position.
     * 
     * @param x the x-coordinate of the drilled block
     * @param y the y-coordinate of the drilled block
     * @param size the size of the drilled block
     */
    DrilledBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        setFill(Color.rgb(73, 27, 29));
    }
}

/*
 * This class is responsible for creating the soil block in the game.
 */
class SoilBlock extends GameBlock {
    /*
     * This constructor creates a soil block at the given position.
     * 
     * @param x the x-coordinate of the soil block
     * @param y the y-coordinate of the soil block
     * @param size the size of the soil block
     */
    SoilBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern soilPattern = new ImagePattern(new Image(Paths.getRandomSoilBlockPath()));
        setFill(soilPattern);
    }
}

/*
 * This class is responsible for creating the top block in the game.
 */
class TopBlock extends GameBlock {
    /*
     * This constructor creates a top block at the given position.
     * 
     * @param x the x-coordinate of the top block
     * @param y the y-coordinate of the top block
     * @param size the size of the top block
     */
    TopBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern topPattern = new ImagePattern(new Image(Paths.getRandomTopBlockPath()));
        setHeight(size+1);
        setFill(topPattern);
    }
}

/*
 * This class is responsible for creating the obstacle block in the game.
 */
class ObstacleBlock extends GameBlock {
    /*
     * This constructor creates an obstacle block at the given position.
     * 
     * @param x the x-coordinate of the obstacle block
     * @param y the y-coordinate of the obstacle block
     * @param size the size of the obstacle block
     */
    ObstacleBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern obstaclePattern = new ImagePattern(new Image(Paths.getRandomObstacleBlockPath()));
        setFill(obstaclePattern);
    }
}

/*
 * This class is responsible for creating the lava block in the game.
 */
class LavaBlock extends GameBlock {
    /*
     * This constructor creates a lava block at the given position.
     * 
     * @param x the x-coordinate of the lava block
     * @param y the y-coordinate of the lava block
     * @param size the size of the lava block
     */
    LavaBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern lavaPattern = new ImagePattern(new Image(Paths.getRandomLavaBlockPath()));
        setFill(lavaPattern);
    }
}

/*
 * This class is responsible for creating the valuable block in the game.
 */
class ValuableBlock extends GameBlock {
    /*
     * This constructor creates a valuable block at the given position.
     * 
     * @param x the x-coordinate of the valuable block
     * @param y the y-coordinate of the valuable block
     * @param size the size of the valuable block
     * @param worth the worth of the valuable block
     * @param weight the weight of the valuable block
     */
    ValuableBlock(int x, int y, int size, int worth, int weight) {
        super(x, y, size, worth, weight);
        ImagePattern valuablePattern = new ImagePattern(new Image(Paths.getRandomValuableBlockPath()));
        setFill(valuablePattern);
    }
}

/*
 * This class is responsible for creating the game blocks.
 */
public class GameBlockManager {
    public static int BLOCK_SIZE;
    private static HashMap<String, Integer[]> valuableBlockAttributes;

    /*
     * This method sets the block size and the attributes of the valuable blocks.
     * 
     * @param size the size of the block
     * @param attributes the attributes of the valuable blocks
     */
    public static void setBlockAttributes(int size, HashMap<String, Integer[]> attributes) {
        BLOCK_SIZE = size;
        valuableBlockAttributes = attributes;
    }

    /*
     * This method returns a sky block at the given position.
     * 
     * @param x the x-coordinate of the sky block
     * @param y the y-coordinate of the sky block
     * @return a sky block at the given position
     */
    public static Rectangle getSkyBlock(int x, int y) {
        return new SkyBlock(x, y, BLOCK_SIZE);
    }

    /*
     * This method returns a drilled block at the given position.
     * 
     * @param x the x-coordinate of the drilled block
     * @param y the y-coordinate of the drilled block
     * @return a drilled block at the given position
     */
    public static Rectangle getDrilledBlock(int x, int y) {
        return new DrilledBlock(x, y, BLOCK_SIZE);
    }

    /*
     * This method returns a soil block at the given position.
     * 
     * @param x the x-coordinate of the soil block
     * @param y the y-coordinate of the soil block
     * @return a soil block at the given position
     */
    public static Rectangle getSoilBlock(int x, int y) {
        return new SoilBlock(x, y, BLOCK_SIZE);
    }

    /*
     * This method returns a top block at the given position.
     * 
     * @param x the x-coordinate of the top block
     * @param y the y-coordinate of the top block
     * @return a top block at the given position
     */
    public static Rectangle getTopBlock(int x, int y) {
        return new TopBlock(x, y, BLOCK_SIZE);
    }

    /*
     * This method returns an obstacle block at the given position.
     * 
     * @param x the x-coordinate of the obstacle block
     * @param y the y-coordinate of the obstacle block
     * @return an obstacle block at the given position
     */
    public static Rectangle getObstacleBlock(int x, int y) {
        return new ObstacleBlock(x, y, BLOCK_SIZE);
    }

    /*
     * This method returns a valuable block at the given position.
     * 
     * @param x the x-coordinate of the valuable block
     * @param y the y-coordinate of the valuable block
     * @return a valuable block at the given position
     */
    public static Rectangle getAmozoniteBlock(int x, int y) {
        Integer[] attributes = valuableBlockAttributes.get("amazonite");
        int worth = attributes[0];
        int weight = attributes[1];
        return new ValuableBlock(x, y, BLOCK_SIZE, worth, weight);
    }

    /*
     * This method returns a valuable block at the given position.
     * 
     * @param x the x-coordinate of the valuable block
     * @param y the y-coordinate of the valuable block
     * @return a valuable block at the given position
     */
    public static Rectangle getBronziumBlock(int x, int y) {
        Integer[] attributes = valuableBlockAttributes.get("bronzium");
        int worth = attributes[0];
        int weight = attributes[1];
        return new ValuableBlock(x, y, BLOCK_SIZE, worth, weight);
    }

    /*
     * This method returns a valuable block at the given position.
     * 
     * @param x the x-coordinate of the valuable block
     * @param y the y-coordinate of the valuable block
     * @return a valuable block at the given position
     */
    public static Rectangle getDiamondBlock(int x, int y) {
        Integer[] attributes = valuableBlockAttributes.get("diamond");
        int worth = attributes[0];
        int weight = attributes[1];
        return new ValuableBlock(x, y, BLOCK_SIZE, worth, weight);
    }

    /*
     * This method returns a valuable block at the given position.
     * 
     * @param x the x-coordinate of the valuable block
     * @param y the y-coordinate of the valuable block
     * @return a valuable block at the given position
     */
    public static Rectangle getRandomValuableBlock(int x, int y) {
        int randomInt = (int) (Math.random() * valuableBlockAttributes.size());
        String block = valuableBlockAttributes.keySet().toArray(new String[0])[randomInt];
        Integer[] attributes = valuableBlockAttributes.get(block);
        return new ValuableBlock(x, y, BLOCK_SIZE, attributes[0], attributes[1]);
    }

    /*
     * This method returns a lava block at the given position.
     * 
     * @param x the x-coordinate of the lava block
     * @param y the y-coordinate of the lava block
     * @return a lava block at the given position
     */
    public static Rectangle getLavaBlock(int x, int y) {
        return new LavaBlock(x, y, BLOCK_SIZE);
    }
}
