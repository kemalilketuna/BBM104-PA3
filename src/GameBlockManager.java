import java.util.HashMap;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

class GameBlock extends Rectangle {
    private int worth;
    private int weight;

    GameBlock(int x, int y, int size, int worth, int weight) {
        super(x * size, y * size, size, size);
        setStroke(null);
        this.weight = weight;
        this.worth = worth;
    }

    public int getWorth() {
        return worth;
    }

    public int getWeight() {
        return weight;
    }
}

class SkyBlock extends GameBlock {
    SkyBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        setFill(Color.LIGHTBLUE);
    }
}

class DrilledBlock extends GameBlock {
    DrilledBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        setFill(Color.rgb(73, 27, 29));
    }
}

class SoilBlock extends GameBlock {
    SoilBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern soilPattern = new ImagePattern(new Image(Paths.getRandomSoilBlockPath()));
        setFill(soilPattern);
    }
}

class TopBlock extends GameBlock {
    TopBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern topPattern = new ImagePattern(new Image(Paths.getRandomTopBlockPath()));
        setHeight(size+1);
        setFill(topPattern);
    }
}

class ObstacleBlock extends GameBlock {
    ObstacleBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern obstaclePattern = new ImagePattern(new Image(Paths.getRandomObstacleBlockPath()));
        setFill(obstaclePattern);
    }
}

class LavaBlock extends GameBlock {
    LavaBlock(int x, int y, int size) {
        super(x, y, size, 0, 0);
        ImagePattern lavaPattern = new ImagePattern(new Image(Paths.getRandomLavaBlockPath()));
        setFill(lavaPattern);
    }
}

class ValuableBlock extends GameBlock {
    ValuableBlock(int x, int y, int size, int worth, int weight) {
        super(x, y, size, worth, weight);
        ImagePattern valuablePattern = new ImagePattern(new Image(Paths.getRandomValuableBlockPath()));
        setFill(valuablePattern);
    }
}

public class GameBlockManager {
    public static int BLOCK_SIZE;
    private static HashMap<String, Integer[]> valuableBlockAttributes;

    public static void setBlockAttributes(int size, HashMap<String, Integer[]> attributes) {
        BLOCK_SIZE = size;
        valuableBlockAttributes = attributes;
    }

    public static Rectangle getSkyBlock(int x, int y) {
        return new SkyBlock(x, y, BLOCK_SIZE);
    }

    public static Rectangle getDrilledBlock(int x, int y) {
        return new DrilledBlock(x, y, BLOCK_SIZE);
    }

    public static Rectangle getSoilBlock(int x, int y) {
        return new SoilBlock(x, y, BLOCK_SIZE);
    }

    public static Rectangle getTopBlock(int x, int y) {
        return new TopBlock(x, y, BLOCK_SIZE);
    }

    public static Rectangle getObstacleBlock(int x, int y) {
        return new ObstacleBlock(x, y, BLOCK_SIZE);
    }

    public static Rectangle getAmozoniteBlock(int x, int y) {
        Integer[] attributes = valuableBlockAttributes.get("amazonite");
        int worth = attributes[0];
        int weight = attributes[1];
        return new ValuableBlock(x, y, BLOCK_SIZE, worth, weight);
    }

    public static Rectangle getBronziumBlock(int x, int y) {
        Integer[] attributes = valuableBlockAttributes.get("bronzium");
        int worth = attributes[0];
        int weight = attributes[1];
        return new ValuableBlock(x, y, BLOCK_SIZE, worth, weight);
    }

    public static Rectangle getDiamondBlock(int x, int y) {
        Integer[] attributes = valuableBlockAttributes.get("diamond");
        int worth = attributes[0];
        int weight = attributes[1];
        return new ValuableBlock(x, y, BLOCK_SIZE, worth, weight);
    }

    public static Rectangle getRandomValuableBlock(int x, int y) {
        int randomInt = (int) (Math.random() * valuableBlockAttributes.size());
        String block = valuableBlockAttributes.keySet().toArray(new String[0])[randomInt];
        Integer[] attributes = valuableBlockAttributes.get(block);
        return new ValuableBlock(x, y, BLOCK_SIZE, attributes[0], attributes[1]);
    }

    public static Rectangle getLavaBlock(int x, int y) {
        return new LavaBlock(x, y, BLOCK_SIZE);
    }
}
