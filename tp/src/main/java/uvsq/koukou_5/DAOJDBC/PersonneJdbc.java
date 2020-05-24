package uvsq.koukou_5.DAOJDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uvsq.koukou_5.Personne1.Builder;
import uvsq.koukou_5.Numero_telephone;
import uvsq.koukou_5.Personne1;
import uvsq.koukou_5.Type_tel;
import java.time.LocalDate;

/**
 * la class PersonneJdbc .
 * 
 * @author koussaila
 */
public class PersonneJdbc implements DAOJDBC<Personne1> {
	/**
	 * la connection a la bdd est null
	 */
	private Connection connect = null;
	/**
	 * cree la table Personnel la clé est id, avec connect
	 */
	private String sql = "CREATE TABLE Personnel(" + "id int," + "nom VARCHAR(20) NOT NULL,"
			+ "prenom VARCHAR(20) NOT NULL," + "fonction VARCHAR(30) NOT NULL," + "date_naisssance Date NOT NULL,"
			+ "PRIMARY KEY(id)" + ")";
	/**
	 * l Statement .
	 */
	private Statement statement;

	/**
	 * le constroctor de PersonneJdbc on test si elle existe deja la table Personnel
	 * avec la connexion connect sinon on la cree
	 * 
	 * SQLException En cas d'erreur d'ecriture.
	 */
	public PersonneJdbc() {

		connect = this.getConnection();
		try {
			statement = connect.createStatement();
			if (!doesTableExists("Personnel", connect)) {
				statement.execute(sql);
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
	 * @param conn      le nom de la connetion .
	 * @throws SQLException En cas d'erreur d'ecriture.
	 * @return RESULTA de lexistance de la table.
	 */
	boolean doesTableExists(String tableName, Connection conn) throws SQLException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);
		return result.next();
	}

	/**
	 * fonction getConnection qui vas fair la connexion a la bdd on cas d'erreur une
	 * exception se déclenche o
	 * 
	 * @return conn trouvé, ou message e en cas d'erreur.
	 * IOException   En cas d'erreur d'ecriture .
	 */
	public Connection getConnection() {
		/**
		 * la connection a la bdd est null
		 */
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
	 * insert dans la table Personnel un tuble . in insert les attribut on cas
	 * derrur il retourn null ou une exception
	 * 
	 * @return message . message sois le tuble bien inserer ou null on cas cas
	 *         d'erreur d'ecriture
	 */
	public Personne1 create(Personne1 obj) {
		/**
		 * statementest null
		 */
		PreparedStatement statement = null;
		/**
		 * rowsInserted est met a zero .
		 */
		int rowsInserted = 0;
		/**
		 * connect appler la getconnectiob pou insialiser la bdd
		 */
		connect = this.getConnection();
		/**
		 * insert dans la table Personnel un tuble . in insert les attribut on cas
		 * derrur il retourn null ou une exception message sois le tuble bien inserer ou
		 * null on cas cas d'erreur d'ecriture
		 * 
		 * @throws IOException En cas d'erreur d'ecriture .
		 */
		String insert = "INSERT INTO Personnel (id, nom, prenom , fonction,date_naisssance) VALUES (?,?, ?, ?,?)";
		try {
			/**
			 * prepareStatement .
			 */
			statement = connect.prepareStatement(insert);
			/**
			 * recupere la 1er colonne de tuble a inserer id .
			 */
			statement.setInt(1, obj.getId());
			/**
			 * recupere la 2er colonne de tuble a inserer le nom.
			 */
			statement.setString(2, obj.getNom());
			/**
			 * recupere la 3er colonne de tuble a inserer le prenom .
			 */
			statement.setString(3, obj.getPrenom());
			/**
			 * recupere la 4er colonne de tuble a inserer la fonction .
			 */
			statement.setString(4, obj.getFonction());
			/**
			 * recupere la 5er colonne de tuble a inserer DATE DE NAISSANCE .
			 */
			statement.setDate(5, Date.valueOf(obj.getDate_naissance()));
			/**
			 * executeR LA REQUETTE;
			 */
			rowsInserted = statement.executeUpdate();
			/**
			 * fermer la connexion
			 */
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
		if (rowsInserted > 0) {
			System.out.println("Une nouvelle personne a été inséré avec succès!");
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * read elle prend le nom comme id de la table on lit les informations de la
	 * personne avec le nom id on cas derreur exception
	 * 
	 * @return Personne .
	 *  IOException En cas d'erreur d'ecriture .
	 * @param id le nom de personnel a lire
	 */
	public Personne1 read(String id) {
		/**
		 * cree une personnel null
		 */
		Personne1 personne = null;
		try {
			/**
			 * faire la connexion
			 */
			connect = this.getConnection();
			/**
			 * faire la la recherche dans connect lexistance de personnel avec le nom qui a
			 * ete entrer id
			 */
			PreparedStatement prepare = connect.prepareStatement("SELECT * FROM Personnel WHERE nom = ?  ");
			prepare.setString(1, id);
			/**
			 * recuperer le resultat
			 */
			ResultSet result = prepare.executeQuery();
			/**
			 * grace au resultat on vas lire tous les informations de cette personne sinon
			 * une exception
			 */
			if (result.next()) {
				personne = new Builder(result.getInt("id"), result.getString("nom"), result.getString("prenom"),
						result.getString("fonction"), LocalDate.parse(result.getString("date_naisssance")),
						new Numero_telephone(1, Type_tel.fix_perso, "0104050506")).build();
				connect.close();
			} else {
				// throw new PersonneDoncExistException("La personne que vous chercher n'éxiste
				// pas :( !");
			}
		} catch (SQLException e) {
			e.getMessage();
		}

		return personne;
	}

	/**
	 * update elle prend un objet de type Personnel on cherche lobjet si il existe
	 * dans la bdd si on on modifier et on retour les nouvelles information sion un
	 * message d'erreur ou une exception on cas d'erreur d'ecriture
	 * 
	 * @return obj la personne modifier .
	 *  IOException  En cas d'erreur d'ecriture .
	 * @param obj le personnel a modifier.
	 */
	public Personne1 update(Personne1 obj) {
		try {
			connect = this.getConnection();
			PreparedStatement prepareFind = connect.prepareStatement("SELECT * FROM personne WHERE nom = ?  ");
			prepareFind.setString(1, obj.getNom());
			ResultSet res = prepareFind.executeQuery();

			if (res.next()) {
				PreparedStatement prepare = connect.prepareStatement(
						"UPDATE Personnel SET prenom = ?, " + "fonction = ?, " + "datenaisssance = ? WHERE nom = ?");
				prepare.setString(1, obj.getPrenom());
				prepare.setString(2, obj.getFonction());
				prepare.setDate(3, Date.valueOf(obj.getDate_naissance()));
				prepare.setString(4, obj.getNom());
				int result = prepare.executeUpdate();
				assert result == 1;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return obj;
	}

	/**
	 * delete elle prend un objet de type Personnel on fait la connect et on lance
	 * notre requette pour suprrimer il retour un message de succés si il existe
	 * dans la bdd sinon un message que cette element n'existe pas une exception en
	 * cas d'erreur d'ecriture
	 *  retourner un  message de seccés ou pas .
	 *  IOException  En cas d'erreur d'ecriture .
	 * @param obj le personnel a supprimer.
	 */
	public void delete(Personne1 obj) {

		PreparedStatement statement = null;
		connect = this.getConnection();
		int rowsDeleted = 0;
		String delete = "delete from Personnel where id = ?";
		try {
			statement = connect.prepareStatement(delete);
			statement.setInt(1, obj.getId());
			rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Une personne a bien été  supprimer!");
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
