package unimarket.services;

import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import componenti.Prodotto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jooq.generated.Tables;
import db.CreateDatabase;

@Service
public class ProdottoService {

    private final DSLContext dsl;

    public ProdottoService(DSLContext dsl) {
        this.dsl = dsl;
    }
    
    public List<Prodotto> getAllProdotti() {
        List<Prodotto> prodotti = new ArrayList<>();
        try {
            CreateDatabase createDatabase = CreateDatabase.getInstance();
            DSLContext dsl = CreateDatabase.getDSLContext();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prodotti = dsl.selectFrom(Tables.PRODOTTO).fetchInto(Prodotto.class);
        return prodotti;
    }
    
}
