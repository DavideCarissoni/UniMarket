package unimarket.services;

import jooq.generated.tables.records.UtenteRecord;
import jooq.generated.tables.Utente;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    private final DSLContext dsl;

    public UtenteService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public boolean isEmailUnique(String email) {
        return dsl.fetchCount(
                dsl.selectFrom(Utente.UTENTE)
                        .where(Utente.UTENTE.EMAIL.eq(email))
        ) == 0;
    }

    public void saveUser(String nome, String cognome, double numeroTelefono, String email, String password) {
        UtenteRecord userRecord = dsl.newRecord(Utente.UTENTE);
        userRecord.setNome(nome);
        userRecord.setCognome(cognome);
        userRecord.setNumeroTelefono(String.valueOf(numeroTelefono));
        userRecord.setEmail(email);
        userRecord.setPassword(password);
    }
}
