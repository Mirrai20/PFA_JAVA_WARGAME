package Game_state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game_manager.Content;
import Game_manager.GameStateManager;
import Game_manager.JukeBox;
import Game_manager.Keys;


import Game_state.GameOverState;
import Game_state.GameState;
import Game_state.IntroState;
import Game_state.MenuState;
import Game_state.ModeChoix;
import Game_state.ChoixUnite;
import game_main.Game;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

public class ChoixUnite  extends GameState {
	public JFrame newFrame;
	public static final int JwO = 0;
	public static final int Joueur1 = 1;
	public static final int Joueur2 = 2;

	public String joueurCHoix="";
	private BufferedImage bg;
	private BufferedImage fleche;
	private BufferedImage U;
	private BufferedImage confirme;
	private String [] nomU= {"vide","vide","vide","vide","vide"};
	private int i=0,P=0;
	private int[] tchoix= {0,0,0,0,0,0,0};
	private int currentOption = 0;
	private String[] options = {
		"ARCHER",
		"INFANTERIE",
		"MAGE",
		"CAVALERIE",
		"INFANTERIE LOURD",
		"Retourner",
		null
	};
	
	public ChoixUnite(GameStateManager gsm,int j) {
		super(gsm);
		switch(j) {
			case JwO: joueurCHoix="Veuillez choisir vos unites ";P=2;break;
			case Joueur1: joueurCHoix="Choix des unites pour le joueur 1";P=0;break;
			case Joueur2: joueurCHoix="Choix des unites pour le joueur 2";P=1;break;
			

		}
	}	
	public void init() {
		
		bg = Content.UNT_CHOICE[0][0];
		
		fleche = Content.Fleche[0][0];
		confirme = Content.CNFRM_UNT[0][0];
		U= Content.IC_U1[0][0];

		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, joueurCHoix, 50, 10);

		Content.drawString(g, options[0], 50, 150);
		Content.drawString(g, options[1], 150, 150);
		Content.drawString(g, options[2], 280, 150);
		Content.drawString(g, options[3], 80, 170);
		Content.drawString(g, options[4], 200, 170);
		Content.drawString(g, options[5], 20, 220);

		if ( options[6]!=null) {
			Content.drawString(g, options[6], 270, 220);
		}
		
		if(currentOption == 0 && tchoix[0]==0) {g.drawImage(fleche, 30, 148, null);U= Content.IC_U1[0][0];g.drawImage(U, 145, 30, null);}
		else if(currentOption == 1 && tchoix[1]==0) { g.drawImage(fleche, 130, 148, null);U= Content.IC_U2[0][0];g.drawImage(U, 145, 30, null);}
		else if(currentOption == 2 && tchoix[2]==0) {g.drawImage(fleche, 250, 148, null);U= Content.IC_U3[0][0];g.drawImage(U, 160, 30, null);}
		else if(currentOption == 3 && tchoix[3]==0) {g.drawImage(fleche, 60, 168, null);U= Content.IC_U4[0][0];g.drawImage(U, 145, 30, null);}
		else if(currentOption == 4 && tchoix[4]==0 ) { g.drawImage(fleche, 180, 168, null);U= Content.IC_U5[0][0];g.drawImage(U, 160, 30, null);}
		else if(currentOption == 5) { g.drawImage(fleche, 0, 218, null);}
		else if(currentOption == 6 && options[6]!=null) { g.drawImage(fleche, 250, 218, null);}
		
		
		
		
		checkPOSchoix(g);
	}
	
	public void checkPOSchoix(Graphics2D g) {
		for(int j=0;j<i;j++) {
			switch(nomU[j]) {
			case "ARCHER":g.drawImage(confirme, 30, 148, null);tchoix[0]=1;break;			
			case "INFANTERIE":g.drawImage(confirme, 130, 148, null);tchoix[1]=1;break;
			case "MAGE":g.drawImage(confirme, 250, 148, null);tchoix[2]=1;break;
			case "CAVALERIE":g.drawImage(confirme, 60, 168, null);tchoix[3]=1;break;
			case "INFANTERIE LOURD":g.drawImage(confirme,180, 168, null);tchoix[4]=1;break;

			}
		}
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.RIGHT) && currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
			if(currentOption==6 && options[6]==null) {currentOption--;}
			while(tchoix[currentOption]==1 && currentOption<5) {
				currentOption++;
			}
		}
		if(Keys.isPressed(Keys.LEFT) && currentOption > 0) {
			JukeBox.play("menuoption");
			currentOption--;
			

			while(tchoix[currentOption]==1 && currentOption>0) {
				currentOption--;
			}
			if(tchoix[currentOption]==1 && currentOption>0) {currentOption++;}
		}
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("collect");
			selectOption();
		}
	}
	
        //Test main
	private void stocke_Unite(int k,String mode) {
		try
		{
		 String filename="src/FilesTXT/stock_Unite.txt";
                 String contenu="";
		 FileWriter fw = new FileWriter(filename,true);
                 contenu=mode+"//"+"J"+k+"//"+nomU[0]+"//"+nomU[1]+"//"+nomU[2]+"//"+nomU[3]+"\n";
		 fw.write(contenu.replace(" ", "_"));
		 fw.close();
		}
		catch(IOException ioe)
		{
		 System.err.println("IOException: "+ ioe.getMessage());
		}
 
        }
        
        
        private void unite_ord(){
            
            nomU[0]="ARCHER";
            nomU[1]= "INFANTERIE";
            nomU[2]= "MAGE";
            nomU[3]= "CAVALERIE";
            nomU[4]= "INFANTERIE LOURD";
            try{
                String filename="src/FilesTXT/stock_Unite.txt";
                FileWriter fw = new FileWriter(filename,true);
                String contenu="Ord"+"//"+"J2"+"//";

                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i=1; i<=5; i++) {
                    list.add(new Integer(i));
                }

                Collections.shuffle(list);
                for (int i=0; i<=3; i++) {
                    System.out.println(list.get(i)+" // "+nomU[list.get(i)-1]);
                    if(i==3){
                        contenu+=nomU[list.get(i)-1]+"\n";
                    }else{
                        contenu+=nomU[list.get(i)-1]+"//";
                     }

                }
                fw.write(contenu.replace(" ", "_"));
                fw.close();
                System.out.println("\n****************\n");

            }catch(IOException ioe)
            {
		 System.err.println("IOException: "+ ioe.getMessage());
	    }

            
        }
        
        
	private void selectOption() {
		int cpt;
		if(currentOption == 5) {
			System.out.println("Retourner");

		//	System.exit(0);
		}
		
		if(i<4) {
			nomU[i]=options[currentOption];
			i++;
			JukeBox.play("menuoption");
			cpt=currentOption;
			currentOption++;
			while(tchoix[currentOption]==1 && currentOption<5) {
				currentOption++;
				System.out.println("Test++"+i);

			}
			if(currentOption>=5) {
				currentOption=cpt-1;
				while(tchoix[currentOption]==1 && currentOption>0) {
					currentOption--;
					System.out.println("Test--"+i);

				}
			}
			if(i==4) {options[6]="VALIDER"; }
		}else if(i==4 && currentOption == 6 ) {
			System.out.println("VALIDER");
			switch(P) {
				case 0:System.out.println("J1 a Valide ces choix");
                                        stocke_Unite(1,"Hum");
                                        gsm.setJoueur2();
                                        break;
				case 1:System.out.println("J2 a Valide ces choix");
                                        stocke_Unite(2,"Hum");
                                        gsm.setState(GameStateManager.PLAY);                      
                                        break;
				case 2:System.out.println("Vous avez Valide tes choix");
                                        stocke_Unite(1,"Ord");
                                        unite_ord();    
                                        gsm.setState(GameStateManager.PLAY);
                                        break;

			}
			

		}
	
		
	
	}
	
}