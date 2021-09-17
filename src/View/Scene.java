/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Modele.Archer;
import Modele.Cavalerie;
import Modele.Desert;
import Modele.EauProfond;
import Modele.Foret;
import Modele.Infanterie;
import Modele.InfanterieLourde;
import Modele.Mage;
import Modele.Montagne;
import Modele.Neige;
import Modele.Plaine;
import Modele.Unite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import static View.Wargame.BSIZE;
import static View.Hexmech.XYVertex;
import static View.Wargame.BSIZE;
import static controller.Controller.plateauPerso;
import static controller.Controller.tour;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;

/**
 *
 * @author tahad
 */
public class Scene extends javax.swing.JPanel {
    
        private ImageIcon icoFond;
	private Image imgFond1;
        private static int BORDERS=50;
        public Unite pers;
        public static JLabel label1,label2,label3,label4,label5;
        private ImageIcon save;
        private ImageIcon nextturn;
        private Image save1;
        private Image nextturn1;
   
        
    /**
     * Creates new form Scene
     */
    public Scene() throws IOException {
        super();
        save = new ImageIcon(getClass().getResource("/images/save11.jpg"));
        this.save1 = this.save.getImage();
        
        nextturn = new ImageIcon(getClass().getResource("/images/turn.png"));
        this.nextturn1 = this.nextturn.getImage();
        
        icoFond = new ImageIcon(getClass().getResource("/images/worldmap1.png"));
        this.imgFond1 = this.icoFond.getImage();
    }
    public void remplirlabel(MouseEvent e)
    {
        int x = e.getX(); 
        int y = e.getY(); 
        Point p = new Point( Hexmech.pxtoHex(x,y));
        if(e.getX()>=1045&&e.getX()<=1140&&e.getY()>=505 &&e.getY()<=545 )
        {
            label1.setText("                                                                                                                                                                                                       "
                +"Tour :"+tour);
        }
        else if((plateauPerso[p.x][p.y]!=null))
        {
            label1.setText("                                                                                                                                                                                                       "
                +"Tour :"+tour);
            label5.setText(plateauPerso[p.x][p.y].getJoueur()+": "+plateauPerso[p.x][p.y].getNomUnite());
                label2.setText("Vie: "+plateauPerso[p.x][p.y].getPv());
                label3.setText("Déplacement: "+plateauPerso[p.x][p.y].getDeplacement());
                label4.setIcon(new ImageIcon(plateauPerso[p.x][p.y].getImgUnite()));
        }
    }
    public Box initLabel()
    {
                Box box = Box.createVerticalBox();
                box.setLocation(0,0);
                box.setBounds(0, 0, 50, 20);
                label1 = new JLabel();
                label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
                box.add(label1);
                //label1.setForeground(new Color(153, 255, 51));
                label1.setFont(new  java.awt.Font(Font.SERIF, Font.BOLD, 20));
                
                label5 = new JLabel();
                label5.setAlignmentX(Component.RIGHT_ALIGNMENT);
               label5.setForeground(new Color(153, 255, 51));
                box.add(label5);
                box.setBounds(1200, 800, 50, 20);
                label5.setFont(new  java.awt.Font(Font.SERIF, Font.BOLD, 20));
                
                label2 = new JLabel();
                label2.setAlignmentX(Component.RIGHT_ALIGNMENT);
                box.add(label2);
                label2.setForeground(new Color(153, 255, 51));
                label2.setFont(new  java.awt.Font(Font.SERIF, Font.BOLD, 20));

                label3 = new JLabel();
                label3.setAlignmentX(Component.RIGHT_ALIGNMENT);
                box.add(label3);
                label3.setForeground(new Color(153, 255, 51));
                label3.setFont(new  java.awt.Font(Font.SERIF, Font.BOLD, 20));
                
                label4 = new JLabel();
                label4.setAlignmentX(Component.RIGHT_ALIGNMENT);
                box.add(label4);
                box.setBounds(1200, 800, 50, 20);

            return box;
    }
   
    
    
    public void paintComponent(Graphics g)//methode pour dessiner les images , l'image du plateau et les personnages apres chaque mise a jour    
    {
		
	super.paintComponent(g);
	Graphics g2 = (Graphics2D)g;
       
	int x = (this.getWidth() - imgFond1.getWidth(null)) / 2;
        int y = (this.getHeight() - imgFond1.getHeight(null)) / 2;
        
	g2.drawImage(this.imgFond1, -50,0, null); // Dessin de l'image de fond 
        g2.drawImage(this.save1, 1165, 500, null); // icon save       
        g2.drawImage(this.nextturn1, 1025,490, null); // icon tour suivant
        
        for (int i=0;i<BSIZE;i++) 
        {
            for (int j=0;j<BSIZE;j++)
            {
		Hexmech.drawHex(i,j, (Graphics2D) g2);
            }
	}
      
          for (int i=0;i<BSIZE;i++) 
        {
            for (int j=0;j<BSIZE;j++)
            {    if(plateauPerso[i][j]!=null)
		 Hexmech.fillHex(plateauPerso[i][j], plateauPerso[i][j].getX(), plateauPerso[i][j].getY(), (Graphics2D) g2);
            }
	}
    }

          
    
    }