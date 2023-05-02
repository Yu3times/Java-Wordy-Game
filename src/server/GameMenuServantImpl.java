package server;

import wordy_idl.*;

/**
 * This class is the implementation of the object to be used for the client
 * for initiating operations in the main menu such as: logging in,
 * starting a new game, joining a game, getting the list of the best players,
 * or getting the list of the longest words.
 */
public class GameMenuServantImpl extends GameMenuServantPOA{

    //TODO: Implement the methods in the Game menu servant.

    @Override
    public void logIn(String username, String password)
            throws InvalidPasswordException, NoUserFoundException, LoggedInException {


    }

    @Override
    public void startGame(int userId) throws NoPlayersJoinedException {

    }

    @Override
    public int joinGame(int userId) throws NoGameFoundException {
        return 0;
    }

    @Override
    public String[] getBestPlayers() {
        return new String[0];
    }

    @Override
    public String[] getLongestWords() {
        return new String[0];
    }
}
