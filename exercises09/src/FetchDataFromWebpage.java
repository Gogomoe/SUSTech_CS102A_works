import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FetchDataFromWebpage {

    public static void main(String[] args) throws IOException {
        final String URL = "http://horstmann.com/index.html";
        System.out.println("Fetch: " + URL);

        java.net.URL url = new URL(URL);

        BufferedInputStream in = new BufferedInputStream(url.openStream());
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(url.getFile().substring(1))
        );

        byte[] data = new byte[1024];

        while (in.read(data) != -1) {
            out.write(data);
        }

        System.out.println("Fetch: " + URL + "SUCCEED");

        in.close();
        out.close();
    }
}
