import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * La Classe Biglietto. La classe biglietto ha una funzione, quella di ritornare il
 * biglietto per il film selezionato
 */
public class Biglietto {
	/** L' orario. */
	String orario;

	/** La data. */
	Date data;

	/** La sala. */
	Sala sala;

	/** Il film. */
	Film film;

	/** Il tipo. */
	Tipo tipo;

	/**
	 * Gets l' orario.
	 *
	 * @return l' orario
	 */
	public String getOrario() {
		return orario;
	}

	/**
	 * Sets l' orario.
	 *
	 * @param orario
	 *            il nuovo orario
	 */
	public void setOrario(String orario) {
		this.orario = orario;
	}

	/**
	 * Gets la data.
	 *
	 * @return la data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Sets la data.
	 *
	 * @param data
	 *            la nuova data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Gets la sala.
	 *
	 * @return la sala
	 */
	public Sala getSala() {
		return sala;
	}

	/**
	 * Sets la sala.
	 *
	 * @param sala
	 *            la nuova sala
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	/**
	 * Gets il film.
	 *
	 * @return il film
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Sets il film.
	 *
	 * @param film
	 *            il nuovo film
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * Gets il tipo.
	 *
	 * @return il tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * Sets il tipo.
	 *
	 * @param tipo
	 *            il nuovo tipo
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * Instantiates un nuovo biglietto.
	 *
	 * @param orario
	 *            l' orario
	 * @param data
	 *            la data
	 * @param sala
	 *            la sala
	 * @param film
	 *            il film
	 * @param tipo
	 *            il tipo
	 */
	public Biglietto(String orario, Date data, Sala sala, Film film, Tipo tipo) {
		this.orario = orario;
		this.data = data;
		this.sala = sala;
		this.film = film;
		this.tipo = tipo;
	}

	/**
	 * Visualizza biglietto.
	 *
	 * @return una stringa con i dati del biglietto.
	 */
	public String visualizzaBiglietto() {
		System.out.println("Film: " + this.film.titolo);
		System.out.println("Orario: " + this.orario);
		String dataStringa = new SimpleDateFormat("dd/MM/yyyy").format(this.data);
		System.out.println("Data: " + dataStringa);
		System.out.println("Prezzo per biglietto: " + this.tipo.getPrezzo());
		System.out.println("Sala: " + this.sala.nome);
		return "Film: " + this.film.titolo + "\nOrario: " + this.orario + "\nData: " + dataStringa + "\nPrezzo: "
				+ this.tipo.getPrezzo() + "\nSala: " + this.sala.nome;
	}
}
