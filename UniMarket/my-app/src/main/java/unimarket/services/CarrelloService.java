package unimarket.services;

import componenti.Carrello;
import componenti.Prodotto;
import jooq.generated.Tables;
import unimarket.observer.CarrelloObserver;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CarrelloService implements ICarrelloService {
    private final DSLContext dsl;
    private static final Logger logger = Logger.getLogger(CarrelloService.class.getName());

    private final Map<Integer, List<CarrelloObserver>> observersMap = new HashMap<>();

    @Autowired
    public CarrelloService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Carrello getOrCreateCarrello(int idUtente) {
        Carrello carrello = getCarrello(idUtente);
        if (carrello == null) {
            carrello = new Carrello(idUtente);
            salvaCarrello(carrello);
        }
        return carrello;
    }

    public Carrello getCarrello(int idUtente) {
        return dsl.select(
                Tables.CARRELLO.ID_CARRELLO,
                Tables.CARRELLO.ID_CLIENTE
        		).from(Tables.CARRELLO)
        		.where(Tables.CARRELLO.ID_CLIENTE.eq(idUtente))
        		.fetchOneInto(Carrello.class);
    }

    public void salvaCarrello(Carrello carrello) {
        int idCarrello = dsl.insertInto(Tables.CARRELLO)
                .set(Tables.CARRELLO.ID_CLIENTE, carrello.getIdUtente())
                .set(Tables.CARRELLO.TOTALE, 0.0f)
                .returning(Tables.CARRELLO.ID_CARRELLO)
                .fetchOne()
                .getIdCarrello();

        carrello.setIdCarrello(idCarrello);
        logger.info("Nuovo carrello creato per l'utente: " + carrello.getIdUtente());
    }

    public void aggiungiProdotto(Carrello carrello, Prodotto nuovoProdotto, int quantita) {
        if (nuovoProdotto != null && quantita > 0) {
            for (int i = 0; i < quantita; i++) {
                carrello.getProdotti().add(nuovoProdotto);
            }
            carrello.setTotale(carrello.getTotale() + nuovoProdotto.getPrezzo() * quantita);
            logger.info("Prodotto aggiunto al carrello: " + nuovoProdotto.getNome());
            
            notificaProdottoAggiunto(carrello.getIdUtente(), nuovoProdotto, quantita);
        }
    }

    public void rimuoviProdotto(Carrello carrello, Prodotto prodottoRimosso, int quantita) {
        if (prodottoRimosso != null && quantita > 0) {
            for (int i = 0; i < quantita; i++) {
                carrello.getProdotti().remove(prodottoRimosso);
            }
            carrello.setTotale(carrello.getTotale() - prodottoRimosso.getPrezzo() * quantita);
            logger.info("Prodotto rimosso dal carrello: " + prodottoRimosso.getNome());
            
            notificaProdottoRimosso(carrello.getIdUtente(), prodottoRimosso, quantita);
        }
    }

    @Override
    public void aggiungiObserver(int idUtente, CarrelloObserver observer) {
        observersMap.computeIfAbsent(idUtente, k -> new ArrayList<>()).add(observer);
    }

    @Override
    public void rimuoviObserver(int idUtente, CarrelloObserver observer) {
        List<CarrelloObserver> observers = observersMap.get(idUtente);
        if (observers != null) {
            observers.remove(observer);
            if (observers.isEmpty()) {
                observersMap.remove(idUtente);
            }
        }
    }

    private void notificaAggiornamentoGenerico(int idUtente) {
        List<CarrelloObserver> observers = observersMap.get(idUtente);
        if (observers != null) {
            for (CarrelloObserver observer : observers) {
                observer.onAggiornamento();
            }
        }
    }

    private void notificaProdottoAggiunto(int idUtente, Prodotto prodotto, int quantita) {
        List<CarrelloObserver> observers = observersMap.get(idUtente);
        if (observers != null) {
            for (CarrelloObserver observer : observers) {
                observer.onProdottoAggiunto(prodotto, quantita);
            }
        }
    }

    private void notificaProdottoRimosso(int idUtente, Prodotto prodotto, int quantita) {
        List<CarrelloObserver> observers = observersMap.get(idUtente);
        if (observers != null) {
            for (CarrelloObserver observer : observers) {
                observer.onProdottoRimosso(prodotto, quantita);
            }
        }
    }
}

