package ba.unsa.etf.rpr.tutorijal10;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.util.Locale;
import java.util.ResourceBundle;

public class GuiController {
    public Main main;
    public TextField imeDrzave;
    public TextField imeDrzave1;
    public TextField imeDrzave2;
    public Button stampaj;
    public Button pronadjiGlavniGrad;
    public Button obrisiDrzavu;
    public Button pronadjiDrzavu;
    public Button ispisGradova;
    public Menu file;
    public Menu help;
    public Menu view;
    public Menu jezik;
    public MenuItem saveAs, close, about, bosanski, engleski, njemacki, francuski;

    public void ispisiGradove(ActionEvent actionEvent) {
        System.out.println("Gradovi su:\n" + main.ispisiGradove());
    }

    public void glavniGrad(ActionEvent actionEvent) {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Grad trazeniGrad = dao.glavniGrad(imeDrzave.getText());
        System.out.println(trazeniGrad.getNaziv());
    }

    public void brisanjeDrzave(ActionEvent actionEvent) {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Grad glavniGrad = dao.glavniGrad(imeDrzave1.getText());
        dao.obrisiDrzavu(imeDrzave1.getText());
        if(dao.nadjiDrzavu(imeDrzave1.getText()) == null) {
            System.out.println("Drzava uspješno obrisana");
        } else {
            System.out.println("Neuspješno brisanje");
        }
    }

    public void pretragaDrzave(ActionEvent actionEvent) {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        if(dao.nadjiDrzavu(imeDrzave2.getText()) == null) {
            System.out.println("Tražena država ne postoji!");
        } else {
            System.out.println("Tražena država postoji u bazi podataka!");
        }
    }

    public void stampajGradove(ActionEvent actionEvent) {
        try {
            new GradoviReport().showReport(GeografijaDAO.getInstance().getConn());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void bosanski(ActionEvent actionEvent) {
        Stage stage = (Stage) imeDrzave.getScene().getWindow();
        stage.setTitle(ResourceBundle.getBundle("Translation_bs").getString("title"));
        imeDrzave.setPromptText(ResourceBundle.getBundle("Translation_bs").getString("imeDrzave"));
        imeDrzave1.setPromptText(ResourceBundle.getBundle("Translation_bs").getString("imeDrzave"));
        imeDrzave2.setPromptText(ResourceBundle.getBundle("Translation_bs").getString("imeDrzave"));
        stampaj.setText(ResourceBundle.getBundle("Translation_bs").getString("stampaj"));
        pronadjiDrzavu.setText(ResourceBundle.getBundle("Translation_bs").getString("pronadiDrzavu"));
        pronadjiGlavniGrad.setText(ResourceBundle.getBundle("Translation_bs").getString("pronadiGlavniGrad"));
        obrisiDrzavu.setText(ResourceBundle.getBundle("Translation_bs").getString("obrisiDrzavu"));
        file.setText(ResourceBundle.getBundle("Translation_bs").getString("file"));
        help.setText(ResourceBundle.getBundle("Translation_bs").getString("help"));
        view.setText(ResourceBundle.getBundle("Translation_bs").getString("view"));
        jezik.setText(ResourceBundle.getBundle("Translation_bs").getString("jezik"));
        saveAs.setText(ResourceBundle.getBundle("Translation_bs").getString("saveAs"));
        close.setText(ResourceBundle.getBundle("Translation_bs").getString("close"));
        about.setText(ResourceBundle.getBundle("Translation_bs").getString("about"));
        bosanski.setText(ResourceBundle.getBundle("Translation_bs").getString("bosanski"));
        engleski.setText(ResourceBundle.getBundle("Translation_bs").getString("engleski"));
        francuski.setText(ResourceBundle.getBundle("Translation_bs").getString("francuski"));
        njemacki.setText(ResourceBundle.getBundle("Translation_bs").getString("njemacki"));
        ispisGradova.setText(ResourceBundle.getBundle("Translation_bs").getString("ispisiGradove"));


    }

    public void engleski(ActionEvent actionEvent) {

    }

}
