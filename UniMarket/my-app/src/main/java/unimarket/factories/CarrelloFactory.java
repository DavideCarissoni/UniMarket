package unimarket.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import componenti.Carrello;
import unimarket.services.ICarrelloService;

@Component
public class CarrelloFactory {
    @Autowired
    private ICarrelloService carrelloService;

    public Carrello creaCarrello(int idUtente) {
        Carrello carrello = carrelloService.getCarrello(idUtente);
        if (carrello == null) {
            carrello = new Carrello(idUtente);
            carrelloService.salvaCarrello(carrello);
        }
        return carrello;
    }
}
