import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * La Classe Carta. Questa classe, estensione della classe Pagamento, gestisce i pagamenti tramite carta di credito o prepagata
 */
public class Carta extends Pagamento {

	/** Il numero carta. */
	String numeroCarta;

	/** Il numero segreto. */
	int numeroSegreto;

	/** La data scadenza. */
	String dataScadenza;

	/**
	 * Instantiates una nuova carta.
	 *
	 * @param cliente
	 *            il cliente
	 * @param prenotazione
	 *            il prenotazione
	 * @param preventivo
	 *            il preventivo
	 * @param numeroCarta
	 *            il numero carta
	 * @param numeroSegreto
	 *            il numero segreto
	 * @param dataScadenza
	 *            la data scadenza
	 */
	public Carta(Cliente cliente, Prenotazione prenotazione, Preventivo preventivo, String numeroCarta,
			int numeroSegreto, String dataScadenza) {
		super(cliente, prenotazione, preventivo);
		this.numeroCarta = numeroCarta;
		this.numeroSegreto = numeroSegreto;
		this.dataScadenza = dataScadenza;
	}

	/**
	 * Gets il numero carta.
	 *
	 *
	 * @return il numero carta
	 */
	public String getNumeroCarta() {
		return numeroCarta;
	}

	/**
	 * Sets il numero carta.
	 *
	 * @param numeroCarta
	 *            il nuovo numero carta
	 */
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	/**
	 * Gets il numero segreto.
	 *
	 * @return il numero segreto
	 */
	public int getNumeroSegreto() {
		return numeroSegreto;
	}

	/**
	 * Sets il numero segreto.
	 *
	 * @param numeroSegreto
	 *            il nuovo numero segreto
	 */
	public void setNumeroSegreto(int numeroSegreto) {
		this.numeroSegreto = numeroSegreto;
	}

	/**
	 * Gets la data scadenza.
	 *
	 * @return la data scadenza
	 */
	public String getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * Sets la data scadenza.
	 *
	 * @param dataScadenza
	 *            la nuova data scadenza
	 */
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * Paga con carta. il pagamento con carta e' possibile solo se l'utente e' autenticato
	 *
	 * @return un intero: vale 1 se il pagamento e' andato a buon fine, vale 0 se il numero di carta non corrisponde per sintassi ad una carta reale, vale 0 se
	 * il numero segreto non presenta 3 caratteri numerici, vale 0 se la data di scadenza e' precedente al giorno corrente
	 * @throws Exception
	 *             l' eccezione
	 */
	public int pagaConCarta() throws Exception {
		SQLDataBase sqlDataBase = new SQLDataBase();
		dataScadenza = dataScadenza.substring(0, 3) + "20" + dataScadenza.substring(3, 5);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		date = formatter.parse(dataScadenza);
		if (preventivo.totale != 0
				&& sqlDataBase.controlloAutenticazione(this.cliente.nomeutente, this.cliente.password)
				&& prenotazione != null) {
			Pattern pattern;
			Matcher matcher;
			String formatoNumeroCarta = "^[1-9][0-9]{15}";
			pattern = Pattern.compile(formatoNumeroCarta);
			matcher = pattern.matcher(numeroCarta);
			if (!matcher.matches()) {
				System.err.println("Devi inserire 16 numeri");
				return 0;
			}
			if (numeroSegreto > 1000 || numeroSegreto < 99) {
				System.err.println("Il numero inserito e' inesatto deve avere 3 caratteri numerici");
				return 0;
			}
			if (date.getMonth() < new Date().getMonth() && date.getYear() <= new Date().getYear()) {
				System.err.println("Carta scaduta");
				return 0;
			}
			return 1;
		}
		return 0;
	};
}
