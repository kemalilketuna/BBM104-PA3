import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
    public static void playDrillSound() {
        Media sound = new Media(new File(Paths.DRILL_SOUND).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playLavaSound() {
        Media sound = new Media(new File(Paths.LAVA_SOUND).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playChallengeDoneSound() {
        Media sound = new Media(new File(Paths.CHALLENGE_DONE).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playClickSound() {
        Media sound = new Media(new File(Paths.CLICK).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
