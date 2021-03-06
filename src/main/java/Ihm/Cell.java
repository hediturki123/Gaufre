package Ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import Ai.Ai;
import Ai.Brain;
import Ai.Coup;
import Ai.Coupgagnant;
import Ai.Easy;
import Backend.GameLevel;
import Backend.GameMode;
import Backend.Jeu;
import Backend.Turn;

import java.awt.*;
import java.awt.event.*;

public class Cell extends JComponent {
    Image _img;
    int x, y, w, h;

    public Cell(Image img, int _x, int _y, int _w, int _h) {
        this._img = img;
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
        this.setVisible(true);
        this.addMouseListener(new cellMouseListener(this));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;
        drawable.drawImage(_img, 0, 0, w, h, this);
    }
}

class cellMouseListener implements MouseListener {
    Cell _cell;
    Coup coupCourant = null;
    boolean firstMove = false;

    cellMouseListener(Cell cell) {
        this._cell = cell;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (!this._cell.isVisible() || !(Jeu.canPlay))
            return;
        int i = _cell.x;
        int j = _cell.y;
        System.out.println("P  : " + Jeu.canPlay);
        System.out.printf("Mouse position : (%d,%d)\nGame position : (%d,%d)\n", e.getX(), e.getY(), i, j);
        Jeu.occupe(i, j);
        Jeu.canPlay = false;
        Thread ai = new Thread() {
            public void run() {
                System.out.println("AI  : " + Jeu.canPlay);
                try {
                    if (Jeu.canPlay) {
                        try {
                            interrupt();
                            return;
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                    Jeu._ui.playerBar.setText(Jeu.getPlayer());

                    if (Jeu.mode_JEU == GameMode.PVA && Jeu.GameOver == false && Jeu.mode_IA == GameLevel.Hard) {
                        Thread.sleep(1000);
                        coupCourant = Brain.nextMove();
                        Jeu.occupe(coupCourant.i, coupCourant.j);
                    }
                    if (Jeu.mode_JEU == GameMode.PVA && Jeu.GameOver == false && Jeu.mode_IA == GameLevel.Medium) {
                        Thread.sleep(1000);
                        Coupgagnant.PlayC();

                    }
                    if (Jeu.mode_JEU == GameMode.PVA && Jeu.GameOver == false && Jeu.mode_IA == GameLevel.Easy) {
                        Thread.sleep(1000);
                        Easy.Play();
                    }
                    Jeu._ui.playerBar.setText(Jeu.getPlayer());
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
                Jeu.canPlay = true;
            }
        };
        ai.start();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}