//package org.monstermash.ui;
//
//import javax.swing.JPanel;
//import java.awt.BorderLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//
//public class MonsterOptions extends JPanel {
//    private final MonsterOverview overview;
//
//    public MonsterOptions() {
//        overview = new MonsterOverview();
//    }
//
//    public void init() {
//        this.overview.init();
//        super.setLayout(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        JPanel overviewElements = new JPanel();
//        overviewElements.setLayout(new BorderLayout());
//        overviewElements.add(overview, BorderLayout.WEST);
//        super.add(overviewElements, constraints);
//    }
//
//    public String getMonsterName() {
//        return overview.getMonsterName();
//    }
//}
