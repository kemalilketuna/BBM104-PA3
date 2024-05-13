import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The SoundPlayer class provides static methods for playing various sound effects in the game.
 * <p>
 * It utilizes the JavaFX {@link Media} and {@link MediaPlayer} classes to load and play audio files.
 * The audio files are located using the file paths specified in the {@link Paths} class.
 * </p>
 * <p>
 * The available sound effects include:
 * <ul>
 *     <li>Drill sound: Played when the drill is in action.</li>
 *     <li>Lava sound: Played when the drill encounters lava.</li>
 *     <li>Challenge done sound: Played when a challenge is completed.</li>
 *     <li>Click sound: Played when a button or interactive element is clicked.</li>
 * </ul>
 * </p>
 * <p>
 * To play a sound effect, simply call the corresponding static method of the SoundPlayer class.
 * The method will load the audio file, create a MediaPlayer instance, and start playing the sound.
 * </p>
 */
public class SoundPlayer {
    /**
     * Plays the drill sound effect.
     */
    public static void playDrillSound() {
        Media sound = new Media(new File(Paths.DRILL_SOUND).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Plays the lava sound effect.
     */
    public static void playLavaSound() {
        Media sound = new Media(new File(Paths.LAVA_SOUND).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Plays the challenge done sound effect.
     */
    public static void playChallengeDoneSound() {
        Media sound = new Media(new File(Paths.CHALLENGE_DONE).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Plays the click sound effect.
     */
    public static void playClickSound() {
        Media sound = new Media(new File(Paths.CLICK).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
