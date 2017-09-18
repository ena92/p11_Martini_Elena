import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class FilmTest {
	Film film;
	Cinema cinema;
	@Parameter(0) public String input;
	@Parameter(1) public String input2;
	@Before
	public void setUp() throws Exception {
		film = new Film(1, "Spiderman",2017,"Azione","120 min");
		cinema = new Cinema("Ariston","Sanremo","Via Matteotti 4");
		cinema.film = new ArrayList<Film>();
		cinema.film.add(film);
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
		film = new Film();
		film.setId(2);
		film.setTitolo("The War-Il pianeta delle scimmie");
		film.setTipo("Fantascienza");
		film.setAnnoPubblicazione(2016);
		film.setDurata("150 min");
		assertEquals(2, film.getId());
		assertEquals("The War-Il pianeta delle scimmie",film.getTitolo());
		assertEquals(2016,film.getAnnoPubblicazione());
		assertEquals("Fantascienza", film.getTipo());
		assertEquals("150 min", film.getDurata());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(1, film.getId());
		assertEquals("Spiderman",film.getTitolo());
		assertEquals(2017,film.getAnnoPubblicazione());
		assertEquals("Azione", film.getTipo());
		assertEquals("120 min", film.getDurata());
	}
	@Test
	public void ricercaCinemaTest(){
		assertEquals(film,film.ricercaFilm(cinema, input));
		assertNull(film.ricercaFilm(cinema,input2));
	}

}
