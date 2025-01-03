package db;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Classe per la generazione del database con connessione 
 * Il database è vuoto di default
 * Da eliminare se si decide di usare una nuova ckasse per la gestione del database
 * 
 * @Davide Carissoni
*/

public class CreateDatabase {
    private static final String DB_PATH = "src/main/db/";
    private static final String DB_FILE = DB_PATH + "database.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;

    // Metodo per ottenere la connessione al database
    public static Connection getConnection() throws SQLException {
        // Controlla che la directory esista, altrimenti la crea
        File dbDir = new File(DB_PATH);
        if (!dbDir.exists()) {
            boolean created = dbDir.mkdirs();
            if (created) {
            	System.out.println("Directory creata: " + DB_PATH);
            } else {
                throw new RuntimeException("Errore nella creazione della directory " + DB_PATH);
            }
        }

        // Restituisce la connessione al database
        return DriverManager.getConnection(URL);
    }

    // Metodo main per testare la connessione
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connessione al database stabilita con successo!");
            }
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database:");
            e.printStackTrace();
        }
    }
}
