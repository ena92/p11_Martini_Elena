import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PagamentoTest {
	Pagamento pagamento;
	Cliente cliente;
	Film film;
	double totale;
	SQLDataBase sqlDataBase;
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao");
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		totale =13.0;
		film = new Film(1,"Spy",2017,"Commedia","120min");
		pagamento = new Pagamento(cliente,film,totale);
		
	}
	@Test
	public void setGetMetodhsTest() throws Exception{
		assertEquals(cliente,pagamento.getCliente());
		assertEquals(film,pagamento.getFilm());
		assertEquals(totale,pagamento.getTotale(),0);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		Cliente cliente2 = new Cliente(2,"Alice","ena92","Pippo.1992",date,"Via torti","Ciao");
		Film film2 = new Film(2,"Go!",2017,"Azione","120min");
		pagamento.setCliente(cliente2);
		pagamento.setFilm(film2);
		pagamento.setTotale(6.5);
		
		assertEquals(cliente2,pagamento.getCliente());
		assertEquals(film2,pagamento.getFilm());
		assertEquals(6,pagamento.getTotale(),5);
	}
	@Test
	public void pagaConCartaTest() throws Exception {
		assertEquals(1,pagamento.pagaConCarta("1234567890123456", 123, "06/18"));
		assertEquals(0,pagamento.pagaConCarta("12345", 123, "06/18"));
		assertEquals(0,pagamento.pagaConCarta("1234567890123456",22,"06/18"));
		assertEquals(0,pagamento.pagaConCarta("1234567890123456",2211,"06/18"));
		pagamento = new Pagamento(cliente,film,0);
		assertEquals(0,pagamento.pagaConCarta("1234567890123456",221,"06/18"));
	}
	@Test
	public void pagaConPaypalTest() throws Exception{
		assertEquals(1,pagamento.pagaConPayPal("ele@gmail.com", "Pippo.1992"));
		assertEquals(0,pagamento.pagaConPayPal("ele@gmai.i", "Pippo.1992"));
		pagamento = new Pagamento(cliente,film,0);
		assertEquals(0,pagamento.pagaConPayPal("ele@gmai.it", "Pippo.1992"));
	}
	@Test
	public void pagaConAbbonamentoTest() throws Exception{
		assertEquals(1,pagamento.pagaConAbbonamento(cliente));
		cliente.setAbbonamento(false);
		assertEquals(0,pagamento.pagaConAbbonamento(cliente));
		pagamento = new Pagamento(cliente,film,0);
		assertEquals(0,pagamento.pagaConAbbonamento(cliente));
	}
	@After
	public void delete() throws SQLException{
		sqlDataBase.deleteTableUtentiReg();
	}
}