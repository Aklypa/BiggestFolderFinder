import java.util.HashMap;

public class SizeCalculator {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> char2Multiplier = getMultipliers();


    // 24B; 243Kb; 36Mb; 34Gb; 42Tb
    public static String getHumanReadableSize(long size) {
        for (int i = 0; i < sizeMultipliers.length; i++) {
            double value = ((double)size) / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value * 100) / 100. + " " + sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Very big!";
    }
    //  24B; 240Kb; 36Mb; 34Gb; 42Tb
    //  24B; 240K; 36M; 34G; 42T
    //  235K => 235*1024 = 240640
    public static long getSizeFromHumanReadable(String size) {
        char sizeFactor = size.replaceAll("[0-9\\s+]+", "").charAt(0);
        int multiplier = char2Multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));

        return length;
    }
    private static HashMap<Character, Integer> getMultipliers() {
        HashMap<Character, Integer> char2Multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++) {
            char2Multiplier.put(sizeMultipliers[i], (int) Math.pow(1024, i));
        }
        return char2Multiplier;
    }

}
