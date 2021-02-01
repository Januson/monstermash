package org.monstermash.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private final FileMenu fileMenu;

    public MenuBar() {
        this.fileMenu = new FileMenu("File");
        super.add(this.fileMenu);
    }

    private static class FileMenu extends JMenu {
        private final JMenuItem exit;

        public FileMenu(String title) {
            super(title);
            exit = new JMenuItem("Exit");
            exit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X,
                InputEvent.CTRL_DOWN_MASK));
            exit.addActionListener(new ExitApp());
            super.add(exit);
        }
    }

    private static class ExitApp implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            System.exit(0);
        }
    }

}




























