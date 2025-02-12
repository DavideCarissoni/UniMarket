package unimarket.services;

import static org.mockito.Mockito.*;

import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.InsertSetStep;
import org.jooq.exception.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import componenti.CartaCredito;
import jooq.generated.Tables;
import jooq.generated.tables.records.CartaCreditoRecord;

@ExtendWith(MockitoExtension.class)
class CartaServiceTest {

    @Mock
    private DSLContext dsl;

    @Mock
    private InsertSetStep<CartaCreditoRecord> insertSetStep;

    @Mock
    private InsertSetMoreStep<CartaCreditoRecord> insertSetMoreStep;

    @InjectMocks
    private CartaService cartaService;

    private CartaCredito carta;

    @BeforeEach
    void setUp() {
        carta = new CartaCredito("1234567890123456", "123", "Mario", "Rossi");
    }
    @Test
    void testSalvaCarta_Successo() {
        when(dsl.insertInto(Tables.CARTA_CREDITO)).thenReturn(insertSetStep);
        when(insertSetStep.set(eq(Tables.CARTA_CREDITO.NUMERO_CARTA), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.set(eq(Tables.CARTA_CREDITO.CODICE_SICUREZZA), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.set(eq(Tables.CARTA_CREDITO.NOME_INTESTATARIO), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.set(eq(Tables.CARTA_CREDITO.COGNOME_INTESTATARIO), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.execute()).thenReturn(1);

        cartaService.salvaCarta(carta); 

        verify(dsl).insertInto(Tables.CARTA_CREDITO);
        verify(insertSetStep).set(eq(Tables.CARTA_CREDITO.NUMERO_CARTA), any(String.class));
        verify(insertSetMoreStep).set(eq(Tables.CARTA_CREDITO.CODICE_SICUREZZA), any(String.class));
        verify(insertSetMoreStep).set(eq(Tables.CARTA_CREDITO.NOME_INTESTATARIO), any(String.class));
        verify(insertSetMoreStep).set(eq(Tables.CARTA_CREDITO.COGNOME_INTESTATARIO), any(String.class));
        verify(insertSetMoreStep).execute();
    }

    @Test
    void testSalvaCarta_Errore() {
        when(dsl.insertInto(Tables.CARTA_CREDITO)).thenReturn(insertSetStep);
        when(insertSetStep.set(eq(Tables.CARTA_CREDITO.NUMERO_CARTA), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.set(eq(Tables.CARTA_CREDITO.CODICE_SICUREZZA), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.set(eq(Tables.CARTA_CREDITO.NOME_INTESTATARIO), any(String.class)))
                .thenReturn(insertSetMoreStep);
        when(insertSetMoreStep.set(eq(Tables.CARTA_CREDITO.COGNOME_INTESTATARIO), any(String.class)))
                .thenReturn(insertSetMoreStep);
        
        when(insertSetMoreStep.execute()).thenThrow(new DataAccessException("Errore DB"));

        RuntimeException thrown = null;
        try {
            cartaService.salvaCarta(carta);
        } catch (RuntimeException e) {
            thrown = e; 
        }

        assert thrown != null;
        assert thrown.getMessage().contains("Errore durante l'inserimento della carta");
    }

}
