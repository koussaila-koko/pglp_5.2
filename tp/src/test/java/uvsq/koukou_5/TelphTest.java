package uvsq.koukou_5;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TelphTest {

	@Test
	public void GetidTest() throws IOException, ClassNotFoundException {
    	Numero_telephone t = new Numero_telephone(32, Type_tel.fix_perso, "0665432356");
    	assertEquals(t.getId(), 32);
    	
}
	@Test
	public void GetNumerotest() throws IOException, ClassNotFoundException {
    	Numero_telephone t = new Numero_telephone(32, Type_tel.fix_perso, "0665432356");
    	assertEquals(t.getNumero(), "0665432356");
}
	@Test
	public void GetTypeTest() throws IOException, ClassNotFoundException {
    	Numero_telephone t = new Numero_telephone(32, Type_tel.fix_perso, "0665432356");
    	assertEquals(t.getType(), Type_tel.fix_perso);
}
}