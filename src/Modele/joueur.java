package Modele;

public class joueur {

		private String nom;
		private int score;
		
		public joueur(String nom, int score)
		{
			this.nom=nom;
			this.score=score;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
		
		
}
