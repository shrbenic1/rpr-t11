package ba.unsa.etf.rpr;

public class Drzava {
    private Integer id;
    private String naziv;
    private Grad glavniGrad;

    public Drzava() {

    }

    public Drzava(Integer id, String naziv, Grad glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }
}
