package componenti;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import jooq.generated.Tables;
import db.CreateDatabase;

public class Prodotto {

    private static int contatore = 1;
    private int codice;
    private String nome;
    private String descrizione;
    private Float prezzo;
    private int quantita;
        
    public Prodotto() {}
    
    public Prodotto(String nome, String descrizione, Float prezzo, int quantita) {
        this.codice = contatore++;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita; 
    }
  
    /*
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
    */

    public int getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }
    
    public void setPrezzo(Float prezzo) {
    	this.prezzo = prezzo;
    }

}
