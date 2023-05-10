package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import wordy_idl.GameMenuServant;
import wordy_idl.GameServant;
import wordy_idl.GameServantHelper;
import wordy_idl.NoGameFoundException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InGameFrame {

    private JFrame inGameFrame;
    private JFrame gameMenuFrame;
    private PendingGameView pendingGameView = new PendingGameView();
    private InGameView inGameView;
    private JPanel resultsView;
    private GameMenuServant gameMenuServant;
    private GameServant gameServant;
    private NamingContextExt ncRef;
    private Thread pendingThread;
    private int gameId = -1;
    private int playerId;


    protected InGameFrame(JFrame gameMenuFrame, NamingContextExt ncRef,
                          GameMenuServant gameMenuServant, int playerId) {
        inGameFrame = new JFrame("Wordy Game");
        this.ncRef = ncRef;
        this.gameMenuFrame = gameMenuFrame;
        this.gameMenuServant = gameMenuServant;
        this.playerId = playerId;

        inGameFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                pendingThread.stop();
                exitFrame();
            }
        });
    }

    protected void launchFrame() {
        //TODO

        inGameFrame.setSize(500, 500);
        inGameFrame.setResizable(false);
        inGameFrame.setLocationRelativeTo(null);
        inGameFrame.setVisible(true);


        // Try to join a pending game first, if no game can be found, create a new game and set to a new view.
        try {
            gameId = gameMenuServant.joinGame(playerId);
        } catch (NoGameFoundException e) {
            gameId = gameMenuServant.createGame(playerId);
        }
        inGameFrame.setContentPane(pendingGameView.getPanel());

        // Once the view has been set, obtain the game object using the game id as a reference.
            try {
                System.out.println("Getting game servant ID " + gameId);
                String name = String.valueOf(gameId);
                gameServant = GameServantHelper.narrow(ncRef.resolve_str(name));
                inGameView = new InGameView(gameServant);
                System.out.println("Game servant obtained");
            } catch (NotFound e) {
                e.printStackTrace();
            } catch (CannotProceed e) {
                e.printStackTrace();
            } catch (InvalidName e) {
                e.printStackTrace();
            }

        // Once the game servant is obtained, get the remaining ETA from it while it is waiting for new players.
        pendingThread = new Thread(new Runnable() {

            @Override
            public void run() {
                    //
                    long timeRemaining = gameServant.getRemainingPendingTime() / 1000;

                    while (timeRemaining > 0) {
                        pendingGameView.updateTimeRemaining(timeRemaining);
                        timeRemaining = gameServant.getRemainingPendingTime() / 1000;
                    }

                    // Once the countdown is finished, check if there are any players who have joined.
                    // If there are no players who have joined, inform the player with a dialog box
                    // and exit out of the in game frame.
                    int playerCount = gameServant.checkPlayerCount();
                    if (playerCount <= 1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "No other players have joined the game",
                                "No players Joined.",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        exitFrame();
                        return;
                    }

                        // If more than 1 player has joined, switch to the in game view
                    else {
                        inGameFrame.setContentPane(inGameView.getPanel());

                        // Wait for the ready timer.
                        long readyTimeRemaining;
                        do {
                            readyTimeRemaining = gameServant.getRemainingReadyTime() / 1000;
                        } while (readyTimeRemaining > 0);

                        // Now do the round timer
                        long roundTimeRemaining;
                        do {
                            roundTimeRemaining = gameServant.getRemainingRoundTime() / 1000;
                        } while (roundTimeRemaining > 0);

                        // Once the round is finished, get the winner and show it with the results view.
                        //TODO


                    }
                }

        });
        pendingThread.start();



    }

    public void exitFrame() {
        inGameFrame.dispose();
        gameMenuFrame.setVisible(true);
    }



}
