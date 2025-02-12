package componenti;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private int idCarrello;
    private int idUtente;
    private List<Prodotto> prodotti = new ArrayList<>();
    private float totale;

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
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
