package componenti;

import java.sql.SQLException;
import org.jooq.DSLContext;
import jooq.generated.Tables;
import db.CreateDatabase;

public class Prodotto {

    private static int codice=0;
    private String nome;
    private String descrizione;
    private float prezzo;
    private int quantita;

    public Prodotto(int codice, String nome, String descrizione, float prezzo, int quantita) {
        Prodotto.codice = codice++;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public void nuovoProdotto(String nome, String descrizione, float prezzo, int quantita){
        try {
            DSLContext create = CreateDatabase.getDSLContext();
            create.insertInto(Tables.PRODOTTO, Tables.PRODOTTO.CODICE, Tables.PRODOTTO.NOME, Tables.PRODOTTO.DESCRIZIONE, 
            Tables.PRODOTTO.PREZZO, Tables.PRODOTTO.QUANTITÀ)
            .values(Prodotto.codice, this.nome, this.descrizione, this.prezzo, this.quantita)
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
