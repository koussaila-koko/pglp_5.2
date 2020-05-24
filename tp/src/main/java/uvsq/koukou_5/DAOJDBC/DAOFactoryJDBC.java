package uvsq.koukou_5.DAOJDBC;

import uvsq.koukou_5.Groupe;
import uvsq.koukou_5.Personne1;

/**
 * class DAOFactoryJDBC .
 * @author koussaila
 */
public class DAOFactoryJDBC {
	/**
	 * la methode getPersonneDAO elle retourne le PersonneJdbc
	 * 
	 * @return Groupe de type DAOJDBC Personne1 .
	 */
	public final static DAOJDBC<Personne1> getPersonneDAO() {
		return new PersonneJdbc();
	}

	/**
	 * la methode getGroupeDAO elle retourne le groupe jdbc
	 * @return GroupeJdbc .
	 */
	public final static DAOJDBC<Groupe> getGroupeDAO() {
		return new GroupeJdbc();
	}
}
