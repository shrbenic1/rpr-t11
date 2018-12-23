package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private static Connection conn;

    private static void initialize() {

    }

    private GeografijaDAO() {

    }

    public GeografijaDAO getInstance() {
        if(instance == null) initialize();
        return instance;
    }

    Grad glavniGrad(String drzava) {
        return null;
    }

    void obrisiDrzavu(String drzava) {

    }

    ArrayList<Grad> gradovi() {
        return null;
    }

    void dodajGrad(Grad grad) {

    }

    void dodajDrzavu(Drzava drzava) {

    }

    void izmijeniGrad(Grad grad) {

    }

    Drzava nadjiDrzavu(String drzava) {
        return null;
    }
}

