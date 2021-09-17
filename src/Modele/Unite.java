package Modele;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Unite {
	protected String nomUnite;
	protected int attaque; 
	protected int defense ;
	protected int deplacement; 
	protected int vision ; 
	protected int pv ;
        protected int x, y; 
        protected Image imgPers;
        protected ImageIcon iconPers;
        protected String nomImage;
        protected String joueur;
        
	public Unite(String nomUnite, int attaque, int defense, int deplacement, int vision, int pv,int x, int y,String nomImage,String joueur) {
		super();
		this.nomUnite = nomUnite;
		this.attaque = attaque;
		this.defense = defense;
		this.deplacement = deplacement;
		this.vision = vision;
		this.pv = pv;
                this.x=x;
                this.y=y;
                this.nomImage= nomImage; 
                this.iconPers = new ImageIcon(getClass().getResource(nomImage));
                this.imgPers = this.iconPers.getImage();
                this.joueur=joueur;
	}
        public String getJoueur() {
		return joueur;
	}
	public String getNomUnite() {
		return nomUnite;
	}
	public void setNomUnite(String nomUnite) {
		this.nomUnite = nomUnite;
	}
	public int getAttaque() {
		return attaque;
	}
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getDeplacement() {
		return deplacement;
	}
	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	public int getVision() {
		return vision;
	}
	public void setVision(int vision) {
		this.vision = vision;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
         public int getX() {return x;}

        public int getY() {return y;}

        public void setX(int x) {this.x = x;}

        public void setY(int y) {this.y = y;}

        public String getImgNameUnite()
        {   
            return nomImage;
        }
         public Image getImgUnite()
        {   
      
            return imgPers;
        }
        
	
	}
