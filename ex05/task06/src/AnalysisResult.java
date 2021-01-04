import java.io.*;
import java.util.*;

public class AnalysisResult {
    public static void main(String[] args) {
        File fRead = new File("Score.txt");
        File fWrite = new File("Score_Analysis.txt");
        try {
            Writer out = new FileWriter(fWrite, true);// TODO: APPEND to fWrite
            BufferedWriter bufferedWriter = new BufferedWriter(out); // TODO: bufferWriter -> out
            Reader in = new FileReader(fRead);// TODO: in -> fRead
            BufferedReader bufferedReader = new BufferedReader(in); // TODO: bufferReader -> in
            String str = null;
            while ((str=bufferedReader.readLine())!=null) {
                double totalScore = Analysis.getTotalScore(str);
                str = str + " Total Score: " + totalScore;
                System.out.println(str);
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
