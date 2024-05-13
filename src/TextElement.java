import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

/**
 * The TextElement class represents a text element in the game.
 * <p>
 * It extends the JavaFX {@link Text} class and provides additional functionality
 * for positioning and styling the text.
 * </p>
 * <p>
 * The TextElement is constructed with the text content, x-coordinate, and y-coordinate.
 * It sets the translation properties to position the text at the specified coordinates.
 * </p>
 * <p>
 * The text is styled using the Verdana font with a bold weight and a size of 20 pixels.
 * The text color is set to white.
 * </p>
 */
public class TextElement extends Text {
    /**
     * Constructs a TextElement with the specified text content and position.
     *
     * @param text The text content of the element.
     * @param x    The x-coordinate of the text position.
     * @param y    The y-coordinate of the text position.
     */
    public TextElement(String text, int x, int y) {
        super(text);
        setTranslateX(x);
        setTranslateY(y);
        setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setFill(Color.WHITE);
    }
}