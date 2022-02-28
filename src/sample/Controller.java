package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.*;

public class Controller extends Main{

    public MenuItem close;
    boolean saved = false;
    boolean savedOnce = false;
    public TextArea textArea;
    Clipboard clip = Clipboard.getSystemClipboard();
    ClipboardContent clipCon = new ClipboardContent();
    File file = null;
    FileChooser fileChooser = new FileChooser();

    public void paste(ActionEvent actionEvent) {
        if (textArea.getSelectedText() == null){
            textArea.appendText(clip.getString());
        }
        else{
            textArea.replaceText(textArea.getSelection(),clip.getString());
        }
    }
    public void close(ActionEvent actionEvent){
        System.exit(0);
    }
    public void copy(ActionEvent actionEvent){
        clipCon.putString(textArea.getSelectedText());
        clip.setContent(clipCon);
    }
    public void cut(ActionEvent actionEvent){
        clipCon.putString(textArea.getSelectedText());
        clip.setContent(clipCon);
        textArea.replaceText(textArea.getSelection(),"");
    }

    public void oneX(ActionEvent actionEvent) {
        textArea.setFont(Font.font(14));
    }

    public void twoX(ActionEvent actionEvent) {
        textArea.setFont(Font.font(18));
    }

    public void threeX(ActionEvent actionEvent) {
        textArea.setFont(Font.font(20));
    }

    public void fourX(ActionEvent actionEvent) {
        textArea.setFont(Font.font(22));
    }

    public void open(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        file = fileChooser.showOpenDialog(stage);
        try {
            FileReader reader = new FileReader(file);
            Scanner myReader = new Scanner(reader);
            myReader.useDelimiter("\\Z");
            textArea.setText(myReader.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        changeTitle(file.getName());
    }

    public void save() throws IOException {
        if (!savedOnce){
            try {
                file = fileChooser.showSaveDialog(stage);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(textArea.getText());
                fileWriter.close();
                savedOnce = true;
            }catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File not saved");
                alert.setResizable(false);
                alert.setContentText("File not saved");
            }
        }
        else {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(textArea.getText());
                fileWriter.close();
                savedOnce = true;
            }catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File not saved");
                alert.setResizable(false);
                alert.setContentText("File not saved");
            }
            saved = true;
        }
        changeTitle(file.getName());
    }

    public void saveAs(ActionEvent actionEvent) throws IOException {
        try {
            file = fileChooser.showSaveDialog(stage);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(textArea.getText());
            fileWriter.close();
            savedOnce = true;
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("File not saved");
            alert.setResizable(false);
            alert.setContentText("File not saved");
        }
        changeTitle(file.getName());
    }

    public void newFile(ActionEvent actionEvent) throws Exception {
        if (saved){
            textArea.clear();
            saved = false;
            savedOnce = false;
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("File not saved");
            alert.setResizable(false);
            alert.setContentText("Do you want to save this file.");

            ButtonType save = new ButtonType("Save");
            ButtonType dontSave = new ButtonType("Don't save");
            ButtonType cancel = new ButtonType("Cancel");

            alert.getButtonTypes().clear();

            alert.getButtonTypes().addAll(save, dontSave, cancel);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == save) {
                save();
            }
            else if(result.get() == dontSave){
                textArea.clear();
            }
            else if (result.get() == cancel){
                alert.close();
            }
        }
        changeTitle("Untitled Note");
    }

    public void savePlease(KeyEvent event) {
        saved = false;
        if (saved){
            stage.setTitle("*"+stage.getTitle());
        }

    }
}