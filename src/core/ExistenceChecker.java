package core;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.File;

public class ExistenceChecker {
    private ComboBox comboBox;
    private Label available1;
    private Label available2;
    private Label unavailable1;
    private Label unavailable2;

    public ExistenceChecker(ComboBox comboBox,
                            Label available1,
                            Label available2,
                            Label unavailable1,
                            Label unavailable2) {
        this.comboBox = comboBox;
        this.available1 = available1;
        this.available2 = available2;
        this.unavailable1 = unavailable1;
        this.unavailable2 = unavailable2;
    }

    public void handleVersion(String version){
        File[] fileToCheck;
        if (version.equals(FinalVariables.version94)){
            fileToCheck = FinalVariables.pathsFor94;
            System.out.println("SDSDASDAS");
        } else {
            fileToCheck = FinalVariables.pathsFor95;
        }
        for (File file : fileToCheck) {
            if (file.toString().contains("plus") && file.exists()) {
                available1.setVisible(true);
                unavailable1.setVisible(false);
            } else {
                available1.setVisible(false);
                unavailable1.setVisible(true);
            }

            if (file.toString().contains("enterprise") && file.exists()) {
                available2.setVisible(true);
                unavailable2.setVisible(false);
            } else {
                available2.setVisible(false);
                unavailable2.setVisible(true);
            }
        }
    }
}

