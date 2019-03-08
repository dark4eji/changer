package core.utils;

import core.FinalVariables;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class BuildStatusMonitor {
    BasicFileAttributes attr;
    Path path;
    public void checkState(ComboBox<String> versionsComboBox,
                           ToggleGroup toggleGroup,
                           Label label,
                           Button btn) {

        if (!new File(buildPathToBuild(versionsComboBox, toggleGroup)).exists()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    label.setText("Selected build is unavailable");
                    btn.setDisable(true);
                }
            });

        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        attr = Files.readAttributes(path, BasicFileAttributes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    label.textProperty().setValue("Selected build is available:\n"
                            + "Subversion: 9.5.2\n"
                            + "Downloaded: " + attr.creationTime());
                    if (btn.isDisabled()) {
                        btn.setDisable(false);
                    }
                }
            });
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String buildPathToBuild(ComboBox<String> setVersion, ToggleGroup setBuild){
        String version = ExistenceChecker.getVersion(setVersion);
        String build = ExistenceChecker.getBuild(setBuild);
        this.path = Paths.get(FinalVariables.buildStorage.toString(), version, build);
        return this.path.toString();
    }
}
