package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import wordy_idl.GameMenuServant;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMenuFrame extends AbstractGameMenuFrameImpl{

    protected GameMenuFrame(String title, GameMenuServant gameMenuServant, JFrame loginFrame,
                            int playerId, NamingContextExt ncRef)  {
        gameMenuFrame = new JFrame(title);
        this.ncRef = ncRef;
        this.gameMenuServant = gameMenuServant;
        this.loginFrame = loginFrame;
        this.playerId = playerId;
        implementButtons();
        gameMenuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                loginFrame.setVisible(true);
                gameMenuFrame.dispose();
            }
        });
    }

    protected void launchFrame() {
        gameMenuFrame.setContentPane(gameMenuPanel);
        gameMenuFrame.setSize(500, 800);
        gameMenuFrame.setResizable(false);
        gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setVisible(true);
    }


}
