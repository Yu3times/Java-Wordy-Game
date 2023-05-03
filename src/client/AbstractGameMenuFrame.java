package client;

import javax.swing.*;

abstract class AbstractGameMenuFrame {

    protected JFrame gameMenuFrame;
    protected JFrame loginFrame;
    protected JButton playGameButton;
    protected JPanel gameMenuPanel;
    protected JButton showBestPlayersButton;
    protected JButton showLongestWordsButton;
    protected JButton exitButton;

    protected abstract void implementButtons();
}
