package Ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

import Ai.Ai;
import Ai.Brain;
import Ai.Coup;
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
        int i = _cell.x;
        int j = _cell.y;
        System.out.printf("Mouse position : (%d,%d)\nGame position : (%d,%d)\n", e.getX(), e.getY(), i, j);
        Jeu.occupe(i,j);
        if (Jeu.mode_JEU == GameMode.PVA && Jeu.GameOver == false) {
            coupCourant = Brain.nextMove();
            Jeu.occupe(coupCourant.i, coupCourant.j);
        }
        System.out.println("AI is playing.");
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