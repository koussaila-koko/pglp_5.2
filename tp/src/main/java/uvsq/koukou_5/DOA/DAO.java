package uvsq.koukou_5.DOA;

import java.io.*;
import java.io.IOException;

/**
 * Classe abstraite DAO.
 * 
 * @param <T> Classe des objets
 * @author koussaila
 */
public abstract class DAO<T> {
	/**
	 * fichier .
	 */
	protected File fichier;
	/**
	 * fichout .
	 */
	protected FileOutputStream fichout;
	/**
	 * objetout .
	 */
	protected ObjectOutputStream objetout;

	/**
	 * Constructeur. Creation d'un builder pour Personnel.
	 * 
	 * @param id Identifiant
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	public DAO(int id) throws IOException {
		fichier = new File(id + ".txt");
		fichout = new FileOutputStream(fichier);
		objetout = new ObjectOutputStream(fichout);
	}

	/**
	 * methede create .
	 * 
	 * @param obj .
	 * @throws IOException En cas d'erreur d'ecriture .
	 * @return UN OBJET DE TYPE T
	 */
	public abstract T create(T obj) throws IOException;

	/**
	 * methede find .
	 * 
	 * @param id .
	 * @throws IOException            En cas d'erreur d'ecriture .
	 * @throws ClassNotFoundException En cas il trouve rien.
	 * @return UN OBJET DE TYPE T
	 */
	public abstract T find(String id) throws IOException, ClassNotFoundException;

	/**
	 * methede update .
	 * 
	 * @param obj .
	 * @throws IOException En cas d'erreur d'ecriture .
	 * @return UN OBJET DE TYPE T
	 */
	public abstract T update(T obj) throws IOException;

	/**
	 * methede delete .
	 * 
	 * @param obj .
	 */
	public abstract void delete(T obj);

	/**
	 * methede deserialize .
	 * 
	 * @param bytes .
	 * @throws IOException            En cas d'erreur d'ecriture .
	 * @throws ClassNotFoundException En cas il trouve rien.
	 * @return ob ObjectInputStream
	 */
	public Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
		ByteArrayInputStream arr = new ByteArrayInputStream(bytes);
		ObjectInputStream ob = new ObjectInputStream(arr);
		return ob.readObject();
	}

	/**
	 * methede byte .
	 * 
	 * @param obj .
	 * @throws IOException En cas d'erreur d'ecriture .
	 * @return tab .
	 */
	public byte[] serialize(final Object obj) throws IOException {
		ByteArrayOutputStream tab = new ByteArrayOutputStream();
		ObjectOutputStream ob = new ObjectOutputStream(tab);
		ob.writeObject(obj);
		return tab.toByteArray();
	}
}
