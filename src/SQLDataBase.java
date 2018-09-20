import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * La Classe SQLDataBase.
 * 
 *La seguente classe implementa il database, contente le tabelle delle
 *mappe delle sale del cinema e degli utenti registrati. Inoltre vi
 *sono presenti 2 funzioni per l'eliminazione delle tabelle.
 *
 */
public class SQLDataBase {
	Connection c = null;
	Statement stmt = null;

	/**
	 * Connessione al SQL database.
	 */
	// Funzione per la connessione al database
	private void connectionSQLDataBase() throws Exception {
		c = null;
		stmt = null;

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = c.createStatement();
	}

	/**
	 * Funzione per la creazione della tabella utenti registrati del database
	 * 
	 * @return 0
	 * @throws Exception
	 * 	            eccezione
	 */
	public int creaDataBase() throws Exception {
		connectionSQLDataBase();
		String sqlUtentiRegistrati = null;
		DatabaseMetaData meta = c.getMetaData();
		ResultSet res;
		res = meta.getTables(null, null, "UTENTIREG", null);
		if (!res.next()) {
			sqlUtentiRegistrati = "CREATE TABLE UTENTIREG " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT     NOT NULL,"
					+ " NOME           TEXT    NOT NULL, " + " NOMEUTENTE           TEXT    NOT NULL, "
					+ " PASSWORD            TEXT     NOT NULL, " + " SUGGERIMENTO           TEXT    NOT NULL, "
					+ " DATADINASCITA          TEXT    NOT NULL, " + " INDIRIZZO TEXT NOT NULL)";
			stmt.executeUpdate(sqlUtentiRegistrati);
			stmt.close();
			c.close();
			return 0;
		}
		stmt.close();
		c.close();
		return 0;
	}

	/**
	 * Funzione per la creazione della tabella Mappe del database
	 * 
	 * @param nomeTabella
	 *                 il nome della tabella
	 * @param nomeCinema
	 *                 il nome del cinema
	 * @param numeroPosti
	 *                 il numero di posti totali
	 * @return 0 se operazioni avvenute con successo, -1 in caso contrario
	 * @throws Exception
	 *                 eccezione  
	 */
	public int creaDataBaseMappe(String nomeTabella, String nomeCinema, int numeroPosti) throws Exception {
		int count = 0;
		connectionSQLDataBase();
		count++;
		String sqlMappa = null;
		DatabaseMetaData meta = c.getMetaData();
		ResultSet res;
		res = meta.getTables(null, null, nomeTabella, null);
		if (!res.next()) {
			sqlMappa = "CREATE TABLE " + nomeTabella + "(ID INTEGER PRIMARY KEY AUTOINCREMENT     NOT NULL, "
					+ "NOMESALA           TEXT    NOT NULL, ";
			for (int i = 1; i <= numeroPosti; i++) {
				String posto = "POSTO" + i + "		 TEXT NOT NULL, ";
				sqlMappa = sqlMappa + posto;
			}
			sqlMappa = sqlMappa + "ORARIO            TEXT     NOT NULL, " + "CINEMA            TEXT     NOT NULL, "
					+ "DATA TEXT NOT NULL, " + "POSTIOCCUPATI INT NOT NULL)";
			stmt.executeUpdate(sqlMappa);
			c.getMetaData();
			stmt.close();
			c.close();
			return 0;
		} else {
			stmt.close();
			c.close();
			return -1;
		}
	}

	/**
	 * Funzione da richiamare se si vuole inserire un nuovo utente nel database
	 * 
	 * @param nome
	 *          il nome
	 * @param nomeu
	 *          l'username
	 * @param password
	 *          la password
	 * @param suggerimento
	 *          la frase di suggerimento
	 * @param dataNascita
	 *          la data di nascita
	 * @param indirizzo
	 *          l'indirizzo
	 * @return true se l'utente viene inserito con successo, false se non viene inserito il nuovo utente
	 * @throws Exception
	 *          eccezione
	 */
	public boolean inserisciUtente(String nome, String nomeu, String password, String suggerimento, String dataNascita,
			String indirizzo) throws Exception {
		connectionSQLDataBase();
		c.setAutoCommit(false);
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, nomeu);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println("Al seguente nome utente è già associato un account");
			stmt.close();
			c.close();
			return false;
		} else {
			String sql = "INSERT INTO UTENTIREG (NOME,NOMEUTENTE,PASSWORD,SUGGERIMENTO,DATADINASCITA,INDIRIZZO) "
					+ "VALUES ('" + nome + "','" + nomeu + "','" + password + "' ,'" + suggerimento + "','"
					+ dataNascita + "','" + indirizzo + "');";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			return true;
		}
	}

	/**
	 * Funzione usata dalla seguente classe per verificare se la data è passata
	 * o no
	 * 
	 * @param nomeSala
	 *            il nome della sala
	 * @return la data
	 * @throws Exception
	 *            eccezione
	 */
	public String restituisciData(String nomeSala) throws Exception {
		String queryCheck;
		String data = null;
		queryCheck = "SELECT DATA from " + nomeSala;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = c.prepareStatement(queryCheck);
		rs = ps.executeQuery();
		if (rs.next()) {
			do {
				data = rs.getString("DATA");
			} while (rs.next());
		}
		stmt.close();
		c.close();
		return data;
	}

	/**
	 * Funzione per inserire la mappa di una nuova sala nel database
	 * 
	 * @param dataOggi
	 *             la data di oggi
	 * @param nomeSala
	 *             il nome della sala
	 * @param numeroPosti
	 *             il numero dei posti totali
	 * @param postiOccupati
	 *             il numero dei posti occupati
	 * @param nomeCinema
	 *             il nome del cinema
	 * @param orario
	 *             l'orario dello spettacolo
	 * @throws Exception
	 *             eccezione
	 */
	public void inserisciMappaSala(Date dataOggi, String nomeSala, int numeroPosti, int postiOccupati,
			String nomeCinema, String orario) throws Exception {
		connectionSQLDataBase();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String queryCheck = "SELECT * from " + nomeSala;
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ResultSet rs = ps.executeQuery();
		Date date = null;
		String dataStringa = formatter.format(dataOggi);
		dataOggi = formatter.parse(dataStringa);
		if (rs.next()) {
			date = formatter.parse(restituisciData(nomeSala));
		}
		connectionSQLDataBase();
		String sql;
		String data;
		queryCheck = "SELECT * from " + nomeSala;
		ps = c.prepareStatement(queryCheck);
		rs = ps.executeQuery();
		if (!rs.next() || dataOggi.after(date)) {
			data = formatter.format(dataOggi);
			;
			sql = "INSERT INTO " + nomeSala + " (NOMESALA,";
			for (int i = 1; i <= numeroPosti; i++) {
				String posto = "POSTO" + i + ",";
				sql = sql + posto;
			}
			sql = sql + "ORARIO,CINEMA,DATA,POSTIOCCUPATI)" + "VALUES ('" + nomeSala + "',";
			for (int i = 1; i <= numeroPosti; i++) {
				String posto = "'LIBERO',";
				sql = sql + posto;
			}
			sql = sql + "'" + orario + "','" + nomeCinema + "','" + data + "'," + postiOccupati + ");";
			stmt.executeUpdate(sql);
		} else {
		}
		stmt.close();
		c.close();
	}

	/**
	 * Funzione che ritorna la mappa della sala desiderata presente nel database
	 * 
	 * @param sala
	 *            il nome della sala
	 * @param numeroRighe
	 *            il numero di posti per riga
	 * @param numeroColonne
	 *            il numero di posti per colonna
	 * @param orario
	 *            l'orario
	 * @param cinema
	 *            il cinema
	 * @return una matrice contente la mappa della sala
	 * @throws SQLException
	 *            eccezione
	 */
	public String[][] creaArrayMappaSala(String sala, int numeroRighe, int numeroColonne, String orario, String cinema)
			throws Exception {
		String[][] mappa = null;
		connectionSQLDataBase();
		String queryCheck;
		PreparedStatement ps;
		ResultSet rs;
		queryCheck = "SELECT * from " + sala + " WHERE NOMESALA = ? AND ORARIO = ? and CINEMA = ?";
		mappa = new String[numeroRighe][numeroColonne];
		ps = c.prepareStatement(queryCheck);
		ps.setString(1, sala);
		ps.setString(2, orario);
		ps.setString(3, cinema);
		rs = ps.executeQuery();
		while (rs.next()) {
			int count = 0;
			for (int i = 0; i < numeroRighe; i++) {
				for (int j = 0; j < numeroColonne; j++) {
					count++;
					mappa[i][j] = rs.getString("POSTO" + count);
				}
			}
		}
		stmt.close();
		c.close();
		return mappa;
	}

	/**
	 * Funzione utilizzata per aggiornare la sala
	 * 
	 * @param sala
	 *           il nome della sala
	 * @param numeroPosto
	 *           il numero di posti totali
	 * @param orario
	 *           l'orario
	 * @param cinema
	 *           il cinema
	 * @throws Exception
	 *           eccezione
	 */
	public void aggiornaMappaSalaPerOrario(String sala, int numeroPosto, String orario, String cinema)
			throws Exception {
		connectionSQLDataBase();
		String queryCheck;
		PreparedStatement ps;
		queryCheck = "UPDATE " + sala + " SET POSTO" + numeroPosto
				+ " = ? where NOMESALA = ? and ORARIO = ? and CINEMA = ?";
		ps = c.prepareStatement(queryCheck);
		ps.setString(1, "OCCUPATO");
		ps.setString(2, sala);
		ps.setString(3, orario);
		ps.setString(4, cinema);
		ps.executeUpdate();
		stmt.close();
		c.close();
	}

	/**
	 * Funzione utilizzata per aggiornara la sala
	 * 
	 * @param sala
	 *          il nome della sala
	 * @param postiOccupati
	 *          il numero di posti occupati
	 * @param orario
	 *          l'orario
	 * @param cinema
	 *          il cinema
	 * @throws Exception
	 *          eccezione
	 */
	public void aggiornapostiOccupati(String sala, int postiOccupati, String orario, String cinema) throws Exception {
		connectionSQLDataBase();
		c.setAutoCommit(false);
		String query;
		PreparedStatement ps;
		ResultSet rs;
		query = "UPDATE " + sala + " SET POSTIOCCUPATI = ? where NOMESALA = ? and ORARIO = ? and CINEMA = ?";
		ps = c.prepareStatement(query);
		ps.setInt(1, postiOccupati + 1);
		ps.setString(2, sala);
		ps.setString(3, orario);
		ps.setString(4, cinema);
		ps.executeUpdate();
		c.commit();
		stmt.close();
		c.close();
	}

	/**
	 * Funzione che ritorna il numero dei posti occupati in una determinata sala
	 * 
	 * @param sala
	 *          il nome della sala
	 * @param orario
	 *          l'orario
	 * @param cinema
	 *          il cinema
	 * @return il numero di posti occupati
	 * @throws Exception
	 *          eccezione
	 */
	public int postiOccupati(String sala, String orario, String cinema) throws Exception {
		connectionSQLDataBase();
		c.getMetaData();
		String query;
		int temp = -1;
		PreparedStatement ps2;
		ResultSet rs;
		query = "SELECT POSTIOCCUPATI from " + sala + " WHERE NOMESALA = ? and ORARIO = ? and CINEMA = ?";
		ps2 = c.prepareStatement(query);
		ps2.setString(1, sala);
		ps2.setString(2, orario);
		ps2.setString(3, cinema);
		rs = ps2.executeQuery();
		if (rs.next()) {
			temp = rs.getInt("POSTIOCCUPATI");
			stmt.close();
			c.close();
		}
		return temp;
	}

	/**
	 * Effettua il controlla sulla registrazione da parte di un utente
	 * 
	 * @param userName
	 *              l'username
	 * @return true se l'username scelto non ha corrispondenza nel database, false in caso contrario
	 * @throws Exception
	 *              eccezione
	 */
	public boolean usernameNonRegistrata(String userName) throws Exception {
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println("Al seguente nome utente è associato un account");
			stmt.close();
			c.close();
			return false;
		} else {
			stmt.close();
			c.close();
			return true;
		}
	}

	/**
	 * Funzione per ottenere il suggerimento della password associata
	 * all'account
	 * 
	 * @param userName
	 *              l'username
	 * @return la frase di suggerimento inserita
	 * @throws Exception
	 *              eccezione
	 */
	public String suggerimentoPass(String userName) throws Exception {
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		c.setAutoCommit(false);
		String queryCheck = "SELECT SUGGERIMENTO from UTENTIREG where NOMEUTENTE = ? ";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		String fraseSuggerimento = rs.getString("SUGGERIMENTO");
		stmt.close();
		c.close();
		return fraseSuggerimento;
	}

	/**
	 * Controllo autenticazione.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return true se utente registrato, false in caso contrario
	 * @throws Exception
	 *             the exception
	 */
	public boolean controlloAutenticazione(String username, String password) throws Exception {
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		c.setAutoCommit(false);
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ? and PASSWORD = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			stmt.close();
			c.close();
			return false;
		} else {
			stmt.close();
			c.close();
			return true;
		}
	}

	/**
	 * Restituisce i dati del cliente registrato
	 * 
	 *  @param username
	 *            il nome utente 
	 *  @param password
	 *            la password
	 *  @return una lista contente l'utente regitrato
	 *  @throws Exception
	 *            eccezione
	 */
	public ArrayList<String> restituisciClienteRegistrato(String username, String password) throws Exception {
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		c.setAutoCommit(false);
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ? and PASSWORD = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			stmt.close();
			c.close();
			return null;
		} else {
			ArrayList<String> cliente = new ArrayList<String>();
			cliente.add(rs.getString("NOME"));
			cliente.add(rs.getString("NOMEUTENTE"));
			cliente.add(rs.getString("PASSWORD"));
			cliente.add(rs.getString("DATADINASCITA"));
			cliente.add(rs.getString("INDIRIZZO"));
			cliente.add(rs.getString("SUGGERIMENTO"));
			stmt.close();
			c.close();
			return cliente;
		}
	}

	/**
	 * Elimina la tabella mappe dal database
	 * 
	 * @param nomeSala
	 *            il nome della sala
	 * @throws Exception
	 *            l'eccezione
	 */
	public void deleteTableMappe(String nomeSala) throws Exception {
		connectionSQLDataBase();
		stmt = c.createStatement();
		String sql = "DROP TABLE " + nomeSala;
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	}

	/**
	 * Elimina la tabella utenti dal database
	 * 
	 * @throws SQLException
	 * 			l'eccezzione
	 */
	public void deleteTableUtentiReg() throws Exception {
		connectionSQLDataBase();
		stmt = c.createStatement();
		String sql = "DROP TABLE UTENTIREG";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	}
}
