package unimarket.services;

import componenti.Carrello;
import componenti.Prodotto;
import jooq.generated.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrelloService {
    private final DSLContext dsl;

    public CarrelloService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Carrello getCarrello() {
        return dsl.select(Tables.CARRELLO.ID_CARRELLO,
                        Tables.CARRELLO.ID_CLIENTE
                        )
                .from(Tables.CARRELLO)
                .fetchOneInto(Carrello.class);
    }


}
