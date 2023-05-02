package client;

public class ClientLauncher {

    public static void main(String[] args) {
        //TODO: Have the ClientLauncher get the Game menu servant object from the ORB first
        // then pass it to the proceeding objects.
        LoginFrame loginFrame = new LoginFrame("Wordy Login");
        loginFrame.launchFrame();
    }
}
