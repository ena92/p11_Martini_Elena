import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class AutenticazioneTestFunzionali{
	Autenticazione autenticazione;
	SQLDataBase sqlDataBase;
	@Before
	public void setup() throws Exception{
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		data = formatter.parse(dataNascita);
		sqlDataBase.inserisciUtente("Elena", "ena92", "Pippo.1992", "Ciao", dataNascita, "Via Torti");
	}
	//Si riferisce allo scenario principale, dove il cliente prova ad autenticarsi
	@Test
	public void test() throws Exception {
		autenticazione = new Autenticazione("ena92","Pippo.1992");
		assertTrue(autenticazione.verifyDat());
		assertFalse(new Autenticazione("alix","Alice.1992").verifyDat());
	}
	//Si riferisce allo scenario alternativo per il recupero della password in caso di fallimento nell'autenticazione
	@Test
	public void recuperoPassTest() throws Exception{
		autenticazione = new Autenticazione("ena92","Pipo.1992");
		assertEquals("Ciao",autenticazione.recuperaPass(autenticazione.getNomeUtente()));
		autenticazione = new Autenticazione("en92","Pippo.1992"); 
		assertNull(autenticazione.recuperaPass(autenticazione.getNomeUtente()));
	}
	//Richiamata per resettare il database
	@After
	public void deleteDataBase() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
