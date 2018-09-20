import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PagamentoTest {
	Pagamento pagamento;
	Cliente cliente;
	Biglietto biglietto,biglietto2;
	SQLDataBase sqlDataBase;
	ArrayList<Prenotazione> prList;
	Prenotazione prenotazione,prenotazione2;
	@Before
	public void setUp() throws Exception {
		Sala sala = new Sala(1,"Roof",3,3);
		ArrayList<Programmazione> prog = new ArrayList<Programmazione>();
		prog.add(new Programmazione(1,"15:00",sala));
		prList = new ArrayList<Prenotazione>();
		Film film = new Film(1, "Spiderman",2017,"Azione","120 min",prog);
		Date date = new Date();
		Preventivo preventivo = new Preventivo(1,13.0);
		date = new Date();
		biglietto = new Biglietto("15:00",date,film.getProgrammazione().get(0).sala,film,Tipo.STANDARD);
		ArrayList<Biglietto> bigliettoList = new ArrayList<Biglietto>();
		bigliettoList.add(biglietto);
		prenotazione = new Prenotazione(1,0,bigliettoList);
		prenotazione2 = new Prenotazione(1,1,bigliettoList);
		prList.add(prenotazione);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		cliente = new Cliente (1,"Elena","ena92","Pippo.1992",date,"Via torti","Ciao",true,prList);
		sqlDataBase = new SQLDataBase();
		sqlDataBase.creaDataBase();
		sqlDataBase.inserisciUtente(cliente.getNome(), cliente.getNomeutente(), cliente.getPassword(), cliente.getSuggerimento(), "31/05/1992", cliente.getIndirizzo());
		
		pagamento = new Pagamento(cliente,prenotazione,preventivo);
	}
	@Test
	public void setGetMetodhsTest() throws Exception{
		assertEquals(cliente,pagamento.getCliente());
		assertEquals(prenotazione,pagamento.getPrenotazione());
		assertEquals(13.0,pagamento.getPreventivo().getTotale(),0);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse("31/05/1992");
		Cliente cliente2 = new Cliente(2,"Alice","ena92","Pippo.1992",date,"Via torti","Ciao",true,prList);
		pagamento.setCliente(cliente2);
		pagamento.setPrenotazione(prenotazione2);
		pagamento.setPreventivo(new Preventivo(2,6.5));
		
		assertEquals(cliente2,pagamento.getCliente());
		assertEquals(prenotazione2,pagamento.getPrenotazione());
		assertEquals(6,pagamento.getPreventivo().getTotale(),5);
	}

	@After
	public void delete() throws Exception{
		sqlDataBase.deleteTableUtentiReg();
	}
}