import java.util.*;

public class Analysis {
    public static double getTotalScore(String s) {
        Scanner scanner = new Scanner(s);
        scanner.useDelimiter("[^0123456789.]+");
        double totalScore = 0;
        while (scanner.hasNext()) {
            try {
                double score = scanner.nextDouble();
                totalScore += score;
            }
            catch (InputMismatchException exp) {
                String t = scanner.next();
                exp.printStackTrace();
            }
        }
        return totalScore;
    }
}
