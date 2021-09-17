package Modele;

public abstract class Terrain {
	protected String nom;
	protected int pointDeplacement;
	protected int bonusDefence;
	 
	public Terrain(String nom,int pointDeplacement,int bonusDefence) {
		super();
		this.nom=nom;
		this.pointDeplacement=pointDeplacement;
		this.bonusDefence=bonusDefence;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getPointDeplacement() {
		return pointDeplacement;
	}
	public void setPointDeplacement(int pointDeplacement) {
		this.pointDeplacement = pointDeplacement;
	}
	public int getBonusDefence() {
		return bonusDefence;
	}
	public void setBonusDefence(int bonusDefence) {
		this.bonusDefence = bonusDefence;
	}

}
