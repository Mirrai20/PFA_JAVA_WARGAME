/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author tahad
 */
public class Chrono implements Runnable{

	//**** VARIABLES **//
	private final int PAUSE = 3; // temps d'attente entre 2 tours de boucle : 3 millisecondes
	
	
	//**** METHODES **//
	@Override
	public void run() {

		while(true){ 			
			Wargame.scene.repaint(); // Appel de la méthode PaintComponent de l'objet scene
			try {Thread.sleep(PAUSE);} // temps de pause du flux (3 ms)
			catch (InterruptedException e) {}
		}
	}
    
}
