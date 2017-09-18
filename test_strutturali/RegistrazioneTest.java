import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class RegistrazioneTest {
	Registrazione registrazione;
	Cliente cliente;
	String dataNascita = "26/08/1985";
	Date data;
	@Before
	public void setUp() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Giovanni","giova","Giovanni.1985",data,"Via GrossiBianchi","Giovanni.1985");
		registrazione = new Registrazione(cliente);
	}

	@Test
	public void setMethodsTest() {
		Cliente cliente2 = new Cliente(2,"Claudio","clamar","Agaggio.56",data,"Via Torti","Agaggio.56");
		registrazione.setCliente(cliente2);
		assertEquals(cliente2, registrazione.getCliente());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(cliente,registrazione.getCliente());
	}
	@Test
	public void insertDataTest() throws Exception{
		assertEquals(1,registrazione.insertData());
		registrazione = new Registrazione();
		registrazione.setCliente(new Cliente(3,"Alice","ali94","Lapo94",data,"Via Torti","Lapo94"));
		assertEquals(0,registrazione.insertData());
	}
	@Test
	public void formatoPasswordCorrettoTest(){
		assertTrue(registrazione.formatoPasswordCorretto("Lapo.1994"));
		assertFalse(registrazione.formatoPasswordCorretto("Lapo94"));
	}

}
