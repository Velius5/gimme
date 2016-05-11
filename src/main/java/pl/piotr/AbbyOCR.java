package pl.piotr;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import org.apache.commons.lang3.StringUtils;
import pl.piotr.ReceiptsTemplates.Biedronka;
import pl.piotr.ReceiptsTemplates.Lidl;
import pl.piotr.ReceiptsTemplates.Receipt;
import pl.piotr.ReceiptsTemplates.Tesco;
import pl.piotr.ReceiptsTemplates.Zabka;
import pl.piotr.ocrsdk.Client;
import pl.piotr.ocrsdk.ProcessingSettings;
import pl.piotr.ocrsdk.Task;

/**
 * Główna klasa silnika OCR odpowiedzialnego za rozpoznawanie tekstu.
 * Pozwala na użycie silnika Abbyy OCR.
 * @author Piotr Czarny
 */
public class AbbyOCR extends ReceiptParser{

    private static Client restClient;

    /**
     * Funkcja przesyła zdjęcie do serwisu Abbyy OCR i pobiera przetworzone
     * dane.
     * @param img - zdjęcie paragonu do przetworzenia
     * @return obiekt klasy Receipt zawierający przeczytane dane
     */
    public static Receipt recognizeReceipt(byte[] img) throws Exception {
        System.out.println("Process documents using ABBYY Cloud OCR SDK.\n");

        if (!checkAppId()) {
            return null;
        }
        restClient = new Client();
        restClient.serverUrl = "http://cloud.ocrsdk.com";
        restClient.applicationId = ClientSettings.APPLICATION_ID;
        restClient.password = ClientSettings.PASSWORD;

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
        
        return parseString(text.toUpperCase());
    }

    /**
     * Sprawdza czy podano ID i hasło dla serwisu Abbyy OCR
     *
     * @return false-jeśli nie podano ID lub hasła
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

    /**
     * Funckja wysyła zapytanie do serwisu Abbyy OCR i czeka na odpowiedź
     */
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
    /**
     * Funkcja oczekuje na przetworzenie zdjęcia przez Abbyy OCR
     */
    private static Task waitForCompletion(Task task) throws Exception {
        	while (task.isTaskActive()) {

			Thread.sleep(2000);
			System.out.println("Waiting..");
			task = restClient.getTaskStatus(task.Id);
		}
		return task;
	}
}
