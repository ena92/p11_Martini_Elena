import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class AutenticazioneTestFunzionali{
	Autenticazione autenticazione;
	SQLDataBase sqlDataBase;
	Cliente cliente;
	ArrayList<Prenotazione> prList;
	Date data = null;
	//Per potersi autenticare e' necessario essere prima registrati. Nel funzione setup viene registrato un utente nuovo
	@Before
	public void setup() throws Exception{
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = formatter.parse(dataNascita);
		prList= new ArrayList<Prenotazione>();
		cliente = new Cliente(1,"Beatrice","bea92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992",true,prList);
		
		sqlDataBase.inserisciUtente("Beatrice","bea92","Pippo.1992","Pippo.1992",dataNascita,"Via GrossiBianchi");
	}
	//Si riferisce allo scenario principale, dove il cliente riesce ad autenticarsi
	@Test
	public void test() throws Exception {
		autenticazione = new Autenticazione(1,cliente);
		assertTrue(autenticazione.verifyDat());
	}
	//Si riferisce allo scenario alternativo 4a: nome utente e password non coincidenti
	@Test
	public void test2() throws Exception {
		Cliente cliente2 = new Cliente(2,"Daniele","brigno","Daniele.1992",data,"Via opera pia","Daniele.1992",true,prList);
		assertFalse(new Autenticazione(1,cliente2).verifyDat());
	}
	//Si riferisce allo scenario alternativo 1a: per il recupero della password in caso di fallimento nell'autenticazione
	@Test
	public void recuperoPassTest() throws Exception{
		autenticazione = new Autenticazione(1,cliente);
		assertEquals("Pippo.1992",autenticazione.recuperaPass());
		cliente = new Cliente(1,"Beatrice","be92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992",true,prList);
		autenticazione = new Autenticazione(1,cliente); 
		assertNull(autenticazione.recuperaPass());
	}
	//Richiamata per resettare il database
	@After
	public void deleteDataBase() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
