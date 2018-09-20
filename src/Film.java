import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe Film. La classe descrive gli attributi dei film
 */
public class Film {

	/** Attributi. */
	int id;

	/** Il titolo. */
	String titolo;

	/** L' anno pubblicazione. */
	int annoPubblicazione;

	/** Il tipo. */
	String tipo;

	/** La durata. */
	String durata;

	/**
	 * Le stelle. Sono settate a 5 per ogni film dal momento che non è stata
	 * implementata una funzione per l'aggiunta recensione
	 */
	int stelle = 5;

	/** La programmazione. */
	ArrayList<Programmazione> programmazione;

	/**
	 * Instantiates un nuovo film.
	 *
	 * @param id
	 *            l' id
	 * @param titolo
	 *            il titolo
	 * @param annoPubblicazione
	 *            l' anno pubblicazione
	 * @param tipo
	 *            il tipo
	 * @param durata
	 *            la durata
	 * @param programmazione
	 * 			  le programmazioni del film
	 */
	public Film(int id, String titolo, int annoPubblicazione, String tipo, String durata,
			ArrayList<Programmazione> programmazione) {
		this.id = id;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.tipo = tipo;
		this.durata = durata;
		this.programmazione = programmazione;
	};

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
	 * Gets il titolo.
	 *
	 * @return il titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Sets il titolo.
	 *
	 * @param titolo
	 *            il nuovo titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Gets l' anno di pubblicazione.
	 *
	 * @return l' anno di pubblicazione
	 */
	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	/**
	 * Sets l'anno di pubblicazione.
	 *
	 * @param annoPubblicazione
	 *            il nuovo anno di pubblicazione
	 */
	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	/**
	 * Gets il tipo.
	 *
	 * @return il tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets il tipo.
	 *
	 * @param tipo
	 *            il nuovo tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets la durata.
	 *
	 * @return la durata
	 */
	public String getDurata() {
		return durata;
	}

	/**
	 * Sets la durata.
	 *
	 * @param durata
	 *            la nuova durata
	 */
	public void setDurata(String durata) {
		this.durata = durata;
	}

	/**
	 * Gets le stelle.
	 *
	 * @return le stelle
	 */
	public int getStelle() {
		return stelle;
	}

	/**
	 * Sets le stelle.
	 *
	 * @param stelle
	 *            le nuove stelle
	 */
	public void setStelle(int stelle) {
		this.stelle = stelle;
	}

	/**
	 * Gets la programmazione.
	 *
	 * @return la programmazione
	 */
	public ArrayList<Programmazione> getProgrammazione() {
		return programmazione;
	}

	/**
	 * Sets la programmazione.
	 *
	 * @param programmazione
	 *            la nuova programmazione
	 */
	public void setProgrammazione(ArrayList<Programmazione> programmazione) {
		this.programmazione = programmazione;
	}

	/**
	 * Scheda film.
	 *
	 * @return la scheda del film intesa come: genere, durata, anno di
	 *         pubblicazione, media stelle
	 */
	public ArrayList<String> schedaFilm() {
		ArrayList<String> dettagli = new ArrayList<String>();
		dettagli.add(this.tipo);
		dettagli.add(this.durata);
		dettagli.add(String.valueOf(this.annoPubblicazione));
		dettagli.add(String.valueOf(this.stelle));
		return dettagli;

	}
}
