package unimarket.services;

import componenti.Carrello;
import componenti.CarrelloObserver;
import componenti.Prodotto;
import jooq.generated.Tables;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CarrelloService {

    private final DSLContext dsl;
    private static final Logger logger = Logger.getLogger(CarrelloService.class.getName());

    private final List<CarrelloObserver> observers = new ArrayList<>();

    @Autowired
    public CarrelloService(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Ottiene o crea il carrello di un utente.
     */
    public Carrello getOrCreateCarrello(int idUtente) {
        Carrello carrello = getCarrello(idUtente);
        if (carrello == null) {
            carrello = new Carrello(idUtente);
            salvaCarrello(carrello);
        }
        return carrello;
    }

    /**
     * Ottiene il carrello di un utente dal database.
     */
    public Carrello getCarrello(int idUtente) {
        Carrello carrello = dsl.select(
                Tables.CARRELLO.ID_CARRELLO,
                Tables.CARRELLO.ID_CLIENTE
            )
            .from(Tables.CARRELLO)
            .where(Tables.CARRELLO.ID_CLIENTE.eq(idUtente))
            .fetchOneInto(Carrello.class);

        if (carrello != null) {
            // Recupera i prodotti associati al carrello
            List<Prodotto> prodotti = dsl.select(
                    Tables.PRODOTTO.CODICE,
                    Tables.PRODOTTO.NOME,
                    Tables.PRODOTTO.DESCRIZIONE,
                    Tables.PRODOTTO.PREZZO,
                    Tables.PRODOTTO.QUANTITÀ
                )
                .from(Tables.PRODOTTO)
                .join(Tables.CARRELLO_PRODOTTO)
                .on(Tables.PRODOTTO.CODICE.eq(Tables.CARRELLO_PRODOTTO.CODICE))
                .where(Tables.CARRELLO_PRODOTTO.ID_CARRELLO.eq(carrello.getIdCarrello()))
                .fetchInto(Prodotto.class);

            carrello.setProdotti(prodotti);
            carrello.setTotale(calcolaTotale(carrello));
        }

        return carrello;
    }

    /**
     * Salva un nuovo carrello nel database.
     */
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

    /**
     * Aggiunge un prodotto al carrello e notifica gli observer.
     */
    public void aggiungiProdotto(Carrello carrello, Prodotto nuovoProdotto, int quantita) {
        if (nuovoProdotto != null && quantita > 0) {
            for (int i = 0; i < quantita; i++) {
                carrello.getProdotti().add(nuovoProdotto);
            }
            carrello.setTotale(calcolaTotale(carrello));
            aggiornaCarrello(carrello);
            notificaProdottoAggiunto(nuovoProdotto, quantita);
            logger.info("Prodotto aggiunto al carrello: " + nuovoProdotto.getNome());
        } else {
            logger.warning("Tentativo di aggiungere un prodotto nullo o con quantità non valida");
        }
    }

    /**
     * Rimuove un prodotto dal carrello e notifica gli observer.
     */
    public void rimuoviProdotto(Carrello carrello, Prodotto prodottoRimosso, int quantita) {
        int qta = getQuantitaProdotto(carrello, prodottoRimosso);
        if (prodottoRimosso != null && quantita > 0 && qta >= quantita) {
            for (int i = 0; i < quantita; i++) {
                carrello.getProdotti().remove(prodottoRimosso);
            }
            carrello.setTotale(calcolaTotale(carrello));
            aggiornaCarrello(carrello);
            notificaProdottoRimosso(prodottoRimosso, quantita);
            logger.info("Prodotto rimosso dal carrello: " + prodottoRimosso.getNome());
        } else {
            logger.warning("Tentativo di rimuovere un prodotto nullo o con quantità non valida");
        }
    }

    /**
     * Calcola la quantità di un prodotto nel carrello.
     */
    public int getQuantitaProdotto(Carrello carrello, Prodotto prodotto) {
        return (int) carrello.getProdotti().stream()
                .filter(p -> p.getCodice() == prodotto.getCodice())
                .count();
    }

    /**
     * Calcola il totale del carrello.
     */
    public float calcolaTotale(Carrello carrello) {
        return (float) carrello.getProdotti().stream()
                .mapToDouble(Prodotto::getPrezzo)
                .sum();
    }

    /**
     * Aggiorna il carrello nel database.
     */
    private void aggiornaCarrello(Carrello carrello) {
        // Rimuovi tutti i prodotti associati al carrello
        dsl.delete(Tables.CARRELLO_PRODOTTO)
            .where(Tables.CARRELLO_PRODOTTO.ID_CARRELLO.eq(carrello.getIdCarrello()))
            .execute();

        // Aggiungi i prodotti aggiornati al carrello
        for (Prodotto prodotto : carrello.getProdotti()) {
            dsl.insertInto(Tables.CARRELLO_PRODOTTO)
                .set(Tables.CARRELLO_PRODOTTO.ID_CARRELLO, carrello.getIdCarrello())
                .set(Tables.CARRELLO_PRODOTTO.CODICE, prodotto.getCodice())
                .execute();
        }

        // Aggiorna il totale del carrello
        dsl.update(Tables.CARRELLO)
            .set(Tables.CARRELLO.TOTALE, carrello.getTotale())
            .where(Tables.CARRELLO.ID_CARRELLO.eq(carrello.getIdCarrello()))
            .execute();
    }

    // ------------------ GESTIONE OBSERVER ------------------

    public void aggiungiObserver(int idUtente, CarrelloObserver observer) {
        Carrello carrello = getOrCreateCarrello(idUtente);
        if (carrello != null) {
            carrello.aggiungiObserver(observer);
        }
    }

    public void rimuoviObserver(CarrelloObserver observer) {
        observers.remove(observer);
    }

    private void notificaProdottoAggiunto(Prodotto prodotto, int quantita) {
        for (CarrelloObserver observer : observers) {
            observer.onProdottoAggiunto(prodotto, quantita);
        }
    }

    private void notificaProdottoRimosso(Prodotto prodotto, int quantita) {
        for (CarrelloObserver observer : observers) {
            observer.onProdottoRimosso(prodotto, quantita);
        }
    }
}

