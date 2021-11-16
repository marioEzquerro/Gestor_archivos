package crear;

import Common.*;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * @author Mario Ezquerro
 *
 * Controlador encargado del menu contextual y la creacion del archivo y
 * carpetas
 */
public class Crear_controller {

    @FXML
    private TextField campo;
    @FXML
    private Button btnCrear;
    @FXML
    private Text desc;
    @FXML
    private Text accion;

    public void closeVentana(ActionEvent event) {
        Funciones.cerrarVentana(event);
    }

    public void crear(ActionEvent event) throws IOException {
        File archivo = new File(Constants.path + "/" + campo.getText() + ".txt");
        File carpeta = new File(Constants.path + "/" + campo.getText());

        if (!archivo.exists() && accion.getText().equals("Crear Archivo")) { // creamos archivo (no existe)
            desc.setText(resultadoCreacionElemento(archivo, "Crear Archivo"));
            Funciones.escribirEnArchivo(archivo, Constants.textAreaContent);
        } else if (!carpeta.exists() && accion.getText().equals("Crear Carpeta")) { // creamos carpeta (no existe)
            desc.setText(resultadoCreacionElemento(carpeta, "Crear Carpeta"));
        } else {
            desc.setText("Ya existe");
        }
    }

    // crear carpeta/archivo
    public String resultadoCreacionElemento(File ruta, String nombre) throws IOException {
        if (nombre.equals("Crear Archivo")) {
            return (ruta.createNewFile()) ? "Archivo creado" : "¡Error!";
        } else {
            return (ruta.mkdir()) ? "Carpeta creada" : "¡Error!";
        }
    }

    // llamado con el input de teclado
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

    // recibe y almacena el string "Crear ..."
    public void recibir(String tit) {
        accion.setText(tit);
    }
}
