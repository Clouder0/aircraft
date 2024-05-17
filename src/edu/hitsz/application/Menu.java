package edu.hitsz.application;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JCheckBox musicCheckBox;

    public Menu() {
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GameBase gameBase = new EasyGame(musicCheckBox.isSelected());
                Main.cardPanel.add(gameBase);
                Main.cardLayout.last(Main.cardPanel);
                gameBase.action();
            }
        });
        mediumButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GameBase gameBase = new MediumGame(musicCheckBox.isSelected());
                Main.cardPanel.add(gameBase);
                Main.cardLayout.last(Main.cardPanel);
                gameBase.action();
            }
        });
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GameBase gameBase = new HardGame(musicCheckBox.isSelected());
                Main.cardPanel.add(gameBase);
                Main.cardLayout.last(Main.cardPanel);
                gameBase.action();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
