// TODO: Auto-generated Javadoc
/**
 * La Classe Pagamento.
 */
public class Pagamento {

	/** Il cliente. */
	Cliente cliente;

	/** La prenotazione. */
	Prenotazione prenotazione;

	/** Il preventivo. */
	Preventivo preventivo;

	/**
	 * Gets il cliente.
	 *
	 * @return il cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets il cliente.
	 *
	 * @param cliente
	 *            il nuovo cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets prenotazione.
	 *
	 * @return la prenotazione
	 */
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	/**
	 * Sets la prenotazione
	 *
	 * @param prenotazione
	 *            la nuova prenotazione
	 */
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	/**
	 * Gets il preventivo.
	 *
	 * @return il preventivo
	 */
	public Preventivo getPreventivo() {
		return preventivo;
	}

	/**
	 * Sets il preventivo.
	 *
	 * @param p
	 *            il nuovo preventivo
	 */
	public void setPreventivo(Preventivo p) {
		this.preventivo = p;
	}

	/**
	 * Instantiates un nuovo pagamento.
	 *
	 * @param cliente
	 *            il cliente
	 * @param prenotazione
	 *            la prenotazione
	 * @param preventivo
	 *            il preventivo
	 */
	public Pagamento(Cliente cliente, Prenotazione prenotazione, Preventivo preventivo) {
		this.preventivo = preventivo;
		this.cliente = cliente;
		this.prenotazione = prenotazione;
	}

}
