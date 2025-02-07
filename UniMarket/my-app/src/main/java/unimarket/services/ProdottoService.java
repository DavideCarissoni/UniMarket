package unimarket.services;

import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import jooq.generated.Tables;
import componenti.Prodotto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
	
@Service
public class ProdottoService {
	
	    private final DSLContext dsl;

	    @Autowired
	    public ProdottoService(DSLContext dsl) {
	        this.dsl = dsl;
	    }
	    
	    public void nuovoProdotto(Prodotto prodotto){
	    	try {
	    		dsl.insertInto(Tables.PRODOTTO)
	            .set(Tables.PRODOTTO.NOME, prodotto.getNome())
	            .set(Tables.PRODOTTO.DESCRIZIONE, prodotto.getDescrizione())
	            .set(Tables.PRODOTTO.PREZZO, prodotto.getPrezzo())
	            .set(Tables.PRODOTTO.QUANTITÀ, prodotto.getQuantita())
	            .execute();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void modificaQuantita(int codice, int quantita){
	        	try {
	                dsl.update(Tables.PRODOTTO)
	                    .set(Tables.PRODOTTO.QUANTITÀ, quantita)
	                    .where(Tables.PRODOTTO.CODICE.eq(codice))
	                    .execute();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	    }

	    public void rimuoviProdotto(int codice){
	    	try {
	            dsl.delete(Tables.PRODOTTO)
	                .where(Tables.PRODOTTO.CODICE.eq(codice))
	                .execute();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Prodotto> getAllProdotti() {
	        return dsl.select(Tables.PRODOTTO.CODICE,
	                Tables.PRODOTTO.NOME,
	                Tables.PRODOTTO.DESCRIZIONE,
	                Tables.PRODOTTO.PREZZO,
	                Tables.PRODOTTO.QUANTITÀ
	            )
	        		.from(Tables.PRODOTTO)
	        		.fetchInto(Prodotto.class);
	    }
		    
}
