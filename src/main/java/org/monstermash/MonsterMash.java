package org.monstermash;

import org.monstermash.ui.MainWindow;
import org.monstermash.ui.MonsterOptions;

import javax.swing.SwingUtilities;

public class MonsterMash {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(new MonsterOptions());
        SwingUtilities.invokeLater(mainWindow::run);
    }
}
