package Common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @author Mario Ezquerro
 *
 * Funciones comunes para distintos controladores
 */
public class Funciones {

    public static void resetPath() {
        Constants.path = Constants.DEFAULT_PATH;
    }

    public static void borrarElementosRecursivo(File dir) {
        File[] archivos = dir.listFiles();
        if (archivos != null) {
            for (File temp : archivos) {
                borrarElementosRecursivo(temp);
            }
        }
        dir.delete();
    }

    public static void escribirEnArchivo(File archivo, String contenido) throws IOException {
        Path ruta = Paths.get(archivo.getPath()); // especificamos la ruta
        Files.write(ruta, contenido.getBytes());
    }

    // EN ACCTION EVENT
    public static Stage getStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }

    public static void cerrarVentana(ActionEvent event) {
        getStage(event).close();
    }

    public static void renombrarVentana(ActionEvent event, String titulo) {
        getStage(event).setTitle(titulo);
    }
}
