package uvsq.koukou_5;






import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;


import org.junit.Test;

import uvsq.koukou_5.DOA.DAO;
import uvsq.koukou_5.DOA.DAOFactory;
import uvsq.koukou_5.Personne1.Builder;

public class PersonnelTest {


	  @Test
	    public void getnomTest() {
	    	Personne1 pers = new Builder(2,
	       			"cristion",
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
	        assertEquals("cristion", pers.getNom());
	    }

	   
	  
	  
	  @Test
	    public void getPrenomTest() {
	    	Personne1 pers = new Builder(2,
	       			"cristion",
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
	        assertEquals("ronaldo", pers.getPrenom());
	     
	    }
	  @Test
	    public void getFonctionTest() {
	    	Personne1 pers = new Builder(2,
	       			"cristion",
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
	     
	        assertEquals("directeur", pers.getFonction());

	    
	       
	    }
	  @Test
	    public void getDateTest() {
	    	Personne1 pers = new Builder(2,
	       			"cristion", 
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
	        assertEquals(LocalDate.of(1997, 04, 22), pers.getDate_naissance());
	     
	       
	    }
	  @Test
	    public void geTidTest() {
	    	Personne1 pers = new Builder(65,
	       			"cristion", 
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
	        assertEquals(65, pers.getId());
	     
	       
	    }
		@Test
		public void DOACreatetest() throws IOException  {
			Personne1 p2, p1 = new Builder(65,
	       			"cristion", 
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
		    DAO<Personne1>personnalDao=DAOFactory.getPersonne1DAO ( ) ;
			p2= personnalDao.create (p1);
			assertTrue(p1.getNom()==p2.getNom());
			
		
		}
		@Test
		public void DOAUpdatetest() throws IOException  {
			Personne1 p, p1 = new Builder(65,
	       			"cristion", 
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build() , p3 = new Builder(65,
	    	       			"loa", 
	    	       			"nab", 
	    	       			"directeur", 
	    	       			LocalDate.parse("1994-11-09"),
	    	       			new Numero_telephone( 3, Type_tel.fix_perso, "0675459809"))
	    	    			.build();
			 
			 DAO<Personne1>personnalDao=DAOFactory.getPersonne1DAO ( ) ;
				p= personnalDao.create (p1);
				assertTrue(p.getNom()==p.getNom());
		   
			 assertFalse(p3.getNom()==p.getNom());
			 p3= personnalDao.update (p);
			 assertTrue(p3.getNom()==p.getNom()); 
			 assertEquals(p3.getNom(), "cristion");
			 assertTrue(p3.getPrenom()==p.getPrenom()); 
			 assertEquals(p3.getPrenom(), "ronaldo");
			 assertTrue(p3.getFonction()==p.getFonction()); 
			 assertEquals(p3.getFonction(), "directeur");
		}
		@Test
		public void test2() throws IOException {
			Personne1 p,p3, p1 = new Builder(65,
	       			"cristion", 
	       			"ronaldo", 
	       			"directeur", 
	       			LocalDate.parse("1997-04-22"),
	       			new Numero_telephone( 3, Type_tel.fix_perso, "0104050506"))
	    			.build();
			 
			 DAO<Personne1>personnalDao=DAOFactory.getPersonne1DAO ( ) ;
				p= personnalDao.create (p1);
				p3= personnalDao.create (p1);
				   assertEquals(p3.getNom(), p.getNom());
				    assertEquals(p3.getPrenom(), p.getPrenom());
				    assertEquals(p3.getFonction(), p.getFonction());
				  
				    assertEquals("cristion", p.getNom());
				    assertEquals("ronaldo", p.getPrenom());
				    assertEquals("directeur", p.getFonction());
				 
			
		 

		    
			
		}

}
