//package org.monstermash.ui;
//
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//
//public class MonsterOverview extends JPanel {
//    private final JTextField name;
//
//    public MonsterOverview() {
//        name = new JTextField(20);
//    }
//
//    public void init() {
//        super.setLayout(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridy = 0;
//        //Name
//        constraints.gridx = 0;
//        adjustGridBagAnchor(constraints);
//        super.add(new JLabel("Name"), constraints);
//        constraints.gridx = 1;
//        adjustGridBagAnchor(constraints);
//        super.add(name, constraints);
//        constraints.gridy++;
//    }
//
//    private void adjustGridBagAnchor(GridBagConstraints gridBag) {
//        if (gridBag == null) {
//            return;
//        }
//        if (gridBag.gridx == 0) {
//            gridBag.anchor = GridBagConstraints.EAST;
//        } else {
//            gridBag.anchor = GridBagConstraints.WEST;
//        }
//    }
//
//    public String getMonsterName() {
//        return name.getText();
//    }
//
//}
