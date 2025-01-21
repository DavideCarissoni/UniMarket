package db;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Classe per la generazione del database con connessione 
 * + creazione tabelle
*/

public class CreateDatabase {
    private static final String DB_PATH = "src/main/java/db/";
    private static final String DB_FILE = DB_PATH + "database.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;
    private static final Logger LOGGER = Logger.getLogger(CreateDatabase.class.getName());

    // Metodo per ottenere la connessione al database
    public static Connection getConnection() throws SQLException {
        // Controlla che la directory esista, altrimenti la crea
        File dbDir = new File(DB_PATH);
        if (!dbDir.exists()) {
            boolean created = dbDir.mkdirs();
            if (created) {
            	LOGGER.info("Directory creata: " + DB_PATH);
            } else {
                LOGGER.info("Errore nella creazione della directory " + DB_PATH);
            }
        }
        return DriverManager.getConnection(URL);
    }

    // Metodo main per testare la connessione e creazione tabelle
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            	LOGGER.info("Connessione al database stabilita con successo!");
        } catch (SQLException e) {
        	LOGGER.log(Level.SEVERE, "Errore nella connessione al database:", e);
        }
        
        try {
        	creaTabellaProfili();
        	LOGGER.info("Tabella 'profili utente' creata correttamente");
        	
        	creaTabellaProdotti();
        	LOGGER.info("Tabella 'prodotti' creata correttamente");
        
        	creaTabellaAdmin();
        	LOGGER.info("Tabella 'admin' creata correttamente");
        	
        } catch (Exception e) {
        	LOGGER.log(null, "Errore", e);
        }
        /* Visualizza percorso file
        System.out.println("Cartella di lavoro corrente: " + System.getProperty("user.dir"));
        */
    }
    
    //Metodo per creare la tabella profilo utenti
    public static void creaTabellaProfili() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS profili_utente (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                cognome TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                numero_telefono TEXT,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

        esegui("profili_utenti", createTableSQL);
    }
    
    //Metodo per creare la tabella prodotti
    public static void creaTabellaProdotti() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS prodotti (
                codice INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                descrizione TEXT NOT NULL,
                prezzo TEXT NOT NULL,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

        esegui("prodotti", createTableSQL);
    }
    
    //Metodo per creare la tabella admin
    public static void creaTabellaAdmin() {
    	String createTableSQL = """
                CREATE TABLE IF NOT EXISTS admin (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cognome TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    numero_telefono TEXT,
                    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;
    	
    	esegui("admin", createTableSQL);
    }
    
    //metodo per il controllo della corretta esecuzione della creazione tabelle
    public static void esegui(String tabella, String sql) {
    	try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
               stmt.execute(sql);
               LOGGER.log(Level.INFO, "{0} eseguita con successo", tabella);
           } catch (SQLException e) {
               LOGGER.log(Level.SEVERE, "Errore durante la creazione della tabella:" + tabella, e);
           }
    }
    
}
