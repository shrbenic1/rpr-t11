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

    private void insert() {
        Grad pariz = new Grad("Pariz", 2206488);
        Drzava francuska = new Drzava("Francuska", pariz);
        pariz.setDrzava(francuska);
        dodajGrad(pariz);
        dodajDrzavu(francuska);
        Grad london = new Grad("London", 8825000 );
        Drzava ujedinjenoKraljevstvo = new Drzava("Ujedinjeno Kraljevstvo", london);
        london.setDrzava(ujedinjenoKraljevstvo);
        dodajDrzavu(ujedinjenoKraljevstvo);
        dodajGrad(london);
        Grad manchester = new Grad("Manchester", 545500);
        manchester.setDrzava(ujedinjenoKraljevstvo);
        dodajGrad(manchester);
        Grad bec = new Grad("Beč", 1899055);
        Drzava austrija = new Drzava("Austrija", bec);
        bec.setDrzava(austrija);
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

    Grad nadjiGrad(String grad) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id, naziv, broj_stanovnika, drzava FROM grad WHERE naziv = ?");
            statement.setString(1, grad);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.isClosed()) {
               return null;
            }
            while(resultSet.next()) {
                return new Grad(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

