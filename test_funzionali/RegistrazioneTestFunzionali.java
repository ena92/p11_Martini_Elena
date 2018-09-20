import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistrazioneTestFunzionali {
	Registrazione registrazione;
	Cliente cliente,cliente2,cliente3;
	SQLDataBase sqlDataBase;
	Date data = null;
	ArrayList<Prenotazione> prList;
	@Before
	public void setUp() throws Exception {
		sqlDataBase = new SQLDataBase();
		String dataNascita = "31/05/1992";
		prList = new ArrayList<Prenotazione>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via Torti","Ciao",true,prList);
		cliente2 = new Cliente(2,"Elena","ena1992","Pippo.1992",data,"Via Torti","Ciao",true,prList);
		registrazione = new Registrazione(cliente);
		registrazione.insertData();
	}
	//Si riferisce allo scenario principale di registrazione di un nuovo cliente avvenuta con successo
	@Test
	public void test() throws Exception {
		registrazione = new Registrazione(cliente2);
		assertEquals(1,registrazione.insertData());
	}
	//Si riferisce allo scenario alternativo 3a e 3b: formato password non corretto, condizioni non accettate
	@Test
	public void testPassCond() throws Exception{
		registrazione = new Registrazione(new Cliente(2,"Elena","ena1992","Pippo1992",data,"Via Torti","Ciao",true,prList));
		assertEquals(0,registrazione.insertData());
	}
	//Si riferisce allo scenario alternativo 4a: nome utente gia' presente nel database
	@Test
	public void test3() throws Exception{
		registrazione = new Registrazione(cliente);
		registrazione.insertData();
		assertEquals(-1,registrazione.insertData());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
