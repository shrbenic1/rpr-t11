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
        if (instance == null) {
            createNewDatabase();
            createNewTable();
            instance.insert();
        }
        instance = new GeografijaDAO();
    }

    private  void insert() {

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

    private static void createNewDatabase() {
        String url = "jdbc:sqlite:baza.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private GeografijaDAO() {

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

    }

    void dodajDrzavu(Drzava drzava) {

    }

    void izmijeniGrad(Grad grad) {

    }

    Drzava nadjiDrzavu(String drzava) {
        return null;
    }
}

