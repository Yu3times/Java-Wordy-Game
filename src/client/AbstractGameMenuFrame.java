package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import wordy_idl.GameMenuServant;

import javax.swing.*;

abstract class AbstractGameMenuFrame {

    protected JFrame gameMenuFrame;
    protected JFrame loginFrame;
    protected JButton playGameButton;
    protected JPanel gameMenuPanel;
    protected JButton showBestPlayersButton;
    protected JButton showLongestWordsButton;
    protected JButton exitButton;
    protected GameMenuServant gameMenuServant;
    protected NamingContextExt ncRef;
    protected int playerId;


    protected abstract void implementButtons();
}
