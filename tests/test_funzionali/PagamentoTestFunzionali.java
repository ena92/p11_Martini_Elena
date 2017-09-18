import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PagamentoTestFunzionali {
	Pagamento pagamento;
	SQLDataBase sqlDataBase;
	Cliente cliente;
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao");
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		pagamento = new Pagamento(cliente, new Film(1,"Spy",2017,"Commedia","120min"),6.5);
	}
	//Si riferisce allo scenario di pagamento tramite abbonamento
	@Test
	public void pagamentoAbbonamentoTest() throws Exception {
		assertEquals(1,pagamento.pagaConAbbonamento(cliente));
	}
	//Si riferisce allo scenario di pagamento tramite paypal
	@Test
	public void pagamentoPayPalTest() throws Exception {
		assertEquals(1,pagamento.pagaConPayPal("ele@gmail.com", "ciao"));
		assertEquals(0,pagamento.pagaConPayPal("ele", "ciao"));
	}
	//Si riferisce allo scenario di pagamento tramire carta di credito
	@Test
	public void pagamentoCartaTest() throws Exception {
		assertEquals(1,pagamento.pagaConCarta("1234567812345678", 123, "31/08/17"));
		assertEquals(0,pagamento.pagaConCarta("123",123,"31/05/1992"));
		assertEquals(0,pagamento.pagaConCarta("1234567812345678", 12, "31/08/17"));
		assertEquals(0,pagamento.pagaConCarta("12345678123456789", 123, "31/08/17"));
		
	}
	@After
	public void delete() throws SQLException{
		sqlDataBase.deleteTableUtentiReg();
	}
}
