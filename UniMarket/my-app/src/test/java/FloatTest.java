

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jooq.generated.Tables;
import unimarket.Application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest (classes = Application.class) // Carica il contesto Spring
public class FloatTest {

    @Autowired
    private DSLContext create; // Inietta il DSLContext configurato tramite Spring

    @Test
    public void testPrezzoConversion() {
        // Esegui una query semplice per verificare la conversione
        Float prezzo = create.select(Tables.PRODOTTO.PREZZO)
                             .from(Tables.PRODOTTO)
                             .where(Tables.PRODOTTO.CODICE.eq(1)) // Sostituisci con un ID valido
                             .fetchOneInto(Float.class);

        System.out.println("Prezzo: " + prezzo);
        assertNotNull(prezzo); // Verifica che il valore non sia nullo
    }
}