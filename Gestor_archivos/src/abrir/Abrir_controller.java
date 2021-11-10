/*
 * Controlador encargado de dibujar los elementos del directorio, creacion y gestion de las acciones
 * del menu contextual y abrir "Crear_ventana.fxml"
 */
package abrir;

import crear.Crear_controller;
import gestor_archivos.Files_controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Mario Ezquerro
 */
public class Abrir_controller implements Initializable {

    @FXML
    private TilePane tilePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dibujarElementos();
    }

    public void dibujarElementos() {
        tilePane.getChildren().clear();

        File archivos = new File(Files_controller.getRuta());
        ImageView fotoElemento = new ImageView();

        File[] listaArchivos = archivos.listFiles();
        for (File f : listaArchivos) {
            //declaramos elementos
            BorderPane borderPane = new BorderPane();
            Text nombre = new Text();

            // seleccionar imagen de elemento
            if (f.isDirectory()) {
                fotoElemento = setImage("/sources/carpeta.png");
            } else {
                fotoElemento = setImage("/sources/documento.png");
            }

            // colocar elementos
            nombre.setText(f.getName());
            borderPane.setCenter(fotoElemento);
            borderPane.setBottom(nombre);
            borderPane.setPadding(new Insets(12, 12, 12, 12));
            BorderPane.setAlignment(nombre, Pos.CENTER);
            tilePane.getChildren().addAll(borderPane);
        }

        // menu contextual
        ContextMenu contextMenu = new ContextMenu();
        MenuItem crearCarpeta = new MenuItem("Crear carpeta");
        MenuItem crearArchivo = new MenuItem("Crear archivo");
        contextMenu.getItems().addAll(crearCarpeta, crearArchivo);

        crearCarpeta.setOnAction(event -> {
            crear("Crear Carpeta");
        });
        crearArchivo.setOnAction(event -> {
            crear("Crear Archivo");
        });

        tilePane.setOnContextMenuRequested(
                (ContextMenuEvent e) -> {
                    contextMenu.show(tilePane, e.getScreenX(), e.getScreenY());
                });
    }

    // cargar imagen
    public ImageView setImage(String src) {
        ImageView fotoElemento = new ImageView();
        fotoElemento.setFitWidth(60);
        fotoElemento.setFitHeight(60);
        Image image = new Image(getClass().getResource(src).toString());
        fotoElemento.setImage(image);
        return fotoElemento;
    }

    //abir la ventana que muestra el menu crear
    public void crear(String tipo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/crear/Crear_archivo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Crear_controller c = (Crear_controller) fxmlLoader.getController(); //cargamos el controlador de "crear"

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // enviar controlador y titulo ("Crear ...")
            c.recibir(this, tipo);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
