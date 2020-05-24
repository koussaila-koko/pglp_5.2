package uvsq.koukou_5;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * la classe personnel. Possede un nom et prenom, une fonction, une date de
 * naissance et une liste de Numero_telephone.
 * 
 * @author koussaila
 */
public class Personne1 implements Composite, Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 445679674461517053L;
	/**
	 * le id Identifiant unique.
	 */
	private final int id;
	/**
	 * nom de personnel.
	 */
	private final String nom;
	/**
	 * prenom de personnel.
	 */
	private final String prenom;
	/**
	 * fonction de personnel.
	 */
	private final String fonction;
	/**
	 * date naissance de personnel.
	 */
	private final LocalDate date_naissance;
	/**
	 * la liste des Numeros telephone de personnel.
	 */
	private final ArrayList<Numero_telephone> num_telephone;

	/**
	 * la class Builder c'est pour cree un personnel.
	 */
	public static class Builder {
		/**
		 * Identifiant unique.
		 */
		private final int id;
		/**
		 * nom .
		 */
		private final String nom;
		/**
		 * prenom .
		 */
		private final String prenom;
		/**
		 * fonction .
		 */
		private String fonction;
		/**
		 * date_naissance .
		 */
		private LocalDate date_naissance;
		/**
		 * num_telephone .
		 */
		private ArrayList<Numero_telephone> num_telephone;

		/**
		 * Constructeur. Creation d'un builder pour Personnel.
		 * 
		 * @param i              Identifiant
		 * @param nom            de personnel
		 * @param prenom         de personnel
		 * @param fonction       de personnel
		 * @param date_naissance la date de naissance
		 * @param num            le Numero_telephone
		 */
		public Builder(final int i, final String nom, final String prenom, final String fonction,
				final LocalDate date_naissance, final Numero_telephone num) {
			this.id = i;
			this.nom = nom;
			this.prenom = prenom;
			this.fonction = fonction;
			this.date_naissance = date_naissance;
			this.num_telephone = new ArrayList<Numero_telephone>();
			num_telephone.add(num);
		}

		/**
		 * la methode fonction pour changer la fonction de personnel.
		 * 
		 * @param fonction Nouvelle fonction
		 * @return Builder du Personnel
		 */
		public Builder fonction(final String fonction) {
			this.fonction = fonction;
			return this;
		}

		/**
		 * la methode Date_naissance pour changer la Date_naissancen de personnel.
		 * 
		 * @param date_naissance Nouvelle fonction
		 * @return Builder du Personnel
		 */
		public Builder Date_naissance(final LocalDate date_naissance) {
			this.date_naissance = date_naissance;
			return this;
		}

		/**
		 * la methode Num_telephone pour ajouter un numero.
		 * 
		 * @param num_telephone a ajouter
		 * @return Builder du Personnel
		 */
		public Builder Num_telephone(final Numero_telephone num_telephone) {
			this.num_telephone = new ArrayList<Numero_telephone>();
			this.num_telephone.add(num_telephone);
			return this;
		}

		/**
		 * la methode build. Retourne le Personnel crée à partir de ce Builder.
		 * 
		 * @return Personne1 de Builder.
		 */
		public Personne1 build() {
			return new Personne1(this);
		}
	}

	/**
	 * Constructeur. Crée un Personnel à partir des informations du builder.
	 * 
	 * @param builder Builder du Personnel
	 */
	private Personne1(final Builder builder) {
		this.id = builder.id;
		nom = builder.nom;
		prenom = builder.prenom;
		fonction = builder.fonction;
		num_telephone = builder.num_telephone;
		date_naissance = builder.date_naissance;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return Identifiant
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retourne nom.
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne prenom.
	 * 
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Retourne fonction.
	 * 
	 * @return fonction
	 */
	public String getFonction() {
		return fonction;
	}

	/**
	 * Retourne date_naissance.
	 * 
	 * @return date_naissance .
	 */
	public LocalDate getDate_naissance() {
		return date_naissance;
	}

	/**
	 * Retourne la liste num_telephone.
	 * 
	 * @return num_telephone
	 */
	public ArrayList<Numero_telephone> getNumero_telephone() {
		return num_telephone;
	}

	/**
	 * Retourne une chaine de caractère qui représentant le personnel.
	 */
	public void print() {
		System.out.println(this.nom + " " + this.prenom + ": fonction: " + this.fonction + "date de naissance: "
				+ this.date_naissance + "" + "nemero telephone" + this.num_telephone);
	}
}
