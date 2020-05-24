package uvsq.koukou_5.DOA;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

import uvsq.koukou_5.Personne1;

/**
 * Classe Personne1DAO.
 * 
 * @author koussaila
 */
public class Personne1DAO extends DAO<Personne1> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6069153880772176756L;

	/**
	 * constructor .
	 * 
	 * @param id identifiant.
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	public Personne1DAO(final int id) throws IOException {
		super(id);
	}

	/**
	 * methode create pour la création.
	 * 
	 * @param obj Personne1 à créer
	 * @return obj créé, ou null en cas d'erreur
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	@Override
	public Personne1 create(final Personne1 obj) throws IOException {
		objetout.writeObject(obj);
		return obj;
	}

	/**
	 * la methode find . Retourne le Personne1 a partir de id,
	 * 
	 * @param id Identifiant du Personne1 à trouver
	 * @return personne trouvé, ou null en cas d'erreur
	 * @throws IOException            En cas d'erreur d'ecriture .
	 * @throws ClassNotFoundException En cas il trouve rien.
	 */
	@Override
	public Personne1 find(final String id) throws IOException, ClassNotFoundException {
		File s = new File(id + ".txt");
		Object deserializer = null;
		if (s.exists()) {
			byte[] content = Files.readAllBytes(s.toPath());
			deserializer = deserialize(content);
		}
		Personne1 personne = (Personne1) deserializer;
		System.out.println(personne.toString());
		return personne;
	}

	/**
	 * la methode update . Retourne le Personne1 .
	 * 
	 * @param obj Personne1
	 * @return obj trouvé, ou null en cas d'erreur.
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	@Override
	public Personne1 update(final Personne1 obj) throws IOException {
		fichier.delete();
		this.create(obj);
		return obj;
	}

	/**
	 * methode delete Pour la suppression. Suprime le fichier repreésentant le
	 * Personne1 obj.
	 * 
	 * @param obj Groupe à supprimer
	 */
	@Override
	public void delete(final Personne1 obj) {
		fichier.delete();
	}

}
