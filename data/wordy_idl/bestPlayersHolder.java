package wordy_idl;


/**
* wordy_idl/bestPlayersHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/

public final class bestPlayersHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[][] = null;

  public bestPlayersHolder ()
  {
  }

  public bestPlayersHolder (String[][] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = wordy_idl.bestPlayersHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    wordy_idl.bestPlayersHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return wordy_idl.bestPlayersHelper.type ();
  }

}
