package uvsq.koukou_5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * class Groupe Représente un groupe de composant. Implémentes Composant et
 * IterableComposant.
 * 
 * @author koussaila
 */
public class Groupe implements Composite, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4022842957914180835L;
	/**
	 * Identifiant unique.
	 */
	private final int id;
	/**
	 * Liste de composite appartenant à ce groupe.
	 */
	private ArrayList<Personne1> personnes = new ArrayList<Personne1>();
	/**
	 * le nom ce groupe.
	 */
	private String nom;

	/**
	 * Constructeur. Crée un groupe vide a partir d'un nom.
	 * 
	 * @param i   Identifiant
	 * @param nom du groupe
	 */
	public Groupe(final int i, final String nom) {
		this.id = i;
		this.nom = nom;
	}

	/**
	 * Ajouter un composite au groupe.
	 * 
	 * @param c Composite à ajouter
	 */
	public void add(final Personne1 c) {
		personnes.add(c);
	}

	/**
	 * fontion print Retourne les compisite du groupe.
	 */
	public void print() {
		for (Composite composant : personnes) {
			composant.print();
		}
	}

	/**
	 * fonction remove Retourne le composant du groupe se trouvant à index. Le
	 * retire du groupe.
	 * 
	 * @param composant à retirer
	 */
	public void remove(final Composite composant) {
		personnes.remove(composant);
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id l'dentifiant du groupe.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retourne le nom du groupe .
	 * 
	 * @return nom.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retorune le nombre de composants qu'il contient le groupe.
	 * 
	 * @return size la taille du groupe
	 */
	public int size() {
		return personnes.size();
	}

	/**
	 * methode toString c'est a partir de groupe on Retourne une chaine de caractère
	 * représentant ce groupe.
	 * 
	 * @return la représentation du groupe
	 */
	public String toString() {
		return "Groupe" + this.nom + "(" + this.personnes.size() + ")";
	}

	/**
	 * methode hierarchique
	 */
	public void hierarchique() {
		Iterator<Personne1> iterator = personnes.iterator();
		System.out.println(this.getNom() + ":    ");
		while (iterator.hasNext()) {
			Composite comp = iterator.next();
			comp.print();
		}
	}

	/**
	 * methode getListe qui retourne la liste des personnes
	 * 
	 * @return personnes .
	 */
	public ArrayList<Personne1> getListe() {
		return this.personnes;

	}
}