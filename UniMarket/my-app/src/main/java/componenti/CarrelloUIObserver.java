package componenti;

import unimarket.views.checkoutform.CheckoutFormView;

public class CarrelloUIObserver implements CarrelloObserver {

    private CheckoutFormView checkoutFormView;

    public CarrelloUIObserver(CheckoutFormView checkoutFormView) {
        this.checkoutFormView = checkoutFormView;
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
}
