import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Sala.
 * La classe sala, si riferisce allo user case: "seleziona posto"
 */
public class Sala {
	
	/** The nome. */
	String nome;
	
	/** The numero file. */
	int numeroFile;
	
	/** The numero posti per fila. */
	int numeroPostiPerFila;
	
	/** The posti totali. */
	int postiTotali;
	
	/** The posti occupati. */
	int postiOccupati = 0;
	
	
	
	/** The db. */
	SQLDataBase db = new SQLDataBase();
	
	/**
	 * Instantiates a new sala.
	 *
	 * @param nome the nome
	 * @param numeroFile the numero file
	 * @param numeroPostiPerFila the numero posti per fila
	 */
	public Sala(String nome, int numeroFile, int numeroPostiPerFila) {
		this.nome = nome;
		this.numeroFile = numeroFile;
		this.numeroPostiPerFila = numeroPostiPerFila;
		this.postiTotali = numeroFile*numeroPostiPerFila;
	}
	
	/**
	 * Instantiates a new sala.
	 */
	public Sala() {
		this.postiTotali = this.numeroFile * this.numeroPostiPerFila;
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
	 * Gets the numero file.
	 *
	 * @return the numero file
	 */
	public int getNumeroFile() {
		return numeroFile;
	}
	
	/**
	 * Sets the numero file.
	 *
	 * @param numeroFile the new numero file
	 */
	public void setNumeroFile(int numeroFile) {
		this.numeroFile = numeroFile;
	}
	
	/**
	 * Gets the numero posti per fila.
	 *
	 * @return the numero posti per fila
	 */
	public int getNumeroPostiPerFila() {
		return numeroPostiPerFila;
	}
	
	/**
	 * Sets the numero posti per fila.
	 *
	 * @param numeroPostiPerFila the new numero posti per fila
	 */
	public void setNumeroPostiPerFila(int numeroPostiPerFila) {
		this.numeroPostiPerFila = numeroPostiPerFila;
	}
	
	/**
	 * Gets the posti totali.
	 *
	 * @return the posti totali
	 */
	public int getPostiTotali() {
		return postiTotali;
	}
	
	/**
	 * Sets the posti totali.
	 */
	public void setPostiTotali() {
		this.postiTotali = this.numeroFile * this.numeroPostiPerFila;
	}
	
	/**
	 * Gets the posti occupati.
	 *
	 * @return the posti occupati
	 * @throws Exception 
	 */
	public int getPostiOccupati(String orario, Cinema cinema) throws Exception {
		SQLDataBase db = new SQLDataBase();
		this.postiOccupati = db.postiOccupati(this.nome, orario, cinema.getNome());
		return postiOccupati;
	}
	
	/**
	 * Crea la mappa della sala come una matrice di stringhe.
	 *
	 * @return the mappa sala
	 * @throws Exception 
	 */
	public String[][] creaMappaSala(String orario, Cinema cinema) throws Exception {
		db = new SQLDataBase();
		db.creaDataBaseMappe(this.nome, cinema.getNome(), this.postiTotali);
		db.inserisciMappaSala(new Date(), this.nome, this.postiTotali, this.postiOccupati, cinema.getNome(), orario);
		String[][] mappaSala = db.creaArrayMappaSala(this.nome, this.numeroPostiPerFila, this.numeroFile, orario, cinema.getNome());
		return mappaSala;
	}
	/**
	 * La funzione permette di selezionare un posto nella sala, se esso è disponibile
	 * 
	 * @param postoselezionato
	 * @param orario
	 * @param cinema
	 * @return 0 se l'operazione non è andata a buon fine, il posto selezionato se invece è andato tutto a buon fine
	 * @throws Exception
	 */
	public int selezionaPosto(int postoselezionato, String orario, Cinema cinema) throws Exception{
		if(postoselezionato > 0 && postoselezionato <= this.postiTotali){
			int numeroPosto = 0;
			SQLDataBase sqlDataBase = new SQLDataBase();
			int rigaPosto = -1;
			int colonnaPosto = -1;
			for(int i = 0; i<creaMappaSala(orario,cinema).length; i++){
				for(int j = 0; j<creaMappaSala(orario,cinema)[i].length; j++){
					numeroPosto++;
					if(postoselezionato == numeroPosto){
						rigaPosto = i;
						colonnaPosto = j;
					}
				}
			}
			if(creaMappaSala(orario,cinema)[rigaPosto][colonnaPosto].equals("LIBERO")){
				creaMappaSala(orario,cinema)[rigaPosto][colonnaPosto] = "OCCUPATO";
				sqlDataBase.aggiornaMappaSalaPerOrario(this.nome, postoselezionato, orario, cinema.getNome());
				sqlDataBase.aggiornapostiOccupati(this.nome,this.postiOccupati, orario,cinema.getNome());
				this.postiOccupati = sqlDataBase.postiOccupati(this.nome, orario,cinema.getNome());
			} else{
				System.out.println("Posto già occupato");
				postoselezionato=0;		
			}
		} else{
			System.out.println("Numero posto inesistente");
			postoselezionato = 0;
		}
		return postoselezionato;
	}
}
