import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * La Classe Prenotazione. Contiente uno o piu' biglietti acquistati dall'utente, gestisce inoltre la modalita' di stampa scelta dall'utente
 */
public class Prenotazione {

	/** L' id. */
	int id;

	/** La modalita. */
	int modalitaStampa;

	/** Il codice. */
	String codice;

	/** I biglietti. */
	ArrayList<Biglietto> biglietti;
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
	 * Gets la modalita' di stampa.
	 *
	 * @return la modalita' di stampa
	 */
	public int getModalitaStampa() {
		return modalitaStampa;
	}

	/**
	 * Sets la modalita' di stampa.
	 *
	 * @param modalitaStampa
	 *            la nuova modalita' di stampa
	 */
	public void setModalitaStampa(int modalitaStampa) {
		this.modalitaStampa = modalitaStampa;
	}
	/**
	 * Gets il codice.
	 *
	 * @return il codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Sets il codice.
	 *
	 * @param codice
	 *            il nuovo codice
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * Gets i biglietti.
	 *
	 * @return i biglietti
	 */
	public ArrayList<Biglietto> getBiglietti() {
		return biglietti;
	}

	/**
	 * Sets i biglietti.
	 *
	 * @param biglietti
	 *            la nuova lista di biglietti
	 */
	public void setBiglietti(ArrayList<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	/**
	 * Instantiates una nuova prenotazione.
	 *
	 * @param id
	 *            l'id
	 * @param modalitaStampa
	 *            la modalita' di stampa
	 * @param biglietti
	 *            i biglietti
	 */
	public Prenotazione(int id, int modalitaStampa, ArrayList<Biglietto> biglietti) {
		this.id = id;
		this.modalitaStampa = modalitaStampa;
		this.biglietti = biglietti;
	}

	/**
	 * Generazione codice. Se si e' scelto di stampare il biglietto al cinema genera un codice da mostrare
	 *
	 * @return una stringa contenente il codice di prenotazione da mostrare per
	 *         ritirare il biglietto in cassa, null in caso si sia scelto di stampare a casa il/i biglietto/i
	 */
	public String generazioneCodice() {
		String randomStr = null;
		if (modalitaStampa == 1) {
			String possibleCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			Random random = new Random();
			
			StringBuilder sb = new StringBuilder(8);
			for (int i = 0; i < 8; i++) {
				sb.append(possibleCharacters.charAt(random.nextInt(possibleCharacters.length())));
			}
			randomStr = sb.toString();
			this.codice = randomStr;
		}
		return randomStr;
	}

	
}
