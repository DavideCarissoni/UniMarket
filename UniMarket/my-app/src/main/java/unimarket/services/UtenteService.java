package unimarket.services;

import db.CreateDatabase;
import jooq.generated.Tables;
import componenti.Utente;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Table;
import org.jooq.TableField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import java.util.List;

@Service
public class UtenteService {

    private int counter = 0;
    private final DSLContext dsl;
    private final BCryptPasswordEncoder passwordEncoder;

    public UtenteService(DSLContext dsl) {
        this.dsl = dsl;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public boolean isEmailUnique(String email) {
        return !dsl.fetchExists(
            dsl.selectOne()
               .from(Tables.UTENTE)
               .where(Tables.UTENTE.EMAIL.eq(email))
        );
    }

    public void creaAccount(String nome, String cognome, String telefono, String email, String password) {
        // Genera un ID univoco
        int newId = generateUniqueId(Tables.UTENTE, Tables.UTENTE.ID);
        
        // Hash della password
        String hashedPassword = passwordEncoder.encode(password);

        try {
            dsl.insertInto(Tables.UTENTE, 
                           Tables.UTENTE.ID, 
                           Tables.UTENTE.NOME, 
                           Tables.UTENTE.COGNOME, 
                           Tables.UTENTE.NUMERO_TELEFONO, 
                           Tables.UTENTE.EMAIL, 
                           Tables.UTENTE.PASSWORD)
               .values(newId, nome, cognome, telefono, email, hashedPassword)
               .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int generateUniqueId(Table<?> table, TableField<?, Integer> idColumn) {
        Random random = new Random();
        int id;

        do {
            id = 1000 + random.nextInt(9000);
        } while (isIdExists(table, idColumn, id));

        return id;
    }

    private boolean isIdExists(Table<?> table, TableField<?, Integer> idColumn, int id) {
        return dsl.fetchExists(
            dsl.selectOne()
               .from(table)
               .where(idColumn.eq(id))
        );
    }
    
    public boolean login(String email, String password) {
        Record1<String> record = dsl.select(Tables.UTENTE.PASSWORD)
                                    .from(Tables.UTENTE)
                                    .where(Tables.UTENTE.EMAIL.eq(email))
                                    .fetchOne();

        if (record != null) {
            String hashedPassword = record.value1();
            return passwordEncoder.matches(password, hashedPassword);
        }

        return false; // Email non trovata o errore
    }
    
    public List<Utente> getAllUtenti() {
        return dsl.select(Tables.UTENTE.ID,
        		Tables.UTENTE.NOME,
        		Tables.UTENTE.COGNOME,
        		Tables.UTENTE.EMAIL,
        		Tables.UTENTE.NUMERO_TELEFONO,
        		Tables.UTENTE.PASSWORD
        		)    
        		.from(Tables.UTENTE)
        		.fetchInto(Utente.class);
    }
}
