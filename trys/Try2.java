import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Try2 extends Application {
    private int number = 0;
    private Label label;

    @Override
    public void start(Stage primaryStage) {
        label = new Label(String.valueOf(number));
        label.setStyle("-fx-font-size: 24px;");

        StackPane root = new StackPane(label);
        root.setPrefSize(300, 300);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                number--;
            } else if (event.getCode() == KeyCode.RIGHT) {
                number++;
            }
            label.setText(String.valueOf(number));
        });

        primaryStage.setTitle("Number Changer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
