
/**
 * La Classe Abbonamento. E' un'estensione della classe Pagamento, gestisce i pagamenti tramite abbonamento
 */
public class Abbonamento extends Pagamento {

	/**
	 * Instantiates un nuovo abbonamento.
	 *
	 * @param cliente
	 *            il cliente
	 * @param prenotazione
	 *            la prenotazione
	 * @param preventivo
	 *            il preventivo
	 */
	public Abbonamento(Cliente cliente, Prenotazione prenotazione, Preventivo preventivo) {
		super(cliente, prenotazione, preventivo);
	}

	/**
	 * Paga con abbonamento. E' possibile pagare con abbonamento solo se l'utente e' autenticato e possiede un abbonamento
	 * 
	 * @return un intero: 0 se l'abbonamento non e' attivo, 1 se l'abbonamento e' attivo
	 * @throws Exception
	 *             l' eccezione
	 */
	public int pagaConAbbonamento() throws Exception {
		SQLDataBase sqlDataBase = new SQLDataBase();
		if (preventivo.totale != 0
				&& sqlDataBase.controlloAutenticazione(this.cliente.nomeutente, this.cliente.password)
				&& prenotazione != null) {
			if (this.cliente.abbonamento) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	};

}
