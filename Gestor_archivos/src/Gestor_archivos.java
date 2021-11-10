/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Gestor_archivos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gestor_archivos/Files_ventana.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Gestor M10");
        stage.setScene(scene);
        stage.show();

        //eliminar todas las ventanas tras cerrar esta
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
