package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class AbstractGameMenuFrameImpl extends AbstractGameMenuFrame{

    //TODO: Implement the methods

    @Override
    protected void implementButtons() {

        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        showBestPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        showLongestWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loginFrame.setVisible(true);
                gameMenuFrame.setVisible(false);

            }
        });


    }
}
