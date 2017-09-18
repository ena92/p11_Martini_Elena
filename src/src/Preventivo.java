
// TODO: Auto-generated Javadoc
/**
 * La Classe Preventivo calcola il preventivo.
 */
public class Preventivo {
	
	
	/** L'id. */
	int id;
	
	/** totale. */
	double totale;
	
	public Preventivo(int id, double totale){
		this.id = id;
		this.totale = totale;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	/**
	 * Calcola totale.
	 *
	 * @param prezzo il prezzo
	 * @return totale in double
	 */
	public double calcolaTotale(double prezzo){
		totale = totale + prezzo;
		return totale;
	}
}
