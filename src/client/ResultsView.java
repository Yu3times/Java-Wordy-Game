package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultsView {
    private JPanel resultsPanel;
    private JTable currentGameLeaderboardsTable;
    private JLabel waitingLabel;
    private JScrollPane currentGameLeaderboardsScroll;
    private JLabel theWinnerIsLabel;
    private JLabel winnerNameLabel;

    public ResultsView(String[][] tableData, String winnerName) {

        initializeTableData(tableData);
        winnerNameLabel.setText(winnerName);
    }


    private void initializeTableData(String[][] tableData) {
        String[] columnNames = {"Players", "Wins"};
        DefaultTableModel dtm = new DefaultTableModel(tableData, columnNames) {

            public boolean isCellEditable(int row, int column) { return false; }
        };

        currentGameLeaderboardsTable.setModel(dtm);
        currentGameLeaderboardsTable.setCellSelectionEnabled(false);
    }

    public JPanel getPanel() {
        return resultsPanel;
    }
}
