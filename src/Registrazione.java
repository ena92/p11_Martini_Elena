import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class Registrazione.
 * La classe permette l'inserimento di un nuovo utente nel database,
 * si riferisce allo user case: "Registrarsi"
 */
public class Registrazione {
	
	/** Attributi */
	int id;
	Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Registrazione(Cliente cliente){
		this.cliente = cliente;
	}
	/**
	 * Insert data.
	 * La seguente funzione prende i dati del cliente in input e li inserisce nel database
	 * @throws Exception 
	 */
	public int insertData() throws Exception{
		if(formatoPasswordCorretto(cliente.password)){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String data = formatter.format(cliente.dataNascita);
			SQLDataBase db = new SQLDataBase();
			db.creaDataBase();
			db.inserisciUtente(cliente.nome,cliente.nomeutente, cliente.password, cliente.suggerimento,data,cliente.indirizzo);
			return 1;
		} else{
			System.err.println("Password non accettata");
			return 0;
		}
	}
	public Registrazione(){
		
	}
	/**
	 * Formato password corretto.
	 *
	 * @param password the password
	 * @return true, if successful
	 * 
	 * La seguente funzione controlla che la password rispetti i seguenti requisti: avere minimo 8 caratteri,
	 * avere un carattere speciale e contenere almeno un numero e una lettera maiuscola
	 */
	public boolean formatoPasswordCorretto(String password){
		Pattern pattern;
		Matcher matcher;
		String Password_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,}$";
		pattern = Pattern.compile(Password_pattern);
		matcher = pattern.matcher(password);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
}
