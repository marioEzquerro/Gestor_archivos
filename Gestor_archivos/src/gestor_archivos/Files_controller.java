/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_archivos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Files_controller implements Initializable {

    @FXML
    public void openAbout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/about/About_ventana.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            Scene scene = new Scene(root1);

            stage.setTitle("About");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void abrirArchivo(ActionEvent event) {
        System.out.println("Abriendo...");
    }

    @FXML
    public void crearArchivo(ActionEvent event) {
        System.out.println("Creando...");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
