package client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import wordy_idl.*;

public class LoginFrame extends AbstractLoginFrameImpl {


    protected LoginFrame(String title, GameMenuServant gameMenuServant) {

        this.gameMenuServant = gameMenuServant;
        loginFrame = new JFrame(title);
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    protected void launchFrame() {

        implementButtonListeners();

        loginFrame.setContentPane(loginPanel);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setSize(500, 300);
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }

}
