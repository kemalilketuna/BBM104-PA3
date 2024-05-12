import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

class GameBlock extends Rectangle {
    GameBlock(int x, int y, int size) {
        super(x * size, y * size, size, size);
    }
}

class SkyBlock extends GameBlock {
    SkyBlock(int x, int y, int size) {
        super(x, y, size);
        setFill(Color.LIGHTBLUE);
    }
}

class SoilBlock extends GameBlock {
    SoilBlock(int x, int y, int size) {
        super(x, y, size);
        ImagePattern soilPattern = new ImagePattern(new Image(Paths.getRandomSoilBlockPath()));
        setFill(soilPattern);
    }
}

public class GameBlockManager {
    public static int BLOCK_SIZE;

    public static Rectangle getSkyBlock(int x, int y) {
        return new SkyBlock(x, y, BLOCK_SIZE);
    }

    public static Rectangle getSoilBlock(int x, int y) {
        return new SoilBlock(x, y, BLOCK_SIZE);
    }
}
