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
	
	protected Utente(String nome, String cognome, String numero_telefono, String email, String password) {
		super();
		this.id = ID++;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numero_telefono;
		this.email = email;
		this.password = password;
	}
	
	public void creaAccount(String nome, String cognome, String numetoTelefono, String email, String password){
		try {
			DSLContext create = CreateDatabase.getInstance().getDSLContext();
			create.insertInto(Tables.UTENTE, Tables.UTENTE.ID, Tables.UTENTE.NOME, Tables.UTENTE.COGNOME, Tables.UTENTE.NUMERO_TELEFONO, Tables.UTENTE.EMAIL, Tables.UTENTE.PASSWORD)
			.values(this.id, nome, cognome, numeroTelefono, email, password)
			.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// da correggere con la query giusta
	public boolean login(String email, String password) {
		try {
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
