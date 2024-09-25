import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HttpImageStatusCli {
    public void askStatus() {

        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

        String code;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter HTTP status code: ");
                code = scanner.nextLine();

                if (code.matches("\\d+")) {
                    downloader.downloadStatusImage(Integer.parseInt(code));
                    System.out.println("Image downloaded successfully.");
                    break;
                } else {
                    System.out.println("Invalid HTTP status code. It should be between 100 and 599.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        scanner.close();
    }
}



