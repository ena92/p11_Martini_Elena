import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	Cliente cliente;
	Date data, data2;
	Biglietto biglietto;
	ArrayList<Prenotazione> prenotazioni, prenotazioni2;
	@Before
	public void setUp() throws Exception {
		Sala sala = new Sala(1,"ROOF1",3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"12:00",sala));
		Film film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min",prog);
		biglietto = new Biglietto(prog.get(0).getOrarioInizio(),data,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(new Prenotazione(1,0,bigliettoList));
		prenotazioni2 = new ArrayList<Prenotazione>();
		prenotazioni2.add(new Prenotazione(1,1,bigliettoList));
		String dataNascita = "31/05/1992";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = formatter.parse(dataNascita);
		cliente = new Cliente(1,"Elena","ena92","Pippo.1992",data,"Via GrossiBianchi","Pippo.1992",true,prenotazioni);
		data2 = formatter.parse("27/01/1994");
	}

	@Test
	public void setMethodsTest() {
		cliente.setId(2);
		cliente.setNome("Alice");
		cliente.setNomeUtente("alice1");
		cliente.setPassword("Alice.1994");
		cliente.setIndirizzo("via baracca");
		cliente.setDataNascita(data2);
		cliente.setSuggerimento("Alice.1994");
		cliente.setPrenotazioni(prenotazioni2);
		cliente.setAbbonamento(false);
		cliente.setCondizioni(false);
		assertEquals(2,cliente.getId());
		assertEquals("Alice",cliente.getNome());
		assertEquals("alice1",cliente.getNomeutente());
		assertEquals("via baracca", cliente.getIndirizzo());
		assertEquals(data2,cliente.getDataNascita());
		assertEquals("Alice.1994", cliente.getPassword());
		assertEquals("Alice.1994", cliente.getSuggerimento());
		assertEquals(prenotazioni2, cliente.getPrenotazioni());
		assertFalse(cliente.getAbbonamento());
		assertFalse(cliente.getCondizioni());
	}
	@Test
	public void getMethodsTest(){
		assertEquals(1,cliente.getId());
		assertEquals("Elena",cliente.getNome());
		assertEquals("ena92",cliente.getNomeutente());
		assertEquals("Via GrossiBianchi", cliente.getIndirizzo());
		assertEquals(data,cliente.getDataNascita());
		assertEquals("Pippo.1992", cliente.getPassword());
		assertEquals("Pippo.1992", cliente.getPassword());
		assertTrue(cliente.getAbbonamento());
		assertEquals(prenotazioni, cliente.getPrenotazioni());
		assertTrue(cliente.getCondizioni());
	}
	@Test
	public void isAbbonamentoTest(){
		assertTrue(cliente.isAbbonamento());
	}
	@Test
	public void eliminazionePrenotazioneTest() throws ParseException{
		Calendar calendar = Calendar.getInstance();
		Date dateOraCorrente = null;
	    Date dateOraInizio = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		String orario = biglietto.getOrario();
		String cT = calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes();
		dateOraCorrente = simpleDateFormat.parse(cT);
		dateOraInizio = simpleDateFormat.parse(orario);
		long diff = dateOraInizio.getTime() - dateOraCorrente.getTime();
		assertEquals(-1,cliente.eliminazionePrenotazione(0));
		if(diff >=30*60*1000){
			assertEquals(0,cliente.eliminazionePrenotazione(1));
			biglietto.setOrario(cT);
			assertEquals(-1,cliente.eliminazionePrenotazione(1));
		}else{
			assertEquals(-1,cliente.eliminazionePrenotazione(1));
			int orarioInt = calendar.getTime().getHours()+2;
			cT = orarioInt +":00";
			biglietto.setOrario(cT);
			assertEquals(0,cliente.eliminazionePrenotazione(1));
		}
	}

}
