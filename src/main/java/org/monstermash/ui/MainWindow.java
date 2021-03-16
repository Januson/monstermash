//package org.monstermash.ui;
//
//import javax.swing.JFrame;
//import javax.swing.WindowConstants;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//
//public class MainWindow extends JFrame {
//    private final MonsterOptions monsterOptions;
//    private final MenuBar menu;
//
//    public MainWindow(MonsterOptions monsterOptions, MenuBar menu) {
//        super("MonsterMash!");
//        this.monsterOptions = monsterOptions;
//        this.menu = menu;
//    }
//
//    public void run() {
//        this.monsterOptions.init();
//        super.setLayout(new BorderLayout());
//        super.add(monsterOptions, BorderLayout.NORTH);
//        super.add(menu);
//        super.setJMenuBar(menu);
//        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        super.setPreferredSize(new Dimension(850, 615));
//        super.setMinimumSize(super.getPreferredSize());
//        super.setMaximumSize(super.getPreferredSize());
//        super.setSize(super.getPreferredSize());
//        super.setVisible(true);
//    }
//}
