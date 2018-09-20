import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutenticazioneTest {
	Cliente cliente;
	Cliente cliente2;
	Autenticazione autenticazione;
	Autenticazione autenticazione2;
	SQLDataBase sqlDataBase;
	ArrayList<Prenotazione> prList;
	@Before
	public void setUp() throws Exception {
		String dataNascita = "31/05/1992";
		prList = new ArrayList<Prenotazione>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992",true,prList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente("Elena","ena92","Pippo.1992","Pippo.1992",dataNascita,"Via GrossiBianchi");
		cliente2 = new Cliente(2,"Daniele","brigno","Daniele.1992",data,"Via opera pia","Daniele.1992",true,prList);
		autenticazione = new Autenticazione(1,cliente);
		autenticazione2 = new Autenticazione(2,cliente2);
	}

	@Test
	public void verirfyDatatest() throws Exception {
		assertTrue(autenticazione.verifyDat());
		assertFalse(autenticazione2.verifyDat());
	}
	@Test
	public void recuperaPasstest() throws Exception{
		assertEquals("Pippo.1992",autenticazione.recuperaPass());
		assertNull(autenticazione2.recuperaPass());
	}
	@Test
	public void setMethods(){
		autenticazione.setId(2);
		autenticazione.setCliente(cliente2);
		assertEquals(2,autenticazione.getId());
		assertEquals(cliente2,autenticazione.getCliente());
	
	}
	@Test
	public void getMethods(){
		assertEquals(1,autenticazione.getId());
		assertEquals(cliente,autenticazione.getCliente());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
