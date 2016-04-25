package pl.piotr;

import pl.piotr.ocrsdk.ClientSettings;
import pl.piotr.ReceiptsTemplates.Receipt;
import pl.piotr.ReceiptsTemplates.Zabka;
import pl.piotr.ReceiptsTemplates.Tesco;
import pl.piotr.ReceiptsTemplates.Lidl;
import pl.piotr.ReceiptsTemplates.Biedronka;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import org.apache.commons.lang3.StringUtils;
import pl.piotr.ocrsdk.Client;
import pl.piotr.ocrsdk.ProcessingSettings;
import pl.piotr.ocrsdk.Task;

/**
 *
 * @author Piotr Czarny
 */
public class AbbyOCR extends ReceiptParser{

    private static Client restClient;
    
    public static Receipt recognizeReceipt(byte[] img) throws Exception {
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
        String language = "Polish";
        String imageSource = "photo";

        ProcessingSettings.OutputFormat outputFormat = ProcessingSettings.OutputFormat.txt;

        ProcessingSettings settings = new ProcessingSettings();
        settings.setLanguage(language);
        settings.setOutputFormat(outputFormat);
        settings.setImageSource(imageSource);

        Task task = null;

        task = restClient.processImage(img, settings);

        String text = waitAndDownloadResult(task);
        return parseString(text);
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

    private static String waitAndDownloadResult(Task task)
            throws Exception {
        task = waitForCompletion(task);
        String ocrResult = null;
        if (task.Status == Task.TaskStatus.Completed) {
            System.out.println("Downloading..");
            ocrResult = restClient.downloadResult(task);
            System.out.println("Ready");
        } else if (task.Status == Task.TaskStatus.NotEnoughCredits) {
            System.out.println("Not enough credits to process document. "
                    + "Please add more pages to your application's account.");
        } else {
            System.out.println("Task failed");
        }
        
        return ocrResult;
    }
    private static Task waitForCompletion(Task task) throws Exception {
		// Note: it's recommended that your application waits
		// at least 2 seconds before making the first getTaskStatus request
		// and also between such requests for the same task.
		// Making requests more often will not improve your application performance.
		// Note: if your application queues several files and waits for them
		// it's recommended that you use listFinishedTasks instead (which is described
		// at http://ocrsdk.com/documentation/apireference/listFinishedTasks/).
		while (task.isTaskActive()) {

			Thread.sleep(2000);
			System.out.println("Waiting..");
			task = restClient.getTaskStatus(task.Id);
		}
		return task;
	}
}
