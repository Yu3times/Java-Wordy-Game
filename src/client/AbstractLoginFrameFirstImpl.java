package client;

import wordy_idl.IncompleteCredentialsException;
import wordy_idl.InvalidPasswordException;
import wordy_idl.LoggedInException;
import wordy_idl.NoUserFoundException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class AbstractLoginFrameFirstImpl extends AbstractLoginFrame{

    public void implementButtonListeners() {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameMenuServant.logIn(usernameField.getText(), passwordField.getText());
                } catch (InvalidPasswordException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Your password is incorrect.",
                            "Invalid password",
                            JOptionPane.ERROR_MESSAGE
                            );
                    //ex.printStackTrace();
                } catch (NoUserFoundException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No user of the given username exists.",
                            "No user found",
                            JOptionPane.ERROR_MESSAGE
                    );
                    //ex.printStackTrace();
                } catch (LoggedInException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You are already logged in from another machine.",
                            "Already logged in",
                            JOptionPane.ERROR_MESSAGE
                    );
                    //ex.printStackTrace();
                } catch (IncompleteCredentialsException incompleteCredentialsException) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please fill in the missing fields before you log in",
                            "Incomplete credentials",
                            JOptionPane.ERROR_MESSAGE
                    );
                    //incompleteCredentialsException.printStackTrace();
                }
            }
        });
    }

}
