package Modele;

public class Archer extends Unite {

	public Archer(String nomUnite, int attaque, int defense, int deplacement, int vision, int pv,int x, int y,String joueur) {
		
                super(nomUnite,attaque, defense, deplacement, vision, pv,x, y, "/images/archer.png", joueur);
                
	}

}
