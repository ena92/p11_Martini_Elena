import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class SelezionaPostoTest {
	Sala sala;
	Biglietto biglietto;
	Cinema cinema;
	String [][] mappaSala;
	SQLDataBase db;
	Preventivo preventivo;
	ArrayList<Programmazione> newProg;
	@Before
	public void setUp() throws Exception {
		Date dataOggi = new Date();
		cinema = new Cinema(1,"Ariston", "Sanremo", "Via Matteotti 4");
		sala = new Sala(1,"Stanza1",2,2);
		newProg = new ArrayList<Programmazione>();
		Programmazione programmazione = new Programmazione(1,"15:00",sala);
		newProg.add(programmazione);
		ArrayList<Film> film = new ArrayList<Film>();
		film.add(new Film(1,"Time",2017,"Commedia","120min",newProg));
		cinema.setFilm(film);
		biglietto = new Biglietto(cinema.getFilm().get(0).programmazione.get(0).getOrarioInizio(),dataOggi,sala,cinema.getFilm().get(0),Tipo.STANDARD);
		
		preventivo = new Preventivo(1,0);
		db = new SQLDataBase();
		db.creaDataBaseMappe(sala.getNome(), cinema.getNome(), sala.getPostiTotali());
	}
	//Si riferisce allo scenario principale di Selezione posto e calcolo totale
	@Test
	public void test() throws Exception {
		assertEquals(1,sala.selezionaPosto(1, cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio(), cinema));
		preventivo.setTotale(preventivo.calcolaTotale(biglietto.getTipo().getPrezzo()));
		assertEquals(2,sala.selezionaPosto(2, cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio() , cinema));
		preventivo.setTotale(preventivo.calcolaTotale(biglietto.getTipo().getPrezzo()));
		assertEquals(16.6,preventivo.getTotale(),0);
	}
	//Si riferisce allo scenario alternativo 1a: nessun posto disponibile
	@Test
	public void test1() throws Exception {
		for(int i = 1; i<=sala.postiTotali;i++){
			sala.selezionaPosto(i, "15:00", cinema);
		}
		assertEquals(-1,sala.selezionaPosto(1,cinema.getFilm().get(0).getProgrammazione().get(0).getOrarioInizio(), cinema));
		
	}
	@After
	public void deleteTable() throws Exception{
		db = new SQLDataBase();
		db.deleteTableMappe(sala.getNome());
	}

}
