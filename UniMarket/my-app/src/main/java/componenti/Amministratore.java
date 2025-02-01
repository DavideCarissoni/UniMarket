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
	
	public void aggiungiProdotto(Prodotto prodotto) {
		prodotto.nuovoProdotto(prodotto);
	}

	public void modificaQuantita(int codice, int quantita) {
		int qta = quantita;
		prodotto.modificaQuantita(codice, qta);
	}

	public void rimuoviProdotto(int codice) {
		prodotto.rimuoviProdotto(codice);
	}
}
