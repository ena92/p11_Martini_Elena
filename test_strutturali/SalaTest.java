import static org.junit.Assert.*;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class SalaTest {
	Sala sala;
	Sala sala2;
	Cinema cinema;
	String [][] mappaSala;
	SQLDataBase db;
	@Before
	public void setUp() throws Exception {
		Date dataOggi = new Date();
		sala = new Sala("Stanza1",2,2);
		
		cinema = new Cinema("Ariston", "Sanremo", "Via Matteotti 4");
		db = new SQLDataBase();
		sala.selezionaPosto(1, "15:00", cinema);
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
	}
	@Test
	public void setMethodsTest() throws Exception {
		db.deleteTableMappe(sala.getNome());
		sala = new Sala();
		sala.setNome("Stanza2");
		sala.setNumeroPostiPerFila(3);
		sala.setNumeroFile(3);
		sala.setPostiTotali();
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
		assertEquals("Stanza2",sala.getNome());
		assertEquals(3, sala.getNumeroPostiPerFila());
		assertEquals(3, sala.getNumeroFile());
		assertEquals(9, sala.getPostiTotali());
	}
	@Test
	public void getMethodsTest() throws Exception {
		assertEquals("Stanza1",sala.getNome());
		assertEquals(2, sala.getNumeroPostiPerFila());
		assertEquals(2, sala.getNumeroFile());
		assertEquals(4, sala.getPostiTotali());
		assertEquals(1, sala.getPostiOccupati("15:00", cinema));
	}
	@Test
	public void selezionaPostoTest() throws Exception{
		assertEquals(0,sala.selezionaPosto(1, "15:00", cinema));
		assertEquals(2,sala.selezionaPosto(2, "15:00", cinema));
		assertEquals(0,sala.selezionaPosto(10, "15:00", cinema));
		
	}
	@Test
	public void creaMappaSalaTest() throws Exception{
		mappaSala = sala.creaMappaSala("15:00", cinema);
		for(int i = 0; i<sala.getNumeroFile(); i++){
			for(int j = 0; j<sala.getNumeroPostiPerFila();j++){
				if(j!=0 && i != 0){
					assertEquals("LIBERO",mappaSala[i][j]);
				}
			}
		}
		assertEquals("OCCUPATO",mappaSala[0][0]);
	}
	@After
	public void deleteTable() throws Exception{
		db = new SQLDataBase();
		db.deleteTableMappe(sala.getNome());
	}
}
