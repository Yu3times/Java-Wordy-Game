package wordy_idl;


/**
* wordy_idl/randomLettersHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/

public final class randomLettersHolder implements org.omg.CORBA.portable.Streamable
{
  public char value[] = null;

  public randomLettersHolder ()
  {
  }

  public randomLettersHolder (char[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = wordy_idl.randomLettersHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    wordy_idl.randomLettersHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return wordy_idl.randomLettersHelper.type ();
  }

}
