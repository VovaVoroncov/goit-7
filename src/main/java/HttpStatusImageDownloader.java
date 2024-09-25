import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class HttpStatusImageDownloader {
    public void downloadStatusImage(int code) throws IOException {

        HttpStatusChecker statusChecker = new HttpStatusChecker();
        String imageUrl = statusChecker.getStatusImage(code);

        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            int nameStart = imageUrl.lastIndexOf('/');
            String fileName = imageUrl.substring(nameStart + 1);

            Path destination = Path.of(fileName);
            Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}