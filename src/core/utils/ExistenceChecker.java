package core.utils;

import core.FinalVariables;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.nio.file.Path;

public class ExistenceChecker {

    public static synchronized String getVersion(ComboBox<String> comboBox) throws NullPointerException {
        return comboBox.getSelectionModel().getSelectedItem();
    }

    public static synchronized String getBuild(ToggleGroup toggleGroup) throws NullPointerException {
        String build;
        if (toggleGroup.getSelectedToggle().toString().contains("PLUS")) {
            build = "plus";
        } else if (toggleGroup.getSelectedToggle().toString().contains("Enterprise")) {
            build = "enterprise";
        } else {
            build = "develop";
        }
        return build;
    }

    public static void checkStartSettings() {
        if (!new File(FinalVariables.buildStorage.toString()).exists()) {
            Path[] paths = {FinalVariables.buildStorage,
                    FinalVariables.build94,
                    FinalVariables.build95};

            for (Path path : paths){
                new File(path.toString()).mkdir();
            }
        }
    }
}

