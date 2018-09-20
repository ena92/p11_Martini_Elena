import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * La Classe Registrazione. La classe permette l'inserimento di un nuovo utente
 * nel database.
 */
public class Registrazione {

	/**  Attributi. */
	int id;
	
	/** Il cliente. */
	Cliente cliente;

	/**
	 * Gets il cliente.
	 *
	 * @return il cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets il cliente.
	 *
	 * @param cliente il nuovo cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Instantiates una nuova registrazione.
	 *
	 * @param cliente il cliente
	 */
	public Registrazione(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Insert data. La seguente funzione prende i dati del cliente in input e li
	 * inserisce nel database
	 *
	 * @return un intero che vale 1 se operazione andata a buon fine, 0 in caso contratio
	 * @throws Exception l'eccezione
	 */
	public int insertData() throws Exception {
		if (formatoPasswordCorretto(cliente.password) && cliente.condizioni) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String data = formatter.format(cliente.dataNascita);
			SQLDataBase db = new SQLDataBase();
			db.creaDataBase();
			if (db.inserisciUtente(cliente.nome, cliente.nomeutente, cliente.password, cliente.suggerimento, data,
					cliente.indirizzo))
				return 1;
			return -1;
		} else {
			System.err.println("Formato password non corretto o condizioni non accettate");
			return 0;
		}
	}
	/**
	 * Formato password corretto. La seguente funzione controlla che la password rispetti i
	 * seguenti requisti: avere minimo 8 caratteri, 
	 * avere un carattere speciale e contenere almeno un numero e una lettera maiuscola
	 * 
	 * @param password
	 *            la password
	 * @return true, se la password rispetta i requisiti richiesti, false in caso contrario
	 *      
	 */
	public boolean formatoPasswordCorretto(String password) {
		Pattern pattern;
		Matcher matcher;
		String Password_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,}$";
		pattern = Pattern.compile(Password_pattern);
		matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
