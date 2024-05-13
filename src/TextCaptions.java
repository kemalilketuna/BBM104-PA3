public class TextCaptions {
    public static final String GAME_TITLE = "HU-Load Game";
    public static final String GAME_OVER = "Game Over";
    public static final String RESTART = "Restart";
    private static final String COLLECTED_MONEY = "Collected Money: %d";
    public static final String WIN = "You Win!";
    private static final String FUEL = "Fuel: %d";
    private static final String WEIGHT = "Weight: %d";
    private static final String MONEY = "Money: %d";

    public static String getCollectedMoneyString(int money) {
        return String.format(COLLECTED_MONEY, money);
    }

    public static String getMoneyString(int money) {
        return String.format(MONEY, money);
    }

    public static String getFuelString(int fuel) {
        return String.format(FUEL, fuel);
    }

    public static String getWeightString(int weight) {
        return String.format(WEIGHT, weight);
    }
}
