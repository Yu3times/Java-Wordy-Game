package wordy_idl;


/**
* wordy_idl/GameMenuServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/


// Interface for the Game Menu Servant object
public interface GameMenuServantOperations 
{
  int logIn (String username, String password) throws InvalidPasswordException, NoUserFoundException, LoggedInException, IncompleteCredentialsException;
  int createGame (int userId);
  int joinGame (int userId) throws NoGameFoundException;
  String[][] getBestPlayers ();
  String[] getLongestWords ();
  void logOut (String username);
} // interface GameMenuServantOperations
