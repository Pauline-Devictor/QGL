package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MenuBar {

    public static JMenuBar createMenuBar() {

        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JCheckBoxMenuItem cbmi;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the File Menu.
        menu = new JMenu("Fichier");

        menuItem = new JMenuItem("Screenshot");
        menu.add(menuItem);

        menuBar.add(menu);

        menu = new JMenu("Affichage");
        cbmi = new JCheckBoxMenuItem("Lecteur");
        menu.add(cbmi);
        cbmi = new JCheckBoxMenuItem("Detail bateau");
        menu.add(cbmi);
        menuBar.add(menu);

        menu = new JMenu("Edit");
        menuItem = new JMenuItem("InitGame");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                GraphInitGame graphInitGame = new GraphInitGame();
                graphInitGame.createInitGame();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);

        return menuBar;

    }
}

