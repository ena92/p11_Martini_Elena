import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * La Classe Cinema. La classe cinema, si riferisce agli user case: ricerca
 * cinema e seleziona cinema
 */
public class Cinema {

	/** Attributi. */
	int id;

	/** Il nome. */
	String nome;

	/** La citta'. */
	String city;

	/** L' indirizzo. */
	String indirizzo;
	/** Le sale. */
	ArrayList<Sala> sale;

	/** I film. */
	ArrayList<Film> film;

	/**
	 * Instantiates un nuovo cinema.
	 *
	 * @param id
	 *            l' id
	 * @param nome
	 *            il nome
	 * @param city
	 *            la citta'
	 * @param indirizzo
	 *            l' indirizzo
	 */
	public Cinema(int id, String nome, String city, String indirizzo) {
		this.id = id;
		this.nome = nome;
		this.city = city;
		this.indirizzo = indirizzo;
	}

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
	 * Gets il nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets il nome.
	 *
	 * @param nome
	 *            il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets la citta'.
	 *
	 * @return la citta'
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets la citta'.
	 *
	 * @param city
	 *            la nuova citta'
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets l' indirizzo.
	 *
	 * @return l' indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets l' indirizzo.
	 *
	 * @param indirizzo
	 *            il nuovo indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets le sale.
	 *
	 * @return le sale
	 */
	public ArrayList<Sala> getSale() {
		return sale;
	}

	/**
	 * Sets i film.
	 *
	 * @param film
	 *            i nuovi film
	 */
	public void setFilm(ArrayList<Film> film) {
		this.film = film;
	}

	/**
	 * Gets i film.
	 *
	 * @return i film
	 */
	public ArrayList<Film> getFilm() {
		return this.film;
	}

	/**
	 * Sets le sale.
	 *
	 * @param sale
	 *            le nuove sale
	 */
	public void setSale(ArrayList<Sala> sale) {
		this.sale = sale;
	}

	/**
	 * Ricerca film, ricerca tra i film presenti nel cinema se esiste quello con
	 * il titolo cercato.
	 *
	 * @param filmTitolo
	 *            il titolo del film
	 * @return il film, se esiste e trasmesso nel cinema selezionato
	 */
	public Film ricercaFilm(String filmTitolo) {
		Iterator<Film> iterator = this.film.iterator();
		while (iterator.hasNext()) {
			Film film = iterator.next();
			if (film.getTitolo().toLowerCase().contains(filmTitolo)
					|| film.getTitolo().contains(filmTitolo)) {
				return film;
			}
		}
		return null;
	}
}
