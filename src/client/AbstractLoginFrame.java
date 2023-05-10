package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import wordy_idl.*;

import javax.swing.*;

abstract class AbstractLoginFrame {

    protected JFrame loginFrame;
    protected GameMenuServant gameMenuServant;
    protected JPanel loginPanel;
    protected JPanel loginCenterPanel;
    protected JPasswordField passwordField;
    protected JTextField usernameField;
    protected JPanel loginSouthPanel;
    protected JButton loginButton;
    protected JButton exitButton;
    protected JLabel usernameLabel;
    protected JLabel passwordLabel;
    protected NamingContextExt ncRef;

    public abstract void implementButtonListeners();
}
