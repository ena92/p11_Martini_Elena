import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BigliettoTest {
	Biglietto biglietto;
	Sala sala;
	Film film;
	Cinema cinema;
	Date data;
	String dataStringa;
	@Before
	public void setUp() throws Exception {
		sala = new Sala(1,"ROOF1",3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min",prog);
		data = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dataStringa = formatter.format(data);
		biglietto = new Biglietto("15:00",data,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
	}

	@Test
	public void getMethods() {
		assertEquals(data, biglietto.getData());
		assertEquals(8.30,biglietto.tipo.getPrezzo(),0.5);
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
		biglietto.setTipo(Tipo.BAMBINI);
		assertEquals(data, biglietto.getData());
		assertEquals(Tipo.BAMBINI,biglietto.getTipo());
		assertEquals(5.00,biglietto.tipo.getPrezzo(),0);
		assertEquals(film,biglietto.getFilm());
		assertEquals(sala,biglietto.getSala());
		assertEquals("15:00",biglietto.getOrario());
		biglietto.setTipo(Tipo.FORZEORDINE);
		assertEquals(7.00,biglietto.tipo.getPrezzo(),0);
		biglietto.setTipo(Tipo.OVER65);
		assertEquals(5.00,biglietto.tipo.getPrezzo(),0);
		biglietto.setTipo(Tipo.STUDENTI);
		assertEquals(6.00,biglietto.tipo.getPrezzo(),0);
	}
	
	@Test
	public void visualizzaBigliettoTest(){
		String stringa = "Film: "+biglietto.getFilm().getTitolo() + "\nOrario: " + biglietto.getOrario()+ "\nData: " +dataStringa +"\nPrezzo: "+biglietto.tipo.getPrezzo()+"\nSala: "+biglietto.getSala().getNome();
		assertEquals(stringa,biglietto.visualizzaBiglietto());
	}
}
