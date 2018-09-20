import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PrenotazioneTest {
	Prenotazione prenotazione;
	Biglietto biglietto;
	Sala sala;
	Film film;
	Date data;
	ArrayList<Biglietto> bigliettoList;
	@Before
	public void setUp() throws Exception {
		sala = new Sala(1,"ROOF1",3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min",prog);
		data = new Date();
		biglietto = new Biglietto("15:00",data,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		
	}
	@Test
	public void getMethods(){
		assertEquals(1,prenotazione.getId());
		assertEquals(0,prenotazione.getModalitaStampa());
		assertNull(prenotazione.getCodice());
		assertEquals(bigliettoList,prenotazione.getBiglietti());
	}
	@Test
	public void setMethods(){
		prenotazione.setCodice("BFE");
		prenotazione.setId(2);
		prenotazione.setModalitaStampa(1);
		bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(new Biglietto("21:00",data,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD));
		prenotazione.setBiglietti(bigliettoList);
		assertEquals("BFE",prenotazione.getCodice());
		assertEquals(bigliettoList,prenotazione.getBiglietti());
		assertEquals(2,prenotazione.getId());
		assertEquals(1,prenotazione.getModalitaStampa());
	}
	@Test
	public void generazioneCodiceTest(){
		prenotazione.generazioneCodice();
		assertNull(prenotazione.getCodice());
		prenotazione.setModalitaStampa(1);
		assertEquals(prenotazione.generazioneCodice(),prenotazione.getCodice());
	}
}
