package uvsq.koukou_5.DOA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import uvsq.koukou_5.Numero_telephone;

/**
 * Classe Numero_telephoneDAO.
 * 
 * @author koussaila
 */
public class Numero_telephoneDAO extends DAO<Numero_telephone> {
	/**
	 * constructor .
	 * 
	 * @param id identifiant.
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	public Numero_telephoneDAO(final int id) throws IOException {
		super(id);
	}

	/**
	 * methode create pour la création.
	 * 
	 * @param obj Numero_telephone à créer
	 * @return obj créé, ou null en cas d'erreur
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	@Override
	public Numero_telephone create(final Numero_telephone obj) throws IOException {
		objetout.writeObject(obj);
		return obj;
	}

	/**
	 * la methode find . Retourne le Numero_telephone a partir de id,
	 * 
	 * @param id Identifiant du Numero_telephone à trouver
	 * @return Numero_telephone trouvé, ou null en cas d'erreur
	 * @throws IOException            En cas d'erreur d'ecriture .
	 * @throws ClassNotFoundException En cas il trouve rien.
	 */
	@Override
	public Numero_telephone find(final String id) throws IOException, ClassNotFoundException {
		File s = new File(id + ".txt");
		Object deserializer = null;
		if (s.exists()) {
			byte[] content = Files.readAllBytes(s.toPath());
			deserializer = deserialize(content);
		}
		Numero_telephone numero_tel = (Numero_telephone) deserializer;
		System.out.println(numero_tel.toString());
		return numero_tel;
	}

	/**
	 * la methode update Pourr remplacer .
	 * 
	 * @param obj le nouv Numero_telephone a ajouter.
	 * @throws IOException En cas d'erreur d'ecriture .
	 * @return obj le Numero_telephone
	 */
	@Override
	public Numero_telephone update(final Numero_telephone obj) throws IOException {
		fichier.delete();
		this.create(obj);
		return obj;
	}

	/**
	 * methode delete Pour la suppression. Suprime le fichier repreésentant le
	 * Numero_telephone ogj
	 * 
	 * @param obj Groupe à supprimer
	 */
	@Override
	public void delete(final Numero_telephone obj) {
		fichier.delete();
	}

}
