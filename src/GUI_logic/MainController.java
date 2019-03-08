package GUI_logic;

import core.FolderOpener;
import core.utils.ExistenceChecker;
import core.utils.BuildStatusMonitor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;


public class MainController {
    private ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private RadioButton plus;

    @FXML
    private Button exit;

    @FXML
    private Button install;

    @FXML
    private MenuItem buildsFolder;

    @FXML
    private MenuItem spttFolder;

    @FXML
    private RadioButton enterprise;

    @FXML
    private Label label;

    @FXML
    private ComboBox<String> versionsComboBox;

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
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
    void initialize() {

        ExistenceChecker.checkStartSettings();
        plus.setToggleGroup(toggleGroup);
        enterprise.setToggleGroup(toggleGroup);
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
