package wordy_idl;

/**
* wordy_idl/NoUserFoundExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Monday, May 1, 2023 8:10:20 AM SGT
*/

public final class NoUserFoundExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public wordy_idl.NoUserFoundException value = null;

  public NoUserFoundExceptionHolder ()
  {
  }

  public NoUserFoundExceptionHolder (wordy_idl.NoUserFoundException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = wordy_idl.NoUserFoundExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    wordy_idl.NoUserFoundExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return wordy_idl.NoUserFoundExceptionHelper.type ();
  }

}
