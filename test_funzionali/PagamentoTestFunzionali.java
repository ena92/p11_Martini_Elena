import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PagamentoTestFunzionali {
	SQLDataBase sqlDataBase;
	Cliente cliente;
	Carta carta;
	Paypal paypal;
	Abbonamento abbonamento;
	@Before
	public void setUp() throws Exception {
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		ArrayList<Prenotazione> prList = new ArrayList<Prenotazione>();
		prog.add(new Programmazione(1,"15:00",new Sala(1,"Roof",2,2)));
		Film film = new Film(1,"Spy",2017,"Commedia","120min",prog);
		Date date = new Date();
		Biglietto biglietto = new Biglietto("15:00",date,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		Prenotazione prenotazione = new Prenotazione(1,0,bigliettoList);
		prList.add(prenotazione);
		date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao",true,prList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		carta = new Carta(cliente, prenotazione,new Preventivo(1,6.5),"1234567812345678", 123, "12/18");
		paypal = new Paypal(cliente, prenotazione,new Preventivo(1,6.5),"ele@gmail.com", "ciao");
		abbonamento = new Abbonamento(cliente, prenotazione,new Preventivo(1,6.5));
	}
	//Si riferisce allo scenario principale di pagamento tramite carta di credito
	@Test
	public void pagamentoCartaTest() throws Exception {
		assertEquals(1,carta.pagaConCarta());
	}
	//Si riferisce allo scenario alternativo 3b: pagamento tramite abbinamento
	@Test
	public void pagamentoAbbonamentoTest() throws Exception {
		assertEquals(1,abbonamento.pagaConAbbonamento());
	}
	//Si riferisce allo scenario alternatico 3a: pagamento tramite paypal
	@Test
	public void pagamentoPayPalTest() throws Exception {
		assertEquals(1,paypal.pagaConPayPal());
	}
	@Test
	//Si riferisce allo scenario alternativo 7a: dati inseriti errati
	public void dataError() throws Exception{
		paypal.setMail("ele");
		assertEquals(0,paypal.pagaConPayPal());
		carta.setNumeroCarta("123");
		assertEquals(0,carta.pagaConCarta());
		carta.setNumeroCarta("1234567812345678");
		carta.setNumeroSegreto(12);
		assertEquals(0,carta.pagaConCarta());
		carta.setDataScadenza("08/17");
		carta.setNumeroSegreto(123);
		assertEquals(0,carta.pagaConCarta());
		cliente.setAbbonamento(false);
		assertEquals(0,abbonamento.pagaConAbbonamento());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
