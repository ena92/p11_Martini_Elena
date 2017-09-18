import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	Cliente cliente;
	Date data, data2;
	@Before
	public void setUp() throws Exception {
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992");
		data2 = formatter.parse("27/01/1994");
	}

	@Test
	public void setMethodsTest() {
		cliente.setId(2);
		cliente.setNome("Alice");
		cliente.setNomeUtente("alice1");
		cliente.setPassword("Alice.1994");
		cliente.setIndirizzo("via baracca");
		cliente.setDataNascita(data2);
		cliente.setSuggerimento("Alice.1994");
		cliente.setAbbonamento(false);
		assertEquals(2,cliente.getId());
		assertEquals("Alice",cliente.getNome());
		assertEquals("alice1",cliente.getNomeutente());
		assertEquals("via baracca", cliente.getIndirizzo());
		assertEquals(data2,cliente.getDataNascita());
		assertEquals("Alice.1994", cliente.getPassword());
		assertEquals("Alice.1994", cliente.getSuggerimento());
		assertFalse(cliente.getAbbonamento());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(1,cliente.getId());
		assertEquals("Elena",cliente.getNome());
		assertEquals("ena92",cliente.getNomeutente());
		assertEquals("Via GrossiBianchi", cliente.getIndirizzo());
		assertEquals(data,cliente.getDataNascita());
		assertEquals("Pippo.1992", cliente.getPassword());
		assertTrue(cliente.getAbbonamento());
		assertEquals("Pippo.1992", cliente.getSuggerimento());
	}
	@Test
	public void isAbbonamentoTest(){
		assertTrue(cliente.isAbbonamento());
	}

}
