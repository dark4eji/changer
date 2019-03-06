package GUI_logic;

import core.ExistenceChecker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;

public class MainController {
    private ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private RadioButton plus;

    @FXML
    private RadioButton enterprise;

    @FXML
    private Label label;

    @FXML
    private Button btn;

    @FXML
    private ComboBox<String> versionsComboBox;

    @FXML
    private void click(ActionEvent event) {
        try {
            System.out.println(ExistenceChecker.buildPath(ExistenceChecker.getVersion(versionsComboBox),
                    ExistenceChecker.getBuild(toggleGroup)));
        } catch(NullPointerException e) {
            System.out.println("NullError");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.showAndWait();
    }

    @FXML
    private void refreshLabelStatus(ActionEvent event) {
        System.out.println("Hello");
        System.out.println(toggleGroup.getSelectedToggle().toString());
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
                while (true) {
                    if (!new File(ExistenceChecker.buildPath(ExistenceChecker.getVersion(versionsComboBox),
                            ExistenceChecker.getBuild(toggleGroup))).exists()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                label.setText("Selected build is unavailable");
                                btn.setDisable(true);
                            }
                        });
//
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                label.textProperty().setValue("Selected build is available:\n"
                                        + "Subversion: 9.5.2\n"
                                        + "Downloaded: 25.02.18");
                                if (btn.isDisabled()) {
                                    btn.setDisable(false);
                                }
                            }
                        });
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
