package Game_state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game_main.GamePanel;
import Game_manager.Content;
import Game_manager.Data;
import Game_manager.GameStateManager;
import Game_manager.JukeBox;
import Game_manager.Keys;

public class GameOverState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage fleche;
	
	private int currentOption = 0;
	private String[] options = {
		"REJOUER",
		"QUIT"
	};
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}	
	public void init() {
		bg = Content.CMB_FNSH[0][0];
		fleche = Content.Fleche[0][0];
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 50, 180);
		Content.drawString(g, options[1], 60, 210);
		
		Content.drawString(g, "Le Gagnant est le joueur 1 : (Nom du J)", 20, 42);

		if(currentOption == 0) g.drawImage(fleche, 30, 178, null);
		else if(currentOption == 1) g.drawImage(fleche, 40, 208, null);
		
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
			gsm.setState(GameStateManager.MENU);
		}
		if(currentOption == 1) {
			System.exit(0);
		}
	
	}
	
	
}