package wordy_idl;

/**
* wordy_idl/WrongLetterExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/

public final class WrongLetterExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public wordy_idl.WrongLetterException value = null;

  public WrongLetterExceptionHolder ()
  {
  }

  public WrongLetterExceptionHolder (wordy_idl.WrongLetterException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = wordy_idl.WrongLetterExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    wordy_idl.WrongLetterExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return wordy_idl.WrongLetterExceptionHelper.type ();
  }

}
