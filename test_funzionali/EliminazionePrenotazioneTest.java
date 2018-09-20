import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


	
public class EliminazionePrenotazioneTest {
	Cliente cliente;
	Sala sala;
	Film film;
	Date data;
	Biglietto biglietto;
	ArrayList<Biglietto> bigliettoList;
	ArrayList<Prenotazione> prenotazioniList;
	Prenotazione prenotazione;
	SQLDataBase sqlDataBase;
	Calendar calendar;
	String orarioErrato;
	@Before
	public void setUp() throws Exception {
		calendar = Calendar.getInstance();
		sala = new Sala(1,"ROOF1",3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		String orarioInizios;
		orarioInizios = "15:00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		String cT = calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes();
		Date dateOraCorrente = simpleDateFormat.parse(cT);
		Date orarioInizio = simpleDateFormat.parse(orarioInizios);
		long diff = orarioInizio.getTime() - dateOraCorrente.getTime();
		if(diff <= 30*60*1000){
			orarioErrato = orarioInizios;
			int orarioInt = calendar.getTime().getHours()+2;
			orarioInizios = orarioInt +":00";
		} else{
			orarioErrato = calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes();
		}
		prog.add(new Programmazione(1,orarioInizios,sala));
		film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min",prog);
		data = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataStringa = formatter.format(data);
		biglietto = new Biglietto(prog.get(0).getOrarioInizio(),data,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		prenotazioniList = new ArrayList<Prenotazione>();
		prenotazioniList.add(prenotazione);
		Date date = new Date();
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao",true,prenotazioniList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
	
	}
	//Si riferisce allo scenario principale eliminazione prenotazione
	@Test
	public void eliminazioneRiuscitaTest() throws ParseException{
		assertEquals(0,cliente.eliminazionePrenotazione(1));
	}
	//Si riferisce allo scenario alternativo di eliminazione non riuscita perchè non trovata
	@Test
	public void eliminazioneFallita() throws Exception{
		assertEquals(-1,cliente.eliminazionePrenotazione(0));
	}
	//Si riferisce allo scenario alternativo di eliminazione non riuscita perchè evento prossimo all'inizio
	@Test
	public void elinimazioneFallita() throws Exception{
		biglietto.setOrario(orarioErrato);
		assertEquals(-1,cliente.eliminazionePrenotazione(1));
	}
	
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}

