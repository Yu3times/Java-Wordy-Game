package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alphabet {

    public static String generateRandomLetters() {
        Random random = new Random();
        String vowels = "aeiou";
        String consonants = "bcdfghjklmnpqrstvwxyz";
        int numVowels = random.nextInt(3) + 5; // generate a random number of vowels between 5 and 7
        int numConsonants = 17 - numVowels;
        List<Character> letters = new ArrayList<>();
        // add vowels and consonants to the list
        for (int i = 0; i < numVowels; i++) {
            letters.add(vowels.charAt(random.nextInt(vowels.length())));
        }
        for (int i = 0; i < numConsonants; i++) {
            letters.add(consonants.charAt(random.nextInt(consonants.length())));
        }
        // shuffle the letters
        Collections.shuffle(letters, random);
        // create the final string
        StringBuilder sb = new StringBuilder();
        for (char c : letters) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String randomLetters = generateRandomLetters();
        System.out.println(randomLetters);
    }
}
