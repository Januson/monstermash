package org.monstermash;

import org.monstermash.ui.MainWindow;
import org.monstermash.ui.MenuBar;
import org.monstermash.ui.MonsterOptions;

import javax.swing.SwingUtilities;

public class MonsterMash {
    public static void main(String[] args) {
        MonsterOptions monsterOptions = new MonsterOptions();
        MenuBar menuBar = new MenuBar();
        MainWindow mainWindow = new MainWindow(monsterOptions, menuBar);
        SwingUtilities.invokeLater(mainWindow::run);
    }
}
