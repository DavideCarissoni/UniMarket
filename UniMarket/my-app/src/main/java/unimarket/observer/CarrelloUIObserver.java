package unimarket.observer;

import componenti.Prodotto;

public class CarrelloUIObserver implements CarrelloObserver {

    @Override
    public void onProdottoAggiunto(Prodotto prodotto, int quantita) {
        // Implementa la logica per aggiornare la UI
        System.out.println("Prodotto aggiunto alla UI: " + prodotto.getNome());
    }

    @Override
    public void onProdottoRimosso(Prodotto prodotto, int quantita) {
        // Implementa la logica per aggiornare la UI
        System.out.println("Prodotto rimosso dalla UI: " + prodotto.getNome());
    }

	@Override
	public void onAggiornamento() {
		// TODO Auto-generated method stub
		
	}
}
