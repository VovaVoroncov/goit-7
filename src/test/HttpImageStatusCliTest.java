import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HttpImageStatusCliTest {
    private HttpStatusImageDownloader downloader;
    private Scanner scanner;
    private HttpImageStatusCli cli;

    @BeforeEach
    public void setUp() {
        downloader = mock(HttpStatusImageDownloader.class);
        scanner = mock(Scanner.class);
        cli = new HttpImageStatusCli(downloader, scanner);
    }

    @Test
    public void testValidHttpStatusCode() throws IOException {
        when(scanner.nextLine()).thenReturn("200"); // Mock input
        cli.askStatus();

        verify(downloader).downloadStatusImage(200);
        // Further verification for successful message can be added if needed
    }

    @Test
    public void testInvalidHttpStatusCode() {
        when(scanner.nextLine()).thenReturn("abc").thenReturn("200"); // Mock invalid followed by valid input
        cli.askStatus();

        verify(downloader, times(1)).downloadStatusImage(200);
    }

    @Test
    public void testIOException() throws IOException {
        doThrow(new IOException("Download failed")).when(downloader).downloadStatusImage(anyInt());
        when(scanner.nextLine()).thenReturn("200");

        Exception exception = assertThrows(IOException.class, () -> {
            cli.askStatus();
        });

        assertEquals("Download failed", exception.getMessage());
    }
}
