package org.monstermash.ui;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatBlock {
    private static JDialog currentRenderWindow = null;
    private static final Color TRANSPARENT_COLOR = new Color(238, 238, 238);

    public static void renderImage() {
        if (currentRenderWindow != null) {
            currentRenderWindow.dispose();
            currentRenderWindow = null;
        }
        JDialog renderWindow = new JDialog();
        renderWindow.setTitle("Render Monster");
        renderWindow.setModal(true);
        currentRenderWindow = renderWindow;
        renderWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                currentRenderWindow = null;
            }
        });
        JPanel windowContents = new JPanel();
        windowContents.setBackground(TRANSPARENT_COLOR);
        windowContents.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        windowContents.add(getNewBuffer(), constraints);
        constraints.gridy++;
        constraints.fill = GridBagConstraints.NONE;
        windowContents.add(getNewEndCap(), constraints);
        constraints.gridy++;
        windowContents.add(getWindowContents(), constraints);
        constraints.gridy++;
        windowContents.add(getNewEndCap(), constraints);
        constraints.gridy++;
        windowContents.add(getNewBuffer(), constraints);
        renderWindow.setPreferredSize(renderWindow.getPreferredSize());
        Dimension newDimension = windowContents.getPreferredSize();
        newDimension.width = 450;
        newDimension.height += newDimension.height / 4;
        newDimension.height -= 10;
        renderWindow.setPreferredSize(newDimension);
        renderWindow.setMinimumSize(new Dimension(newDimension.width, 50));
        renderWindow.setMaximumSize(newDimension);
        windowContents.setPreferredSize(newDimension);
        JScrollPane scroller = new JScrollPane(windowContents);
        renderWindow.add(scroller);
        renderWindow.setSize(renderWindow.getPreferredSize());
        renderWindow.setVisible(true);
    }

    private static JPanel getWindowContents() {
        JPanel returnMe = new JPanel();
        returnMe.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < 3; i++) {
            constraints.gridx = i;
            JPanel spacer = new JPanel();
            spacer.setOpaque(false);
            spacer.setPreferredSize(new Dimension(9, 1));
            returnMe.add(spacer, constraints);
        }
        //Actual content
        String nameData = "Ghoul";
        JLabelBrown nameLabel = new JLabelBrown(nameData);
        constraints.gridy++;
        constraints.gridx = 1;
        returnMe.add(nameLabel, constraints);
        constraints.ipady = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy++;
        JLabel sizeTypeTag = new JLabel(String.format("%s %s%s%s, %s",
            "Huge",
            "Undead",
            "Revenant",
            "Revenant",
            "Very evil"
        ));
        returnMe.add(sizeTypeTag, constraints);
        constraints.gridy++;
        returnMe.add(getNewSeparator(), constraints);
        constraints.gridy++;
        returnMe.add(getOverview(), constraints);
        constraints.gridy++;
        returnMe.add(getNewSeparator(), constraints);
        constraints.gridy++;
        returnMe.add(getStatArea(), constraints);
        constraints.gridy++;
        returnMe.add(getNewSeparator(), constraints);
//        constraints.gridy++;
//        returnMe.add(getMiscAttributes(), constraints);
        constraints.gridy++;
        returnMe.add(getNewSeparator(), constraints);
//        constraints.gridy++;
//        returnMe.add(getAbilities(), constraints);
//        constraints.gridy++;
//        returnMe.add(getActions(), constraints);
//        constraints.gridy++;
//        returnMe.add(getReactions(), constraints);
//        constraints.gridy++;
//        returnMe.add(getLegendaryActions(), constraints);
        returnMe.setBackground(Color.decode("#fdf1dd"));
        returnMe.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
            Color.decode("#DCDCDC")));
        returnMe.setPreferredSize(new Dimension(
            returnMe.getPreferredSize().width,
            returnMe.getPreferredSize().height + 50
        ));
        return returnMe;
    }

    private static JPanel getOverview() {
        JPanel returnMe = new JPanel();
        returnMe.setOpaque(false);
        returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
        JPanel armorClass = new JPanel();
        armorClass.setOpaque(false);
        armorClass.setLayout(new BoxLayout(armorClass, BoxLayout.X_AXIS));
        JLabelBrownOverview acLabel;
        acLabel = new JLabelBrownOverview("Armor Class",
            String.format("%d %s",
                27,
                "Natural(Bone)"
            ));
        armorClass.add(acLabel);
        armorClass.add(Box.createHorizontalGlue());
        returnMe.add(armorClass);
        JPanel hitPoints = new JPanel();
        hitPoints.setOpaque(false);
        hitPoints.setLayout(new BoxLayout(hitPoints, BoxLayout.X_AXIS));
        JLabelBrownOverview hpLabel = new JLabelBrownOverview("Hit Points ",
            "227");
        hitPoints.add(hpLabel);
        hitPoints.add(Box.createHorizontalGlue());
        returnMe.add(hitPoints);
        JPanel speed = new JPanel();
        //Get Information
        String speedDataString = String.format("%d ft.", 30);
        int speedSwim = 30;
        int burrowSpeed = 30;
        int climbSpeed = 45;
        int flySpeed = 0;
        if (speedSwim > 0) {
            speedDataString += String.format(", swim %d ft.", speedSwim);
        }
        if (burrowSpeed > 0) {
            speedDataString += String.format(", burrow %d ft.", burrowSpeed);
        }
        if (climbSpeed > 0) {
            speedDataString += String.format(", climb %d ft.", climbSpeed);
        }
        if (flySpeed > 0) {
            speedDataString += String.format(", fly %d ft. %s",
                flySpeed,
                "(hover)");
        }
        speedDataString += " ";
        //Finish panel
        speed.setOpaque(false);
        speed.setLayout(new BoxLayout(speed, BoxLayout.X_AXIS));
        JLabelBrownOverview speedLabel = new JLabelBrownOverview("Speed", speedDataString);
        speed.add(speedLabel);
        speed.add(Box.createHorizontalGlue());
        returnMe.add(speed);
        return returnMe;
    }

    private static JPanel getStatArea() {
        JPanel returnMe = new JPanel();
        returnMe.setOpaque(false);
        returnMe.setPreferredSize(new Dimension(400, 50));
        returnMe.setMaximumSize(returnMe.getPreferredSize());
        returnMe.setMinimumSize(returnMe.getPreferredSize());
        returnMe.setSize(returnMe.getPreferredSize());
        returnMe.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 15;
        constraints.ipady = 5;
        constraints.weightx = (1.0 / 4);
        List<String> stats = List.of(
            "Zdatnost",
            "Reflexy",
            "Inteligence",
            "Vhled"
        );
        for (String stat : stats) {
            JLabelBrown label = new JLabelBrown(stat);
            returnMe.add(label, constraints);
            constraints.gridx++;
        }
        constraints.gridy++;
        constraints.gridx = 0;
        JLabelBrown fortitude = new JLabelBrown("3");
        returnMe.add(fortitude, constraints);

        JLabelBrown reflexes = new JLabelBrown("2");
        constraints.gridx++;
        returnMe.add(reflexes, constraints);

        JLabelBrown intelligence = new JLabelBrown("0");
        constraints.gridx++;
        returnMe.add(intelligence, constraints);

        JLabelBrown insight = new JLabelBrown("1");
        constraints.gridx++;
        returnMe.add(insight, constraints);
        return returnMe;
    }

    private static JLabel getNewSeparator() {
        URL url = StatBlock.class.getResource("/images/separator.png");
        BufferedImage img;
        try {
            img = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(StatBlock.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return new JLabel(new ImageIcon(img));
    }

    private static JPanel getNewEndCap() {
        JPanel returnMe = new JPanel();
        returnMe.setPreferredSize(new Dimension(425, 5));
        returnMe.setSize(returnMe.getPreferredSize());
        returnMe.setMaximumSize(returnMe.getPreferredSize());
        returnMe.setMinimumSize(returnMe.getPreferredSize());
        returnMe.setBackground(Color.decode("#e49937"));
        returnMe.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return returnMe;
    }

    private static JPanel getNewBuffer() {
        JPanel buffer = new JPanel();
        buffer.setOpaque(false);
        buffer.setPreferredSize(new Dimension(100, 50));
        buffer.setMinimumSize(buffer.getPreferredSize());
        return buffer;
    }

}