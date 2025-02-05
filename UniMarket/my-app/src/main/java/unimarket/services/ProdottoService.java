package unimarket.services;

import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import componenti.Prodotto;
import java.util.List;
import jooq.generated.Tables;
import db.CreateDatabase;

@Service
public class ProdottoService {

    private final DSLContext dsl;

    public ProdottoService() {
    	this.dsl = CreateDatabase.getInstance().getDSLContext();    
    }
    
    public List<Prodotto> getAllProdotti() {
        return dsl.selectFrom(Tables.PRODOTTO)
        		.fetchInto(Prodotto.class);
    }
    
}
