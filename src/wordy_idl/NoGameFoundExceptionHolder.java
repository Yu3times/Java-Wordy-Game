package wordy_idl;

/**
* wordy_idl/NoGameFoundExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Monday, May 1, 2023 8:10:20 AM SGT
*/

public final class NoGameFoundExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public NoGameFoundException value = null;

  public NoGameFoundExceptionHolder ()
  {
  }

  public NoGameFoundExceptionHolder (NoGameFoundException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = NoGameFoundExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    NoGameFoundExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return NoGameFoundExceptionHelper.type ();
  }

}
