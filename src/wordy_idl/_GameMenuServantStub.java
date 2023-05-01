package wordy_idl;


/**
* wordy_idl/_GameMenuServantStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Wordy.idl
* Monday, May 1, 2023 4:30:44 PM SGT
*/


// Interface for the Game Menu Servant object
public class _GameMenuServantStub extends org.omg.CORBA.portable.ObjectImpl implements GameMenuServant
{

  public void logIn (String username, String password) throws InvalidPasswordException, NoUserFoundException, LoggedInException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("logIn", true);
                $out.write_string (username);
                $out.write_string (password);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:wordy_idl/InvalidPasswordException:1.0"))
                    throw InvalidPasswordExceptionHelper.read ($in);
                else if (_id.equals ("IDL:wordy_idl/NoUserFoundException:1.0"))
                    throw NoUserFoundExceptionHelper.read ($in);
                else if (_id.equals ("IDL:wordy_idl/LoggedInException:1.0"))
                    throw LoggedInExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                logIn (username, password        );
            } finally {
                _releaseReply ($in);
            }
  } // logIn

  public void startGame (int userId) throws NoPlayersJoinedException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("startGame", true);
                $out.write_long (userId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:wordy_idl/NoPlayersJoinedException:1.0"))
                    throw NoPlayersJoinedExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                startGame (userId        );
            } finally {
                _releaseReply ($in);
            }
  } // startGame

  public int joinGame (int userId) throws NoGameFoundException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("joinGame", true);
                $out.write_long (userId);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:wordy_idl/NoGameFoundException:1.0"))
                    throw NoGameFoundExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return joinGame (userId        );
            } finally {
                _releaseReply ($in);
            }
  } // joinGame

  public String[] getBestPlayers ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getBestPlayers", true);
                $in = _invoke ($out);
                String $result[] = bestPlayersHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getBestPlayers (        );
            } finally {
                _releaseReply ($in);
            }
  } // getBestPlayers

  public String[] getLongestWords ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLongestWords", true);
                $in = _invoke ($out);
                String $result[] = longestWordsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getLongestWords (        );
            } finally {
                _releaseReply ($in);
            }
  } // getLongestWords

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:wordy_idl/GameMenuServant:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _GameMenuServantStub
