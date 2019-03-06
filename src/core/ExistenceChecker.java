package core;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ExistenceChecker {

    public static String getVersion(ComboBox<String> comboBox) throws NullPointerException {
        return comboBox.getSelectionModel().getSelectedItem();
    }

    public static String getBuild(ToggleGroup toggleGroup) throws NullPointerException {
        String build;
        if (toggleGroup.getSelectedToggle().toString().contains("PLUS")) {
            build = "plus";
        } else {
            build = "enterprise";
        }
        return build;
    }

    public static void checkStartSettings() {
        Path[] paths = {FinalVariables.buildStorage,
                FinalVariables.build94,
                FinalVariables.build95};

        if (!new File(FinalVariables.buildStorage.toString()).exists()) {
            for (Path path : paths){
                new File(path.toString()).mkdir();
            }
        }
    }

    public static String buildPath(String version, String build) {
        Path buildPath = Paths.get(FinalVariables.buildStorage.toString(), version, build);
        return buildPath.toString();
    }

}

