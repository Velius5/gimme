package pl.piotr;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Vector;
import pl.piotr.ocrsdk.Client;

/**
 *
 * @author Piotr Czarny
 */
public class AbbyOCR {

    private static Client restClient;

    public static Receipt recognizeReceipt(BufferedImage img) {
        System.out.println("Process documents using ABBYY Cloud OCR SDK.\n");

        if (!checkAppId()) {
            return null;
        }

        //ClientSettings.setupProxy();
        restClient = new Client();

        restClient.serverUrl = "http://cloud.ocrsdk.com";
        restClient.applicationId = ClientSettings.APPLICATION_ID;
        restClient.password = ClientSettings.PASSWORD;

        //Vector<String> argList = new Vector<String>(Arrays.asList(args));
        // Select processing mode
        String mode = "recognize";

        return new Biedronka();
    }

    /**
     * Check that user specified application id and password.
     *
     * @return false if no application id or password
     */
    private static boolean checkAppId() {
        String appId = ClientSettings.APPLICATION_ID;
        String password = ClientSettings.PASSWORD;
        if (appId.isEmpty() || password.isEmpty()) {
            System.out
                    .println("Error: No application id and password are specified.");
            System.out.println("Please specify them in ClientSettings.java.");
            return false;
        }
        return true;
    }


}
