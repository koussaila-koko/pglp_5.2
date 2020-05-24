
package uvsq.koukou_5;






import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;


import org.junit.Before;

import org.junit.Test;

import uvsq.koukou_5.Personne1.Builder;

public class SerializablTest {
	
	private Numero_telephone num;
	private Personne1 personne1,personne2;
	private Groupe group ;
	private boolean bool;
	@Before()
	public void setUp() {
		 bool = true;
		 num=new Numero_telephone(5,Type_tel.portable,"0666721065");
		personne1  = new Builder(1,
     			"koussaila",
       			"hamouche", 
       			"directeur", 
       			LocalDate.parse("1998-12-22"),
       			new Numero_telephone( 1, Type_tel.fix_perso, "0104050506"))
    			.build();
		personne2 = new Builder(1,
     			"koussaila",
       			"hamouche", 
       			"directeur", 
       			LocalDate.parse("1998-12-22"),
       			new Numero_telephone( 1, Type_tel.fix_perso, "0104050506"))
    			.build();
		group = new Groupe(2,"info");
		group.add(personne1);
		group.add(personne2);
		
	}
	@Test
	public void portable() throws IOException {
		Numero_telephone numtest = null;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
		ois = new ObjectInputStream(new FileInputStream("test.txt"));
		try {

			
			
				oos.writeObject(num);
				numtest = (Numero_telephone) ois.readObject();

			
		} catch (ClassNotFoundException | IOException error) {
			error.printStackTrace();
		}
		finally {
			oos.close();
			ois.close();
		}
		assertEquals(num.getNumero(), numtest.getNumero());
		assertEquals(num.getType(), numtest.getType());
		
	}
	
	@Test
	public void testpersonne1() throws IOException {
		boolean bool = true;
		ObjectOutputStream oos = null;
			try {
				final FileOutputStream fichier = new FileOutputStream("test1.txt");
				oos = new ObjectOutputStream(fichier);
				oos.writeObject(personne1);
				oos.flush();
				bool = true;
			} catch (IOException e) {
				e.printStackTrace();
				bool = false;
				assertEquals( bool , false);
			} finally {
				assertEquals(bool , true);
				
					if (oos != null) {
						oos.flush();
						oos.close();
						}

		      }}
@Test
public void testpersonne2() throws FileNotFoundException, IOException {
	Personne1 personnetest = null;
ObjectOutputStream oos;
ObjectInputStream ois;
oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
ois = new ObjectInputStream(new FileInputStream("test.txt"));
try {

	
	
		oos.writeObject(personne1);
		personnetest = (Personne1) ois.readObject();

	
} catch (ClassNotFoundException | IOException error) {
	error.printStackTrace();
}
finally {
	oos.close();
	ois.close();
}
assertEquals(personne1.getNom(), personnetest.getNom());
assertEquals(personne1.getPrenom(), personnetest.getPrenom());
assertEquals(personne1.getFonction(), personnetest.getFonction());
}
@Test
public void testgroup1() throws IOException {	

ObjectOutputStream oos = null;
try {
	final FileOutputStream fichier = new FileOutputStream("test1.txt");
	oos = new ObjectOutputStream(fichier);
	oos.writeObject(group);
	oos.flush();
	bool = true;
} catch (IOException e) {
	e.printStackTrace();
	bool = false;
	assertEquals( bool , false); 
} finally {
	assertEquals(bool , true);
	
		if (oos != null) {
			oos.flush();
			oos.close();
			}

  }}

    @Test
    public void PersonalTest() throws IOException, ClassNotFoundException {
     	Personne1 P = new Builder(1,
     			"koussaila",
       			"hamouche", 
       			"directeur", 
       			LocalDate.parse("1998-12-22"),
       			new Numero_telephone( 1, Type_tel.fix_perso, "0104050506"))
    			.build();
        ByteArrayOutputStream outBuff = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outBuff);
        out.writeObject(P);
        out.close();

        byte[] buff = outBuff.toByteArray();
        outBuff.close();

        ByteArrayInputStream inBuff = new ByteArrayInputStream(buff);
        ObjectInputStream in = new ObjectInputStream(inBuff);
        Object observed = in.readObject();
        in.close();
        inBuff.close();

        assertTrue(observed instanceof Personne1);
      
        assertEquals(P.getNom(), "koussaila");
        
        
     
    }
   
    @Test
    public void groupeTest() throws IOException, ClassNotFoundException {
        Groupe g = new Groupe(1, "G");
     
        Personne1 p = new Builder(1,
       			"koussaila",
       			"hamouche", 
       			"directeur", 
       			LocalDate.parse("1998-12-22"),
       			new Numero_telephone( 1, Type_tel.fix_perso, "0104050506"))
    			.build();
        g.add(p);
        ByteArrayOutputStream outBuff = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outBuff);
        out.writeObject(g);
        out.close();

        byte[] buff = outBuff.toByteArray();
        outBuff.close();

        ByteArrayInputStream inBuff = new ByteArrayInputStream(buff);
        ObjectInputStream in = new ObjectInputStream(inBuff);
        Object observed = in.readObject();
        in.close();
        inBuff.close();

        assertTrue(observed instanceof Groupe);
        assertEquals(g.getId(), ((Groupe) observed).getId());
    }
    @Test
    public void groupe2Test() throws IOException, ClassNotFoundException {
        Groupe g = new Groupe(1, "G");
     
        Personne1 p = new Builder(1,
       			"koussaila",
       			"hamouche", 
       			"directeur", 
       			LocalDate.parse("1998-12-22"),
       			new Numero_telephone( 1, Type_tel.fix_perso, "0104050506"))
    			.build();
        g.add(p);
        ByteArrayOutputStream outBuff = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outBuff);
        out.writeObject(g);
        out.close();

        byte[] buff = outBuff.toByteArray();
        outBuff.close();

        ByteArrayInputStream inBuff = new ByteArrayInputStream(buff);
        ObjectInputStream in = new ObjectInputStream(inBuff);
        Object observed = in.readObject();
        in.close();
        inBuff.close();

        
        assertEquals(g.getId(), ((Groupe) observed).getId());
    }
    @Test
    public void serialisationTest() throws IOException, ClassNotFoundException {
    	Numero_telephone t = new Numero_telephone(1, Type_tel.fix_perso, "0665432356");
        ByteArrayOutputStream outBuff = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outBuff);
        out.writeObject(t);
        out.close();

        byte[] buff = outBuff.toByteArray();
        outBuff.close();

        ByteArrayInputStream inBuff = new ByteArrayInputStream(buff);
        ObjectInputStream in = new ObjectInputStream(inBuff);
        Object observed = in.readObject();
        in.close();
        inBuff.close();

        assertTrue(observed instanceof Numero_telephone);
       
    }
    @Test
    public void serialisation2Test() throws IOException, ClassNotFoundException {
    	Numero_telephone t = new Numero_telephone(1, Type_tel.fix_perso, "0665432356");
        ByteArrayOutputStream outBuff = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outBuff);
        out.writeObject(t);
        out.close();

        byte[] buff = outBuff.toByteArray();
        outBuff.close();

        ByteArrayInputStream inBuff = new ByteArrayInputStream(buff);
        ObjectInputStream in = new ObjectInputStream(inBuff);
        Object observed = in.readObject();
        in.close();
        inBuff.close();

       
        assertEquals(t.getId(), ((Numero_telephone) observed).getId());
    }


}
