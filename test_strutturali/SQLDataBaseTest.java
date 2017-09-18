import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SQLDataBaseTest {
	SQLDataBase db;
	Sala sala,sala2;
	Cinema cinema;
	Cliente cliente,cliente2;
	@Before
	public void setUp() throws Exception {
		db = new SQLDataBase();
		sala = new Sala("A1",2,2);
		sala2 = new Sala("A2",2,2);
		cinema = new Cinema("TheSpace","Genova","Via porto");
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992");
		cliente2 = new Cliente(2,"Alessia","ale3","Alessia.33",data,"Via Torti","Alessia.33");
		db.creaDataBase();
		db.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), dataNascita, cliente.getIndirizzo());
		db.creaDataBaseMappe(sala2.getNome(), cinema.getNome(), sala2.getPostiTotali());
		
	}
	@Test
	public void creaDataBaseTest() throws Exception {
		assertEquals(0,db.creaDataBase());
		assertEquals(0,db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali()));
		assertEquals(-1,db.creaDataBaseMappe(sala2.getNome(), cinema.getNome(), sala2.getPostiTotali()));
	}
	@Test
	public void restituisciUtentiRegistratiTest() throws Exception{
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
		ArrayList<String> clienteRegistrato = new ArrayList<String>();
		clienteRegistrato.add("Elena");
		clienteRegistrato.add("ena92");
		clienteRegistrato.add("Pippo.1992");
		clienteRegistrato.add("31/05/1992");
		clienteRegistrato.add("Via GrossiBianchi");
		clienteRegistrato.add("Pippo.1992");
		assertEquals(clienteRegistrato,db.restituisciClienteRegistrato("ena92", "Pippo.1992"));
		assertNull(db.restituisciClienteRegistrato(cliente2.getNomeutente(), cliente2.getPassword()));
	}
	
	@After
	public void deleteDataBase() throws Exception{
		db.deleteTableUtentiReg();
		db.deleteTableMappe(sala.getNome());
		db.deleteTableMappe(sala2.getNome());
	}
}
