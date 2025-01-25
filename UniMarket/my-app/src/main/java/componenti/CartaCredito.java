package componenti;

import java.sql.SQLException;

import org.jooq.DSLContext;
import jooq.generated.Tables;

import db.CreateDatabase;

public class CartaCredito {

    private String numeroCarta;
    private String codiceSicurezza;
    private String nomeIntestatario;
    private String cognomeIntestatario;

    public CartaCredito(String numeroCarta, String codiceSicurezza, String nomeIntestatario, String cognomeIntestatario) {
        this.numeroCarta = numeroCarta;
        this.codiceSicurezza = codiceSicurezza;
        this.nomeIntestatario = nomeIntestatario;
        this.cognomeIntestatario = cognomeIntestatario;
    }

    public void nuovaCarta(){
        try {
            DSLContext create = CreateDatabase.getDSLContext();
            create.insertInto(Tables.CARTA_CREDITO, Tables.CARTA_CREDITO.NUMERO_CARTA, Tables.CARTA_CREDITO.CODICE_SICUREZZA, 
            		Tables.CARTA_CREDITO.NOME_INTESTATARIO, Tables.CARTA_CREDITO.COGNOME_INTESTATARIO)
            .values(this.numeroCarta, this.codiceSicurezza, this.nomeIntestatario, this.cognomeIntestatario)
            .execute();
        } catch (SQLException e) {
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
