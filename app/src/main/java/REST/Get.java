package REST;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabagip on 2/01/16.
 */
public class Get {
    static String stream = null;

    public static List<Integer> datosUrl = new ArrayList<>();
    public static List<String> datosParametros = new ArrayList<>();

    public static final String HOME = "http://3tstmexico.esy.es/shop_register/index.php/";
    public static final String LOGIN = "login/logueo/";
    public static final int LOGIN_CODE = 1;



    public Get() {
    }

    public static void limpiaListas(){
        datosUrl.clear();
        datosParametros.clear();
    }

    HttpURLConnection urlConnection;

    public String GetHTTPData(String urlString) {
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            // Check the connection status
            if (urlConnection.getResponseCode() == 200) {
                // if response code = 200 ok
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                // Read the BufferedInputStream
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                stream = sb.toString();
                // End reading...............


            } else {
                // Do something
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Disconnect the HttpURLConnection
            urlConnection.disconnect();
        }
        // Return the data from specified url
        return stream;
    }
}