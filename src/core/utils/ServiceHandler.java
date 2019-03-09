package core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceHandler {
    private String netStart = "net start";
    private String startService = "net start \"SmartPTT Radio Service\"";
    private String stopService = "net stop \"SmartPTT Radio Service\"";

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

    public void startService() {
        try {
            Process process = Runtime.getRuntime().exec(startService);
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopService(){
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
