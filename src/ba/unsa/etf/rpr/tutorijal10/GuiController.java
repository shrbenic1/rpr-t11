package ba.unsa.etf.rpr.tutorijal10;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
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

    public void jezik(ActionEvent actionEvent) {
        String property;
        switch(((MenuItem) actionEvent.getTarget()).getId()) {
            case "bosanski":
                property = "Translation_bs";
                break;
            case "engleski":
                property = "Translation_en_US";
                break;
            case "njemacki":
                property = "Translation_de";
                break;
            case "francuski":
                property = "Translation_fr";
                break;
             default: return;
        }
        Stage stage = (Stage) imeDrzave.getScene().getWindow();
        stage.setTitle(ResourceBundle.getBundle(property).getString("title"));
        imeDrzave.setPromptText(ResourceBundle.getBundle(property).getString("imeDrzave"));
        imeDrzave1.setPromptText(ResourceBundle.getBundle(property).getString("imeDrzave"));
        imeDrzave2.setPromptText(ResourceBundle.getBundle(property).getString("imeDrzave"));
        stampaj.setText(ResourceBundle.getBundle(property).getString("stampaj"));
        pronadjiDrzavu.setText(ResourceBundle.getBundle(property).getString("pronadiDrzavu"));
        pronadjiGlavniGrad.setText(ResourceBundle.getBundle(property).getString("pronadiGlavniGrad"));
        obrisiDrzavu.setText(ResourceBundle.getBundle(property).getString("obrisiDrzavu"));
        file.setText(ResourceBundle.getBundle(property).getString("file"));
        help.setText(ResourceBundle.getBundle(property).getString("help"));
        view.setText(ResourceBundle.getBundle(property).getString("view"));
        jezik.setText(ResourceBundle.getBundle(property).getString("jezik"));
        saveAs.setText(ResourceBundle.getBundle(property).getString("saveAs"));
        close.setText(ResourceBundle.getBundle(property).getString("close"));
        about.setText(ResourceBundle.getBundle(property).getString("about"));
        bosanski.setText(ResourceBundle.getBundle(property).getString("bosanski"));
        engleski.setText(ResourceBundle.getBundle(property).getString("engleski"));
        francuski.setText(ResourceBundle.getBundle(property).getString("francuski"));
        njemacki.setText(ResourceBundle.getBundle(property).getString("njemacki"));
        ispisGradova.setText(ResourceBundle.getBundle(property).getString("ispisiGradove"));
    }

    public void saveAs(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML File (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter1);
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("DOCX (*.docx)", "*.docx");
        fileChooser.getExtensionFilters().add(extFilter2);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            new GradoviReport().saveReport(file.getAbsolutePath());
        }
    }
}
