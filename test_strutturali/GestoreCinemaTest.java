import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class GestoreCinemaTest {
	GestoreCinema gestoreCinema;
	Cinema cinema;
	Cinema cinema2;
	ArrayList<Sala> sale;
	ArrayList<Film> film;
	ArrayList<Cinema> cinemaEs;
	Film film1;
	
	@Parameter(0) public String input;
	@Parameter(1) public String input2;
	@Parameter(2) public String input3;
	@Parameter(3) public String input4;
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema(1,"Ariston","Sanremo","Via Matteotti 4");
		cinema2 = new Cinema(2,"Space","Genova","Corso Buenos Aires");
		
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
		cinema2.setFilm(film);
		cinema2.setSale(sale);
		cinemaEs = new ArrayList<Cinema>();
		cinemaEs.add(cinema);
		gestoreCinema = new GestoreCinema(cinemaEs);
	}

	@Parameters
	public static Collection<String[]> data(){
		return Arrays.asList(new String[][] {
			{"ariston","Gaston","space","aspa"},
			{"Ariston","gaston","Space","Aspa"},
			{"Ar","ga","Sp","acee"},
			{"riston","Ga","ace","pece"},
			{"sto","Gasto","ce","As"}
		});
	}
	@Test
	public void getMethods(){
		assertEquals(cinemaEs,gestoreCinema.getCinemaEsistenti());
	}
	@Test
	public void setMethods(){
		cinemaEs = new ArrayList<Cinema>();
		cinemaEs.add(cinema2);
		gestoreCinema.setCinemaEsistenti(cinemaEs);
		assertEquals(cinemaEs,gestoreCinema.getCinemaEsistenti());
	}
	@Test
	public void ricercaCinemaTest(){
		cinemaEs = new ArrayList<Cinema>();
		cinemaEs.add(cinema);
		cinemaEs.add(cinema2);
		gestoreCinema = new GestoreCinema(cinemaEs);
		assertEquals(cinema,gestoreCinema.ricercaCinema(input));
		assertEquals(cinema2,gestoreCinema.ricercaCinema(input3));
		assertNotEquals(cinema,gestoreCinema.ricercaCinema(input2));
		assertNotEquals(cinema2,gestoreCinema.ricercaCinema(input4));
	}
}
