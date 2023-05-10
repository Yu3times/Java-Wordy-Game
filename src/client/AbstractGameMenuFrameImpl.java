package client;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

abstract class AbstractGameMenuFrameImpl extends AbstractGameMenuFrame{


    protected AbstractGameMenuFrameImpl() {}

    @Override
    protected void implementButtons() {


        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                gameMenuFrame.setVisible(false);
                new InGameFrame(gameMenuFrame, ncRef, gameMenuServant, playerId).launchFrame();

            }
        });

        showBestPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Best players button pressed");
                new BestPlayersFrame(gameMenuServant).launchFrame();
            }
        });

        showLongestWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
                gameMenuFrame.dispose();
            }
        });


    }
}
