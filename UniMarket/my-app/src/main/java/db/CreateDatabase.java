package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariDataSource;

/*Classe per la generazione del database con connessione 
 * + creazione tabelle
 * NON FUNZIONA --> mantengo solo per metodo getConnection
*/

public class CreateDatabase {
	
	@Bean
	public DataSource dataSource() {
	    HikariDataSource dataSource = new HikariDataSource();
	    dataSource.setJdbcUrl("jdbc:sqlite:src/main/java/db/database.db");
	    return dataSource;
	}

	@Bean
	public DSLContext dslContext(DataSource dataSource) {
	    return DSL.using(dataSource, SQLDialect.SQLITE);
	}

	/*
    private static final String DB_PATH = "src/main/java/db/";
    private static final String DB_FILE = DB_PATH + "database.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;
    private static final Logger LOGGER = Logger.getLogger(CreateDatabase.class.getName());

    private static volatile CreateDatabase instance;
    private Connection connection;
    private DSLContext dslContext;

    private CreateDatabase() {
    	 try {
    	        Files.createDirectories(Paths.get(DB_PATH)); // Assicura che il percorso esista
    	    } catch (IOException e) {
    	        LOGGER.warning("Errore nella creazione della directory: " + e.getMessage());
    	    }

    	    try {
    	        this.connection = DriverManager.getConnection(URL);
    	        this.dslContext = DSL.using(connection, SQLDialect.SQLITE);
    	    } catch (SQLException e) {
    	        LOGGER.warning("Errore nella connessione al database: " + e.getMessage());
    	    }
    }
    
    public static CreateDatabase getInstance() {
        if (instance == null) {
            synchronized (CreateDatabase.class) {
                if (instance == null) {
                    instance = new CreateDatabase();
                }
            }
        }
        return instance;
    }
    public static DSLContext getDSLContext() throws SQLException {
        return getInstance().dslContext;  // Usa l'istanza esistente
    }


    public synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {  // Controllo se la connessione è chiusa
            connection = DriverManager.getConnection(URL);
            dslContext = DSL.using(connection, SQLDialect.SQLITE);
        }
        return connection;
    }
*/
}

