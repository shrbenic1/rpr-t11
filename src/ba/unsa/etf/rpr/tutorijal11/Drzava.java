package ba.unsa.etf.rpr.tutorijal11;

public class Drzava {
    private  int id;
    private String naziv;
    private Grad glavniGrad;

    public Drzava() {

    }

    public Drzava(String naziv, Grad glavniGrad) {
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
