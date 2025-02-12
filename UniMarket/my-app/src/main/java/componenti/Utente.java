package componenti;

public abstract class Utente {

	private static int ID=0;
	private int id;
	private String nome;
	private String cognome;
	private String numeroTelefono;
	private String email;
	private String password;
	
	protected Utente(String nome, String cognome, String telefono, String email, String password) {
		super();
		this.id = ID++;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = telefono;
		this.email = email;
		this.password = password;
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
