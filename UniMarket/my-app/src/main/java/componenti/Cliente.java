package componenti;

import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import jooq.generated.Tables;
import db.CreateDatabase;

public class Cliente extends Utente {
	
	private int id;
	private String indirizzo;
	
	public Cliente(int ID, String nome, String cognome, String numeroTelefono, String email, String password, String indirizzo) {
		super(nome, cognome, numeroTelefono, email, password);
		this.indirizzo = indirizzo;
		this.id = ID;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
	private List<CartaCredito> carte = new ArrayList<>();

	public void aggiungiCartaCredito(CartaCredito carta) {
		carta.nuovaCarta();
		carte.add(carta);
	}

	public void rimuoviCartaCredito(CartaCredito carta) {
		carte.remove(carta);
	}
	
	public List<CartaCredito> getCarte() {
		return carte;
	}

	public void salvaDati(){
		try {
			DSLContext create = CreateDatabase.getDSLContext();
			create.insertInto(Tables.CLIENTE, Tables.CLIENTE.ID, Tables.CLIENTE.INDIRIZZO, Tables.CLIENTE.NUMERO_CARTA)
			.values(this.id, this.indirizzo, this.carte.toString())
			.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
