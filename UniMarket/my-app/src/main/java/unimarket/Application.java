package unimarket;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import db.CreateDatabase;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;
import unimarket.data.SamplePersonRepository;
import java.sql.SQLException;
import java.util.logging.Logger;
/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "unimarket-gui", variant = Lumo.DARK)
public class Application implements AppShellConfigurator {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	public Application() {
		try {
            CreateDatabase.getConnection();
            LOGGER.info("Connessione al database stabilita con successo!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.severe("Errore durante la connessione al database");
        }
	}
    SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
            SqlInitializationProperties properties, SamplePersonRepository repository) {
        // This bean ensures the database is only initialized when empty
        return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
            @Override
            public boolean initializeDatabase() {
                if (repository.count() == 0L) {
                    return super.initializeDatabase();
                }
                return false;
            }
        };
    }
}
