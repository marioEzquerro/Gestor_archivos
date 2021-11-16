package abrir;

import Common.*;
import crear.Crear_controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Mario Ezquerro
 *
 * Controlador encargado de dibujar los elementos del directorio, creacion y
 * gestion de las acciones del menu contextual y abrir "Crear_ventana.fxml"
 */
public class Abrir_controller implements Initializable {

    @FXML
    private TilePane tilePane;
    public Stage MainStage;
    public TextArea MainTextArea;

    public void setMainStage(Stage stg, TextArea txtArea) {
        MainStage = stg;
        MainTextArea = txtArea;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funciones.resetPath();
        dibujarElementos();
    }

    private void dibujarElementos() {
        tilePane.getChildren().clear(); // clear de todo

        // dibujado de elementos
        File archivos = new File(Constants.path);
        ImageView fotoElemento = new ImageView();

        File[] listaArchivos = archivos.listFiles();
        for (File f : listaArchivos) {
            //declaramos elementos
            BorderPane borderPane = new BorderPane();
            Text nombre = new Text();

            // seleccionar imagen de elemento
            fotoElemento = (f.isDirectory()) ? setImage("/sources/carpeta.png") : setImage("/sources/documento.png");

            // colocar elementos
            nombre.setText(f.getName());
            borderPane.setCenter(fotoElemento);
            borderPane.setBottom(nombre);
            borderPane.setPadding(new Insets(12, 14, 12, 14));
            BorderPane.setAlignment(nombre, Pos.CENTER);
            borrarBorderPane(borderPane, f.getName()); // agregamos al elemento la funcion de borrar
            abrirElemento(borderPane, f.getName());

            tilePane.getChildren().addAll(borderPane);
        }
        // menu contextual creación
        ContextMenu contextMenu = new ContextMenu();
        MenuItem crearCarpeta = new MenuItem("Crear carpeta");
        MenuItem crearArchivo = new MenuItem("Crear archivo");
        contextMenu.getItems().addAll(crearCarpeta, crearArchivo);

        // triggers del menu contbrir_ventana.fxmextual
        crearCarpeta.setOnAction(event -> {
            crear("Crear Carpeta");
        });
        crearArchivo.setOnAction(event -> {
            crear("Crear Archivo");
        });

        // mostrar el menu contextual onClick
        tilePane.setOnContextMenuRequested(
                (ContextMenuEvent e) -> {
                    contextMenu.show(tilePane, e.getScreenX(), e.getScreenY());
                });
    }

    // cargar imagen
    private ImageView setImage(String src) {
        ImageView fotoElemento = new ImageView();
        fotoElemento.setFitWidth(65);
        fotoElemento.setFitHeight(65);
        Image image = new Image(getClass().getResource(src).toString());
        fotoElemento.setImage(image);
        return fotoElemento;
    }

    @FXML
    private void abrirElemento(BorderPane elemento, String nomElemento) {
        elemento.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                Constants.path += "/" + nomElemento;
                // acciones distintas para archivo o carpeta
                if (nomElemento.contains(".txt")) {
                    try {
                        byte[] bytes = Files.readAllBytes(Paths.get(Constants.path));
                        MainTextArea.setText(new String(bytes));
                        // obtener el nombre del archivo situdao al final de Constants.path  /home/.../[archivo.txt]
                        MainStage.setTitle(Constants.path.subSequence(Constants.path.lastIndexOf("/") + 1, Constants.path.length()).toString());
                        ((Stage) elemento.getScene().getWindow()).close();

                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else {
                    ((Stage) elemento.getScene().getWindow()).setTitle(Constants.path); // cambiar titulo con al nuevo path                }
                    dibujarElementos(); // redibujar ventana
                }
            }
        });
    }

    // eliminamos el elemento de la ventana y despues lo eliminamos del path
    private void borrarBorderPane(BorderPane elemento, String nombre) {
        elemento.setOnContextMenuRequested((ContextMenuEvent e) -> {
            ContextMenu contextBorrar = new ContextMenu();
            MenuItem borrar = new MenuItem("Borrar");

            borrar.setOnAction((ActionEvent event) -> {
                elemento.setManaged(false);
                tilePane.getChildren().remove(elemento);

                Funciones.borrarElementosRecursivo(new File(Constants.path + "/" + nombre)); // llamamos a borrar dirs/txt
            });
            // mostrar el menu contextual
            contextBorrar.getItems().addAll(borrar);
            contextBorrar.show(elemento, e.getScreenX(), e.getScreenY());
            e.consume();
        });
    }

    // retroceder carpetas
    public void retrocederCarpeta(MouseEvent event) {
        if (Constants.path.equals(Constants.DEFAULT_PATH)) {
            ((Stage) tilePane.getScene().getWindow()).close();
        } else {
            Constants.path = Constants.path.subSequence(0, Constants.path.lastIndexOf("/")).toString();
            ((Stage) tilePane.getScene().getWindow()).setTitle(Constants.path);
            dibujarElementos();
        }
    }

    //abir la ventana que muestra el menu crear
    public void crear(String tipoElemento) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/crear/Crear_archivo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Crear_controller c = (Crear_controller) fxmlLoader.getController(); //cargamos el controlador de "crear"

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            c.recibir(tipoElemento); // enviar controlador y titulo ("Crear ...")

            stage.setScene(scene);
            stage.show();

            stage.setOnHidden(e -> dibujarElementos());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
