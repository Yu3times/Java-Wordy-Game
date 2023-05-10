package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import server.database.DatabaseBridge;
import wordy_idl.*;

import java.sql.SQLException;

/**
 * This class is the implementation of the object to be used for the client
 * for initiating operations in the main menu such as: logging in,
 * starting a new game, joining a game, getting the list of the best players,
 * or getting the list of the longest words.
 */
public class GameMenuServantImpl extends GameMenuServantPOA {

    private static DatabaseBridge databasebridge;
    private NamingContextExt ncRef;
    private POA rootpoa;
    private ORB orb;

    public GameMenuServantImpl(NamingContextExt ncRef, POA rootpoa, ORB orb) {
        this.rootpoa = rootpoa;
        this.ncRef = ncRef;
        this.orb = orb;
    }

    static {
        try {
            System.out.println("Initiating database connection...");
            databasebridge = new DatabaseBridge();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not connect to the database. Did you forget to set it up first?");
        }
    }


    @Override
    public int logIn(String username, String password)
            throws InvalidPasswordException, NoUserFoundException, LoggedInException {
        System.out.printf("Responding to login query for user: %s\n", username);
        String[] queryResult = databasebridge.getUserData(username);

        // Check if a result has been found for a given username.
        if (queryResult == null) throw new NoUserFoundException();
        else {
            int player_id = Integer.parseInt(queryResult[0]);
            String queriedPassword = queryResult[2];
            int userStatus = Integer.parseInt(queryResult[3]);

            // Check if the passwords are different
            if (!queriedPassword.equals(password)) throw new InvalidPasswordException();

                // Check if the user is already online.
            else if (userStatus == databasebridge.getEquivalentPlayerStatus("online")) throw new LoggedInException();

                // If everything is in order, return the player's id within the database.
            else return player_id;
        }
    }

    /**
     * - Create Game Object
     * - Check database
     * - If no status is "PENDING"
     *      - Add Host to players
     *      - Start Timer
     *      - Start Game when timer is up
     * - If status is "PENDING"
     *      - Call joinGame
     **/
    public int createGame(int userId){
        int gameId = -1;

        try {
            gameId = databasebridge.createNewGame(userId);
            NameComponent path[] = ncRef.to_name(String.valueOf(gameId));
            GameServantImpl gameServantImpl = new GameServantImpl(gameId, databasebridge, ncRef, path);
            org.omg.CORBA.Object ref = null;
            ref = rootpoa.servant_to_reference(gameServantImpl);
            GameServant gameServant = GameServantHelper.narrow(ref);
            ncRef.rebind(path, gameServant);
            System.out.println("Game ID " + gameId + " registered to ORB");


            // Get a list of all the names bound in the Naming Context
            String[] objectList = orb.list_initial_services();
            System.out.println("Current bindings:\n");

            BindingListHolder bindingListHolder = new BindingListHolder();
            BindingIteratorHolder bindingIteratorHolder = new BindingIteratorHolder();

            ncRef.list(1000, bindingListHolder, bindingIteratorHolder);

            Binding[] bindings = bindingListHolder.value;
            if (bindings != null && bindings.length > 0) {
                for (Binding binding : bindings) {
                    System.out.println("Name: " + binding.binding_name[0].id);
                    System.out.println("Class: " + binding.binding_type);
                    System.out.println();
                }
            } else {
                System.out.println("No objects bound in the Naming Context.");
            }

        } catch (ServantNotActive e) {
            e.printStackTrace();
        } catch (WrongPolicy e) {
            e.printStackTrace();
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

        return gameId;
    }



    public int joinGame(int userId) throws NoGameFoundException {
        try {
            int gameId = databasebridge.findPendingGame(userId);
            return gameId;
        } catch (NoGameFoundException e) {
            throw new NoGameFoundException();
        }
    }

    @Override
    public String[][] getBestPlayers() {
        return databasebridge.getTopPlayers(10);
    }

    @Override
    public String[] getLongestWords() {
        return databasebridge.getLongestWords(10);
    }

    @Override
    public void logOut(String username) {

        //TODO
    }

}
