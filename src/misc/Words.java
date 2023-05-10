package misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Words {

    public static final ArrayList<String> WORDLIST;

    static {
        WORDLIST = new ArrayList<>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("data/words.txt"));
            String currentWord;
            while ((currentWord = bufferedReader.readLine()) != null) {
                WORDLIST.add(currentWord);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidWord(String word) {
        return WORDLIST.contains(word);
    }


    public static void main(String[] args) {
        for (String word: Words.WORDLIST) {
            System.out.println(word);
        }

    }

}
