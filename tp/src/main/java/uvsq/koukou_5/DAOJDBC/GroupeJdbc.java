package uvsq.koukou_5.DAOJDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import uvsq.koukou_5.Groupe;
import uvsq.koukou_5.Personne1;

/**
 * interface DAOJDBC .
 * @author koussaila
 */
public class GroupeJdbc implements DAOJDBC<Groupe> {
	/**
	 * la connection a la bdd est null
	 */
	private Connection connect = null;
	/**
	 * creation de la table groupe
	 */

	String sql = "CREATE TABLE groupe ( idGroupe Integer , nomGroupe VARCHAR(100) PRIMARY KEY  )";
	/**
	 * creation de la table contien qui met en relation groupe et personnel elle
	 * contien le nom de groupe et le nom de personnel
	 */
	String sq2 = "CREATE TABLE contenir(" + "id VARCHAR(100)," + "nom VARCHAR(100)" + ")";
	/**
	 * le statement .
	 */
	private Statement statement;

	/**
	 * le constroctor de GroupeJdbc on test si elle existe deja la table groupe
	 * sinon on cree un nouveux groupe apres on test ce groupe dans contient sinon
	 * on cree un nouvelle table contien en relation avec ce groupe Personnel avec
	 * la connexion connect sinon on la cree
	 * 
	 * SQLException En cas d'erreur d'ecriture.
	 */
	public GroupeJdbc() {
		/**
		 * recuperer la connection a la bdd
		 */
		connect = this.getConnection();
		try {
			statement = connect.createStatement();
			if (!doesTableExists("groupe", connect)) {
				statement.execute(sql);
			}
			if (!doesTableExists("contenir", connect)) {
				statement.execute(sq2);
			}
			statement.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * fonction qui vas nous retourner un boolean . pour lexistance de la table dans
	 * une connection elle prend le nom de la table et le nom de la connetion
	 * 
	 * @param tableName le nom de la table a tester .
	 * @param connect   le nom de la connetion .
	 * @return RESULTA de lexistance de la table dans connect.
	 */
	boolean doesTableExists(String tableName, Connection connect) throws SQLException {
		DatabaseMetaData metadata = connect.getMetaData();
		ResultSet resultat = metadata.getTables(null, null, tableName.toUpperCase(), null);

		return resultat.next();
	}

	/**
	 * fair la connection a la bdd .
	 * 
	 *  IOException En cas d'erreur d'ecriture .
	 * @return connect de type connection.
	 */

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:derby:bdd;create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * create elle prend le groupe obj on fait une connection on cree un groupe vide
	 * avec nom et id grace a lid de groupe on ajoute des personnel au groupe il
	 * faut que ces personnes exicte dejai dans la bdd . elle retourne un groupe qui
	 * contirnt des personne une message d'erreur ou une exception sql
	 * 
	 * @return Groupe le groupe qui contiens les personnes qui sont insert .
	 * IOException  En cas d'erreur d'ecriture .
	 * @param obj de type groupe
	 */
	public Groupe create(Groupe obj) {
		connect = this.getConnection();
		try {
			PreparedStatement prepared = connect
					.prepareStatement("INSERT" + " INTO groupe (idGroupe, nomGroupe) VALUES (?, ?)");
			prepared.setInt(1, obj.getId());
			prepared.setString(2, obj.getNom());
			int result = prepared.executeUpdate();
			assert result == 1;
			List<Personne1> lp = obj.getListe();
			for (Iterator<Personne1> it = lp.iterator(); it.hasNext();) {
				Personne1 n = it.next();
				prepared = connect.prepareStatement("INSERT INTO " + "contenir " + "VALUES (?, ?)");
				prepared.setString(1, obj.getNom());
				prepared.setString(2, n.getNom());
				prepared.executeUpdate();
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * read elle prend le nom de groupe nomRecherche on fait la recherche sur ce nom
	 * on recupere le id de groupe grace a ce id on accede a lire tous les personnes
	 * de ce groupe on retour un groupe des personnes si le groupe n'exsite pas une
	 * message d'erreur ou une exception
	 * 
	 * @return Groupe le groupe de personne avec le nom nomRecherche .
     * IOException  En cas d'erreur d'ecriture .
	 * @param nomG le nom de de groupe a lire
	 */
	public Groupe read(String nomG) {
		Groupe groupe = null;
		int rowsDeleted = 0;
		connect = this.getConnection();
		try {
			Statement st = connect.createStatement();
			st.setFetchSize(0);
			System.out.println("Recherche " + nomG);
			String rech = "SELECT * FROM groupe WHERE nomGroupe = ?";
			PreparedStatement prepare = connect.prepareStatement(rech);
			prepare.setString(1, nomG);
			ResultSet result1 = prepare.executeQuery();

			if ((result1.next())) {
				System.out.println("ddddddddddddddddd" + result1.getInt("idGroupe"));
				groupe = new Groupe(result1.getInt("idGroupe"), nomG);

				DAOJDBC<Personne1> jdbsDaoPersonne = DAOFactoryJDBC.getPersonneDAO();

				String rechApa = "SELECT * FROM contenir WHERE id = ?";
				PreparedStatement prepare2 = connect.prepareStatement(rechApa);
				prepare2.setString(1, nomG);
				ResultSet result = prepare2.executeQuery();

				while (result.next()) {

					Personne1 n = jdbsDaoPersonne.read(result.getString("nom"));

					groupe.add(n);

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupe;
	}

	/**
	 * update le groupe prend un groupe retourne null car on pourais pas modifier un
	 * groupe sauf si on est admin de groupe
	 */
	public Groupe update(Groupe obj) {
  this.delete(obj);
 return this.create(obj);
	}

	/**
	 * delete elle prend un objet de type Groupe on fait la connect et on lance
	 * notre requette pour suprrimer il retour un message de succés si il existe
	 * dans la bdd donc on supprime tous les personnes de ce groupe sinon un message
	 * que cette element n'existe pas une exception en cas d'erreur d'ecriture
	 * IOException  En cas d'erreur d'ecriture .
	 * @param obj le personnel a supprimer.
	 */
	public void delete(Groupe obj) {
		PreparedStatement statement = null;
		connect = this.getConnection();
		int rowsDeleted = 0;
		String delete = "Delete from groupe where  nomGroupe=?";
		try {
			statement = connect.prepareStatement(delete);
			statement.setString(1, obj.getNom());
			rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Un membre du groupe a bien été  supprimer!");
				for (Personne1 forme : obj.getListe()) {
					// System.out.println("ccccccccccccccccccccc");
					String sql = "DELETE FROM" + " contenir " + "WHERE id = ? " + "and nom = ? ";
					PreparedStatement prepare = null;
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, obj.getNom());
					prepare.setString(2, forme.getNom());
					int result1 = prepare.executeUpdate();
					assert result1 == 1;
				}
			} else {
				System.out.println("element n'existe pas");
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
