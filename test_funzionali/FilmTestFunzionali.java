import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FilmTestFunzionali {
	Film film;
	Cinema cinema;
	@Parameter(0) public String input;
	@Before
	public void setUp() throws Exception {
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"21:00",new Sala(1,"Roof",2,2)));
		film = new Film(1,"Spy",2017,"Commedia","120min",prog);
		cinema = new Cinema(1,"Odeon","Genova","Via Torti");
		ArrayList<Film> arrayListF = new ArrayList<Film>();
		arrayListF.add(film);
		cinema.setFilm(arrayListF);
	}
	@Parameters
	public static Collection<String[]> data(){
		return Arrays.asList(new String[][] {
			{"Sp"},
			{"Spy"},
			{"spy"},
			{"p"},
			{"py"}
		});
	}
	//Si rifersice allo scenario principale accedere alla scheda del film
	@Test
	public void schedaFilm(){
		ArrayList<String> scheda = new ArrayList<String>(); 
		scheda = film.schedaFilm();
		ArrayList<String> scheda2 = new ArrayList<String>();
		scheda2.add(film.getTipo());
		scheda2.add(film.getDurata());
		scheda2.add(String.valueOf(film.getAnnoPubblicazione()));
		scheda2.add(String.valueOf(film.getStelle()));
		assertEquals(scheda2,scheda);
	}
	
}
