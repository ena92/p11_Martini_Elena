import static org.junit.Assert.*;

import java.util.ArrayList;
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
	ArrayList<Programmazione> newProg,oldProg;
	@Before
	public void setUp() throws Exception {
		Date dataOggi = new Date();
		cinema = new Cinema(1,"Ariston","Sanremo","Via Matteotti 4");
		sala = new Sala(1,"Stanza1",2,2);
		Programmazione programmazione = new Programmazione(1,"15:00",sala);
		Programmazione programmazione2 = new Programmazione(1,"21:00",sala);
		ArrayList<Programmazione> newProg = new ArrayList<Programmazione>(); 
		newProg.add(programmazione);
		newProg.add(programmazione2);
		Film film = new Film(1,"Time",2017,"Commedia","120min",newProg);
		db = new SQLDataBase();
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
		sala.selezionaPosto(1, film.programmazione.get(0).orarioInizio, cinema);
	}
	@Test
	public void setMethodsTest() throws Exception {
		db.deleteTableMappe(sala.getNome());
		sala = new Sala();
		sala.setId(2);
		sala.setNome("Stanza2");
		sala.setNumeroPostiPerFila(3);
		sala.setNumeroFile(3);
		sala.setPostiTotali();
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
		assertEquals(2,sala.getId());
		assertEquals("Stanza2",sala.getNome());
		assertEquals(3, sala.getNumeroPostiPerFila());
		assertEquals(3, sala.getNumeroFile());
		assertEquals(9, sala.getPostiTotali());
	}
	@Test
	public void getMethodsTest() throws Exception {
		assertEquals(1,sala.getId());
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
		for(int i = 1; i<=sala.postiTotali;i++){
			sala.selezionaPosto(i, "15:00", cinema);
		}
		assertEquals(-1,sala.selezionaPosto(1,"15:00", cinema));
		
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
