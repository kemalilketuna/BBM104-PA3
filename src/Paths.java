public class Paths {
    // Attribute file path
    public static final String ATTRIBUTE_FILE_PATH = "./assets/atributes_of_valuables.txt";

    // Soil block paths
    static final String SOIL_BLOCK_1 = "file:./assets/underground/soil_01.png";
    static final String SOIL_BLOCK_2 = "file:./assets/underground/soil_02.png";
    static final String SOIL_BLOCK_3 = "file:./assets/underground/soil_03.png";
    static final String SOIL_BLOCK_4 = "file:./assets/underground/soil_04.png";
    static final String SOIL_BLOCK_5 = "file:./assets/underground/soil_05.png";
    static final String[] SOIL_BLOCK_PATHS = {SOIL_BLOCK_1, SOIL_BLOCK_2, SOIL_BLOCK_3, SOIL_BLOCK_4, SOIL_BLOCK_5};

    // Top block paths
    static final String TOP_BLOCK_1 = "file:./assets/underground/top_01.png";
    static final String TOP_BLOCK_2 = "file:./assets/underground/top_02.png";
    static final String[] TOP_BLOCK_PATHS = {TOP_BLOCK_1, TOP_BLOCK_2};

    // Obstacle block paths
    static final String OBSTACLE_BLOCK_1 = "file:./assets/underground/obstacle_01.png";
    static final String OBSTACLE_BLOCK_2 = "file:./assets/underground/obstacle_02.png";
    static final String OBSTACLE_BLOCK_3 = "file:./assets/underground/obstacle_03.png";
    static final String[] OBSTACLE_BLOCK_PATHS = {OBSTACLE_BLOCK_1, OBSTACLE_BLOCK_2, OBSTACLE_BLOCK_3};

    // Valuable block paths
    static final String AMAZONITE_BLOCK = "file:./assets/underground/valuable_amazonite.png";
    static final String BRONZIUM_BLOCK = "file:./assets/underground/valuable_bronzium.png";
    static final String DIAMOND_BLOCK = "file:./assets/underground/valuable_diamond.png";
    static final String EINSTEINIUM_BLOCK = "file:./assets/underground/valuable_einsteinium.png";
    static final String EMERALD_BLOCK = "file:./assets/underground/valuable_emerald.png";
    static final String GOLDIUM_BLOCK = "file:./assets/underground/valuable_goldium.png";
    static final String IRONIUM_BLOCK = "file:./assets/underground/valuable_ironium.png";
    static final String PLATINUM_BLOCK = "file:./assets/underground/valuable_platinum.png";
    static final String RUBY_BLOCK = "file:./assets/underground/valuable_ruby.png";
    static final String SILVERIUM_BLOCK = "file:./assets/underground/valuable_silverium.png";
    static final String[] VALUABLE_BLOCK_PATHS = {AMAZONITE_BLOCK, BRONZIUM_BLOCK, DIAMOND_BLOCK, EINSTEINIUM_BLOCK, EMERALD_BLOCK, GOLDIUM_BLOCK, IRONIUM_BLOCK, PLATINUM_BLOCK, RUBY_BLOCK, SILVERIUM_BLOCK};

    // Lava block paths
    static final String LAVA_BLOCK_1 = "file:./assets/underground/lava_01.png";
    static final String LAVA_BLOCK_2 = "file:./assets/underground/lava_02.png";
    static final String LAVA_BLOCK_3 = "file:./assets/underground/lava_03.png";
    static final String[] lavaBlockPaths = {LAVA_BLOCK_1, LAVA_BLOCK_2, LAVA_BLOCK_3};

    // Drill paths
    static final String IDLE_LEFT = "file:./assets/drill/drill_38.png";
    static final String IDLE_RIGHT = "file:./assets/drill/drill_right/38.png";
    static final String DRILL_LEFT = "file:./assets/drill/drill_01.png";
    static final String DRILL_RIGHT = "file:./assets/drill/drill_right/01.png";
    static final String FLY_LEFT = "file:./assets/drill/drill_30.png";
    static final String FLY_RIGHT = "file:./assets/drill/drill_right/30.png";

    private static String chooseRandomly(String[] paths){
        int length = paths.length;
        int random = (int) (Math.random() * length);
        return paths[random];
    }

    public static String getRandomLavaBlockPath() {
        return chooseRandomly(lavaBlockPaths);
    }

    public static String getRandomSoilBlockPath() {
        return chooseRandomly(SOIL_BLOCK_PATHS);
    }

    public static String getRandomTopBlockPath() {
        return chooseRandomly(TOP_BLOCK_PATHS);
    }

    public static String getRandomObstacleBlockPath() {
        return chooseRandomly(OBSTACLE_BLOCK_PATHS);
    }

    public static String getRandomValuableBlockPath() {
        return chooseRandomly(VALUABLE_BLOCK_PATHS);
    }

    public static String getAmazonitePath() {
        return AMAZONITE_BLOCK;
    }

    public static String getBronziumPath() {
        return BRONZIUM_BLOCK;
    }

    public static String getDiamondPath() {
        return DIAMOND_BLOCK;
    }
}

