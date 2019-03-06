package GUI_logic;

import core.ExistenceChecker;
import core.FinalVariables;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private RadioButton plus;

    @FXML
    private RadioButton enterprise;

    @FXML
    private Label available1;

    @FXML
    private Label available2;

    @FXML
    private Label unavailable1;

    @FXML
    private Label unavailable2;

    @FXML
    private Button btn;

    @FXML
    private ComboBox<String> versionsComboBox;

    @FXML
    private void click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.showAndWait();
    }

    @FXML
    void initialize(){
        ExistenceChecker ech = new ExistenceChecker(versionsComboBox,
                available1,
                available2,
                unavailable1,
                unavailable2);


        plus.setToggleGroup(toggleGroup);
        enterprise.setToggleGroup(toggleGroup);
        plus.setSelected(true);

        versionsComboBox.getItems().clear();
        versionsComboBox.getItems().addAll("9.4", "9.5");
        versionsComboBox.getSelectionModel().select(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ech.handleVersion(versionsComboBox.getSelectionModel().getSelectedItem().toString());
            }
        }).start();

        System.out.println(versionsComboBox.getSelectionModel().getSelectedItem().toString());

    }
}
