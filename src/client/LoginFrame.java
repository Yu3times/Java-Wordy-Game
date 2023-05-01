package client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends AbstractLoginFrameFirstImpl{

    private JFrame loginFrame;

    protected LoginFrame(String title) {

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
