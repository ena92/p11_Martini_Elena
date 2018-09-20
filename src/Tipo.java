
// TODO: Auto-generated Javadoc
/**
 * Enum Tipo. Definisce le tipologie di biglietto disponibili, con il prezzo assocciato
 */
public enum Tipo {

	/** Standard. prezzo 8.30 */
	STANDARD(8.30),

	/** Studenti. prezzo 6.00 */
	STUDENTI(6.00),

	/** Bambini. prezzo 5.00*/
	BAMBINI(5.00),

	/** Forze dell'ordine. prezzo 7.00*/
	FORZEORDINE(7.00),

	/** Over65. prezzo 5.00*/
	OVER65(5.00);

	/** Il prezzo. */
	private double prezzo;

	/**
	 * Instantiates un nuovo tipo tipo.
	 *
	 * @param prezzo
	 *            il prezzo
	 */
	private Tipo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Ottieni il prezzo.
	 *
	 * @return il prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

}
