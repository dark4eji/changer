package core.utils;

import core.FinalVariables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppsHandler {
    private String tasklist = "tasklist";
    private String dispatcher = "Client.exe";
    private String config = "RSConfigurator.exe";
    private String startDispatcher = "start " + FinalVariables.spttFolder.toString()
            + "\\Client\\"
            + "Client.exe";
    private String startConfig = "start " + FinalVariables.spttFolder.toString()
            + "\\Server\\"
            + "RSConfigurator.exe";
    private String killDispatcher = "Taskkill /IM Client.exe /F";
    private String killConfig = "Taskkill /IM RSConfigurator.exe /F";

    public void handleApps(){
        boolean status = false;
        try {
            String line;
            Process process = Runtime.getRuntime().exec(tasklist);
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (line.contains(config)) {
                    Process killconfig = Runtime.getRuntime().exec(killConfig);
                    try {
                        killconfig.waitFor();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (line.contains(dispatcher)){
                    Process killdisp = Runtime.getRuntime().exec(killDispatcher);
                    try {
                        killdisp.waitFor();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


