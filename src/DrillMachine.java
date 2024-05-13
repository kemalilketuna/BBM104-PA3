import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DrillMachine extends Rectangle{
    private Boolean lookingRight = true;

    public DrillMachine(int x, int y, int size) {
        super(x * size, y * size, size, size);
        // image of the drill machine
        setFill(new ImagePattern(new Image(Paths.IDLE_RIGHT)));
    }

    public void lookLeft(){
        lookingRight = false;
    }

    public void lookRight(){
        lookingRight = true;
    }

    public void idle(){
        if(lookingRight){
            setFill(new ImagePattern(new Image(Paths.IDLE_RIGHT)));
        } else{
            setFill(new ImagePattern(new Image(Paths.IDLE_LEFT)));
        }
    }

    public void drill(){
        if(lookingRight){
            setFill(new ImagePattern(new Image(Paths.DRILL_RIGHT)));
        } else{
            setFill(new ImagePattern(new Image(Paths.DRILL_LEFT)));
        }
    }

    public void fly(){
        if(lookingRight){
            setFill(new ImagePattern(new Image(Paths.FLY_RIGHT)));
        } else{
            setFill(new ImagePattern(new Image(Paths.FLY_LEFT)));
        }
    }
}
