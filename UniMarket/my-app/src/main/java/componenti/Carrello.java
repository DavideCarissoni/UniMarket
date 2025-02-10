package componenti;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Carrello {

    private int idCarrello;
    private int idUtente;
    private List<Prodotto> prodotti = new ArrayList<>();
    private float totale;

    private List<CarrelloObserver> observers = new ArrayList<>();
    
    public Carrello(int idCarrello, int idUtente) {
        this.idCarrello = idCarrello;
        this.idUtente = idUtente;
        this.prodotti = new ArrayList<>();
        this.totale = 0;
    }
    
    public Carrello(int idUtente) {
        this.idUtente = idUtente;
        this.prodotti = new ArrayList<>();
        this.totale = 0;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }
    
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    
    public List<Prodotto> getProdotti() {
        return prodotti;
    }
    
    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
        notificaAggiornamento();
    }

    public float getTotale() {
        return totale;
    }
    
    public void setTotale(float totale) {
        this.totale = totale;
        notificaAggiornamento();
    }
    
 // ------------------ GESTIONE OBSERVER ------------------

    public void aggiungiObserver(CarrelloObserver observer) {
        observers.add(observer);
    }

    public void rimuoviObserver(CarrelloObserver observer) {
        observers.remove(observer);
    }

    private void notificaAggiornamento() {
        for (CarrelloObserver observer : observers) {
            observer.onProdottoAggiunto(null, 0);  // Notifica un aggiornamento generico
        }
    }

    public void notificaProdottoAggiunto(Prodotto prodotto, int quantita) {
        for (CarrelloObserver observer : observers) {
            observer.onProdottoAggiunto(prodotto, quantita);
        }
    }

    public void notificaProdottoRimosso(Prodotto prodotto, int quantita) {
        for (CarrelloObserver observer : observers) {
            observer.onProdottoRimosso(prodotto, quantita);
        }
    }

}
