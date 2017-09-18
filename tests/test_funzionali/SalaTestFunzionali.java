import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SalaTestFunzionali {
	Sala sala;
	Cinema cinema;
	SQLDataBase sqlDB;
	@Before
	public void setUp() throws Exception {
		sala = new Sala("Sala1",3,3);
		cinema = new Cinema("Odeon","Genova","Via Torti");
		sqlDB = new SQLDataBase();
		sqlDB.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
		sqlDB.creaArrayMappaSala(sala.getNome(), sala.getNumeroFile(), sala.getNumeroPostiPerFila(), "15:00", cinema.getNome());
	}
	//Si riferisce allo scenario principale di selezione del posto per l'acquisto del biglietto e anche agli scenari alternativi di 
	//posto inesistente e sala piena
	@Test
	public void test() throws Exception {
		assertEquals(1,sala.selezionaPosto(1,"15:00",cinema));
		assertEquals(0,sala.selezionaPosto(12, "15:00", cinema));
		for(int i = 1; i<=sala.postiTotali;i++){
			sala.selezionaPosto(i, "15:00", cinema);
		}
		assertEquals(0,sala.selezionaPosto(5, "15:00", cinema));
		
	}
	@After
	public void delete() throws Exception{
		sqlDB.deleteTableMappe(sala.getNome());
	}
}
