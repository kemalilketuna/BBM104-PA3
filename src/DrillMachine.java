import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DrillMachine extends Rectangle{
    private Boolean lookingRight = true;

    /*
     * This constructor creates a drill machine at the given position.
     * 
     * @param x the x-coordinate of the drill machine
     * @param y the y-coordinate of the drill machine
     * @param size the size of the drill machine
     */
    public DrillMachine(int x, int y, int size) {
        super(x * size, y * size, size, size);
        // image of the drill machine
        setFill(new ImagePattern(new Image(Paths.IDLE_RIGHT)));
    }

    /*
     * This method makes the drill machine look left.
     */
    public void lookLeft(){
        lookingRight = false;
    }

    /*
     * This method makes the drill machine look right.
     */
    public void lookRight(){
        lookingRight = true;
    }

    /**
     * Sets the drill machine's appearance to the idle state.
     * 
     * <p>
     * If the drill machine is looking right, it sets the fill pattern to the idle right image.
     * Otherwise, it sets the fill pattern to the idle left image.
     * </p>
     */
    public void idle() {
        if (lookingRight) {
            setFill(new ImagePattern(new Image(Paths.IDLE_RIGHT)));
        } else {
            setFill(new ImagePattern(new Image(Paths.IDLE_LEFT)));
        }
    }

    /**
     * Sets the drill machine's appearance to the drilling state.
     * 
     * <p>
     * If the drill machine is looking right, it sets the fill pattern to the drilling right image.
     * Otherwise, it sets the fill pattern to the drilling left image.
     * </p>
     */
    public void drill() {
        if (lookingRight) {
            setFill(new ImagePattern(new Image(Paths.DRILL_RIGHT)));
        } else {
            setFill(new ImagePattern(new Image(Paths.DRILL_LEFT)));
        }
    }

    /**
     * Sets the drill machine's appearance to the flying state.
     * 
     * <p>
     * If the drill machine is looking right, it sets the fill pattern to the flying right image.
     * Otherwise, it sets the fill pattern to the flying left image.
     * </p>
     */
    public void fly() {
        if (lookingRight) {
            setFill(new ImagePattern(new Image(Paths.FLY_RIGHT)));
        } else {
            setFill(new ImagePattern(new Image(Paths.FLY_LEFT)));
        }
    }

}
