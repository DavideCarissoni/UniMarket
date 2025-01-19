package jooq;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class GenerateCode {

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
            System.out.println("Codice generato con successo!");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Errore durante la generazione del codice JOOQ.");
        }
    }
}
