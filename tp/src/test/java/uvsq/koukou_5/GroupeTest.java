package uvsq.koukou_5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;


import org.junit.Before;
import org.junit.Test;


import uvsq.koukou_5.DOA.DAO;
import uvsq.koukou_5.DOA.DAOFactory;

public class GroupeTest {

	
	private Personne1 Personne,Personne2,Personne3,Personne4;
	private Groupe G,G2;

		@Before
		public void setUp() throws Exception {
	    	 G=new Groupe(1,"acteur");
	    	 G2=new Groupe(2,"realisateur");
	    	DAO<Personne1>personneDao=DAOFactory.getPersonne1DAO ( ) ;
			 Personne= personneDao.create (Personne2);
		}

		@Test
		public void Sizetest() {
			G.add(Personne);
			G.add(Personne2);
			G.add(Personne4);
		
			assertTrue(3==G.size());
		
		
			
			
		}
		@Test
		public void Size2test() {
			G.add(Personne);
			G.add(Personne2);
			G.add(Personne3);
			G.add(Personne4);
			G2.add(Personne4);
			G2.add(Personne3);
			 assertEquals(2, G2.size());
		}

	
	    @Test
	    public void addTest() {
	   	 Groupe g1 = new Groupe(1, "INFORMATIQUE");
			g1.add(Personne2);
		    g1.add(Personne3);
		    assertEquals(g1.size(), 2);
	    }
	    
		 /**
	     * Teste la méthode add avec un groupe.
	     */
	    @Test
	    public void add2Test() {
	      //  Groupe g1 = new Groupe(1, "INFORMATIQUE");
	        //Groupe g2 = new Groupe(2, "GESTION");
	        //assertEquals(0, g1.size());
	        
	       
	        //assertEquals(1, g1.size());
	     
	    }
	    
	
		@Test
		public void suprimertest() {
			 Groupe g1 = new Groupe(1, "INFORMATIQUE");
			g1.add(Personne2);
		    g1.add(Personne3);
		    g1.remove(Personne3);
		    assertEquals(g1.size(), 1);
		   
		 
		}
		@Test
		public void DOACreatetest() throws IOException  {
			 Groupe g1 = new Groupe(1, "INFORMATIQUE");
		    DAO<Groupe>groupeDao=DAOFactory.getGroupe ( ) ;
			G= groupeDao.create (g1);
			assertTrue(g1.getNom()==G.getNom());
			
		
		}
		@Test
		public void DOAUpdatetest() throws IOException  {
			 Groupe g1 = new Groupe(1, "INFORMATIQUE");
			 Groupe G3 = new Groupe(5, "MATHS");
		    DAO<Groupe>groupeDao=DAOFactory.getGroupe ( ) ;
			G= groupeDao.create (g1);
			 assertFalse(G3.getNom()==G.getNom());
			 G3= groupeDao.update (G);
			 assertTrue(G3.getNom()==G.getNom());  
			 assertEquals(G3.getNom(), "INFORMATIQUE");
		}
		@Test
		public void test2() throws IOException {
			 Groupe g1 = new Groupe(53, "INFORMATIQUE");
			 Groupe G3;
			 g1.add(Personne);
				g1.add(Personne2);
				g1.add(Personne4);
		    DAO<Groupe>groupeDao=DAOFactory.getGroupe ( ) ;
		    G= groupeDao.create (g1);
		    G3= groupeDao.create (g1);
		    assertEquals(G3.getNom(), G.getNom());
		    assertEquals(G3.getId(), G.getId());
		    assertEquals(G3.size(), G.size());
		    assertEquals(3, G.size());
		    assertEquals("INFORMATIQUE", G.getNom());
		    assertEquals(53, G.getId());

		    
			
		}
}
