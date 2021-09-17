package Modele;

public class Cavalerie extends Unite {

	public Cavalerie(String nomUnite, int attaque, int defense, int deplacement, int vision, int pv,int x, int y,String joueur) {	
                super(nomUnite,attaque, defense, deplacement, vision, pv,x, y,"/images/cavalerie.png", joueur);
	}

}
