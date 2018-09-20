
// TODO: Auto-generated Javadoc
/**
 * La Classe Preventivo, calcola il totale da pagare.
 */
public class Preventivo {

	/** L'id. */
	int id;

	/** Il totale. */
	double totale;

	/**
	 * Instantiates un nuovo preventivo.
	 *
	 * @param id l' id
	 * @param totale il totale
	 */
	public Preventivo(int id, double totale) {
		this.id = id;
		this.totale = totale;
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
	 * 			il nuovo id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets il totale.
	 *
	 * @return il totale
	 */
	public double getTotale() {
		return totale;
	}

	/**
	 * Sets il totale.
	 *
	 * @param totale il nuovo totale
	 */
	public void setTotale(double totale) {
		this.totale = totale;
	}

	/**
	 * Calcola totale.
	 *
	 * @param prezzo
	 *            il prezzo
	 * @return totale in double
	 */
	public double calcolaTotale(double prezzo) {
		totale = totale + prezzo;
		return totale;
	}
}
