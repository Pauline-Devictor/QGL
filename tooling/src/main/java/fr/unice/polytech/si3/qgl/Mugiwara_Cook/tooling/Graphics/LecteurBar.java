//package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics;
//
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Route;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics.DrawRoute;
//
//public class LecteurBar {
//    static Route route;
//    static JFrame frame;
//    static int nbCoup;
//
//    public LecteurBar(Route route, JFrame frame) {
//        this.route = route;
//        this.frame = frame;
//        this.nbCoup = route.getPositionArrayList().size();
//    }
//
//    public static JPanel createLecteurBar() {
//        JPanel panelGeneral = new JPanel();
//        panelGeneral.setBackground(new Color(131, 209, 229));
//
//        JPanel panelInit = new JPanel();
//
//        JButton jButton = new JButton();
//        jButton.setText("<<");
//        panelInit.add(jButton);
//
//        jButton = new JButton();
//        jButton.setText("<");
//        panelInit.add(jButton);
//        jButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                nbCoup -= 1000;
//                Display.info("MDR: " + nbCoup);
//                frame.getContentPane().add(new DrawRoute(route, nbCoup));
//            }
//        });
//
//        jButton = new JButton();
//        jButton.setText(">");
//        panelInit.add(jButton);
//
//        jButton = new JButton();
//        jButton.setText(">>");
//        panelInit.add(jButton);
//
//        panelInit.setBackground(new Color(131, 209, 229));
//
//        panelGeneral.add(panelInit, BorderLayout.SOUTH);
//
//
//        return panelGeneral;
//
//    }
//
//}
