package componenti;

import org.jooq.DSLContext;
import db.CreateDatabase;
import jooq.generated.Tables;

public abstract class Utente {

	private static int ID=0;
	private int id;
	private String nome;
	private String cognome;
	private String numeroTelefono;
	private String email;
	private String password;
    private final DSLContext dsl;
	
	protected Utente(String nome, String cognome, String telefono, String email, String password, DSLContext dsl) {
		super();
		this.id = ID++;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = telefono;
		this.email = email;
		this.password = password;
		this.dsl = dsl;
	}
	
	public int getID() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
}
