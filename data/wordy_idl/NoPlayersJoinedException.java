package wordy_idl;


/**
* wordy_idl/NoPlayersJoinedException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Monday, May 1, 2023 4:30:44 PM SGT
*/

public final class NoPlayersJoinedException extends org.omg.CORBA.UserException
{

  public NoPlayersJoinedException ()
  {
    super(NoPlayersJoinedExceptionHelper.id());
  } // ctor


  public NoPlayersJoinedException (String $reason)
  {
    super(NoPlayersJoinedExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class NoPlayersJoinedException