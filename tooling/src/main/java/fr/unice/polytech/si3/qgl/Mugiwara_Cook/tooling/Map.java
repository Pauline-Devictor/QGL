package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics.DrawRoute;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics.LecteurBar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics.MenuBar;

import javax.swing.*;
import java.awt.*;

public class Map {
    AdvanceSimulator advanceSimulator;

    public Map() {
        advanceSimulator = new AdvanceSimulator();
    }

    public void run() {
        Route route = advanceSimulator.createNextRound();

        final JFrame frame = new JFrame("Simulator");
        frame.setJMenuBar(new MenuBar().createMenuBar());
//        frame.add(new LecteurBar(route,frame).createLecteurBar(), BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new DrawRoute(route,route.getPositionArrayList().size()));
        frame.getContentPane().setBackground(new Color(131, 209, 229));
        frame.setBackground(new Color(131, 209, 229));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

}
