package unimarket.observer;

import componenti.Prodotto;
import unimarket.views.checkoutform.CheckoutFormView;

public class CarrelloUIObserver implements CarrelloObserver {

    public CarrelloUIObserver(CheckoutFormView checkoutFormView) {
    }

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
