package jooq;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateCode {

	private static final Logger logger = LoggerFactory.getLogger(GenerateCode.class);
	
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration()
                .withJdbc(new Jdbc().withDriver("org.sqlite.JDBC")
                .withUrl("jdbc:sqlite:src/main/java/db/database.db")
                )
                .withGenerator(new Generator()
                .withDatabase(new Database().withName("org.jooq.meta.sqlite.SQLiteDatabase") 
                )
                .withTarget(new Target().withPackageName("jooq.generated") 
                .withDirectory("src/main/java")));

            // Esecuzione della generazione del codice
            GenerationTool.generate(configuration);
            logger.info("Codice generato con successo!");

        } catch (Exception e) {
            e.printStackTrace();
           logger.error("Errore durante la generazione del codice JOOQ.");
        }
    }
}
