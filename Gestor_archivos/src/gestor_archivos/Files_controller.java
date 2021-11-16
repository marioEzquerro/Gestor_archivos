package gestor_archivos;

import Common.*;
import abrir.Abrir_controller;
import crear.Crear_controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Mario Ezquerro
 *
 * Controlador del menu principal de la aplicacion del que ramifican el resto de
 * ventanas
 */
public class Files_controller implements Initializable {

    @FXML
    public TextArea textArea; // el original
    public Stage MainStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Funciones.resetPath();
    }

    public void setMainStage(Stage stg) {
        MainStage = stg;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    @FXML
    // Info > ver autor
    private void openAbout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/about/About_ventana.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

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
            Parent root = (Parent) fxmlLoader.load();
            Abrir_controller ac = (Abrir_controller) fxmlLoader.getController();
            ac.setMainStage(MainStage, textArea);

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle(Constants.path);
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL); // bloquear ventana principal
            stage.show();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    // File > Guardar que llama a la la funcion para abrir Crear_archivo.fxml
    public void guardarArchivo(ActionEvent event) throws IOException {
        File archivo = new File(Constants.path);

        if (archivo.exists() && !Constants.path.equals(Constants.DEFAULT_PATH)) { // si el archivo ya existe y hemos abierto uno reescribimos
            Funciones.escribirEnArchivo(archivo, textArea.getText());
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/crear/Crear_archivo.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Crear_controller c = (Crear_controller) fxmlLoader.getController(); //cargamos el controlador de "crear"

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                c.recibir("Crear Archivo"); // enviar titulo ("Crear ...")
                Constants.textAreaContent = textArea.getText();

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @FXML
    // File > Borrar
    public void borrarArchivo(ActionEvent event) {
        if (!Constants.path.equals(Constants.DEFAULT_PATH)) {
            Funciones.borrarElementosRecursivo(new File(Constants.path));
            crearNuevo(event);
        }
    }

    @FXML
    // File > Crear Nuevo
    public void crearNuevo(ActionEvent event) {
        textArea.setText("");
        ((Stage) textArea.getScene().getWindow()).setTitle("NUEVO ARCHIVO");
    }
}
