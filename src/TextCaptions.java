/**
 * The TextCaptions class provides static constants and utility methods for formatting text captions used in the game.
 * <p>
 * The class defines constants for various text captions such as game title, game over message, restart button text, and win message.
 * It also provides methods for formatting dynamic text captions like collected money, fuel, weight, and money.
 * </p>
 * <p>
 * The formatting methods use the {@link String#format(String, Object...)} method to insert the dynamic values into the respective text templates.
 * </p>
 */
public class TextCaptions {
    public static final String GAME_TITLE = "HU-Load Game";
    public static final String GAME_OVER = "Game Over";
    public static final String RESTART = "Restart";
    private static final String COLLECTED_MONEY = "Collected Money: %d";
    public static final String WIN = "You Win!";
    private static final String FUEL = "Fuel: %d";
    private static final String WEIGHT = "Weight: %d";
    private static final String MONEY = "Money: %d";

    /**
     * Returns the formatted text caption for the collected money.
     *
     * @param money the amount of money collected
     * @return the formatted text caption for the collected money
     */
    public static String getCollectedMoneyString(int money) {
        return String.format(COLLECTED_MONEY, money);
    }

    /**
     * Returns the formatted text caption for the fuel.
     *
     * @param fuel the amount of fuel
     * @return the formatted text caption for the fuel
     */
    public static String getMoneyString(int money) {
        return String.format(MONEY, money);
    }

    /**
     * Returns the formatted text caption for the fuel.
     *
     * @param fuel the amount of fuel
     * @return the formatted text caption for the fuel
     */
    public static String getFuelString(int fuel) {
        return String.format(FUEL, fuel);
    }

    /**
     * Returns the formatted text caption for the weight.
     *
     * @param weight the weight of the drill machine
     * @return the formatted text caption for the weight
     */
    public static String getWeightString(int weight) {
        return String.format(WEIGHT, weight);
    }
}
