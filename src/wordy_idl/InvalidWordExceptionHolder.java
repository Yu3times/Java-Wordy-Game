package wordy_idl;

/**
* wordy_idl/InvalidWordExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/

public final class InvalidWordExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public InvalidWordException value = null;

  public InvalidWordExceptionHolder ()
  {
  }

  public InvalidWordExceptionHolder (InvalidWordException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = InvalidWordExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    InvalidWordExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return InvalidWordExceptionHelper.type ();
  }

}