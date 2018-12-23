package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private static Connection conn;
    private ArrayList<Grad> gradovi = new ArrayList<>();
    private ArrayList<Drzava> drzave = new ArrayList<>();

    private static void initialize() {
        instance = new GeografijaDAO();
    }

    private void insert() {
        Grad pariz = new Grad("Pariz", 2206488);
        Drzava francuska = new Drzava("Francuska", pariz);
        pariz.setDrzava(francuska);
        francuska.setGlavniGrad(pariz);
        dodajDrzavu(francuska);
        dodajGrad(pariz);
        Grad london = new Grad("London", 8825000 );
        Drzava uk = new Drzava("UK", london);
        london.setDrzava(uk);
        uk.setGlavniGrad(london);
        dodajDrzavu(uk);
        dodajGrad(london);
        Grad manchester = new Grad("Manchester", 545500);
        manchester.setDrzava(uk);
        dodajGrad(manchester);
        Grad bec = new Grad("Beč", 1899055);
        Drzava austrija = new Drzava("Austrija", bec);
        bec.setDrzava(austrija);
        austrija.setGlavniGrad(bec);
        dodajDrzavu(austrija);
        dodajGrad(bec);
        Grad graz = new Grad("Graz",280200);
        graz.setDrzava(austrija);
        dodajGrad(graz);
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
        for(Drzava x : drzave) {
            if(x.getNaziv().equals(drzava)) {
                return x.getGlavniGrad();
            }
        }
        return null;
    }

    void obrisiDrzavu(String drzava) {

    }

    ArrayList<Grad> gradovi() {
        Collections.sort(gradovi, new Comparator<Grad>() {
            @Override
            public int compare(Grad grad1, Grad grad2) {
                if (grad1.getBrojStanovnika() > grad2.getBrojStanovnika()) {
                    return -1;
                } else if (grad1.getBrojStanovnika() < grad2.getBrojStanovnika()) {
                    return 1;
                }
                return 0;
            }
        });
        return gradovi;
    }

    void dodajGrad(Grad grad) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT OR REPLACE INTO grad(naziv, broj_stanovnika, drzava) VALUES(?,?,?)");
            statement.setString(1, grad.getNaziv());
            statement.setInt(2, grad.getBrojStanovnika());
            statement.setInt(3, grad.getDrzava().getId());
            statement.executeUpdate();
            gradovi.add(grad);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void dodajDrzavu(Drzava drzava) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT OR REPLACE INTO drzava(naziv, glavni_grad) VALUES(?,?)");
            statement.setString(1, drzava.getNaziv());
            statement.setInt(2, drzava.getGlavniGrad().getId());
            statement.executeUpdate();
            drzave.add(drzava);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void izmijeniGrad(Grad grad) {

    }

    Drzava nadjiDrzavu(String drzava) {
        Drzava trazenaDrzava = new Drzava();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id, naziv, glavni_grad FROM drzava WHERE naziv=?");
            statement.setString(1, drzava);
            ResultSet resultSet = statement.executeQuery();
            trazenaDrzava.setNaziv(resultSet.getString(2));
            trazenaDrzava.setId(resultSet.getInt(1));
            return trazenaDrzava;
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
        return null;
    }
}

