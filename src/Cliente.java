import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
// TODO: Auto-generated Javadoc

/**
 * La Class Cliente. Serve per inizializzare un Cliente e presenta una funzione per l'eliminazione di una prenotazione
 */
public class Cliente {

	/** Il nome. */
	String nome;

	/** L' id. */
	int id;

	/** Il nomeutente. */
	String nomeutente;

	/** La password. */
	String password;

	/** La data di nascita. */
	Date dataNascita;

	/** L' indirizzo. */
	String indirizzo;

	/** Il suggerimento. */
	String suggerimento;

	/** L' abbonamento. */
	boolean abbonamento = true;
	/** Le condizioni. */
	boolean condizioni = false;

	/** Le prenotazioni. */
	ArrayList<Prenotazione> prenotazioni;

	/**
	 * Instantiates un nuovo cliente.
	 *
	 * @param id
	 *            l' id
	 * @param nome
	 *            il nome
	 * @param nomeutente
	 *            il nomeutente
	 * @param password
	 *            la password
	 * @param dataNascita
	 *            la data nascita
	 * @param indirizzo
	 *            l' indirizzo
	 * @param suggerimento
	 *            il suggerimento
	 * @param condizioni
	 *            le condizioni
	 * @param prenotazioni
	 * 			  le prenotazioni
	 */
	public Cliente(int id, String nome, String nomeutente, String password, Date dataNascita, String indirizzo,
			String suggerimento, boolean condizioni, ArrayList<Prenotazione> prenotazioni) {
		this.id = id;
		this.nome = nome;
		this.nomeutente = nomeutente;
		this.password = password;
		this.dataNascita = dataNascita;
		this.indirizzo = indirizzo;
		this.suggerimento = suggerimento;
		this.condizioni = condizioni;
		this.prenotazioni = prenotazioni;
	}

	/**
	 * Gets il nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets il nome.
	 *
	 * @param nome
	 *            il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets l' id.
	 *
	 * @return l' id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets l' id.
	 *
	 * @param id
	 *            il nuovo id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets il nome utente.
	 *
	 * @return il nome utente
	 */
	public String getNomeutente() {
		return nomeutente;
	}

	/**
	 * Sets il nome utente.
	 *
	 * @param nomeutente
	 *            the new nome utente
	 */
	public void setNomeUtente(String nomeutente) {
		this.nomeutente = nomeutente;
	}

	/**
	 * Gets il suggerimento.
	 *
	 * @return il suggerimento
	 */
	public String getSuggerimento() {
		return suggerimento;
	}

	/**
	 * Sets il suggerimento.
	 *
	 * @param suggerimento
	 *            il nuovo suggerimento
	 */
	public void setSuggerimento(String suggerimento) {
		this.suggerimento = suggerimento;
	}

	/**
	 * Gets la password.
	 *
	 * @return la password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets la password.
	 *
	 * @param password
	 *            la nuova password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets la data di nascita.
	 *
	 * @return la data di nascita
	 */
	public Date getDataNascita() {
		return dataNascita;
	}

	/**
	 * Sets la data di nascita.
	 *
	 * @param dataNascita
	 *            la nuova data di nascita
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * Gets l'indirizzo.
	 *
	 * @return l' indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets l' indirizzo.
	 *
	 * @param indirizzo
	 *            il nuovo indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Controllo se il cliente e' abbonamento.
	 *
	 * @return true, se e' abbonamento
	 */
	public boolean isAbbonamento() {
		return abbonamento;
	}

	/**
	 * Sets l' abbonamento.
	 *
	 * @param abbonamento
	 *            il nuovo abbonamento
	 */
	public void setAbbonamento(boolean abbonamento) {
		this.abbonamento = abbonamento;
	}

	/**
	 * Gets l' abbonamento.
	 *
	 * @return l' abbonamento
	 */
	public boolean getAbbonamento() {
		return abbonamento;
	}

	/**
	 * Sets le condizioni.
	 *
	 * @param condizioni
	 *            le nuove condizioni
	 */
	public void setCondizioni(boolean condizioni) {
		this.condizioni = condizioni;
	}

	/**
	 * Gets le condizioni.
	 *
	 * @return le condizioni
	 */
	public boolean getCondizioni() {
		return condizioni;
	}

	/**
	 * Gets le prenotazioni.
	 *
	 * @return le prenotazioni
	 */
	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	/**
	 * Sets le prenotazioni.
	 *
	 * @param prenotazioni
	 *            le nuove prenotazioni
	 */
	public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	/**
	 * Funzione per permettere all'utente di eliminare una prenotazione la cui ora di inizio e' superiore a 30 min
	 * 
	 * @param id
	 * 			l'identificativo univoco della prenotazione
	 * @throws ParseException 
	 * 			l'eccezione
	 * 
	 * @return un intero: vale 0 se l'eliminazione e' andata a buon fine -1 in caso contrario
	 */
	public int eliminazionePrenotazione(int id) throws ParseException{
		Iterator<Prenotazione> iteratore = this.prenotazioni.iterator();
		while(iteratore.hasNext()){
			Prenotazione prenotazione = iteratore.next();
			if(prenotazione.getId() == id){
				Calendar calendar = Calendar.getInstance();
				Date dateOraCorrente = null;
			    Date dateOraInizio = null;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
				String orario = prenotazione.getBiglietti().get(0).getOrario();
				String cT = calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes();
				dateOraCorrente = simpleDateFormat.parse(cT);
				dateOraInizio = simpleDateFormat.parse(orario);
				long diff = dateOraInizio.getTime() - dateOraCorrente.getTime();
				if(diff >=  30 * 60 * 1000){
					iteratore.remove();
					return 0;
				}
				return -1;
			}
		}
		return -1;
	}
}
