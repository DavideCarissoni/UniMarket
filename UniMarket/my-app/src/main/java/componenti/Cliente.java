package componenti;

import java.beans.ConstructorProperties;

public class Cliente extends Utente {
	
	private int id;
	private String indirizzo;
	private String numeroCarta;
	
    @ConstructorProperties({"nome", "cognome", "numeroTelefono", "email", "password", "indirizzo"})
	public Cliente(int ID, String nome, String cognome, String numeroTelefono, String email, String password, String indirizzo) {	
		super(nome, cognome, numeroTelefono, email, password);
		this.indirizzo = indirizzo;
		this.id = ID;
	}

	public int getId() {
        return id;
    }
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public String getNumeroCarta() {
		return numeroCarta;
	}
	
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;		
	}
}
