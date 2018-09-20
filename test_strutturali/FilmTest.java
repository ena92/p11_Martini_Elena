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

public class FilmTest {
	Film film;
	Cinema cinema;
	ArrayList<Programmazione> newProg,oldProg; 
	@Before
	public void setUp() throws Exception {
		Sala sala = new Sala(1,"Roof",3,3);
		newProg = new ArrayList<Programmazione>();
		oldProg = new ArrayList<Programmazione>();
		oldProg.add(new Programmazione(1,"15:00",sala));
		newProg.add(new Programmazione(2,"21:00",sala));
		film = new Film(1, "Spiderman",2017,"Azione","120 min",oldProg);
		cinema = new Cinema(1,"Ariston","Sanremo","Via Matteotti 4");
		cinema.film = new ArrayList<Film>();
		cinema.film.add(film);
	}
	@Test
	public void setMethodsTest() {
		film.setId(2);
		film.setTitolo("The War-Il pianeta delle scimmie");
		film.setTipo("Fantascienza");
		film.setAnnoPubblicazione(2016);
		film.setDurata("150 min");
		film.setStelle(4);
		film.setProgrammazione(newProg);
		assertEquals(2, film.getId());
		assertEquals("The War-Il pianeta delle scimmie",film.getTitolo());
		assertEquals(2016,film.getAnnoPubblicazione());
		assertEquals("Fantascienza", film.getTipo());
		assertEquals("150 min", film.getDurata());
		assertEquals(4,film.getStelle());
		assertEquals(newProg,film.getProgrammazione());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(1, film.getId());
		assertEquals("Spiderman",film.getTitolo());
		assertEquals(2017,film.getAnnoPubblicazione());
		assertEquals("Azione", film.getTipo());
		assertEquals("120 min", film.getDurata());
		assertEquals(5,film.getStelle());
		assertEquals(oldProg,film.getProgrammazione());
	}
	@Test
	public void schedaFilmTest(){
		ArrayList<String> scheda = new ArrayList<String>(); 
		scheda = film.schedaFilm();
		assertEquals(film.getTipo(),scheda.get(0));
		assertEquals(film.getDurata(),scheda.get(1));
		assertEquals(String.valueOf(film.getAnnoPubblicazione()),scheda.get(2));
		assertEquals(String.valueOf(film.getStelle()),scheda.get(3));
	}
}
