/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static controller.Controller.*;
import controller.Controller;
import Modele.Terrain;
import Modele.Unite;
import game_main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author BOUFADEN Nourhene, BOUNOUH Siwar, CHABIR Yassine, CHRIGUI Nader, EL BAROUDI Marouane, TOUATI Aladine

 */
public class Wargame {

        
    
        /**
         *board size.
         * hex size in pixels
         * click sur le plateau pour le dep
         */
	public static int BSIZE = 20; //board size.
	final static int HEXSIZE = 60;	//hex size in pixels
	final static int BORDERS = 0;  
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3;
        public static Scene scene;
        public static int click= 0 ; 
        
        public static Point pClick ; // click sur le plateau pour le dep
        public static int fin=0 ;
        public Wargame() {
            try {
                
                Hexmech.setXYasVertex(false); 
                Hexmech.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
                Hexmech.setBorders(BORDERS);
                JFrame fenetre = new JFrame("Wargame");
                fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenetre.setSize(1250, 600);
                
                fenetre.setLocationRelativeTo(null);
                fenetre.setResizable(false);
                fenetre.setAlwaysOnTop(true);
                /**
             *Instanciation de l'objet scene qui presente le plateau du jeu
             * remplir le plateau terrain par le type de terrain de chaque hexagone
               * remplir le plateau de personnage par les instances des unites de chaque equipe
             */
                scene = new Scene();// Instanciation de l'objet scene qui presente le plateau du jeu
                initPlateauTerrain();//remplir le plateau terrain par le type de terrain de chaque hexagone
                initPlateauPerso();// remplir le plateau de personnage par les instances des unites de chaque equipe
                Box box=scene.initLabel();//label pour afficher les onformations des personnages
                scene.repaint();
                scene.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                        for (int i=0;i<BSIZE;i++) 
                        {
                            for (int j=0;j<BSIZE;j++)
                            {    
                                plateauPersoTemp[i][j]=plateauPerso[i][j];
                            }
                        }                        
                        if(e.getX()>=1045&&e.getX()<=1140&&e.getY()>=505 &&e.getY()<=545 )
                        {
                            changerTour();// on change le tour lorsque l'utilisateur clique sur le bouton "tour suivant"
                            scene.remplirlabel(e);// mise aj jour des label
                        }
                        else if (e.getX()>=0 && e.getX()<=900 && e.getY()>=0 &&e.getY()<=600 )
                        {
                            updatePlateau(e);//mise a jour du plateau apres chaque deplacement ou attaque
                            scene.remplirlabel(e);
                        }
                        
                        scene.repaint();// on doit redessiner le plateau du jeu apres chaque mise a jour
                        if(fin==2)
                        {
                            
                            ImageIcon imgc = new ImageIcon(getClass().getResource("/images/fin_1.png"));
                            Image img1 = imgc.getImage();
                            JLabel img =new JLabel();
                                    img.setIcon(new ImageIcon(img1));
                                                fenetre.setContentPane(img);
                        }else if(fin==1)
                        {
                            
                            ImageIcon imgc = new ImageIcon(getClass().getResource("/images/fin_2.png"));
                            Image img1 = imgc.getImage();
                            JLabel img =new JLabel();
                                    img.setIcon(new ImageIcon(img1));
                                                fenetre.setContentPane(img);
                        }
                    }
                });
                
                fenetre.setLayout(null);
                fenetre.setContentPane(scene); // On associe la scene à la fenêtre de l'application

                fenetre.add(box);
                fenetre.getContentPane().setBackground(new Color(255,255,255));

                fenetre.setVisible(true);
                
                fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        Game.getWindGame().setVisible(true);
                    }
                });
            } catch (IOException ex) {
                Logger.getLogger(Wargame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
