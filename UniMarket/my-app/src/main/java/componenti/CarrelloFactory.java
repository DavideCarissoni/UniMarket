package componenti;

import unimarket.services.CarrelloService;

public class CarrelloFactory {
    public static Carrello creaCarrello(int idUtente, CarrelloService carrelloService) {
        Carrello carrello = carrelloService.getCarrello(idUtente);
        if (carrello == null) {
            carrello = new Carrello(idUtente);
            carrelloService.salvaCarrello(carrello);
        }
        return carrello;
    }
}
