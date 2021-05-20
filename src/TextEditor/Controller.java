package TextEditor;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

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

    public void setBtn_clear(ActionEvent event){
        txtArea_main.setText("");
    }

    public void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        String fileToSave = fileChooser.showSaveDialog(null).getAbsolutePath();

        try {
            FileWriter myWriter = new FileWriter(fileToSave);
            myWriter.write(txtArea_main.getText());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //only allow text files to be selected using chooser
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        //set initial directory somewhere user will recognise
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        //let user select file
        File fileToLoad = fileChooser.showOpenDialog(null);
        //if file has been chosen, load it using asynchronous method (define later)
        if(fileToLoad != null){
            loadFileToTextArea(fileToLoad);
        }
    }

    private Task<String> fileLoaderTask(File fileToLoad){
        //Create a task to load the file asynchronously
        Task<String> loadFileTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                BufferedReader reader = new BufferedReader(new FileReader(fileToLoad));
                //Use Files.lines() to calculate total lines - used for progress
                long lineCount;
                try (Stream<String> stream = Files.lines(fileToLoad.toPath())) {
                    lineCount = stream.count();
                }
                //Load in all lines one by one into a StringBuilder separated by "\n" - compatible with TextArea
                String line;
                StringBuilder totalFile = new StringBuilder();
                long linesLoaded = 0;
                while((line = reader.readLine()) != null) {
                    totalFile.append(line);
                    totalFile.append("\n");
                    updateProgress(++linesLoaded, lineCount);
                }
                return totalFile.toString();
            }
        };
        //If successful, update the text area, display a success message and store the loaded file reference
        loadFileTask.setOnSucceeded(workerStateEvent -> {
            try {
                txtArea_main.setText(loadFileTask.get());
                label_notif.setText("File loaded: " + fileToLoad.getName());
            } catch (InterruptedException | ExecutionException e) {
                txtArea_main.setText("Could not load file from:\n " + fileToLoad.getAbsolutePath());
            }
        });
        //If unsuccessful, set text area with error message and status message to failed
        loadFileTask.setOnFailed(workerStateEvent -> {
            txtArea_main.setText("Could not load file from:\n " + fileToLoad.getAbsolutePath());
            label_notif.setText("Failed to load file");
        });
        return loadFileTask;
    }

    private void loadFileToTextArea(File fileToLoad) {
        Task<String> loadFileTask = fileLoaderTask(fileToLoad);
        loadFileTask.run();
    }

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
