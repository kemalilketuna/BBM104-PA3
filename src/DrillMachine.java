import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class DrillMachine extends Rectangle{
    public DrillMachine(int x, int y, int size) {
        super(x * size, y * size, size, size);
        setFill(Color.RED);
    }
}
