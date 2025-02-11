package unimarket.observer;

import componenti.Prodotto;

public interface CarrelloObserver {
    void onProdottoAggiunto(Prodotto prodotto, int quantita);
    void onProdottoRimosso(Prodotto prodotto, int quantita);
	void onAggiornamento();
}