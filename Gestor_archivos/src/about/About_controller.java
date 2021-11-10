/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package about;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author alumno
 */
public class About_controller implements Initializable {

    @FXML
    private ImageView imgView;

    @FXML
    private Pane border;

    public void initialize(URL url, ResourceBundle rb) {
        imgView.fitHeightProperty().bind(border.heightProperty());
        imgView.fitWidthProperty().bind(border.widthProperty());
    }

}
