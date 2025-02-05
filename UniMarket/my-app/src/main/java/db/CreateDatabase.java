package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/*Classe per la generazione del database con connessione 
 * + creazione tabelle
 * NON FUNZIONA --> mantengo solo per metodo getConnection
*/

public class CreateDatabase {
    private static final String DB_PATH = "src/main/java/db/";
    private static final String DB_FILE = DB_PATH + "database.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;
    private static final Logger LOGGER = Logger.getLogger(CreateDatabase.class.getName());

    private static CreateDatabase instance;
    private Connection connection;
    private DSLContext dslContext;

    // Metodo per ottenere la connessione al database
    private CreateDatabase(){
    	try {
            this.connection = DriverManager.getConnection(URL);
            this.dslContext = DSL.using(connection, SQLDialect.SQLITE);
        } catch (SQLException e) {
        	LOGGER.warning("Errore nella creazione della directory " + DB_PATH);
        }
    }

    public DSLContext getDSLContext() {
        return dslContext;
    }

    public static synchronized CreateDatabase getInstance() {
        if (instance == null) {
            instance = new CreateDatabase();
        }
        return instance;
    }
    
    public synchronized Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
        	connection = DriverManager.getConnection(URL);
            dslContext = DSL.using(connection, SQLDialect.SQLITE);
        }
		return connection;	
    }
}
