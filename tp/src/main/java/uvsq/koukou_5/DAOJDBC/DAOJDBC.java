package uvsq.koukou_5.DAOJDBC;

/**
 * interface DAOJDBC .
 * 
 * @param <T> Classe des objets
 * @author koussaila
 */
public interface DAOJDBC<T> {
	/**
	 * *methode create. elle prend obj pour le crée
	 * 
	 * @param obj l'object elle retourne un objet de type T
	 * @return objet
	 */
	T create(T obj);

	/**
	 * methode read elle prends id de fichier
	 * 
	 * @param id .
	 * @return objet .
	 */
	T read(String id);

	/**
	 * methode delete.elle suprimme lobjet a lentrer
	 * 
	 * @param obj.
	 */
	void delete(T obj);

	/**
	 * methode update elle prends objet de type T a l entrer et un retour de meme
	 * type.
	 * 
	 * @param obj .
	 * @return objet .
	 */
	T update(T obj);
}