import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class CinemaTest {
	Cinema cinema;
	Cinema cinema2;
	ArrayList<Sala> sale;
	ArrayList<Film> film;
	Film film1;
	@Parameter(0) public String input;
	@Parameter(1) public String input2;
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema(1,"Ariston","Sanremo","Via Matteotti 4");		
		sale = new ArrayList<Sala>();
		Sala sala1 = new Sala(1,"ROOF 1",2,2);
		Sala sala2 = new Sala(2,"ROOF 2", 3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala1));
		film = new ArrayList<Film>();
		film1 = new Film(1, "Spiderman",2017,"Azione","120 min",prog);
		prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(2,"21:00",sala1));
		Film film2 = new Film(2, "The War-Il pianeta delle scimmie",2017,"Fantascienza","150 min",prog);
		film.add(film1);
		film.add(film2);
		cinema.setFilm(film);
		sale.add(sala1);
		sale.add(sala2);
	}
	@Parameters
	public static Collection<String[]> data(){
		return Arrays.asList(new String[][] {
			{"Spiderman","Aspiderman"},
			{"spiderman","aspiderman"},
			{"Spi","Aspi"},
			{"spi","aspi"},
			{"derman","aderman"}
		});
	}
	@Test
	public void setMethodsTest() {
		cinema.setId(2);
		cinema.setCity("Savona");
		cinema.setNome("Global");
		cinema.setIndirizzo("Via XX settembre 3");
		cinema.setSale(sale);
		cinema.setFilm(film);
		assertEquals(2,cinema.getId());
		assertEquals("Savona", cinema.getCity());
		assertEquals("Global", cinema.getNome());
		assertEquals("Via XX settembre 3", cinema.getIndirizzo());
		assertEquals(sale, cinema.getSale());
		assertEquals(film, cinema.getFilm());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(1,cinema.getId());
		assertEquals("Sanremo", cinema.getCity());
		assertEquals("Ariston", cinema.getNome());
		assertEquals("Via Matteotti 4", cinema.getIndirizzo());
		cinema.setSale(sale);
		cinema.setFilm(film);
		assertEquals(sale, cinema.getSale());
		assertEquals(film, cinema.getFilm());
	}
	
	@Test
	public void ricercaFilmTest(){
		assertEquals(film1,cinema.ricercaFilm(input));
		assertNull(cinema.ricercaFilm(input2));
	}
}
