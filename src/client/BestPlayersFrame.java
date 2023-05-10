package client;

import server.GameMenuServantImpl;
import wordy_idl.GameMenuServant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BestPlayersFrame {
    private JFrame bestPlayersFrame;
    private JScrollPane bestPlayersScrollPane;
    private JTable bestPlayersTable;
    private JPanel bestPlayersPanel;
    private GameMenuServant gameMenuServant;

    public BestPlayersFrame(GameMenuServant gameMenuServant) {
        this.gameMenuServant = gameMenuServant;
        bestPlayersFrame = new JFrame("Best Players");
        bestPlayersFrame.setContentPane(bestPlayersPanel);

        bestPlayersFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                bestPlayersFrame.setVisible(false);
            }
        });
    }

    public void launchFrame() {
        String[] columnNames = new String[]{"Player", "Wins"};
        String[][] tableData = gameMenuServant.getBestPlayers();

        DefaultTableModel dfm = new DefaultTableModel(tableData, columnNames) {

            public boolean isCellEditable(int row, int column) { return false; }

        };

        bestPlayersTable.setModel(dfm);
        bestPlayersFrame.setSize(300, 300);
        bestPlayersFrame.setResizable(false);
        bestPlayersTable.setCellSelectionEnabled(false);
        bestPlayersFrame.setLocationRelativeTo(null);
        bestPlayersFrame.setVisible(true);
    }
}
