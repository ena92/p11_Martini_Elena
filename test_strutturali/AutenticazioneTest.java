import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AutenticazioneTest {
	Cliente cliente;
	Cliente cliente2;
	Autenticazione autenticazione;
	Autenticazione autenticazione2;
	SQLDataBase sqlDataBase;
	@Before
	public void setUp() throws Exception {
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992");
		
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente("Elena","ena92","Pippo.1992","Pippo.1992",dataNascita,"Via GrossiBianchi");
		cliente2 = new Cliente(2,"Daniele","brigno","Daniele.1992",data,"Via opera pia","Daniele.1992");
		autenticazione = new Autenticazione(cliente.nomeutente, cliente.password);
		autenticazione2 = new Autenticazione(cliente2.nomeutente, cliente.password);
	}

	@Test
	public void verirfyDatatest() throws Exception {
		assertTrue(autenticazione.verifyDat());
		assertFalse(autenticazione2.verifyDat());
	}
	@Test
	public void recuperaPasstest() throws Exception{
		assertEquals("Pippo.1992",autenticazione.recuperaPass(cliente.nomeutente));
		assertNull(autenticazione2.recuperaPass(cliente2.nomeutente));
	}
	@Test
	public void setMethods(){
		autenticazione.setNomeUtente(cliente2.nomeutente);
		autenticazione.setPassword(cliente2.password);
		assertEquals(cliente2.nomeutente,autenticazione.getNomeUtente());
		assertEquals(cliente2.password, autenticazione.getPassword());
	
	}
	@Test
	public void getMethods(){
		assertEquals(cliente.nomeutente,autenticazione.getNomeUtente());
		assertEquals(cliente.password, autenticazione.getPassword());
	}
	
}
