package unimarket.services;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import jooq.generated.Tables;
import componenti.Prodotto;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
	
@Service
public class ProdottoService {
	
	    private final DSLContext dsl;

	    @Autowired
	    public ProdottoService(DSLContext dsl) {
	        this.dsl = dsl;
	    }
	    
	    // Genera un nuovo codice a partire da quelli già esistenti
	    public int generaNuovoCodice() {
	        Integer maxCodice = dsl.select(DSL.max(Tables.PRODOTTO.CODICE))
	                               .from(Tables.PRODOTTO)
	                               .fetchOneInto(Integer.class);
	        return maxCodice != null ? maxCodice + 1 : 1; 
	    }
	    
	    public void nuovoProdotto(Prodotto prodotto){
	    	try {
	    		
	    		int nuovoCodice = generaNuovoCodice();
	            prodotto.setCodice(nuovoCodice);
	            
	    		dsl.insertInto(Tables.PRODOTTO)
	    		.set(Tables.PRODOTTO.CODICE, prodotto.getCodice())
	            .set(Tables.PRODOTTO.NOME, prodotto.getNome())
	            .set(Tables.PRODOTTO.DESCRIZIONE, prodotto.getDescrizione())
	            .set(Tables.PRODOTTO.PREZZO, prodotto.getPrezzo())
	            .set(Tables.PRODOTTO.QUANTITÀ, prodotto.getQuantita())
	            .execute();
	        }  catch (DataAccessException e) {
	            throw new RuntimeException("Errore durante l'inserimento del prodotto: " + e.getMessage(), e);
	        }
	    }
	    
	    public void modificaQuantita(int codice, int quantita){
	    	try {
	    		int quantitaAttuale = dsl.select(Tables.PRODOTTO.QUANTITÀ)
                        .from(Tables.PRODOTTO)
                        .where(Tables.PRODOTTO.CODICE.eq(codice))
                        .fetchOne(Tables.PRODOTTO.QUANTITÀ);

	    		int nuovaQuantita = quantitaAttuale - quantita;

	                dsl.update(Tables.PRODOTTO)
	                    .set(Tables.PRODOTTO.QUANTITÀ, nuovaQuantita)
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
		    
	    public Optional<Prodotto> get(Long id) {
	        try {
	            Prodotto prodotto = dsl.select(Tables.PRODOTTO.CODICE,
	                    Tables.PRODOTTO.NOME,
	                    Tables.PRODOTTO.DESCRIZIONE,
	                    Tables.PRODOTTO.PREZZO,
	                    Tables.PRODOTTO.QUANTITÀ
	                )
	                .from(Tables.PRODOTTO)
	                .where(Tables.PRODOTTO.CODICE.eq(id.intValue())) // Supponendo che CODICE sia la chiave primaria
	                .fetchOneInto(Prodotto.class);

	            return Optional.ofNullable(prodotto);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Optional.empty();
	        }
	    }
	    public List<Prodotto> getProdottiPaginati(int offset, int limit) {
	        return dsl.selectFrom(Tables.PRODOTTO)  
	                  .limit(limit)
	                  .offset(offset)
	                  .fetchInto(Prodotto.class);
	    }

	    public int countProdotti() {
	        return dsl.fetchCount(Tables.PRODOTTO);
	    }

	public int getQuantitaById(int id) {
		try {
			return dsl.select(Tables.PRODOTTO.QUANTITÀ)
					.from(Tables.PRODOTTO)
					.where(Tables.PRODOTTO.CODICE.eq(id))
					.fetchOneInto(Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore durante il recupero della quantità del prodotto: " + e.getMessage(), e);
		}
	}

}
