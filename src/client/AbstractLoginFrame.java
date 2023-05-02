package client;

import wordy_idl.*;

import javax.swing.*;

abstract class AbstractLoginFrame {

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

    public abstract void implementButtonListeners();
}
