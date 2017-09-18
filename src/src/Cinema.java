import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Cinema.
 * La classe cinema, si riferisce agli user case: ricerca cinema e seleziona cinema
 */
public class Cinema {
	
	/** Attributi */
	String nome;
	String city;
	String indirizzo;
	/** The sale. */
	ArrayList<Sala> sale;
	
	/** The film. */
	ArrayList<Film> film;
	
	/**
	 * Instantiates a new cinema.
	 *
	 * @param nome the nome
	 * @param city the city
	 * @param indirizzo the indirizzo
	 */
	public Cinema(String nome, String city, String indirizzo){
		this.nome = nome;
		this.city = city;
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Instantiates a new cinema.
	 */
	public Cinema() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Gets the sale.
	 *
	 * @return the sale
	 */
	public ArrayList<Sala> getSale() {
		return sale;
	}
	
	/**
	 * Sets the film.
	 *
	 * @param film the new film
	 */
	public void setFilm(ArrayList<Film> film) {
		this.film = film;
	}
	/**
	 * Gets the film.
	 *
	 * @return the film
	 */
	public ArrayList<Film> getFilm() {
		return this.film;
	}
	
	/**
	 * Sets the sale.
	 *
	 * @param sale the new sale
	 */
	public void setSale(ArrayList<Sala> sale) {
		this.sale = sale;
	}
	/**
	 * Ricerca cinema.
	 * @param cinemaNome String
	 * @return il cinema trovato se esiste.
	 */
	public Cinema ricercaCinema(String cinemaNome){	
		if(this.getNome().toLowerCase().contains(cinemaNome) || this.getNome().contains(cinemaNome)){
			return this;
		}else {
			return null;
		}
	}
}
