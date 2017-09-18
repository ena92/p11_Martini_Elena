import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistrazioneTestFunzionali {
	Registrazione registrazione;
	Cliente cliente,cliente2,cliente3;
	SQLDataBase sqlDataBase;
	@Before
	public void setUp() throws Exception {
		sqlDataBase = new SQLDataBase();
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via Torti","Ciao");
		cliente2 = new Cliente(2,"Elena","ena92","Pippo.1992",data,"Via Torti","Ciao");
		cliente3 = new Cliente(3,"Elena","elena","Pippo",data,"Via Torti","Ciao");
		registrazione = new Registrazione(cliente);
	}
	//Si riferisce allo scenario principale di registrazione di un nuovo cliente e anche a quello alternativo di formato password errato
	@Test
	public void test() throws Exception {
		assertEquals(1,registrazione.insertData());
		registrazione = new Registrazione(cliente2);
		assertEquals(1,registrazione.insertData());
		registrazione = new Registrazione(cliente3);
		assertEquals(0,registrazione.insertData());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
