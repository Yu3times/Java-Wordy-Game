package wordy_idl;


/**
* wordy_idl/InvalidPasswordException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:04 AM SGT
*/

public final class InvalidPasswordException extends org.omg.CORBA.UserException
{

  public InvalidPasswordException ()
  {
    super(InvalidPasswordExceptionHelper.id());
  } // ctor


  public InvalidPasswordException (String $reason)
  {
    super(InvalidPasswordExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class InvalidPasswordException
