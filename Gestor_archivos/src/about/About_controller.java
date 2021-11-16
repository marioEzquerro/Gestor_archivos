package about;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Mario Ezquerro
 *
 * Controlador para la responsividad de la imagen en "About_ventana"
 */
public class About_controller implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private Pane border;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgView.fitHeightProperty().bind(border.heightProperty());
        imgView.fitWidthProperty().bind(border.widthProperty());
    }

}
