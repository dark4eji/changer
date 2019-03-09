package core.copyengine;

import core.FinalVariables;
import core.exceptions.ServiceErrorException;
import core.utils.AppsHandler;
import core.utils.ServiceHandler;
import javafx.application.Platform;
import javafx.scene.control.*;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Copier extends Thread {
    private String build;
    private ServiceHandler sh = new ServiceHandler();
    //private Bufferizator bufferizator = new Bufferizator();
    private AppsHandler ah = new AppsHandler();
    private ProgressBar pg;
    private Label progress;

    public Copier(String file, ProgressBar pg, Label progress)
    {
        this.build = file;
        this.pg = pg;
        this.progress = progress;
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (sh.getServiceStatus()) {
                    progress.setText("Stopping service...");
                }
            }
        });

        if (sh.getServiceStatus()) {
            sh.stopService();
        }

        if (!sh.getServiceStatus()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pg.setProgress(0.25);
                    progress.setText("Closing apps...");
                }
            });

            ah.handleApps();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pg.setProgress(pg.getProgress() + 0.25);
                    progress.setText("Installing build...");
                }
            });

            try {
                System.out.println(new File(build));
                FileUtils.cleanDirectory(new File(FinalVariables.spttFolder.toString()));
                FileUtils.copyDirectory(new File(build), new File(FinalVariables.spttFolder.toString()));

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pg.setProgress(pg.getProgress() + 0.25);
                        if (sh.getServiceStatus()) {
                            progress.setText("Starting service...");
                        }
                    }
                });

                sh.startService();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pg.setProgress(pg.getProgress() + 0.25);
                        pg.setProgress(0.0);
                        progress.setText("");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                "Build successfully installed", ButtonType.OK);
                        alert.show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "Error during new build copying.\nPlease click Install button again.",
                                ButtonType.OK);
                        alert.show();
                        return;
                    }
                });
            }
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.WARNING,
                            "Cannot stop Radio Service service.\nTry again or stop it manually.", ButtonType.OK);
                    alert.show();
                }
            });
        }
    }
}
