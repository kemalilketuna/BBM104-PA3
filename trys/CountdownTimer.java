import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer extends Application {
    private static final int COUNTDOWN_SECONDS = 60;
    private int remainingSeconds = COUNTDOWN_SECONDS;

    private Label countdownLabel;

    @Override
    public void start(Stage primaryStage) {
        countdownLabel = new Label(formatTime(remainingSeconds));
        countdownLabel.setStyle("-fx-font-size: 48px;");

        StackPane root = new StackPane(countdownLabel);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Countdown Timer");
        primaryStage.setScene(scene);
        primaryStage.show();

        startCountdown();
    }

    private void startCountdown() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            countdownLabel.setText(formatTime(remainingSeconds));
            if (remainingSeconds <= 0) {
                timeline.stop();
                countdownLabel.setText("Time's up!");
            }
        }));
        timeline.play();
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
