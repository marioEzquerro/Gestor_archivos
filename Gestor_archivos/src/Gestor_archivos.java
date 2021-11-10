/*
 * Controlador para arrancar el menu principal "Files_ventana.fxml"
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mario Ezquerro
 */
public class Gestor_archivos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gestor_archivos/Files_ventana.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Gestor M10");
        stage.getIcons().add(new Image("/sources/portada.png"));
        stage.setScene(scene);
        stage.show();

        //eliminar todas las ventanas tras cerrar esta
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
