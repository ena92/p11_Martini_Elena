import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistrazioneTest {
	Registrazione registrazione;
	Cliente cliente;
	String dataNascita = "26/08/1985";
	Date data;
	SQLDataBase sqlDataBase;
	private ArrayList<Prenotazione> prList;
	
	@Before
	public void setUp() throws Exception {
		sqlDataBase = new SQLDataBase();
		prList = new ArrayList<Prenotazione>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Giovanni","giova","Giovanni.1985",data,"Via GrossiBianchi","Giovanni.1985",true,prList);
		registrazione = new Registrazione(cliente);
		registrazione.insertData();
	}

	@Test
	public void setMethodsTest() {
		Cliente cliente2 = new Cliente(2,"Claudio","clamar","Agaggio.56",data,"Via Torti","Agaggio.56",true,prList);
		registrazione.setCliente(cliente2);
		assertEquals(cliente2, registrazione.getCliente());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(cliente,registrazione.getCliente());
	}
	@Test
	public void insertDataTest() throws Exception{
		registrazione = new Registrazione(new Cliente(4,"Alice","alix4","Lapino.94",data,"Via Torti","Lapino.94",true,prList));
		assertEquals(1,registrazione.insertData());
		registrazione = new Registrazione(new Cliente(3,"Alice","ali4","Lapo94",data,"Via Torti","Lapo94",true,prList));
		assertEquals(0,registrazione.insertData());
		registrazione = new Registrazione(new Cliente(5,"Giovanni","giova","Giovanni.1985",data,"Via GrossiBianchi","Giovanni.1985",true,prList));
		assertEquals(-1,registrazione.insertData());
	}
	@Test
	public void formatoPasswordCorrettoTest(){
		assertTrue(registrazione.formatoPasswordCorretto("Lapo.1994"));
		assertFalse(registrazione.formatoPasswordCorretto("Lapo94"));
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
