package componenti;

public class Amministratore extends Utente{

	public Amministratore(String nome, String cognome, String numeroTelefono, String email, String password, String ruolo) {
		super(nome, cognome, numeroTelefono, email, password);
	}

	private String ruolo;

	public String getRuolo() {
		return ruolo;
	}

	private Prodotto prodotto;
	
	public void aggiungiProdotto(String nome, String descrizione, float prezzo, int quantita) {
		prodotto.nuovoProdotto(nome, descrizione, prezzo, quantita);
	}

	public void modificaQuantita(int codice, int quantita) {
		int qta = quantita;
		prodotto.modificaQuantita(codice, qta);
	}

	public void rimuoviProdotto(int codice) {
		prodotto.rimuoviProdotto(codice);
	}
}
