/*
 * This file is generated by jOOQ.
 */
package jooq.generated;


import java.util.Arrays;
import java.util.List;
import jooq.generated.tables.Carrello;
import jooq.generated.tables.CarrelloProdotto;
import jooq.generated.tables.CartaCredito;
import jooq.generated.tables.Cliente;
import jooq.generated.tables.HteSamplePerson;
import jooq.generated.tables.Idgenerator;
import jooq.generated.tables.Prodotto;
import jooq.generated.tables.SamplePerson;
import jooq.generated.tables.Utente;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>carrello</code>.
     */
    public final Carrello CARRELLO = Carrello.CARRELLO;

    /**
     * The table <code>carrello_prodotto</code>.
     */
    public final CarrelloProdotto CARRELLO_PRODOTTO = CarrelloProdotto.CARRELLO_PRODOTTO;

    /**
     * The table <code>carta_credito</code>.
     */
    public final CartaCredito CARTA_CREDITO = CartaCredito.CARTA_CREDITO;

    /**
     * The table <code>cliente</code>.
     */
    public final Cliente CLIENTE = Cliente.CLIENTE;

    /**
     * The table <code>HTE_sample_person</code>.
     */
    public final HteSamplePerson HTE_SAMPLE_PERSON = HteSamplePerson.HTE_SAMPLE_PERSON;

    /**
     * The table <code>idgenerator</code>.
     */
    public final Idgenerator IDGENERATOR = Idgenerator.IDGENERATOR;

    /**
     * The table <code>prodotto</code>.
     */
    public final Prodotto PRODOTTO = Prodotto.PRODOTTO;

    /**
     * The table <code>sample_person</code>.
     */
    public final SamplePerson SAMPLE_PERSON = SamplePerson.SAMPLE_PERSON;

    /**
     * The table <code>utente</code>.
     */
    public final Utente UTENTE = Utente.UTENTE;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Carrello.CARRELLO,
            CarrelloProdotto.CARRELLO_PRODOTTO,
            CartaCredito.CARTA_CREDITO,
            Cliente.CLIENTE,
            HteSamplePerson.HTE_SAMPLE_PERSON,
            Idgenerator.IDGENERATOR,
            Prodotto.PRODOTTO,
            SamplePerson.SAMPLE_PERSON,
            Utente.UTENTE
        );
    }
}
