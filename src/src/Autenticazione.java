import java.util.Random;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Autenticazione.
 * La classe autenticazione, permette di autenticare, un cliente del circuito cinema precedentemente iscritto
 * permette inoltre di recuperare la password in caso si fosse dimenticata.
 * Si riferisce allo user case: "Autenticarsi"
 */
public class Autenticazione {
	
	/** Attributi */
	String nomeUtente;
	String password;
	
	/**
	 * Gets nome utente.
	 *
	 * @return nome utente
	 */
	public String getNomeUtente() {
		return nomeUtente;
	}
	
	/**
	 * Sets nome utente.
	 *
	 * @param nomeUtente the new nome utente
	 */
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	/**
	 * Gets password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Autenticazione(String nomeUtente, String password){
		this.nomeUtente = nomeUtente;
		this.password = password;
	}
	/**
	 * Verify dati.
	 * La seguente funzione verifica che i dati inseriti abbiano corrispondenza nel database
	 * @return true, if successful
	 * @throws Exception 
	 */
	public boolean verifyDat() throws Exception{
		SQLDataBase db = new SQLDataBase();
		if(db.controlloAutenticazione(this.nomeUtente, this.password)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Recupera pass.
	 *La seguente funzione ritorna la frase di suggerimento inserita dall'utente in fase di registrazione se
	 *al nome utente è associata una autenticazione
	 * @param username the username
	 * @return the string
	 * @throws Exception 
	 */
	public String recuperaPass(String username) throws Exception{
		SQLDataBase db = new SQLDataBase();
		if(db.usernameNonRegistrata(username)){
			return null;
		}else{
			String fraseSuggerimento = db.suggerimentoPass(username);
			return fraseSuggerimento;
		}
	}

}
