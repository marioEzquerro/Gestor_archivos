
import gestor_archivos.Files_controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Mario Ezquerro
 *
 * Controlador para arrancar el menu principal "Files_ventana.fxml"
 */
public class Gestor_archivos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestor_archivos/Files_ventana.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Files_controller fc = (Files_controller) fxmlLoader.getController(); //cargamos el controlador de "cre

        Scene scene = new Scene(root);

        fc.setMainStage(stage);

        stage.setTitle("NUEVO ARCHIVO");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/sources/portada.png"));
        stage.show();

        stage.setOnCloseRequest(e -> Platform.exit()); // eliminar todas las ventanas tras cerrar esta
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestor_archivos/Files_ventana.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Files_controller fc = (Files_controller) fxmlLoader.getController(); //cargamos el controlador de "cre

        Scene scene = new Scene(root);

        //      fc.setMainStage(stage, fc.getTextArea());
        stage.getIcons().add(new Image("/sources/portada.png"));
        stage.show();

        stage.setOnCloseRequest(e -> Platform.exit()); // eliminar todas las ventanas tras cerrar esta
 */
