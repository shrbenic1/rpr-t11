package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private static Connection conn;
    ArrayList<Grad> gradovi;
    ArrayList<Drzava> drzave;

    private static void initialize() {

    }

    private GeografijaDAO() {
        File file = new File("baza.db");
    }

    public static GeografijaDAO getInstance() {
        if(instance == null) initialize();
        return instance;
    }

    public static void removeInstance() {
        instance = null;
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

