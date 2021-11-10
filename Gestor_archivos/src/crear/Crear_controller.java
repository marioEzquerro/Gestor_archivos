/*
 * Controlador encargado del menu contextual y la creacion del archivo y carpetas
 */
package crear;

import abrir.Abrir_controller;
import gestor_archivos.Files_controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Mario Ezquerro
 */
public class Crear_controller {

    @FXML
    private TextField campo;
    @FXML
    private Button btnCrear;
    @FXML
    private Text desc;

    // importados
    private Abrir_controller abrirController;
    public Text titulo;

    public void closeVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void crear(ActionEvent event) throws IOException {
        File rutaArch = new File(Files_controller.getRuta() + campo.getText() + ".txt");
        File rutaCarp = new File(Files_controller.getRuta() + campo.getText());

        if (!rutaArch.exists() && titulo.getText().equals("Crear Archivo")) { // creamos archivo (no existe)
            desc.setText(crearElemento(rutaArch, "Crear Archivo"));
        } else if (!rutaCarp.exists() && titulo.getText().equals("Crear Carpeta")) { // creamos carpeta (no existe)
            desc.setText(crearElemento(rutaCarp, "Crear Carpeta"));
        } else { 
            desc.setText("Ya existe");
        }
        abrirController.dibujarElementos();
    }

    // crear carpeta/archivo
    public String crearElemento(File ruta, String nombre) throws IOException {
        if (nombre.equals("Crear Archivo")) {
            return (ruta.createNewFile()) ? "Archivo creado" : "¡Error!";
        } else {
            return (ruta.mkdir()) ? "Carpeta creada" : "¡Error!";
        }
    }

    // llamado con el imput de teclado
    public void checkEmpty(KeyEvent event) {
        desc.setText(""); // vaciar campo de descripcion del estado

        if (!event.getCharacter().matches("[a-z]|[A-Z]")) {
            event.consume();
        }

        if (campo.getText().isEmpty()) {
            btnCrear.setDisable(true);
        } else {
            btnCrear.setDisable(false);
        }
    }

    // recibe  y almacena controller, tilePane, y el string "Crear ..."
    public void recibir(Abrir_controller contr, String tipo) {
        abrirController = contr;
        titulo.setText(tipo);
    }

}
