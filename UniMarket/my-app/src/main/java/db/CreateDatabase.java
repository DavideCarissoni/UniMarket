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
 * NON FUNZIONA --> mantengo solo per metodo getConnection
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
                LOGGER.warning("Errore nella creazione della directory " + DB_PATH);
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
    }
}
