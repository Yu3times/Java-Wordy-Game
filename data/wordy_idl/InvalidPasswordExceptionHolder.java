package wordy_idl;

/**
* wordy_idl/InvalidPasswordExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:04 AM SGT
*/

public final class InvalidPasswordExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public wordy_idl.InvalidPasswordException value = null;

  public InvalidPasswordExceptionHolder ()
  {
  }

  public InvalidPasswordExceptionHolder (wordy_idl.InvalidPasswordException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = wordy_idl.InvalidPasswordExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    wordy_idl.InvalidPasswordExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return wordy_idl.InvalidPasswordExceptionHelper.type ();
  }

}
