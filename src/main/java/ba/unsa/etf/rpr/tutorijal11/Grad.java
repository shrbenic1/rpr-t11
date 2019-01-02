package ba.unsa.etf.rpr.tutorijal11;

public class Grad {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;

    public Grad() {

    }

    public Grad(String naziv, int brojStanovnika) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = null;
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
}
