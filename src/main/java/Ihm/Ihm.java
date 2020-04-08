package Ihm;

import javax.swing.*;

import Backend.Jeu;

public class Ihm implements Runnable {
    public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("Gauffre");
		// Ajout de notre composant de dessin dans la fenetre
		UI ui = new UI();
        frame.add(ui);
        
        
		//frame.addKeyListener(new EcouteurKey(j,ng));
		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On fixe la taille et on demarre
        ui.setVisible(true);
		frame.setVisible(true);
		
        frame.setSize(500,500);
		frame.addMouseListener(new mouseListener(ui));
		frame.addKeyListener(new keyListener());
        //frame.add(ui.player, BorderLayout.NORTH);
		/*
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}*/
	}
}