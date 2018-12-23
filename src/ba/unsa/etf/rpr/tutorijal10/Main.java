package ba.unsa.etf.rpr.tutorijal10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class Main extends Application {

    public static String ispisiGradove() {
        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        String spisak = "";
        for(Grad grad: gradovi) {
            spisak+= grad.getNaziv() + " (" + grad.getDrzava().getNaziv() + ")" + " - " + grad.getBrojStanovnika() + "\n";
        }
        return spisak;
    }

    public  static void glavniGrad() {
        System.out.println("Unesite ime države: ");
        Scanner ulaz = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        Grad grad = GeografijaDAO.getInstance().glavniGrad(drzava);
        if(grad!=null) {
            System.out.println("Glavni grad države " + drzava + "je " + grad.getNaziv());
        }
        else {
            System.out.println("Nepostojeća država");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Rad s bazom podataka");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.show();
    }
}
