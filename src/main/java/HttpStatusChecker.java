import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import java.io.IOException;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws HttpStatusException {
        String url = "https://http.cat/" + code + ".jpg";
        try {
            Jsoup.connect(url).
                    ignoreContentType(true).
                    execute();

            } catch (IOException e) {
                if (e instanceof HttpStatusException){
                    throw new HttpStatusException("Image not found.", ((HttpStatusException) e).getStatusCode(), url);
                }
                e.printStackTrace();
            }
            return url;
    }
}
