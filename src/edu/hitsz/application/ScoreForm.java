package edu.hitsz.application;

import edu.hitsz.application.score.ScoreManager;
import edu.hitsz.application.score.ScoreRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.*;

public class ScoreForm {
    private JTable scoreTable;
    private JScrollPane tableScrollPanel;
    private JPanel scorePanel;
    private JButton deleteButton;

    public ScoreForm() {
        String[] columns = {"Name", "Score", "Time"};
        List<ScoreRecord> scores = ScoreManager.getScores();
        String[][] data = new String[scores.size()][3];
        for(int i = 0; i < scores.size(); i++) {
            data[i][0] = String.valueOf(scores.get(i).name);
            data[i][1] = String.valueOf(scores.get(i).score);
            data[i][2] = scores.get(i).time.toString();
        }
        DefaultTableModel model = new DefaultTableModel(data, columns);
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int[] idx = scoreTable.getSelectedRows();
                if(idx.length == 0) return;
                for(int i = idx.length - 1; i >= 0; --i) {
                    model.removeRow(idx[i]);  // reverse delete
                }
                int n = model.getRowCount();
                ScoreManager.clear();
                for(int i = 0; i < n;++i) {
                    ScoreRecord x = new ScoreRecord(model.getValueAt(i,0).toString(), LocalDateTime.parse(model.getValueAt(i,2).toString()), Integer.parseInt(model.getValueAt(i, 1).toString()));
                    ScoreManager.writeScore(x);
                }
            }
        });
    }

    public JPanel getPanel() {
        return scorePanel;
    }
}
