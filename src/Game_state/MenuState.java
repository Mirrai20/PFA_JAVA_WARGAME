// The main menu GameState.

package Game_state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game_manager.Content;
import Game_manager.GameStateManager;
import Game_manager.JukeBox;
import Game_manager.Keys;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage fleche;
	
	private int currentOption = 0;
	private String[] options = {
		"Play",
		"Regles de jeu",
		"About Us",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		fleche = Content.Fleche[0][0];
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 164, 60);
		Content.drawString(g, options[1], 127, 80);
		Content.drawString(g, options[2], 150 , 100);
		Content.drawString(g, options[3], 164, 120);

		
		if(currentOption == 0) g.drawImage(fleche, 144, 58, null);
		else if(currentOption == 1) g.drawImage(fleche, 107, 78, null);
		else if(currentOption == 2) g.drawImage(fleche, 130, 98, null);
		else if(currentOption == 3) g.drawImage(fleche, 144, 118, null);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			JukeBox.play("menuoption");
			currentOption--;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("collect");
			selectOption();
		}
	}
	
	private void selectOption() {
		if(currentOption == 0) {
			gsm.setState(GameStateManager.MODECHOIX);
		}
		if(currentOption == 1) {
			gsm.setState(GameStateManager.GAMEOVER);
		}
		if(currentOption == 2) {
			gsm.setState(GameStateManager.GAMEOVER);
		}
		if(currentOption == 3) {
			System.exit(0);
		}
	}
	
}
