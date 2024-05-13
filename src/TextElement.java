import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
public class TextElement extends Text{
    public TextElement(String text, int x, int y) {
        super(text);
        setTranslateX(x);
        setTranslateY(y);
        setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setFill(Color.WHITE);
    }
}
