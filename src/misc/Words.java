package misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

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


    public static void main(String[] args) {

    }

}
