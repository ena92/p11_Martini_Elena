import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaypalTest {
	private Paypal paypal;
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
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prList = new ArrayList<Prenotazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		Film film = new Film(1, "Spiderman",2017,"Azione","120 min",prog);
		Date date = new Date();
		date = new Date();
		biglietto = new Biglietto("15:00",date,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		prList.add(prenotazione);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao",true,prList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		preventivo = new Preventivo(1,13.0);
		paypal = new Paypal(cliente,prenotazione,preventivo,"ele@gmail.com", "Pippo.1992");
	}
	@Test
	public void getsetMethods()throws Exception{
		paypal.setMail("ele92@gmail.com");
		assertEquals("ele92@gmail.com",paypal.getMail());
		paypal.setPassPaypal("Ciao.2018");
		assertEquals("Ciao.2018",paypal.getPassPaypal());
	}
	@Test
	public void pagaConPaypalTest() throws Exception{
		assertEquals(1,paypal.pagaConPayPal());
		paypal.setMail("ele@gmai.i");
		assertEquals(0,paypal.pagaConPayPal());
		paypal = new Paypal(cliente,prenotazione,new Preventivo(1,0),"ele@gmai.it", "Pippo.1992");
		assertEquals(0,paypal.pagaConPayPal());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
