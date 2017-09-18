import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Film.
 * La classe si riferisce allo user case ricerca film
 */
public class Film {
	
	/** Attributi */
	int id;
	String titolo;
	int annoPubblicazione;
	String tipo;
	String durata;
	/**
	 * Instantiates a new film.
	 *
	 * @param id the id
	 * @param titolo the titolo
	 * @param annoPubblicazione the anno pubblicazione
	 * @param tipo the tipo
	 * @param durata the durata
	 */
	public Film(int id, String titolo, int annoPubblicazione, String tipo, String durata){
		this.id = id;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.tipo = tipo;
		this.durata = durata;
	};
	
	/**
	 * Instantiates a new film.
	 */
	public Film() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the titolo.
	 *
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * Sets the titolo.
	 *
	 * @param titolo the new titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	/**
	 * Gets the anno pubblicazione.
	 *
	 * @return the anno pubblicazione
	 */
	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}
	
	/**
	 * Sets the anno pubblicazione.
	 *
	 * @param annoPubblicazione the new anno pubblicazione
	 */
	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Gets the durata.
	 *
	 * @return the durata
	 */
	public String getDurata() {
		return durata;
	}
	
	/**
	 * Sets the durata.
	 *
	 * @param durata the new durata
	 */
	public void setDurata(String durata) {
		this.durata = durata;
	}
	/**
	 * Ricerca film, ricerca tra i film presenti nel cinema se esiste quello con il titolo cercato
	 * @param cinema the cinema
	 * @param filmTitolo the film titolo
	 * @return the film, se esiste e trasmesso nel cinema selezionato
	 */
	public Film ricercaFilm(Cinema cinema, String filmTitolo){
		for(int i = 0; i<cinema.film.size(); i++){
			if(cinema.film.get(i).getTitolo().toLowerCase().contains(filmTitolo) || cinema.film.get(i).getTitolo().contains(filmTitolo)){
				return cinema.film.get(i);
			} 
		}
		System.out.println("Nessun film con il seguente titolo presente");
		return null;
	}
}
