import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.sqlite.SQLiteConfig.Pragma;
/**
 * 
 * @author Elena Martini
 * 
 * La seguente classe implementa il database, contente le tabelle delle mappe delle sale del cinema e degli utenti registrati.
 * Inoltre vi sono presenti 2 funzioni per l'eliminazione delle tabelle.
 *
 */
public class SQLDataBase {
	Connection c = null;
    Statement stmt = null;
	
	/**
	 * Connection SQL data base.
	 */
	//Funzione per la connessione al database
    private void connectionSQLDataBase(){
    	c = null;
	    stmt = null;
	   try {
	     	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    	stmt = c.createStatement();
	    }catch ( Exception e ) {
	    	System.out.println("Non riesco a connetterni al database");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	   	
    }
    /**
     * Funzione per la creazione della tabella utenti registrati del database
     * @return 
     * @throws Exception
     */
	public int creaDataBase() throws Exception{
		connectionSQLDataBase();    
	    String sqlUtentiRegistrati = null;
	    DatabaseMetaData meta = c.getMetaData(); 
	    ResultSet res;
	    res = meta.getTables(null, null, "UTENTIREG", null); 
	    if(!res.next()){ 
	    	sqlUtentiRegistrati = "CREATE TABLE UTENTIREG " +
	    			"(ID INTEGER PRIMARY KEY AUTOINCREMENT     NOT NULL," +
	    			" NOME           TEXT    NOT NULL, " + 
	    			" NOMEUTENTE           TEXT    NOT NULL, " + 
	    			" PASSWORD            TEXT     NOT NULL, " +
	    			" SUGGERIMENTO           TEXT    NOT NULL, " + 
	    			" DATADINASCITA          TEXT    NOT NULL, " + 
	    			" INDIRIZZO TEXT NOT NULL)";
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
	 * @param nomeTabella
	 * @param nomeCinema
	 * @param numeroPosti
	 * @return
	 * @throws Exception
	 */
	public int creaDataBaseMappe(String nomeTabella, String nomeCinema, int numeroPosti) throws Exception{
		int count = 0;
		connectionSQLDataBase();
		count++;
		String sqlMappa = null;
		DatabaseMetaData meta = c.getMetaData(); 
		ResultSet res;
		res = meta.getTables(null, null, nomeTabella, null); 
		if(!res.next()){ 
			sqlMappa = "CREATE TABLE "+ nomeTabella +
		    		"(ID INTEGER PRIMARY KEY AUTOINCREMENT     NOT NULL, " +
		    		"NOMESALA           TEXT    NOT NULL, ";
		    for(int i = 1; i<= numeroPosti; i++){
		    	String posto = "POSTO"+i+"		 TEXT NOT NULL, ";
		    	sqlMappa = sqlMappa + posto;
		    }
		    sqlMappa = sqlMappa + "ORARIO            TEXT     NOT NULL, " +
		    		"CINEMA            TEXT     NOT NULL, " +
		    		"DATA TEXT NOT NULL, " +
		    		"POSTIOCCUPATI INT NOT NULL)";
		    stmt.executeUpdate(sqlMappa);
		    c.getMetaData();
		    stmt.close();
			c.close();  
		    return 0;
		}else{
		    System.out.println("Esiste già una sala con il nome scelto");
		    stmt.close();
			c.close();  
		    return -1;
		}  
	}
	/**
	 * Funzione da richiamare se si vuole inserire un nuovo utente nel database
	 * @param nome
	 * @param nomeu
	 * @param password
	 * @param suggerimento
	 * @param dataNascita
	 * @param indirizzo
	 * @throws Exception
	 */
	public void inserisciUtente(String nome, String nomeu, String password, String suggerimento, String dataNascita, String indirizzo) throws Exception{
		connectionSQLDataBase();
		c.setAutoCommit(false);
		String sql = "INSERT INTO UTENTIREG (NOME,NOMEUTENTE,PASSWORD,SUGGERIMENTO,DATADINASCITA,INDIRIZZO) " +
				"VALUES ('"+nome+"','"+nomeu+"','"+password+"' ,'"+suggerimento+"','"+dataNascita+"','"+indirizzo+"');";
		stmt.executeUpdate(sql);
		c.commit();
	  	stmt.close();
    	c.close();
	}
	/**
	 * Funzione usata dalla seguente classe per verificare se la data è passata o no
	 * @param nomeSala
	 * @return
	 * @throws Exception
	 */
	public String restituisciData(String nomeSala) throws Exception{
		String queryCheck;
		String data = null;
		queryCheck = "SELECT DATA from "+nomeSala;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = c.prepareStatement(queryCheck);
		rs = ps.executeQuery();
		if(rs.next()){
			do{
				data = rs.getString("DATA");
			}while(rs.next());
			stmt.close();
			c.close();
			return data;
		}
		stmt.close();
	    c.close();
		return data;
	}
	/**
	 * Funzione per inserire la mappa di una nuova sala nel database
	 * @param dataOggi
	 * @param nomeSala
	 * @param numeroPosti
	 * @param postiOccupati
	 * @param nomeCinema
	 * @param orario
	 * @throws Exception
	 */
	public void inserisciMappaSala(Date dataOggi, String nomeSala, int numeroPosti, int postiOccupati, String nomeCinema, String orario) throws Exception{
		connectionSQLDataBase();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String queryCheck = "SELECT * from "+nomeSala;
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ResultSet rs = ps.executeQuery();
		Date date = null;
		String dataStringa = formatter.format(dataOggi);
		dataOggi = formatter.parse(dataStringa);
		if(rs.next()){
			date = formatter.parse(restituisciData(nomeSala));
		}
		connectionSQLDataBase();
		String sql;
		String data;
		queryCheck = "SELECT * from "+nomeSala;
		ps = c.prepareStatement(queryCheck);
		rs = ps.executeQuery();
		if (!rs.next() || dataOggi.after(date)) {
			data = formatter.format(dataOggi);;
			sql = "INSERT INTO "+nomeSala+" (NOMESALA,";
			for(int i = 1; i<= numeroPosti; i++){
				String posto =  "POSTO"+i+",";
				sql = sql + posto;
			}	
			sql = sql	+ "ORARIO,CINEMA,DATA,POSTIOCCUPATI)" +
					"VALUES ('"+nomeSala+"',";
			for(int i = 1; i<= numeroPosti; i++){
				String posto = "'LIBERO',";
				sql = sql + posto;
			}
			sql = sql +"'"+orario+"','"+nomeCinema+"','"+data+"',"+postiOccupati+");";
			stmt.executeUpdate(sql);
		}else{
		}
		stmt.close();
	   	c.close();
	}
	/**
	 * Funzione che ritorna la mappa della sala desiderata presente nel database
	 * @param sala
	 * @param numeroRighe
	 * @param numeroColonne
	 * @param orario
	 * @param cinema
	 * @return
	 * @throws SQLException
	 */
	public String[][] creaArrayMappaSala(String sala, int numeroRighe, int numeroColonne, String orario, String cinema) throws SQLException{
		String[][] mappa = null;
		connectionSQLDataBase();
		String queryCheck;
		PreparedStatement ps;
		ResultSet rs;
		queryCheck = "SELECT * from "+sala+" WHERE NOMESALA = ? AND ORARIO = ? and CINEMA = ?";
		mappa = new String[numeroRighe][numeroColonne];
		ps = c.prepareStatement(queryCheck);
		ps.setString(1, sala);
		ps.setString(2, orario);
		ps.setString(3, cinema);
		rs = ps.executeQuery();
		while(rs.next()){
			int count = 0;
			for(int i = 0; i<numeroRighe; i++){
				for(int j = 0; j<numeroColonne; j++){
					count++;
					mappa[i][j] = rs.getString("POSTO"+count);
				}
			}
		}
		stmt.close();
		c.close();
		return mappa;
	}
	/**
	 * Funzione utilizzata per aggiornare la sala
	 * @param sala
	 * @param numeroPosto
	 * @param orario
	 * @param cinema
	 * @throws Exception
	 */
	public void aggiornaMappaSalaPerOrario(String sala, int numeroPosto, String orario, String cinema) throws Exception{
		connectionSQLDataBase();
		String queryCheck;
		PreparedStatement ps;	
		queryCheck = "UPDATE "+sala+" SET POSTO"+numeroPosto+" = ? where NOMESALA = ? and ORARIO = ? and CINEMA = ?";
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
	 * @param sala
	 * @param postiOccupati
	 * @param orario
	 * @param cinema
	 * @throws Exception
	 */
	public void aggiornapostiOccupati(String sala, int postiOccupati, String orario, String cinema) throws Exception{
		connectionSQLDataBase();
		c.setAutoCommit(false);
		String query;
		PreparedStatement ps;
		ResultSet rs;
		query = "UPDATE "+sala+" SET POSTIOCCUPATI = ? where NOMESALA = ? and ORARIO = ? and CINEMA = ?";
		ps = c.prepareStatement(query);
		ps.setInt(1, postiOccupati+1);
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
	 * @param sala
	 * @param orario
	 * @param cinema
	 * @return
	 * @throws Exception
	 */
	public int postiOccupati(String sala, String orario, String cinema) throws Exception{
		connectionSQLDataBase();
		c.getMetaData();
		String query;
		PreparedStatement ps2;
		ResultSet rs;
		query = "SELECT POSTIOCCUPATI from "+sala+" WHERE NOMESALA = ? and ORARIO = ? and CINEMA = ?";
		ps2 = c.prepareStatement(query);
		ps2.setString(1, sala);
		ps2.setString(2, orario);
		ps2.setString(3, cinema);
		rs = ps2.executeQuery();
		if(rs.next()){
			int temp = rs.getInt("POSTIOCCUPATI");
	   		stmt.close();
	   		c.close();
			return temp;
		}
		stmt.close();
   		c.close();
		return 0;
	}
	/**
	 * Effettua il controlla sulla registrazione da parte di un utente
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean usernameNonRegistrata(String userName) throws Exception{
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			System.out.println("Al seguente nome utente è associato un account");
			stmt.close();
	   		c.close();
			return false;
		}else{
			stmt.close();
	   		c.close();
			return true;
		}
	}
	/**
	 * Funzione per ottenere il suggerimento della password associata all'account
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public String suggerimentoPass(String userName) throws Exception{
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
	 * @param username the username
	 * @param password the password
	 * @return true se utente registrato, false in caso contrario
	 * @throws Exception the exception
	 */
	public boolean controlloAutenticazione(String username, String password) throws Exception{
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		c.setAutoCommit(false);
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ? and PASSWORD = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(!rs.next()){
			stmt.close();
	   		c.close();
			return false;
		}else{
			stmt.close();
	   		c.close();
			return true;
		}
	}
	/**
	 * Restituisce i dati del cliente registrato
	 */
	public ArrayList<String> restituisciClienteRegistrato(String username, String password) throws Exception{
		connectionSQLDataBase();
		DatabaseMetaData md = c.getMetaData();
		c.setAutoCommit(false);
		String queryCheck = "SELECT * from UTENTIREG where NOMEUTENTE = ? and PASSWORD = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(!rs.next()){
			stmt.close();
	   		c.close();
			return null;
		}else{
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
	 * @param nomeSala
	 * @throws Exception
	 */
	public void deleteTableMappe(String nomeSala) throws Exception{
		connectionSQLDataBase();
		stmt = c.createStatement();
		String sql = "DROP TABLE " + nomeSala;
		stmt.executeUpdate(sql);
		stmt.close();
   		c.close();
	}
	/**
	 * Elimina la tabella utenti dal database
	 * @throws SQLException
	 */
	public void deleteTableUtentiReg() throws SQLException{
		connectionSQLDataBase();
		stmt = c.createStatement();
		String sql = "DROP TABLE UTENTIREG";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	}
}
