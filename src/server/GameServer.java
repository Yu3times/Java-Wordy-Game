package server;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import wordy_idl.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

/**
 * This class initiates the ORB using the GameMenuServantImpl object.
 */
public class GameServer {

    public static void main(String[] args) {

        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            GameMenuServantImpl gmsImpl = new GameMenuServantImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(gmsImpl);
            GameMenuServant gameMenuServant = GameMenuServantHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Game Menu";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, gameMenuServant);

            System.out.println("Server ready to go...");

            orb.run();


        } catch (InvalidName e) {
            e.printStackTrace();
        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something wrong with starting up the server; please try again.");
        }

    }

}
