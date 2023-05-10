package server;

import misc.Alphabet;
import misc.Words;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import server.database.DatabaseBridge;
import wordy_idl.*;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;

public class GameServantImpl extends GameServantPOA {

    public static enum GAME_STATUS {
        PENDING("Pending Players"),
        ONGOING("Ongoing"),
        FINISHED("Finished");

        private final String status;

        GAME_STATUS(String inStatus) {
            status = inStatus;
        }

        public String getStatus() {
            return status;
        }

    }

    private NamingContextExt ncRef;
    private DatabaseBridge databaseBridge;
    private ArrayList<Character> currentGeneratedLetters;
    private GAME_STATUS gameStatus = GAME_STATUS.PENDING;
    private int gameId;
    private int currentRound = 1;
    private long pendingTimeRemaining = 0;
    private long roundTimeRemaining = 0;
    private long readyTimeRemaining = 0;
    private long resultsTimeRemaining = 0;
    private int longestWordOwner;
    private String longestWord = "";
    private HashMap<Integer, Integer> usersAndWins = new HashMap<>();
    private NameComponent[] path;



    public GameServantImpl(int gameId, DatabaseBridge databaseBridge, NamingContextExt ncRef, NameComponent path[]) {
        this.gameId = gameId;
        this.path = path;
        this.databaseBridge = databaseBridge;
        gameStatus = GAME_STATUS.PENDING;
        this.ncRef = ncRef;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running a thread.");
                doGameProcess();
            }
        }).start();
    }

    public void doGameProcess() {
        // This countdown is for waiting for other players to join.
        doPendingCountdown(10000);

        // If the game doesn't have enough players, it must delete itself by unbinding itself from the naming service
        if (checkPlayerCount() <= 1) {
            System.out.println("No players found; deleting game ID " + gameId);
            removeGame();
            return;
        }

        // If the game can proceed, first prepare the map of users and wins.
        int listOfPlayers[] = databaseBridge.getGamePlayers(gameId);
        for (int playerId: listOfPlayers) usersAndWins.put(playerId, 0);

        // do the loop until the game is finished.
        while (!gameIsFinished()) {

            // Generate the set of letters
            String random = Alphabet.generateRandomLetters();
            for (int i = 0; i < random.length(); i++) {
                currentGeneratedLetters.add(random.charAt(i));
            }

            // Do a ready-set-go timer for the players
            doReadyCountdown(3000);

            // Do a countdown for the actual game
            doRoundCountdown(10000);

            // Find the winner of the round (if there is one) and make a record for it.
            findRoundWinner();

            // Let the users see the results screen for 5 seconds.
            doResultsCountdown(5000);
        }


        // Destroy the game object once it has done its business.
        removeGame();

    }

    private void removeGame() {
        try {
            databaseBridge.deleteGame(gameId);
            ncRef.unbind(path);
        } catch (InvalidName e) {
            e.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }


    private void doPendingCountdown(long timeRemaining) {
        long constantTime = timeRemaining;
        long startTime = System.currentTimeMillis();
        long deltaTime = 0;
        long endTime;

        while (constantTime > deltaTime) {
            endTime = System.currentTimeMillis();
            deltaTime = endTime - startTime;
            pendingTimeRemaining = constantTime - deltaTime;
        }
    }

    private void doReadyCountdown(long timeRemaining) {
        long constantTime = timeRemaining;
        long startTime = System.currentTimeMillis();
        long deltaTime = 0;
        long endTime;

        while (constantTime > deltaTime) {
            endTime = System.currentTimeMillis();
            deltaTime = endTime - startTime;
            readyTimeRemaining = constantTime - deltaTime;
        }
    }

    private void doRoundCountdown(long timeRemaining) {
        long constantTime = timeRemaining;
        long startTime = System.currentTimeMillis();
        long deltaTime = 0;
        long endTime;

        while (constantTime > deltaTime) {
            endTime = System.currentTimeMillis();
            deltaTime = endTime - startTime;
            roundTimeRemaining = constantTime - deltaTime;
        }
    }

    private void doResultsCountdown(long timeRemaining) {
        long constantTime = timeRemaining;
        long startTime = System.currentTimeMillis();
        long deltaTime = 0;
        long endTime;

        while (constantTime > deltaTime) {
            endTime = System.currentTimeMillis();
            deltaTime = endTime - startTime;
            resultsTimeRemaining = constantTime - deltaTime;
        }
    }

    @Override
    public boolean gameCanStart() throws NoPlayersJoinedException, PendingGameException {
        if (pendingTimeRemaining > 0) throw new PendingGameException();
        else if (checkPlayerCount() <= 1) throw new NoPlayersJoinedException();
        else {
            if (gameStatus == GAME_STATUS.PENDING) {
                gameStatus = GAME_STATUS.ONGOING;
                databaseBridge.setGameStatus(gameId, GAME_STATUS.ONGOING);
            }
            return true;
        }
    }



    private void findRoundWinner(){
        //TODO


    }


    public boolean gameIsFinished() {
        //TODO:
        return false;

    }



    @Override
    public void submitWord(int userId, String word) throws InvalidWordException,
            WordLengthException, WrongLetterException {

        if (wrongLetterInWord(word)) throw new WrongLetterException();
        else if (!Words.isValidWord(word)) throw new InvalidWordException();
        else if (word.length() < 5) throw new WordLengthException();

        longestWordOwner = word.length() > longestWord.length() ? userId : longestWordOwner;
        longestWord = word.length() > longestWord.length() ? word : longestWord;
    }

    private boolean wrongLetterInWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            if (currentGeneratedLetters.contains(currentLetter)) continue;
            else return true;
        }
        return false;
    }

    @Override
    public int checkPlayerCount() {
        return databaseBridge.getPlayerCount(gameId);
    }

    @Override
    public long getRemainingPendingTime() {
        return pendingTimeRemaining;
    }

    @Override
    public long getRemainingReadyTime() {
        return readyTimeRemaining;
    }

    @Override
    public long getRemainingRoundTime() {
        return roundTimeRemaining;
    }

    @Override
    public long getRemainingResultsTime() {
        return resultsTimeRemaining;
    }


    @Override
    public char[] getLetters() {
        char[] lettersArray = new char[currentGeneratedLetters.size()];
        for (int i = 0; i < currentGeneratedLetters.size(); i++) {
            lettersArray[i] = currentGeneratedLetters.get(i);
        }
        return lettersArray;
    }
}
