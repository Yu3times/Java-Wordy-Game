package client;

import javax.swing.*;

public class PendingGameView {
    private JPanel pendingGamePanel;
    private JLabel preparingTextLabel;
    private JLabel currentTimeTextLabel;
    private JLabel actualTimeRemainingLabel;


    public PendingGameView() {
    }

    public void updateTimeRemaining(long time) {
        actualTimeRemainingLabel.setText(String.valueOf(time));
    }

    public JPanel getPanel() {
        return pendingGamePanel;
    }


}
