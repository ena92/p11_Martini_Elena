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
	@Parameter(0) public String input;
	@Parameter(1) public String input2;
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema("Ariston","Sanremo","Via Matteotti 4");
		cinema2 = new Cinema();
		Sala sala1 = new Sala("ROOF 1",2,2);
		Sala sala2 = new Sala("ROOF 2", 3,3);
		sale = new ArrayList<Sala>();
		film = new ArrayList<Film>();
		sale.add(sala1);
		sale.add(sala2);
		Film film1 = new Film(1, "Spiderman",2017,"Azione","120 min");
		Film film2 = new Film(2, "The War-Il pianeta delle scimmie",2017,"Fantascienza","150 min");
		film.add(film1);
		film.add(film2);
	}
	@Parameters
	public static Collection<String[]> data(){
		return Arrays.asList(new String[][] {
			{"ariston","Gaston"},
			{"Ariston","gaston"},
			{"Ar","ga"},
			{"riston","Ga"},
			{"sto","Gasto"}
		});
	}
	@Test
	public void setMethodsTest() {
		cinema2.setCity("Savona");
		cinema2.setNome("Global");
		cinema2.setIndirizzo("Via XX settembre 3");
		cinema2.setSale(sale);
		cinema2.setFilm(film);
		assertEquals("Savona", cinema2.getCity());
		assertEquals("Global", cinema2.getNome());
		assertEquals("Via XX settembre 3", cinema2.getIndirizzo());
		assertEquals(sale, cinema2.getSale());
		assertEquals(film, cinema2.getFilm());
	}
	@Test
	public void getMethodsTest(){
		assertEquals("Sanremo", cinema.getCity());
		assertEquals("Ariston", cinema.getNome());
		assertEquals("Via Matteotti 4", cinema.getIndirizzo());
		cinema.setSale(sale);
		cinema.setFilm(film);
		assertEquals(sale, cinema.getSale());
		assertEquals(film, cinema.getFilm());
	}
	@Test
	public void ricercaCinemaTest(){
		assertEquals(cinema,cinema.ricercaCinema(input));
		assertNotEquals(cinema, cinema.ricercaCinema(input2));
	}
}
