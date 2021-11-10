/*
 * Controlador del menu principal de la aplicacion del que ramifican el resto de ventanas
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Files_controller implements Initializable {
    
    public static String getRuta() {
        return "/home/alumno/FILES/"; //CAMBIAR EN CASO DE ERRORES
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    // Info > ver autor
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
    // File > abrir
    public void abrirArchivo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/abrir/Abrir_ventana.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            Scene scene = new Scene(root2);

            stage.setTitle("Abir");
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL); // bloquear ventana principal
            stage.show();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void guardarArchivo(ActionEvent event) {
        System.out.println("Guardando...");
    }

    @FXML
    public void borrarArchivo(ActionEvent event) {
        System.out.println("Borrando...");
    }

}
