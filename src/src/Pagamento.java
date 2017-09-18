import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class Pagamento.
 * La classe contiene i tre diversi tipi di pagamento selezionabili, si riferisce allo user case: "scegli metodo di pagamento".
 */
public class Pagamento {
	
	/** The cliente. */
	Cliente cliente;
	
	/** The film. */
	Film film;
	
	/** The totale. */
	double totale;
	
	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Sets the cliente.
	 *
	 * @param cliente the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Gets the film.
	 *
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}
	
	/**
	 * Sets the film.
	 *
	 * @param film the new film
	 */
	public void setFilm(Film film) {
		this.film = film;
	}
	
	/**
	 * Gets the totale.
	 *
	 * @return the totale
	 */
	public double getTotale() {
		return totale;
	}
	
	/**
	 * Sets the totale.
	 *
	 * @param totale the new totale
	 */
	public void setTotale(double totale) {
		this.totale = totale;
	}
	
	/**
	 * Instantiates a new pagamento.
	 *
	 * @param cliente the cliente
	 * @param film the film
	 * @param totale the totale
	 */
	public Pagamento(Cliente cliente, Film film, double totale){
		this.totale = totale;
		this.cliente = cliente;
		this.film = film;
	}
	
	/**
	 * Paga con carta. Nel pagamento con carta vengono controllati se i dati inviati siano corretti o meno
	 *
	 * @param numeroCarta the numero carta
	 * @param numeroSegreto the numero segreto
	 * @param dataScadenza the data scadenza
	 * @return 1 se l'operazione è andata a buon fine, 0 in caso contrario
	 * @throws Exception the exception
	 */
	public int pagaConCarta(String numeroCarta, int numeroSegreto, String dataScadenza) throws Exception{
		SQLDataBase sqlDataBase = new SQLDataBase();
		if(totale != 0 && sqlDataBase.controlloAutenticazione(cliente.nomeutente, cliente.password) && film != null){
			Pattern pattern;
			Matcher matcher;
			String formatoNumeroCarta = "^[1-9][0-9]{15}";
			pattern = Pattern.compile(formatoNumeroCarta);
			matcher = pattern.matcher(numeroCarta);
			if(!matcher.matches()){
				System.err.println("Hai inserito meno di 16 numeri");
				return 0;
			}
			if(numeroSegreto > 1000 || numeroSegreto<99){
				System.err.println("Il numero inserito è inesatto deve avere 3 caratteri numerici");
				return 0;
			}
			return 1;
		}
		return 0;
	};
	
	/**
	 * Paga con pay pal. Viene controllato se è stata effettivamente inserita una mail o meno
	 *
	 * @param mail the mail
	 * @param passPaypal the pass paypal
	 * @return 1 se l'operazione è andata a buon fine, 0 in caso contrario
	 * @throws Exception the exception
	 */
	public int pagaConPayPal(String mail, String passPaypal) throws Exception{
		SQLDataBase sqlDataBase = new SQLDataBase();
		if(totale != 0 && sqlDataBase.controlloAutenticazione(cliente.nomeutente, cliente.password) && film != null){
			Pattern pattern;
			Matcher matcher;
			//Stringa che serve per verificare se la stringa possa essere una mail(espressione regolare per le email) 
			String EMAIL_PATTERN =
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(mail);
			if(!matcher.matches()){
				System.err.println("Mail con formato non ricosciuto");
				return 0;
			}
			return 1;
		}
		return 0;
	};
	
	/**
	 * Paga con abbonamento.
	 *
	 * @param cliente the cliente
	 * @return un intero: 0 se l'abbonamento non è attivo, 1 se l'abbonamento è attivo
	 * @throws Exception the exception
	 */
	public int pagaConAbbonamento(Cliente cliente) throws Exception{
		SQLDataBase sqlDataBase = new SQLDataBase();
		if(totale != 0 && sqlDataBase.controlloAutenticazione(cliente.nomeutente, cliente.password) && film != null){
			if(cliente.abbonamento){
				System.out.println("Abbonamento attivo, pagamento effettuato");
				return 1;
			}else{
				System.out.println("Pagamento con abbonamento non disponibile");
				return 0;
			}	
		}
		return 0;
	};
	

}
