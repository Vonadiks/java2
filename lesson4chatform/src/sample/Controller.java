package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;
    @FXML
    public Button sendButton;


    public void onClickBtnSend(ActionEvent actionEvent) {
        textArea.appendText(textField.getText() + "\n");
        textField.requestFocus();
        textField.clear();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendButton.setDefaultButton(true);
        Platform.runLater(() -> textField.requestFocus());
    }
}
