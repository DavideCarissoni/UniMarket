package componenti;

import org.jooq.DSLContext;

public class Amministratore extends Utente{

    private final DSLContext dsl;
    
	public Amministratore(String nome, String cognome, String numeroTelefono, String email, String password, String ruolo, DSLContext dsl) {
		super(nome, cognome, numeroTelefono, email, password, dsl);
		this.dsl = dsl;
	}

	private String ruolo;

	public String getRuolo() {
		return ruolo;
	}

	private Prodotto prodotto;
	/*
	public void aggiungiProdotto(Prodotto prodotto) {
		prodottoService.nuovoProdotto(prodotto);
	}

	public void modificaQuantita(int codice, int quantita) {
		int qta = quantita;
		prodotto.modificaQuantita(codice, qta);
	}

	public void rimuoviProdotto(int codice) {
		prodotto.rimuoviProdotto(codice);
	}
	*/
}
