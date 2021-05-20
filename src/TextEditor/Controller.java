package TextEditor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn_clear;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_open;
    @FXML
    private MenuButton btn_font;
    @FXML
    private Button btn_insert_img;
    @FXML
    private TextArea txtArea_main;
    @FXML
    private Label label_notif;
    @FXML
    private CheckMenuItem font_menu_calibri;
    @FXML
    private CheckMenuItem font_menu_segoe;
    @FXML
    private CheckMenuItem font_menu_arial;
    @FXML
    private CheckMenuItem font_menu_times_new_roman;
    @FXML
    private CheckMenuItem font_menu_comic_sans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        font_menu_arial.setOnAction(actionEvent -> {
            txtArea_main.setFont(new Font("Arial", 12.0));
            font_menu_times_new_roman.setSelected(false);
            font_menu_segoe.setSelected(false);
            font_menu_comic_sans.setSelected(false);
            font_menu_calibri.setSelected(false);
        });

        font_menu_calibri.setOnAction(actionEvent -> {
            txtArea_main.setFont(new Font("Calibri", 12.0));
            font_menu_comic_sans.setSelected(false);
            font_menu_segoe.setSelected(false);
            font_menu_arial.setSelected(false);
            font_menu_times_new_roman.setSelected(false);
        });

        font_menu_comic_sans.setOnAction(actionEvent -> {
            txtArea_main.setFont(new Font("Comic Sans MS", 12.0));
            font_menu_arial.setSelected(false);
            font_menu_calibri.setSelected(false);
            font_menu_times_new_roman.setSelected(false);
            font_menu_segoe.setSelected(false);
        });

        font_menu_segoe.setOnAction(actionEvent -> {
            txtArea_main.setFont(new Font("Segoe UI", 12.0));
            font_menu_arial.setSelected(false);
            font_menu_calibri.setSelected(false);
            font_menu_times_new_roman.setSelected(false);
            font_menu_comic_sans.setSelected(false);
        });

        font_menu_times_new_roman.setOnAction(actionEvent -> {
            txtArea_main.setFont(new Font("Times New Roman", 12.0));
            font_menu_arial.setSelected(false);
            font_menu_calibri.setSelected(false);
            font_menu_comic_sans.setSelected(false);
            font_menu_segoe.setSelected(false);
        });
    }

}
