package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Graphics;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Route;

import javax.swing.*;
import java.awt.*;

public class DrawRoute extends JPanel {
    Route route;
    int nbCoup;

    public DrawRoute(Route route, int nbCoup) {
        this.route = route;
        this.nbCoup = nbCoup;
    }

    public void paint(Graphics g) {
        final int DIVI = 12;

        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int hauteur = (int) tailleEcran.getHeight();
        int largeur = (int) tailleEcran.getWidth();

        for (int k = 0; k < this.route.getCheckpoints().length; k++) {
            g.setColor(new Color(0, 90, 200));
            System.out.println(((Circle) this.route.getCheckpoints()[k].getShape()).getRadius());
            g.fillOval((int) (this.route.getCheckpoints()[k].getPosition().getX() / DIVI - ((Circle) this.route.getCheckpoints()[k].getShape()).getRadius() / DIVI) + largeur / 2, (int) (-this.route.getCheckpoints()[k].getPosition().getY() / DIVI - ((Circle) this.route.getCheckpoints()[k].getShape()).getRadius() / DIVI) + hauteur / 2,
                    (int) ((Circle) this.route.getCheckpoints()[k].getShape()).getRadius() * 2 / DIVI, (int) ((Circle) this.route.getCheckpoints()[k].getShape()).getRadius() * 2 / DIVI);
        }

//        g.setColor(new Color(0, 0, 0));
//        g.fillOval(largeur / 2-60/2, hauteur / 2-60/2, 60, 60);

        int k = 0;

        for (int i = 0; i < this.nbCoup - 1; i++) {
//            System.out.println(route.getPositionArrayList().get(i).getListPosition());
            if (i % 1000 == 0) {
                g.setColor(new Color(255, 0, 0));
                g.fillOval((int) (route.getPositionArrayList().get(i).getX()) / DIVI - 3 + largeur / 2, (int) (-route.getPositionArrayList().get(i).getY()) / DIVI - 3 + hauteur / 2, 6, 6);
                System.out.println(route.getPositionArrayList().get(i).getListPosition());
                k++;
            }
            g.setColor(new Color(0, 0, 0));
            g.drawLine((int) (route.getPositionArrayList().get(i).getX()) / DIVI + largeur / 2, (int) (-route.getPositionArrayList().get(i).getY()) / DIVI + hauteur / 2
                    , (int) (route.getPositionArrayList().get(i + 1).getX()) / DIVI + largeur / 2, (int) (-route.getPositionArrayList().get(i + 1).getY()) / DIVI + hauteur / 2);
        }

        System.out.println(k);
    }


}
