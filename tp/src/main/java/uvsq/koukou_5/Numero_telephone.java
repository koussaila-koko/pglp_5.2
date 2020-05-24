package uvsq.koukou_5;

import java.io.Serializable;

/**
 * la classe téléhone. Possède un numéro id et le type .
 * 
 * @author koussaila
 */
public class Numero_telephone implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5615130987321927670L;
	/**
	 * l'Identifiant de tleph.
	 */
	private final int id;
	/**
	 * type de tleph.
	 */
	private Type_tel type;
	/**
	 * Numero de tleph.
	 */
	private String numero;

	/**
	 * Constructeur. Crée un téléphone grâce à un numéro et le type.
	 * 
	 * @param i      Identifiant
	 * @param type   c'est le type de telphone.
	 * @param numero le numero.
	 */
	public Numero_telephone(final int i, final Type_tel type, final String numero) {
		this.id = i;
		this.type = type;
		this.numero = numero;
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
	 * Retourne type .
	 * 
	 * @return type de telphone
	 */
	public Type_tel getType() {
		return type;
	}

	/**
	 * Retourne numero.
	 * 
	 * @return numero de telphone
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * fonction toString Retourne une chaine de caractère qui répresente le numero
	 * du téléphone.
	 * 
	 * @return chaine de caractère
	 */
	@Override
	public String toString() {
		return " le numero de (" + this.type + ") est :" + this.numero;
	}
}
