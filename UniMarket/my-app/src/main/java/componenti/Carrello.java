package componenti;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Carrello {

    private static final Logger logger = Logger.getLogger(Carrello.class.getName());

    private int idCrrello;
    private int idUtente;
    private List<Prodotto> prodotti = new ArrayList<>();
    private float totale;

    public Carrello(int id, int idUtente) {
        this.idCrrello = id;
        this.idUtente = idUtente;
        this.prodotti = new ArrayList<>();
        this.totale = 0;
    }

    public void aggiungiProdotto(Prodotto nuovoProdotto, int quantita) {
        if (nuovoProdotto != null && quantita > 0) {
            for (int i = 0; i < quantita; i++) {
                prodotti.add(nuovoProdotto);
            }
            calcolaTotale(); 
        } else {
            logger.warning("Tentativo di aggiungere un prodotto nullo o con quantità non valida");
        }
    }
    
    public int getQuantitaProdotto(Prodotto prodotto) {
        int qta = 0;
    	for (Prodotto prod: prodotti) {
            if (prod.getCodice() == prodotto.getCodice()) {
                qta++;
            }
    	}
        return qta;
    }
    public void rimuoviProdotto(Prodotto prodottoRimosso, int quantita) {
        int qta = getQuantitaProdotto(prodottoRimosso);
        if (prodottoRimosso != null && quantita > 0 && qta >= quantita) {
            for (int i = 0; i < quantita; i++) {
                prodotti.remove(prodottoRimosso);
            }
            calcolaTotale(); 
        } else {
            logger.warning("Tentativo di rimuovere un prodotto nullo o con quantità non valida");
        }
    }

    public int getIdCarrello() {
        return idCrrello;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public float getTotale() {
        return totale;
    }

    public void calcolaTotale() {
        totale = 0;
        for(Prodotto prodotto : prodotti){
            totale += prodotto.getPrezzo();
        }
    }
}
