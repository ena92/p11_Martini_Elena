
// TODO: Auto-generated Javadoc
/**
 * La Classe Programmazione. gestisce la programmazione del film nella sala
 */
public class Programmazione {

	/** L' id. */
	int id;

	/** L' orario di inizio. */
	String orarioInizio;

	/** La sala. */
	Sala sala;

	/**
	 * Instantiates una nuova programmazione.
	 *
	 * @param id
	 *            l' id
	 * @param orarioInizio
	 *            l' orario inizio
	 * @param sala
	 *            la sala
	 */
	public Programmazione(int id, String orarioInizio, Sala sala) {
		this.id = id;
		this.orarioInizio = orarioInizio;
		this.sala = sala;
	}

	/**
	 * Gets l' orario di inizio.
	 *
	 * @return l' orario di inizio
	 */
	public String getOrarioInizio() {
		return orarioInizio;
	}

	/**
	 * Sets l' orario di inizio.
	 *
	 * @param orarioInizio
	 *            il nuovo orario di inizio
	 */
	public void setOrarioInizio(String orarioInizio) {
		this.orarioInizio = orarioInizio;
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

}
