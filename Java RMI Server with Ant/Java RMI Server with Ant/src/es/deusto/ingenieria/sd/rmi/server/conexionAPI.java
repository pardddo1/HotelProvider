/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiClient{ ///MÉTODO LEER

    public String getApiResponse(String url) throws Exception{
        URL url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while((line = reader.readLine())!=null){
            response.append(line);
        }

        reader.close();
        conn.disconnect();

        return response.toString();
    }
}*/