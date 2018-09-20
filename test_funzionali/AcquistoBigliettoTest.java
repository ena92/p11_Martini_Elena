import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcquistoBigliettoTest{
	Sala sala;
	Pagamento pagamento;
	Cliente cliente;
	Biglietto biglietto;
	Cinema cinema;
	String [][] mappaSala;
	SQLDataBase db;
	Preventivo preventivo;
	ArrayList<Programmazione> newProg;
	Prenotazione prenotazione;
	Carta carta;
	@Before
	public void setup() throws Exception{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		sala = new Sala(1,"Sala1",4,4);
		Date dataOggi = new Date();
		cinema = new Cinema(1,"Ariston", "Sanremo", "Via Matteotti 4");
		newProg = new ArrayList<Programmazione>();
		Programmazione programmazione = new Programmazione(1,"15:00",sala);
		newProg.add(programmazione);
		ArrayList<Film> film = new ArrayList<Film>();
		film.add(new Film(1,"Time",2017,"Commedia","120min",newProg));
		cinema.setFilm(film);
		biglietto = new Biglietto(cinema.getFilm().get(0).programmazione.get(0).getOrarioInizio(),dataOggi,sala,cinema.getFilm().get(0),Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		preventivo = new Preventivo(1,0);
		ArrayList<Prenotazione> prList = new ArrayList<Prenotazione>();
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao",true,prList);
		db = new SQLDataBase();
		db.creaDataBase();
		db.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
		carta = new Carta(cliente,prenotazione,preventivo,"1234567812345678", 123, "08/21");
	}
	//Si riferisce allo scenario principale di Acquisto Biglietto
	@Test
	public void test() throws Exception{
		assertEquals(1,sala.selezionaPosto(1, cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio(), cinema));
		preventivo.setTotale(preventivo.calcolaTotale(biglietto.getTipo().getPrezzo()));
		assertEquals(2,sala.selezionaPosto(2, cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio() , cinema));
		preventivo.setTotale(preventivo.calcolaTotale(biglietto.getTipo().getPrezzo()));
		assertEquals(16.6,preventivo.getTotale(),0);
		pagamento = new Pagamento(cliente,prenotazione,preventivo);
		assertEquals(1,carta.pagaConCarta());
		prenotazione.setModalitaStampa(1);
		assertEquals(prenotazione.generazioneCodice(),prenotazione.getCodice());
	}
	//Si riferisce allo scenario alternativo 1.a di acquisto biglietti convenzionati
	@Test
	public void test2() throws Exception{
		biglietto.setTipo(Tipo.BAMBINI);
		assertEquals(1,sala.selezionaPosto(1, cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio(), cinema));
		preventivo.setTotale(preventivo.calcolaTotale(biglietto.getTipo().getPrezzo()));
		assertEquals(2,sala.selezionaPosto(2, cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio() , cinema));
		preventivo.setTotale(preventivo.calcolaTotale(biglietto.getTipo().getPrezzo()));
		assertEquals(10,preventivo.getTotale(),0);
		pagamento = new Pagamento(cliente,prenotazione,preventivo);
		assertEquals(1,carta.pagaConCarta());
		prenotazione.setModalitaStampa(1);
		assertEquals(prenotazione.generazioneCodice(),prenotazione.getCodice());
	}
	@After
	public void deleteTable() throws Exception{
		db = new SQLDataBase();
		db.deleteTableMappe(sala.getNome());
		db.deleteTableUtentiReg();
	}

}
