package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private static Connection conn;
    private ArrayList<Grad> gradovi = new ArrayList<>();
    private ArrayList<Drzava> drzave = new ArrayList<>();

    private static void initialize() {
        instance = new GeografijaDAO();
    }

    private void insert() {
        Grad pariz = new Grad(1,"Pariz", 2206488);
        Drzava francuska = new Drzava(1,"Francuska", pariz);
        pariz.setDrzava(francuska);
        francuska.setGlavniGrad(pariz);
        dodajDrzavu(francuska);
        dodajGrad(pariz);
        Grad london = new Grad(2, "London", 8825000 );
        Drzava uk = new Drzava(2, "UK", london);
        london.setDrzava(uk);
        uk.setGlavniGrad(london);
        dodajDrzavu(uk);
        dodajGrad(london);
        Grad manchester = new Grad(3,"Manchester", 545500);
        manchester.setDrzava(uk);
        dodajGrad(manchester);
        Grad bec = new Grad(4, "Beč", 1899055);
        Drzava austrija = new Drzava(3, "Austrija", bec);
        bec.setDrzava(austrija);
        austrija.setGlavniGrad(bec);
        dodajDrzavu(austrija);
        dodajGrad(bec);
        Grad graz = new Grad(5, "Graz",280200);
        graz.setDrzava(austrija);
        dodajGrad(graz);
        gradovi.add(pariz);
        gradovi.add(london);
        gradovi.add(manchester);
        gradovi.add(bec);
        gradovi.add(graz);
        drzave.add(francuska);
        drzave.add(uk);
        drzave.add(austrija);
    }

    private static void createNewTable() {
        String grad = "CREATE TABLE IF NOT EXISTS grad\n" +
                "(\n" +
                "    id int PRIMARY KEY,\n" +
                "    naziv text,\n" +
                "    broj_stanovnika int,\n" +
                "    drzava int,\n" +
                "    CONSTRAINT grad_drzava_id_fk FOREIGN KEY (drzava) REFERENCES drzava (id)\n" +
                ");";
        String drzava = "CREATE TABLE IF NOT EXISTS drzava\n" +
                "(\n" +
                "    id int PRIMARY KEY,\n" +
                "    naziv text,\n" +
                "    glavni_grad int,\n" +
                "    CONSTRAINT drzava_grad_id_fk FOREIGN KEY (glavni_grad) REFERENCES grad (id)\n" +
                ");";
        try (PreparedStatement statement1 = conn.prepareStatement(grad);
             PreparedStatement statement2 = conn.prepareStatement(drzava)) {
            statement1.execute();
            statement2.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private GeografijaDAO() {
        try {
            String url = "jdbc:sqlite:baza.db";
            conn = DriverManager.getConnection(url);
            createNewTable();
            insert();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) initialize();
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
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT OR REPLACE INTO grad(naziv, broj_stanovnika, drzava) VALUES(?,?,?)");
            statement.setString(1, grad.getNaziv());
            statement.setInt(2, grad.getBrojStanovnika());
            statement.setInt(3, grad.getDrzava().getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void dodajDrzavu(Drzava drzava) {

    }

    void izmijeniGrad(Grad grad) {

    }

    Drzava nadjiDrzavu(String drzava) {

        return null;
    }

    Grad nadjiGrad(String grad) {

        return null;
    }
}

