package unimarket.services;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componenti.CartaCredito;
import componenti.Cliente;
import jooq.generated.Tables;

@Service
public class ClienteService {

	private final CartaService cartaService;
	private final DSLContext dsl;

    @Autowired
    public ClienteService(DSLContext dsl, CartaService cartaService) {
		this.cartaService = cartaService;
		this.dsl = dsl;
    }
	
    public void salvaCliente(Cliente cliente) {
        try {
            dsl.insertInto(Tables.CLIENTE)
                .set(Tables.CLIENTE.ID, cliente.getId())
                .set(Tables.CLIENTE.INDIRIZZO, cliente.getIndirizzo())
                .execute();
        } catch (DataAccessException e) {
            throw new RuntimeException("Errore durante il salvataggio del cliente: " + e.getMessage(), e);
        }
    }
    
    public void aggiungiCartaCredito(Cliente cliente, CartaCredito carta, boolean salvaCarta) {
        cliente.setNumeroCarta(carta.getNumeroCarta());  
        
        if (salvaCarta) {
            cartaService.salvaCarta(carta); 
        }
    }
    
    public Cliente getClienteById(int userId) {
        return dsl.selectFrom(Tables.UTENTE)
                  .where(Tables.UTENTE.ID.eq(userId))
                  .fetchOneInto(Cliente.class);
    }

    public CartaCredito getCartaCredito(int userId) {
    	String numeroCarta = dsl.select(Tables.CLIENTE.NUMERO_CARTA)
                                .from(Tables.CLIENTE)
                                .where(Tables.CLIENTE.ID.eq(userId))
                                .fetchOneInto(String.class);

        if (numeroCarta != null) {
            return dsl.selectFrom(Tables.CARTA_CREDITO)
                      .where(Tables.CARTA_CREDITO.NUMERO_CARTA.eq(numeroCarta))
                      .fetchOneInto(CartaCredito.class);
        } else {
            return null; 
        }
    }

  
}
