import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbbonamentoTest {
	Abbonamento abbonamento;
	Cliente cliente;
	Biglietto biglietto,biglietto2;
	SQLDataBase sqlDataBase;
	Prenotazione prenotazione,prenotazione2;
	ArrayList<Prenotazione> prList;
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		Sala sala = new Sala(1,"Roof",3,3);
		prList = new ArrayList<Prenotazione>();
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		Film film = new Film(1, "Spiderman",2017,"Azione","120 min",prog);
		Preventivo preventivo = new Preventivo(1,13.0);
		date = new Date();
		biglietto = new Biglietto("15:00",date,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		prenotazione2 = new Prenotazione(2,1,bigliettoList);
		prList.add(prenotazione);
		prList.add(prenotazione2);
	
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Pippo.1992",true,prList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		
		abbonamento = new Abbonamento(cliente,prenotazione,preventivo);
	}
	@Test
	public void pagaConAbbonamentoTest() throws Exception{
		assertEquals(1,abbonamento.pagaConAbbonamento());
		cliente.setAbbonamento(false);
		assertEquals(0,abbonamento.pagaConAbbonamento());
		abbonamento = new Abbonamento(cliente,prenotazione,new Preventivo(1,0));
		assertEquals(0,abbonamento.pagaConAbbonamento());
	}
	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}
