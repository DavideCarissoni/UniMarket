package componenti;

import org.jooq.DSLContext;
import jooq.generated.Tables;

import db.CreateDatabase;

public class CartaCredito {

    private String numeroCarta;
    private String codiceSicurezza;
    private String nomeIntestatario;
    private String cognomeIntestatario;
    private final DSLContext dsl;

    public CartaCredito(String numeroCarta, String codiceSicurezza, String nomeIntestatario, String cognomeIntestatario, DSLContext dsl) {
        this.dsl = dsl;
		this.numeroCarta = numeroCarta;
        this.codiceSicurezza = codiceSicurezza;
        this.nomeIntestatario = nomeIntestatario;
        this.cognomeIntestatario = cognomeIntestatario;
    }

    public void nuovaCarta(){
        try {
        	dsl.insertInto(Tables.CARTA_CREDITO, Tables.CARTA_CREDITO.NUMERO_CARTA, Tables.CARTA_CREDITO.CODICE_SICUREZZA, 
            		Tables.CARTA_CREDITO.NOME_INTESTATARIO, Tables.CARTA_CREDITO.COGNOME_INTESTATARIO)
            .values(this.numeroCarta, this.codiceSicurezza, this.nomeIntestatario, this.cognomeIntestatario)
            .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public String getCodiceSicurezza() {
        return codiceSicurezza;
    }

    public String getNomeIntestatario() {
        return nomeIntestatario;
    }

    public String getCognomeIntestatario() {
        return cognomeIntestatario;
    }

}
