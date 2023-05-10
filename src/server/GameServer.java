package server;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import wordy_idl.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class initiates the ORB using the GameMenuServantImpl object.
 */
public class GameServer {
    private static final String FILENAME = "words.txt";
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();



            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            GameMenuServantImpl gmsImpl = new GameMenuServantImpl(ncRef, rootpoa, orb);
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(gmsImpl);
            GameMenuServant gameMenuServant = GameMenuServantHelper.narrow(ref);

            String name = "Game Menu";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, gameMenuServant);

            System.out.println("Server ready to go...");

            orb.run();
        } catch (InvalidName e) {
            e.printStackTrace();
        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something wrong with starting up the server; please try again.");
        }
    }

    //Sends 17 random letters to players
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

    //Checks if the input word by user is in the words.txt
    public static boolean checkWord(String word) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase(word)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
