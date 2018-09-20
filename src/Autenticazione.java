// TODO: Auto-generated Javadoc
/**
 * La Classe Autenticazione. La classe autenticazione, permette di autenticare
 * un cliente del circuito cinema precedentemente iscritto, permette inoltre di
 * recuperare la password in caso si fosse dimenticata.
 */
public class Autenticazione {
	
	/** Attributi. */
	int id;
	Cliente cliente;

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
	 * Gets cliente.
	 *
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets cliente.
	 *
	 * @param cliente
	 *            il nuovo cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Instantiates una nuova autenticazione.
	 *
	 * @param id 
	 * 			  l'id
	 * @param cliente
	 *            il cliente
	 */
	public Autenticazione(int id, Cliente cliente) {
		this.id = id;
		this.cliente = cliente;
	}

	/**
	 * Verify dati. La seguente funzione verifica che i dati inseriti abbiano
	 * corrispondenza nel database
	 *
	 * @return true, se username e password corrispondono ad un utente registrato, false in caso negativo
	 * @throws Exception
	 *             l' eccezione
	 */
	public boolean verifyDat() throws Exception {
		SQLDataBase db = new SQLDataBase();
		if (db.controlloAutenticazione(this.cliente.nomeutente, this.cliente.password)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Recupera pass. La seguente funzione ritorna la frase di suggerimento
	 * inserita dall'utente in fase di registrazione, se al nome utente e'
	 * associata una autenticazione
	 * 
	 * @return la stringa contente la frase di suggerimento
	 * @throws Exception
	 *             l' eccezione
	 */
	public String recuperaPass() throws Exception {
		SQLDataBase db = new SQLDataBase();
		if (db.usernameNonRegistrata(this.cliente.nomeutente)) {
			return null;
		} else {
			String fraseSuggerimento = db.suggerimentoPass(this.cliente.nomeutente);
			return fraseSuggerimento;
		}
	}

}
