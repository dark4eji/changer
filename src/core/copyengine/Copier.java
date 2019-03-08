package core.copyengine;

import core.FinalVariables;
import core.utils.AppsHandler;
import core.utils.ServiceHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Copier extends Thread {
    private String build;
    private ServiceHandler sh = new ServiceHandler();
    private Bufferizator bufferizator = new Bufferizator();
    private AppsHandler ah = new AppsHandler();

    public Copier(String file) {
        this.build = file;
    }

    @Override
    public void run() {
        sh.handleService();
        ah.handleApps();
        if (!sh.getServiceStatus()) {
            System.out.println("Creating backup...");
            bufferizator.createBackup();
            System.out.println("Backup created");

            try {
                FileUtils.cleanDirectory(new File(FinalVariables.spttFolder.toString()));
                FileUtils.copyDirectory(new File(build), new File(FinalVariables.spttFolder.toString()));
                sh.handleService();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                "Build successfully installed", ButtonType.OK);
                        alert.show();
                    }
                });

            } catch (IOException e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "Error during new build copying.\nPrevious build will be restored",
                                ButtonType.OK);
                        alert.show();
                        return;
                    }
                });
            }
        }
    }
}
