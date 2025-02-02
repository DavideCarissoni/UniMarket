package unimarket.services;

import jooq.generated.tables.records.UtenteRecord;
import jooq.generated.tables.Utente;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        return dsl.fetchCount(
                dsl.selectFrom(Utente.UTENTE)
                        .where(Utente.UTENTE.EMAIL.eq(email))
        ) == 0;
    }

    public void saveUser(String nome, String cognome, String numeroTelefono, String email, String password) {
        // Usa JOOQ per fare l'inserimento
        dsl.insertInto(Utente.UTENTE)
                .set(Utente.UTENTE.ID, counter++)  // ID incrementale
                .set(Utente.UTENTE.NOME, nome)
                .set(Utente.UTENTE.COGNOME, cognome)
                .set(Utente.UTENTE.NUMERO_TELEFONO, numeroTelefono)
                .set(Utente.UTENTE.EMAIL, email)
                .set(Utente.UTENTE.PASSWORD, passwordEncoder.encode(password)) // Codifica la password
                .execute();
    }

    public Page<Utente> findAll(PageRequest pageRequest) {
        List<Utente> utenti = dsl.selectFrom(Utente.UTENTE)
                .limit(pageRequest.getPageSize())
                .offset((int) pageRequest.getOffset())
                .fetchInto(Utente.class);
        return new PageImpl<>(utenti, pageRequest, dsl.fetchCount(dsl.selectFrom(Utente.UTENTE)));
    }
}
