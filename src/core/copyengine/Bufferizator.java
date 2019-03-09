package core.copyengine;

import core.FinalVariables;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class Bufferizator {
    private String temp = FinalVariables.temp.toString();

    public void createBackup() {
        if (checkTemp() == 0) {
            try {
                FileUtils.copyDirectory(new File(FinalVariables.spttFolder.toString()),
                        new File(temp));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int checkTemp() {
        int status = 1;
        try {
            if (!new File(temp.toString()).exists()) {
                new File(temp.toString()).mkdir();
                status = 0;
            } else if (new File(temp.toString()).exists()) {
                status = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Cannot create temp folder", ButtonType.OK);
            alert.show();
        }
        return status;
    }
}
