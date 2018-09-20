import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * La classe GestoreCinema. La classe ha il solo scopo di ritornare il cinema
 * cercato se esiste
 */
public class GestoreCinema {

	/** I cinema esistenti. */
	ArrayList<Cinema> cinemaEsistenti;

	/**
	 * Instantiates il nuovo gestore cinema.
	 *
	 * @param cinemaEsistenti
	 *            i cinema esistenti
	 */
	public GestoreCinema(ArrayList<Cinema> cinemaEsistenti) {
		this.cinemaEsistenti = cinemaEsistenti;
	}

	/**
	 * Gets i cinema esistenti.
	 *
	 * @return i cinema esistenti
	 */
	public ArrayList<Cinema> getCinemaEsistenti() {
		return cinemaEsistenti;
	}

	/**
	 * Sets i cinema esistenti.
	 *
	 * @param cinemaEsistenti
	 *            i nuovi cinema esistenti
	 */
	public void setCinemaEsistenti(ArrayList<Cinema> cinemaEsistenti) {
		this.cinemaEsistenti = cinemaEsistenti;
	}

	/**
	 * Ricerca cinema. Cerca il cinema, all'interno della lista di cinema presenti
	 * 
	 * @param cinemaNome
	 *            Il nome del cinema
	 * @return il cinema se esiste. null in caso contrario
	 */
	public Cinema ricercaCinema(String cinemaNome) {
		Iterator<Cinema> iterator = this.cinemaEsistenti.iterator();
		while (iterator.hasNext()) {
			Cinema cinema = iterator.next();
			if (cinema.getNome().toLowerCase().contains(cinemaNome)
					|| cinema.getNome().contains(cinemaNome)) {
				return cinema;
			}
		}
		System.err.println("Il cinema ricercato non esiste");
		return null;
	}
}
