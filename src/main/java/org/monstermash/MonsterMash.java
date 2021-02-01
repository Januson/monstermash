package org.monstermash;

import org.monstermash.ui.MainWindow;
import org.monstermash.ui.MenuBar;
import org.monstermash.ui.MonsterOptions;

import javax.swing.SwingUtilities;

public class MonsterMash {
    public static void main(String[] args) {
        final var mainWindow = buildApp();
        SwingUtilities.invokeLater(mainWindow::run);
    }

    private static MainWindow buildApp() {
        final var monsterOptions = new MonsterOptions();
        final var menuBar = new MenuBar();
        return new MainWindow(monsterOptions, menuBar);
    }
}
