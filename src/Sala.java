import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * La Classe Sala. La classe sala, si riferisce allo user case:
 * "seleziona posto"
 */
public class Sala {
	
	/** L'id. */
	int id;
	/** Il nome. */
	String nome;

	/** il numero di file. */
	int numeroFile;

	/** Il numero di posti per fila. */
	int numeroPostiPerFila;

	/** Il numero di posti totali. */
	int postiTotali;

	/** Il numero di posti occupati. */
	int postiOccupati = 0;

	/** Il database. */
	SQLDataBase db = new SQLDataBase();

	/**
	 * Instantiates una nuova sala.
	 *
	 * @param id
	 *            l'id
	 * @param nome
	 *            il nome
	 * @param numeroFile
	 *            il numero file
	 * @param numeroPostiPerFila
	 *            il numero posti per fila
	 */
	public Sala(int id, String nome, int numeroFile, int numeroPostiPerFila) {
		this.id = id;
		this.nome = nome;
		this.numeroFile = numeroFile;
		this.numeroPostiPerFila = numeroPostiPerFila;
		this.postiTotali = numeroFile * numeroPostiPerFila;
	}

	/**
	 * Instantiates una nuova sala.
	 */
	public Sala() {
		this.postiTotali = this.numeroFile * this.numeroPostiPerFila;
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
	 *            il nuovo id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets il nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets il nome.
	 *
	 * @param nome
	 *            il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets il numero file.
	 *
	 * @return il numero file
	 */
	public int getNumeroFile() {
		return numeroFile;
	}

	/**
	 * Sets il numero file.
	 *
	 * @param numeroFile
	 *            il nuovo numero file
	 */
	public void setNumeroFile(int numeroFile) {
		this.numeroFile = numeroFile;
	}

	/**
	 * Gets il numero posti per fila.
	 *
	 * @return il numero posti per fila
	 */
	public int getNumeroPostiPerFila() {
		return numeroPostiPerFila;
	}

	/**
	 * Sets il numero posti per fila.
	 *
	 * @param numeroPostiPerFila
	 *            il nuovo numero posti per fila
	 */
	public void setNumeroPostiPerFila(int numeroPostiPerFila) {
		this.numeroPostiPerFila = numeroPostiPerFila;
	}

	/**
	 * Gets i posti totali.
	 *
	 * @return i posti totali
	 */
	public int getPostiTotali() {
		return postiTotali;
	}

	/**
	 * Sets i posti totali.
	 */
	public void setPostiTotali() {
		this.postiTotali = this.numeroFile * this.numeroPostiPerFila;
	}

	/**
	 * Gets i posti occupati.
	 *
	 * @param orario
	 *            orario
	 * @param cinema
	 *             cinema
	 * @return il numero di posti occupati restituitoli dal database
	 * @throws Exception
	 *             l' eccezione
	 */
	public int getPostiOccupati(String orario, Cinema cinema) throws Exception {
		SQLDataBase db = new SQLDataBase();
		this.postiOccupati = db.postiOccupati(this.nome, orario, cinema.getNome());
		return postiOccupati;
	}

	/**
	 * Crea la mappa della sala come una matrice di stringhe.
	 *
	 * @param orario
	 *            l' orario
	 * @param cinema
	 *            il cinema
	 * @return la mappa della sala
	 * @throws Exception
	 *             l'eccezione
	 */
	public String[][] creaMappaSala(String orario, Cinema cinema) throws Exception {
		db = new SQLDataBase();
		db.creaDataBaseMappe(this.nome, cinema.getNome(), this.postiTotali);
		db.inserisciMappaSala(new Date(), this.nome, this.postiTotali, this.postiOccupati, cinema.getNome(), orario);
		String[][] mappaSala = db.creaArrayMappaSala(this.nome, this.numeroPostiPerFila, this.numeroFile, orario,
				cinema.getNome());
		return mappaSala;
	}

	/**
	 * La funzione permette di selezionare un posto nella sala, se esso e'
	 * disponibile.
	 *
	 * @param postoselezionato
	 *            il postoselezionato
	 * @param orario
	 *            l' orario
	 * @param cinema
	 *            il cinema
	 * @return 0 se l'operazione non è andata a buon fine, il posto selezionato
	 *         se invece è andato tutto a buon fine
	 * @throws Exception
	 *             l' eccezione
	 */
	public int selezionaPosto(int postoselezionato, String orario, Cinema cinema) throws Exception {
		if (this.postiOccupati < this.postiTotali) {
			if (postoselezionato > 0 && postoselezionato <= this.postiTotali) {
				int numeroPosto = 0;
				SQLDataBase sqlDataBase = new SQLDataBase();
				int rigaPosto = -1;
				int colonnaPosto = -1;
				for (int i = 0; i < creaMappaSala(orario, cinema).length; i++) {
					for (int j = 0; j < creaMappaSala(orario, cinema)[i].length; j++) {
						numeroPosto++;
						if (postoselezionato == numeroPosto) {
							rigaPosto = i;
							colonnaPosto = j;
						}
					}
				}
				if (creaMappaSala(orario, cinema)[rigaPosto][colonnaPosto].equals("LIBERO")) {
					creaMappaSala(orario, cinema)[rigaPosto][colonnaPosto] = "OCCUPATO";
					sqlDataBase.aggiornaMappaSalaPerOrario(this.nome, postoselezionato, orario, cinema.getNome());
					sqlDataBase.aggiornapostiOccupati(this.nome, this.postiOccupati, orario, cinema.getNome());
					this.postiOccupati = sqlDataBase.postiOccupati(this.nome, orario, cinema.getNome());
				} else {
					postoselezionato = 0;
				}
			} else {
				postoselezionato = 0;
			}
			return postoselezionato;
		}
		return -1;
	}
}
