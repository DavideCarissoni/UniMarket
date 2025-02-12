package unimarket.services;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componenti.CartaCredito;
import jooq.generated.Tables;

@Service
public class CartaService {

    private final DSLContext dsl;

    @Autowired
    public CartaService(DSLContext dsl) {
    	this.dsl = dsl;
    }
	public void salvaCarta(CartaCredito carta){
	        try {
	        	dsl.insertInto(Tables.CARTA_CREDITO)
	        			.set(Tables.CARTA_CREDITO.NUMERO_CARTA, carta.getNumeroCarta())
	        			.set(Tables.CARTA_CREDITO.CODICE_SICUREZZA, carta.getCodiceSicurezza()) 
	            		.set(Tables.CARTA_CREDITO.NOME_INTESTATARIO, carta.getNomeIntestatario())
	            		.set(Tables.CARTA_CREDITO.COGNOME_INTESTATARIO, carta.getCognomeIntestatario()) 
	            .execute();
	        } catch (DataAccessException e) {
	            throw new RuntimeException("Errore durante l'inserimento della carta: " + e.getMessage(), e);
	        }
	    }
}
