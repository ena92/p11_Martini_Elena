import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class ModalitaStampaTest {
	Prenotazione prenotazione;
	ArrayList<Biglietto> bigliettoList;
	@Before
	public void setup() throws Exception{
		Sala sala = new Sala(1,"Roof",3,3);
		Date date = new Date();
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		Film film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min",prog);
		Biglietto biglietto = new Biglietto("15:00",date,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,1,bigliettoList);
	}
	@Test
	//Si riferisce allo scenario principale di scelta di ritiro biglietti in cassa
	public void ritiroCassaTest(){
		assertEquals(prenotazione.generazioneCodice(),prenotazione.getCodice());
	}
	@Test
	//Si riferisce allo scenario alternativo di stampa dei biglietti a casa
	public void stampaCasaTest(){
		prenotazione = new Prenotazione(2,0,bigliettoList);
		prenotazione.generazioneCodice();
		assertNull(prenotazione.getCodice());
	}
}
