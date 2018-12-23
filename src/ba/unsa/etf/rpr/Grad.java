package ba.unsa.etf.rpr;

public class Grad {
    private Integer id;
    private String naziv;
    private Integer brojStanovnika;
    private Drzava drzava;

    public Grad() {

    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
    }
}
