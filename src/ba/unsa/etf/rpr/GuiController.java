package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.File;

public class GuiController {
    public Main main;
    public TextField imeDrzave;
    public TextField imeDrzave1;
    public TextField imeDrzave2;

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
}
