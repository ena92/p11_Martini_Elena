import static org.junit.Assert.*;

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
public class CinemaTestFunzionali {
	Film film;
	Cinema cinema;
	GestoreCinema gestoreCinema;
	@Parameter(0) public String input;
	@Parameter(1) public String input1;
	@Before
	public void setUp() throws Exception {
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",new Sala(1,"Roof",2,2)));
		film = new Film(1,"Spy",2017,"Commedia","120min",prog);
		cinema = new Cinema(1,"Odeon","Genova","Via XX");
		ArrayList<Film> Afilm = new ArrayList<Film>();
		Afilm.add(film);
		cinema.setFilm(Afilm);
		ArrayList<Cinema> cinemaAR = new ArrayList<Cinema>();
		cinemaAR.add(cinema);
		gestoreCinema = new GestoreCinema(cinemaAR);
	}
	@Parameters
	public static Collection<String[]> data(){
		return Arrays.asList(new String[][] {
			{"Od","Sp"},
			{"de","Spy"},
			{"on","spy"},
			{"Odeon","p"},
			{"odeon","py"}
		});
	}
	//Si riferisce allo scenario principale della ricerca del cinema e selezione e ricerca film all'interno del cinema 
	@Test
	public void ricercaCinemaTest() {
		assertEquals(cinema, gestoreCinema.ricercaCinema(input));
		assertEquals(film,gestoreCinema.ricercaCinema(input).ricercaFilm(input1));
	}
	//Si riferisce allo scenario alternativo 6a cinema non trovato e film non trovato
		@Test
		public void test() {
			
			assertNull(gestoreCinema.ricercaCinema("Go"));
			assertNull(cinema.ricercaFilm("Ap"));
		}

}
