import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class InfoScreens {
    public static void showRedScreen(Stage primaryStage, Pane pane) {
        int height = (int) pane.getHeight();

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        Text text = new Text(TextCaptions.GAME_OVER);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
        text.setFill(Color.WHITE);
        text.setTranslateY(-height / 6);
        root.getChildren().add(text);

        // Restart button
        Button restartButton = createRestartButton();
        restartButton.setOnAction(event -> {
            primaryStage.close();
            Platform.runLater(() -> new Main().start(new Stage()));
        });
        restartButton.setTranslateY(height / 6);
        root.getChildren().add(restartButton);

        Scene scene = new Scene(root, pane.getWidth(), pane.getHeight());
        primaryStage.setScene(scene);
    }

    public static void showGreenScreen(Stage primaryStage, Pane pane, int score) {
        int height = (int) pane.getHeight();

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        Text text = new Text(TextCaptions.WIN);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
        text.setFill(Color.WHITE);
        text.setTranslateY(-height / 6);
        root.getChildren().add(text);

        Text scoreText = new Text(TextCaptions.getCollectedMoneyString(score));
        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        scoreText.setFill(Color.WHITE);
        root.getChildren().add(scoreText);

        // Restart button
        Button restartButton = createRestartButton();
        restartButton.setOnAction(event -> {
            primaryStage.close();
            Platform.runLater(() -> new Main().start(new Stage()));
        });
        restartButton.setTranslateY(height / 6);
        root.getChildren().add(restartButton);

        Scene scene = new Scene(root, pane.getWidth(), pane.getHeight());
        primaryStage.setScene(scene);
    }

    public static Button createRestartButton() {
        Button restartButton = new Button(TextCaptions.RESTART);
        // background color of the button Color.white
        restartButton.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        // larger button size
        restartButton.setPrefSize(200, 50);
        restartButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        return restartButton;
    }
}