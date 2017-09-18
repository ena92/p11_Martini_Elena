import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;


public class PreventivoTestFunzionali {
	Preventivo preventivo;
	double var1;
	double var2;
	double var3;
	@Before
	public void setUp() throws Exception {
		preventivo = new Preventivo(1,0);
		var1 = 1.0;
		var2 = -1.0;
		var3 = 6.5;
	}
	//Si riferisce allo scenario del calcolo del totale, in realtà una parte di trova anche nella classe SalaTest
	@Test
	public void testCalcolaPrezzo() {
		assertEquals(1, preventivo.calcolaTotale(var1),0);
		assertEquals(0,preventivo.calcolaTotale(var2),0);
		assertEquals(6,preventivo.calcolaTotale(var3),5);
	}

}
