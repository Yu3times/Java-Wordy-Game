package client;

import wordy_idl.GameServant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameView {
    private JScrollPane submittedWordScrollPane;
    private JList submittedWordList;
    private JPanel timerPanel;
    private JPanel submitWordPanel;
    private JLabel timerLabel;
    private JTextField inputWordField;
    private JButton submitButton;
    private JLabel yourLettersLabel;
    private JLabel randomLettersLabel;
    private JLabel feedbackLabel;
    private JPanel inGamePanel;
    private GameServant gameServant;


    public InGameView(GameServant gameServant) {

        implementComponents();
        this.gameServant = gameServant;

    }

    private void implementComponents() {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
    }

    public void updateRoundTimeRemaining(long time) {
        timerLabel.setText(String.format(
                "Estimated time remaining: %d seconds", time
        ));
    }

    public JPanel getPanel() {
        return inGamePanel;
    }
}
