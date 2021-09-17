package Modele;

public class Infanterie extends Unite {

	public Infanterie(String nomUnite, int attaque, int defense, int deplacement, int vision, int pv,int x, int y,String joueur) {
		
                super(nomUnite,attaque, defense, deplacement, vision, pv,x, y, "/images/infanterie.png", joueur);
	}

}
