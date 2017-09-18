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
public class PreventivoTest {
	Preventivo preventivo;
	@Parameter(0) public double expected;
	 @Parameter(1) public double input1;
	 @Parameter(2) public double input2;
	 @Parameter(3) public double ris;
	double totale;
	@Before
	public void setUp() throws Exception {
		//totale = 6.50;
		preventivo = new Preventivo(1,input1);
	}
	@Parameters
	public static Collection<Double[]> data(){
		return Arrays.asList(new Double[][] {
			{13.0,6.5,6.5,6.5},
			{19.5,13.0,6.5,13.0}
			
		});
	}
	@Test
	public void setMethodsTest() {
		preventivo.setId(2);
		preventivo.setTotale(7.0);
		assertEquals(2,preventivo.getId());
		assertEquals(7.0,preventivo.getTotale(),0);
	}
	@Test
	public void getMehodsTest(){
		assertEquals(1,preventivo.getId());
		assertEquals(ris,preventivo.getTotale(),0.5);
	}
	@Test
	public void calcolaTotaleTest(){
		assertEquals(expected,preventivo.calcolaTotale(input2),0);
	}
}
