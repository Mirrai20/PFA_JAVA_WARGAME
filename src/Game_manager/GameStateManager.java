// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

package Game_manager;

import java.awt.Graphics2D;

import Game_state.GameOverState;
import Game_state.GameState;
import Game_state.IntroState;
import Game_state.MenuState;
import Game_state.ModeChoix;
import Game_state.ChoixUnite;
import View.Wargame;
import game_main.Game;
//import Game_state.PauseState;
//import com.neet.DiamondHunter.GameState.PlayState;


public class GameStateManager {
	

        public static Wargame wrg;
        //
	private boolean paused;
//	private PauseState pauseState;

	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 7;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;
	public static final int MODECHOIX = 4;
	public static final int CHOIXUNITE2J = 5;
	public static final int CHOIXUNITE1J = 6;

	

	
	public GameStateManager() {
		
		JukeBox.init();
		
		paused = false;
//		pauseState = new PauseState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}
	public void setJoueur2() {
		gameStates[5] = new ChoixUnite(this,ChoixUnite.Joueur2);
		gameStates[5].init();
	}

	public  void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
		//	gameStates[i] = new ModeChoix(this);
			gameStates[i].init();
		
                
                
		}
		else if(i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) {
                Game.getWindGame().setVisible(false);
                     wrg= new Wargame();
		}
		else if(i == GAMEOVER) {
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
		else if(i == MODECHOIX) {
			gameStates[i] = new ModeChoix(this);
			gameStates[i].init();
		}
		else if(i == CHOIXUNITE2J) {
			gameStates[i] = new ChoixUnite(this,ChoixUnite.Joueur1);
			gameStates[i].init();
		}
		else if(i == CHOIXUNITE1J) {
			gameStates[i] = new ChoixUnite(this,ChoixUnite.JwO);
			gameStates[i].init();
		}
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void update() {
		if(paused) {
			//pauseState.update();
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g) {
		if(paused) {
		//	pauseState.draw(g);
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
        
	
}
