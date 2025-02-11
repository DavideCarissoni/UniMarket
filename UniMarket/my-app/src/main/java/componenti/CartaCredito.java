package componenti;

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
