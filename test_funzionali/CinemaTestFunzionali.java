import static org.junit.Assert.*;

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
	Cinema cinema;
	@Parameter(0) public String input;
	@Before
	public void setUp() throws Exception {
		cinema = new Cinema("Odeon","Genova","Via XX");
	}
	@Parameters
	public static Collection<String[]> data(){
		return Arrays.asList(new String[][] {
			{"Od"},
			{"de"},
			{"on"},
			{"Odeon"},
			{"odeon"}
		});
	}
	//Si riferisce allo scenario principale della ricerca del cinema
	@Test
	public void ricercaCinemaTest() {
		assertEquals(cinema, cinema.ricercaCinema(input));
		assertNull(cinema.ricercaCinema("Go"));
	}

}
