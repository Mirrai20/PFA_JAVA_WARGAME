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

public class ModeChoix extends GameState {
	
	private BufferedImage bg;
	private BufferedImage fleche;
	private BufferedImage modeHum;
	private BufferedImage modeOrd;

	private int currentOption = 0;
	private String[] options = {
		"Contre Humain",
		"Contre Ordinateur"
	};
	
	public ModeChoix(GameStateManager gsm) {
		super(gsm);
	}	
	public void init() {
		bg = Content.BG_CHOICE[0][0];
		fleche = Content.Fleche[0][0];
		modeHum= Content.IC_HUM[0][0];
		modeOrd= Content.IC_ORD[0][0];

		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 30, 160);
		Content.drawString(g, options[1], 195, 160);
		
		Content.drawString(g, "Merci de choisir le mode de jeu", 45, 30);
		g.drawImage(modeHum, 40, 75, null);
		g.drawImage(modeOrd, 225, 75, null);

		if(currentOption == 0) g.drawImage(fleche, 10, 158, null);
		else if(currentOption == 1) g.drawImage(fleche, 175, 158, null);
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.RIGHT) && currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
		}
		if(Keys.isPressed(Keys.LEFT) && currentOption > 0) {
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
			gsm.setState(GameStateManager.CHOIXUNITE2J);
		}
		if(currentOption == 1) {
			gsm.setState(GameStateManager.CHOIXUNITE1J);
		}
	
	}
	
}
