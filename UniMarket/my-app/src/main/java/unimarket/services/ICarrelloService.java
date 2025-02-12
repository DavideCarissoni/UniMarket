package unimarket.services;

import componenti.Carrello;
import componenti.Prodotto;
import unimarket.observer.CarrelloObserver;

public interface ICarrelloService {
    Carrello getCarrello(int idUtente);
    void salvaCarrello(Carrello carrello);
    void aggiungiProdotto(Carrello carrello, Prodotto prodotto, int quantita);
    void rimuoviProdotto(Carrello carrello, Prodotto prodotto, int quantita);
    void aggiungiObserver(int idUtente, CarrelloObserver observer);
    void rimuoviObserver(int idUtente, CarrelloObserver observer);
}