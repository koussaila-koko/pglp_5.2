package uvsq.koukou_5;

import java.io.IOException;
import java.time.LocalDate;

import uvsq.koukou_5.Personne1.Builder;
import uvsq.koukou_5.DAOJDBC.DAOFactoryJDBC;
import uvsq.koukou_5.DAOJDBC.DAOJDBC;

/**
 * class App 
 * @author koussaila
 */
public class App {
	public static void main(String[] args) throws IOException {
		/**
		 * Initialisation des variables.
		 */
		DAOJDBC<Personne1> DAOPersonne = DAOFactoryJDBC.getPersonneDAO();
		DAOJDBC<Groupe> Gro = DAOFactoryJDBC.getGroupeDAO();
		Groupe G = new Groupe(1, "informatique"), G2 = null;
		Groupe G1 = new Groupe(2, "gestion");
		Personne1 personne = new Builder(14344, "idir", "michel", "directeur", LocalDate.parse("1990-07-15"),
				new Numero_telephone(1, Type_tel.fix_perso, "0104050506")).build();
		Personne1 personne2 = new Builder(15, "nabil", "CANAVARO", "directeur", LocalDate.parse("1994-04-22"),
				new Numero_telephone(1, Type_tel.fix_perso, "0676788956")).build();
		Personne1 personne3 = new Builder(534353, "koukou", "NABIL", "directeur", LocalDate.parse("1997-02-13"),
				new Numero_telephone(1, Type_tel.fix_perso, "0678655565")).build();
		Personne1 personne4 = new Builder(1, "ROJA", "KAMILIA", "directeur", LocalDate.parse("1991-04-09"),
				new Numero_telephone(1, Type_tel.fix_perso, "0755666567")).build();
		Personne1 personne5 = null;
		G.add(personne);
		G.add(personne2);
		System.out.println(G.size());
		// personne5 = DAOPersonne.create(personne2);
		// personne5 = DAOPersonne.create(personne);
		// personne5 = DAOPersonne.create(personne3);

		G2 = Gro.create(G);
		// G2.print();
		G2 = Gro.read(G.getNom());
		G2.print();
		Gro.delete(G2);
	}
}
