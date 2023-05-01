package misc;

import java.util.ArrayList;
import java.util.Random;

public class Alphabet {

    private static final char[] ALPHABET = new char[] {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final char[] VOWELS;
    private static final char[] CONSONANTS;

    static {
        VOWELS = new char[] { ALPHABET[0], ALPHABET[4], ALPHABET[8], ALPHABET[14], ALPHABET[20]};
        CONSONANTS = new char[ALPHABET.length - 5];
        int i = 0;
        for (char letter: ALPHABET) {
            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
                continue;
            }
            CONSONANTS[i] = letter;
            i++;
        }
    }

    public static char getRandomLetter() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        int max = ALPHABET.length;
        int index = rng.nextInt(max);
        return ALPHABET[index];
    }


    private static char getRandomVowel() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        int max = VOWELS.length;
        int randomIndex = rng.nextInt(max);
        return VOWELS[randomIndex];
    }

    private static char getRandomVowel(long seed) {
        Random rng = new Random(seed);
        int max = VOWELS.length;
        int randomIndex = rng.nextInt(max);
        return VOWELS[randomIndex];
    }

    private static char getRandomConsonant(long seed) {
        Random rng = new Random(seed);
        int max = CONSONANTS.length;
        int randomIndex = rng.nextInt(max);
        return CONSONANTS[randomIndex];
    }

    private static char getRandomConsonant() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        int max = CONSONANTS.length;
        int randomIndex = rng.nextInt(max + 1);
        return CONSONANTS[randomIndex];
    }


    public static ArrayList<Character> getRandomLetters() {
        ArrayList<Character> letters = new ArrayList<>();
        int vowelCount = generateRandomVowelCount();
        int consonantCount = 17 - vowelCount;

        for (int i = 0; i < vowelCount; i++) {
            Character letter = getRandomVowel(System.currentTimeMillis() + i);
            letters.add(letter);
        }

        for (int i = 0; i < consonantCount; i++) {
            Character letter = getRandomConsonant(System.currentTimeMillis() + i);
            letters.add(letter);
        }

        return letters;
    }

    private static int generateRandomVowelCount() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        int out;


        do {
            out = rng.nextInt(9);
        } while (out < 5);
        return out;
    }

    public static void main(String[] args) {
        for (Character letter: getRandomLetters().toArray(new Character[0])) {
            System.out.println(letter);
        }
    }
}
