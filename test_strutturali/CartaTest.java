import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartaTest {
	Carta carta;
	private Cliente cliente;
	private SQLDataBase sqlDataBase;
	private Object film;
	private Biglietto biglietto;
	private Prenotazione prenotazione;
	ArrayList<Prenotazione> prList;
	Preventivo preventivo;
	@Before
	public void setUp() throws Exception {
		Sala sala = new Sala(1,"Roof",3,3);
		preventivo = new Preventivo(1,13.0);
		Date date = new Date();
		date = new Date();
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		Film film = new Film(1, "Spiderman",2017,"Azione","120 min",prog);
		biglietto = new Biglietto("15:00",date,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		prList = new ArrayList<Prenotazione>();
		prList.add(prenotazione);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao",true,prList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		carta = new Carta(cliente,prenotazione,preventivo,"1234567890123456", 123, "10/21");
	}
	@Test 
	public void setgetMethods() throws Exception{
		carta.setNumeroCarta("9845673298456732");
		assertEquals("9845673298456732",carta.getNumeroCarta());
		carta.setNumeroSegreto(452);
		assertEquals(452,carta.getNumeroSegreto());
		carta.setDataScadenza("10/21");
		assertEquals("10/21",carta.getDataScadenza());
	}
	
	@Test
	public void pagaConCartaTest() throws Exception {
		assertEquals(1,carta.pagaConCarta());
		carta.setNumeroCarta("12345");
		assertEquals(0,carta.pagaConCarta());
		carta.setNumeroCarta("1234567890123456");
		carta.setNumeroSegreto(22);
		assertEquals(0,carta.pagaConCarta());
		carta.setNumeroSegreto(2211);
		assertEquals(0,carta.pagaConCarta());
		carta.setNumeroSegreto(221);
		carta.setDataScadenza("05/18");
		assertEquals(0,carta.pagaConCarta());
		preventivo = new Preventivo(1,0);
		carta = new Carta(cliente,prenotazione,preventivo,"1234567890123456",221,"10/18");
		assertEquals(0,carta.pagaConCarta());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
