import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * La Classe Paypal. e' un'estensione della classe Pagamento, gestisce i pagamenti tramite paypal
 */
public class Paypal extends Pagamento {

	/** La mail del conto paypal. */
	String mail;

	/** La password paypal. */
	String passPaypal;

	/**
	 * Instantiates una nuovo pagamento paypal.
	 *
	 * @param cliente
	 *            il cliente
	 * @param prenotazione
	 *            la prenotazione
	 * @param preventivo
	 *            il preventivo
	 * @param mail
	 *            la mail paypal
	 * @param passPaypal
	 *            la password paypal
	 */
	public Paypal(Cliente cliente, Prenotazione prenotazione, Preventivo preventivo, String mail, String passPaypal) {
		super(cliente, prenotazione, preventivo);
		this.mail = mail;
		this.passPaypal = passPaypal;
	}

	/**
	 * Gets la mail.
	 *
	 * @return la mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets la mail.
	 *
	 * @param mail
	 *            la nuova mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets la password paypal.
	 *
	 * @return la password paypal
	 */
	public String getPassPaypal() {
		return passPaypal;
	}

	/**
	 * Sets la password paypal.
	 *
	 * @param passPaypal
	 *            la nuova password paypal
	 */
	public void setPassPaypal(String passPaypal) {
		this.passPaypal = passPaypal;
	}

	/**
	 * Paga con paypal. Viene controllato se e' stata effettivamente inserita
	 * una mail corretta o meno
	 *
	 * @return un intero che vale 1 se l'operazione e' andata a buon fine, 0 in caso contrario
	 * @throws Exception
	 *             l'eccezione
	 */
	public int pagaConPayPal() throws Exception {
		SQLDataBase sqlDataBase = new SQLDataBase();
		if (preventivo.totale != 0 && sqlDataBase.controlloAutenticazione(cliente.nomeutente, cliente.password)
				&& prenotazione != null) {
			Pattern pattern;
			Matcher matcher;
			// Stringa che serve per verificare se la stringa possa essere una
			// mail(espressione regolare per le email)
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(mail);
			if (!matcher.matches()) {
				System.err.println("Mail con formato non ricosciuto");
				return 0;
			}
			return 1;
		}
		return 0;
	};

}
