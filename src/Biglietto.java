import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Biglietto.
 * La classe biglietto ha come unica funziona, quella di ritornare il biglietto per il film selezionato
 */
public class Biglietto {
	
	/**  Attributi. */
	double prezzo;
	
	/** The orario. */
	String orario;
	
	/** The data. */
	Date data;
	
	/** The sala. */
	Sala sala;
	
	/** The film. */
	Film film;
	
	/**
	 * Gets the prezzo.
	 *
	 * @return the prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Sets the prezzo.
	 *
	 * @param prezzo the new prezzo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Gets the orario.
	 *
	 * @return the orario
	 */
	public String getOrario() {
		return orario;
	}

	/**
	 * Sets the orario.
	 *
	 * @param orario the new orario
	 */
	public void setOrario(String orario) {
		this.orario = orario;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Gets the sala.
	 *
	 * @return the sala
	 */
	public Sala getSala() {
		return sala;
	}

	/**
	 * Sets the sala.
	 *
	 * @param sala the new sala
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
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
	 * Instantiates a new biglietto.
	 *
	 * @param prezzo the prezzo
	 * @param orario the orario
	 * @param data the data
	 * @param sala the sala
	 * @param film the film
	 */
	public Biglietto(double prezzo,String orario, Date data, Sala sala, Film film){
		this.prezzo = prezzo;
		this.orario = orario;
		this.data = data;
		this.sala = sala;
		this.film = film;
	}
	
	/**
	 * Visualizza biglietto.
	 *
	 * @return una stringa con i dati del biglietto.
	 */
	public String visualizzaBiglietto(){
		System.out.println("Film: "+this.film.titolo);
		System.out.println("Orario: "+this.orario);
		String dataStringa = new SimpleDateFormat("dd/MM/yyyy").format(this.data);
		System.out.println("Data: "+dataStringa);
		System.out.println("Prezzo per biglietto: "+this.prezzo);
		System.out.println("Sala: "+this.sala.nome);
		return "Film: "+this.film.titolo + "\nOrario: " + this.orario+ "\nData: " + dataStringa+"\nPrezzo: "+this.prezzo+"\nSala: "+this.sala.nome;
	}
}
