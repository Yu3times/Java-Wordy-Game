package wordy_idl;


/**
* wordy_idl/GameServantHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Wednesday, May 10, 2023 8:44:05 AM SGT
*/


// Interface for a Game object when a player joins a game
abstract public class GameServantHelper
{
  private static String  _id = "IDL:wordy_idl/GameServant:1.0";

  public static void insert (org.omg.CORBA.Any a, GameServant that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static GameServant extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (GameServantHelper.id (), "GameServant");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static GameServant read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_GameServantStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, GameServant value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static GameServant narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof GameServant)
      return (GameServant)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _GameServantStub stub = new _GameServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static GameServant unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof GameServant)
      return (GameServant)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _GameServantStub stub = new _GameServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
