package wordy_idl;


/**
* wordy_idl/NoUserFoundException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Monday, May 1, 2023 8:10:20 AM SGT
*/

public final class NoUserFoundException extends org.omg.CORBA.UserException
{

  public NoUserFoundException ()
  {
    super(NoUserFoundExceptionHelper.id());
  } // ctor


  public NoUserFoundException (String $reason)
  {
    super(NoUserFoundExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class NoUserFoundException
