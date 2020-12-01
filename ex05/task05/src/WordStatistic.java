import java.io.*;
import java.util.*;

public class WordStatistic {
    Vector<String> allWord, noSameWord;
    File file = new File("english.txt");
    Scanner sc = null;
    String regex;

    WordStatistic() {
        allWord = new Vector<String>();
        noSameWord = new Vector<String>();
        regex = "[\\s\\d\\p{Punct}]+";
        try {
            sc = new Scanner(file); // TODO: sc -> FILE
            sc.useDelimiter(regex); // TODO: sc calls useDelimiter and pass regex
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    void setFileName(String name) {
        file = new File(name);
        try {
            sc = new Scanner(file);
            sc.useDelimiter(regex);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void wordStatistic() {
        try {
            while (sc.hasNext()) {
                String word = sc.next();
                allWord.add(word);
                if (!noSameWord.contains(word))
                    noSameWord.add(word);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Vector<String> getAllWord() {
        return allWord;
    }

    public Vector<String> getNoSameWord() {
        return noSameWord;
    }
}

