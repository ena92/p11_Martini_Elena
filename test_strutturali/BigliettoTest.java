import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BigliettoTest {
	Biglietto biglietto;
	Sala sala;
	Film film;
	Date data;
	String dataStringa;
	@Before
	public void setUp() throws Exception {
		sala = new Sala("ROOF1",3,3);
		film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min");
		data = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dataStringa = formatter.format(data);
		biglietto = new Biglietto(6.50,"15:00",data,sala,film);
	}

	@Test
	public void getMethods() {
		assertEquals(data, biglietto.getData());
		assertEquals(6.50,biglietto.getPrezzo(),0.5);
		assertEquals(film,biglietto.getFilm());
		assertEquals(sala,biglietto.getSala());
		assertEquals("15:00",biglietto.getOrario());
	}
	@Test
	public void setMethods(){
		biglietto.setData(data);
		biglietto.setFilm(film);
		biglietto.setOrario("15:00");
		biglietto.setSala(sala);
		biglietto.setPrezzo(6.50);
		assertEquals(data, biglietto.getData());
		assertEquals(6.50,biglietto.getPrezzo(),0.5);
		assertEquals(film,biglietto.getFilm());
		assertEquals(sala,biglietto.getSala());
		assertEquals("15:00",biglietto.getOrario());
		
	}
	@Test
	public void visualizzaBigliettoTest(){
		String stringa = "Film: "+biglietto.getFilm().getTitolo() + "\nOrario: " + biglietto.getOrario()+ "\nData: " +dataStringa +"\nPrezzo: "+biglietto.getPrezzo()+"\nSala: "+biglietto.getSala().getNome();
		assertEquals(stringa,biglietto.visualizzaBiglietto());
	}
}
