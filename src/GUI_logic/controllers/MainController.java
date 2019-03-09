package GUI_logic.controllers;

import core.FolderOpener;
import core.copyengine.Copier;
import core.utils.ExistenceChecker;
import core.utils.BuildStatusMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {
    private ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private RadioButton plus;

    @FXML
    private RadioButton enterprise;

    @FXML
    private RadioButton develop;

    @FXML
    private Button exit;

    @FXML
    private Button install;

    @FXML
    private MenuItem buildsFolder;

    @FXML
    private MenuItem spttFolder;

    @FXML
    private Label label;

    @FXML
    private Label progress;

    @FXML
    private ComboBox<String> versionsComboBox;

    @FXML
    private MenuItem credentials;

    @FXML
    private ProgressBar pg;


    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void openBuildFolder(ActionEvent event) {
        try {
            new FolderOpener().openBuildFolder();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Cannot find build folder", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void openSpttFolder(ActionEvent event) {
        try {
            new FolderOpener().openSpttFolder();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Cannot find SmartPTT folder", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void install(ActionEvent event) {
        new Copier(new BuildStatusMonitor().buildPathToBuild(versionsComboBox, toggleGroup), pg, progress).start();
    }

    @FXML
    private void openCredsSettings(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlfiles/CredentialsGUI.fxml"));
            stage.setTitle("Credentials");
            stage.setScene(new Scene(root, 344, 183));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        progress.setText("");
        ExistenceChecker.checkStartSettings();
        plus.setToggleGroup(toggleGroup);
        enterprise.setToggleGroup(toggleGroup);
        develop.setToggleGroup(toggleGroup);
        plus.setSelected(true);

        versionsComboBox.getItems().clear();
        versionsComboBox.getItems().addAll("9.4", "9.5");
        versionsComboBox.getSelectionModel().select(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                BuildStatusMonitor tc = new BuildStatusMonitor();
                while (true) {
                    tc.checkState(versionsComboBox,
                            toggleGroup,
                            label,
                            install);
                }
            }
        }).start();
    }
}
