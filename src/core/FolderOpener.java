package core;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FolderOpener {
    /**
     * Метод, открывающий папку с билдами.
     * Привязан к элементу меню Navigation.
     * @throws IllegalArgumentException
     */
    public void openBuildFolder() throws IllegalArgumentException {
        try {
            Desktop.getDesktop().open(new File(FinalVariables.buildStorage.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, открывающий папку SmartPTT.
     * Привязан к элементу меню Navigation.
     * @throws IllegalArgumentException
     */
    public void openSpttFolder() throws IllegalArgumentException {
        try {
            Desktop.getDesktop().open(new File(FinalVariables.spttFolder.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
