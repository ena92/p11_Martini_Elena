import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VisualizzaElencoPrenotazioniTest {
	Biglietto biglietto;
	Sala sala;
	Film film;
	Date data;
	String dataStringa;
	String stringa;
	Prenotazione prenotazione;
	ArrayList<Biglietto> bigliettoList;
	ArrayList<Prenotazione> prenotazioniList;
	SQLDataBase sqlDataBase;
	Cliente cliente;
	@Before
	public void setUp() throws Exception {
		sala = new Sala(1,"ROOF1",3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		film = new Film(1,"I Guardiani della Galassia", 2016, "Avventura", "120 min",prog);
		data = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataStringa = formatter.format(data);
		biglietto = new Biglietto("15:00",data,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
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
	//Si riferisce allo scenario principale di visualizzazione acquisti
	@Test
	public void visualizzaBigliettoTest(){
		ArrayList<Prenotazione> acquistiEffettuati = new ArrayList<Prenotazione>();
		acquistiEffettuati = prenotazioniList;
		cliente.setPrenotazioni(prenotazioniList);
		assertEquals(acquistiEffettuati,cliente.getPrenotazioni());
	}
	//Si riferisce allo scenario alternativo di nessun acquisto trovato
	@Test
	public void nessunAcquisto(){
		prenotazioniList = null;
		cliente.setPrenotazioni(prenotazioniList);
		assertNull(cliente.getPrenotazioni());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
