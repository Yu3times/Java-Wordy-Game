package wordy_idl;


/**
* wordy_idl/PendingGameException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/

public final class PendingGameException extends org.omg.CORBA.UserException
{

  public PendingGameException ()
  {
    super(PendingGameExceptionHelper.id());
  } // ctor


  public PendingGameException (String $reason)
  {
    super(PendingGameExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class PendingGameException
