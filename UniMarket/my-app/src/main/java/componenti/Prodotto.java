package componenti;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.impl.DSL;

import jooq.generated.Tables;
import db.CreateDatabase;

public class Prodotto {

    private static int contatore = 1;
    private int codice;
    private String nome;
    private String descrizione;
    private float prezzo;
    private int quantita;

    public Prodotto(String nome, String descrizione, float prezzo, int quantita) {
        this.codice = contatore++;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public void nuovoProdotto(Prodotto prodotto){
        try {
            DSLContext create = CreateDatabase.getDSLContext();
            create.insertInto(Tables.PRODOTTO, Tables.PRODOTTO.CODICE, Tables.PRODOTTO.NOME, Tables.PRODOTTO.DESCRIZIONE, 
            Tables.PRODOTTO.PREZZO, Tables.PRODOTTO.QUANTITÀ)
            .values(prodotto.getCodice(), prodotto.getNome(), prodotto.getDescrizione(), prodotto.getPrezzo(), prodotto.getQuantita())
            .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void modificaQuantita(int codice, int quantita){
        try {
            DSLContext create = CreateDatabase.getDSLContext();
            create.update(Tables.PRODOTTO)
            .set(Tables.PRODOTTO.QUANTITÀ, quantita)
            .where(Tables.PRODOTTO.CODICE.eq(codice))
            .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rimuoviProdotto(int codice){
        try {
            DSLContext create = CreateDatabase.getDSLContext();
            create.delete(Tables.PRODOTTO)
            .where(Tables.PRODOTTO.CODICE.eq(codice))
            .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

}
