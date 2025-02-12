package componenti;

public class Prodotto {

    private int codice;
    private String nome;
    private String descrizione;
    private Float prezzo;
    private int quantita;
        
    public Prodotto() {}
    
    public Prodotto(int codice, String nome, String descrizione, Float prezzo, int quantita) {
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita; 
    }

    public int getCodice() {
        return codice;
    }
    
    public void setCodice(int codice) {
    	this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }
    
    public void setPrezzo(Float prezzo) {
    	this.prezzo = prezzo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

}
