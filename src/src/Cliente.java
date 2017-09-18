import java.util.Date;
// TODO: Auto-generated Javadoc

/**
 * The Class Cliente.
 */
public class Cliente {
	
	/** The nome. */
	String nome;
	
	/** The id. */
	int id;
	
	/** The nomeutente. */
	String nomeutente;
	
	/** The password. */
	String password;
	
	/** The data nascita. */
	Date dataNascita;
	
	/** The indirizzo. */
	String indirizzo;
	
	/** The suggerimento. */
	String suggerimento;
	
	/** The abbonamento. */
	boolean abbonamento = true;
	
	
	/**
	 * Instantiates a new cliente.
	 *
	 * @param nome the nome
	 * @param nomeutente the nomeutente
	 * @param password the password
	 * @param dataNascita the data nascita
	 * @param indirizzo the indirizzo
	 * @param suggerimento the suggerimento
	 */
	public Cliente(int id, String nome, String nomeutente, String password, Date dataNascita, String indirizzo, String suggerimento){
		this.id = id;
		this.nome = nome;
		this.nomeutente = nomeutente;
		this.password = password;
		this.dataNascita = dataNascita;
		this.indirizzo = indirizzo;
		this.suggerimento = suggerimento;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the nomeutente.
	 *
	 * @return the nomeutente
	 */
	public String getNomeutente() {
		return nomeutente;
	}
	
	/**
	 * Sets the nomeutente.
	 *
	 * @param nomeutente the new nomeutente
	 */
	public void setNomeUtente(String nomeutente) {
		this.nomeutente = nomeutente;
	}
	/**
	 * Gets the suggerimento.
	 *
	 * @return the suggerimento
	 */
	public String getSuggerimento() {
		return suggerimento;
	}
	
	/**
	 * Sets the suggerimento.
	 *
	 * @param suggerimento the new suggerimento
	 */
	public void setSuggerimento(String suggerimento) {
		this.suggerimento = suggerimento;
	}
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the data nascita.
	 *
	 * @return the data nascita
	 */
	public Date getDataNascita() {
		return dataNascita;
	}
	
	/**
	 * Sets the data nascita.
	 *
	 * @param dataNascita the new data nascita
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Checks if is abbonamento.
	 *
	 * @return true, if is abbonamento
	 */
	public boolean isAbbonamento() {
		return abbonamento;
	}
	
	/**
	 * Sets the abbonamento.
	 *
	 * @param abbonamento the new abbonamento
	 */
	public void setAbbonamento(boolean abbonamento) {
		this.abbonamento = abbonamento;
	}
	public boolean getAbbonamento(){
		return abbonamento;
	}
	
}
