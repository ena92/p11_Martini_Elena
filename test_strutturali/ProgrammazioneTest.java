import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class ProgrammazioneTest {
	Programmazione programmazione;
	Sala sala,sala2;
	@Before
	public void setUp() throws Exception {
		sala = new Sala(1,"ROOF1",2,2);
		sala2 = new Sala(2,"ROOF2",4,4);
		programmazione = new Programmazione(1,"21:00",sala2);
	}
	@Test
	public void setMethodsTest() {
		programmazione.setSala(sala);
		programmazione.setOrarioInizio("15:00");
		assertEquals(sala,programmazione.getSala());
		assertEquals("15:00",programmazione.getOrarioInizio());
	}
	@Test
	public void getMethodsTest() {
		assertEquals(sala2,programmazione.getSala());
		assertEquals("21:00",programmazione.getOrarioInizio());
	}
}
