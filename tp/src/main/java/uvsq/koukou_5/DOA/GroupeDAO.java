package uvsq.koukou_5.DOA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import uvsq.koukou_5.Groupe;

/**
 * Classe GroupeDAO.
 * 
 * @author koussaila
 */
public class GroupeDAO extends DAO<Groupe> {
	/**
	 * constructor .
	 * 
	 * @param id identifiant.
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	public GroupeDAO(final int id) throws IOException {
		super(id);
	}

	/**
	 * methode create pour la création.
	 * 
	 * @param obj Groupe à créer
	 * @return obj Groupe créé, ou null en cas d'erreur
	 * @throws IOException En cas d'erreur d'ecriture .
	 */
	@Override
	public Groupe create(final Groupe obj) throws IOException {
		objetout.writeObject(obj);
		return obj;
	}

	/**
	 * la methode find . Retourne le Groupe a partir de id,
	 * 
	 * @param id Identifiant du Groupe à trouver
	 * @return obj Groupe trouvé, ou null en cas d'erreur
	 * @throws IOException            En cas d'erreur d'ecriture .
	 * @throws ClassNotFoundException En cas il trouve rien.
	 */
	@Override
	public Groupe find(final String id) throws IOException, ClassNotFoundException {
		File s = new File(id + ".txt");
		Object deserializer = null;
		if (s.exists()) {
			byte[] content = Files.readAllBytes(s.toPath());
			deserializer = deserialize(content);
		}
		Groupe groupe = (Groupe) deserializer;
		System.out.println(groupe.toString());
		return groupe;
	}

	/**
	 * la methode update Pourr remplacer .
	 * 
	 * @param obj le nouv groupe a ajouter.
	 * @throws IOException En cas d'erreur d'ecriture .
	 * @return obj le groupe
	 */
	@Override
	public Groupe update(final Groupe obj) throws IOException {
		fichier.delete();
		this.create(obj);
		return obj;
	}

	/**
	 * methode delete Pour la suppression. Suprime le fichier repreésentant le
	 * Groupe obj.
	 * 
	 * @param obj Groupe à supprimer
	 */
	@Override
	public void delete(final Groupe obj) {
		fichier.delete();
	}

}
