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
		film = new Film(1,"Spy",2017,"Commedia","120min");
		cinema = new Cinema("Odeon","Genova","Via Torti");
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
	//Si riferisce allo scenario principale di ricerca film
	@Test
	public void test() {
		assertEquals(film,film.ricercaFilm(cinema, input));
		assertNull(film.ricercaFilm(cinema, "Ap"));
	}

}
