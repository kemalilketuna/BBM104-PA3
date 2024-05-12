public class Paths {
    // Soil block paths
    static final String SOIL_BLOCK_1 = "file:../assets/underground/soil_01.png";
    static final String SOIL_BLOCK_2 = "file:../assets/underground/soil_02.png";
    static final String SOIL_BLOCK_3 = "file:../assets/underground/soil_03.png";
    static final String SOIL_BLOCK_4 = "file:../assets/underground/soil_04.png";
    static final String SOIL_BLOCK_5 = "file:../assets/underground/soil_05.png";
    static final String[] SOIL_BLOCK_PATHS = {SOIL_BLOCK_1, SOIL_BLOCK_2, SOIL_BLOCK_3, SOIL_BLOCK_4, SOIL_BLOCK_5};

    // Top block paths
    static final String TOP_BLOCK_1 = "file:../assets/underground/top_01.png";
    static final String TOP_BLOCK_2 = "file:../assets/underground/top_02.png";

    private static String chooseRandomly(String[] paths){
        int length = paths.length;
        int random = (int) (Math.random() * length);
        return paths[random];
    }

    public static String getRandomSoilBlockPath() {
        return chooseRandomly(SOIL_BLOCK_PATHS);
    }
}

