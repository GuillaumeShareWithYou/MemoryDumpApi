package guillaume.spyWeb.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkingTest {

        public static void main(String a[]) throws Exception{

            URL url = null;
            InputStream is = null;
            try {
                url = new URL("http://localhost:8080/courses/1");
                is = url.openStream();
                byte[] b = new byte[8];
                while(is.read(b) != -1){
                    System.out.print(new String(b));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

}
