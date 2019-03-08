package core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceHandler {
    private String netStart = "net start";
    private String startService = "net start \"SmartPTT Radio Service\"";
    private String stopService = "net stop \"SmartPTT Radio Service\"";

    public void handleService() {
        try {
            if (getServiceStatus()) {
                System.out.println("Stopping Radio Service...");
                stopService();
                if (!getServiceStatus()) {
                    System.out.println("Stopped");
                } else {
                    System.out.println("Error while stopping service");
                }
            } else {
                System.out.println("Starting Radio Service...");
                startService();
                if (getServiceStatus()) {
                    System.out.println("Started");
                } else {
                    System.out.println("Error while starting service");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getServiceStatus() {
        boolean status = false;
        try {
            String line;
            Process process = Runtime.getRuntime().exec(netStart);
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (line.contains("SmartPTT Radio Service")) {
                    status = true;
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    private void startService() {
        try {
            Process process = Runtime.getRuntime().exec(startService);
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stopService(){
        try {
            Process process = Runtime.getRuntime().exec(stopService);
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
