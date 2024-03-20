import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;


public class ApiClient{ ///MÉTODO LECTURA DE API
//LLAMADA REST CON TOKEN TIPO BEARER
    public String getApiResponse(String url, String token) throws Exception{
         //String apiUrl = "https://ds2324.arambarri.eus";
        url = "https://ds2324.arambarri.eus";
        // Token Bearer
        /*String*/ token = "0518ee96193abf0dca7b3a46591653eb2b162f3fb2dd6fa681b65b97e3e00243187a1b6839aac73946715fb62719b12a1eb14afc36018935b935c2dbf293448fc98a5cde5a219fc208a3db97489b2c2c479825f212d87658ff3b369e4951b0b3f101ac8d52330262e60846ae80b45b6799c69371e4f47a548053137ada4ec6e5";
        
        // Configuración del cliente HTTP
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        
        // Construcción de la solicitud HTTP con el encabezado de autorización
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .build();
        
        try {
            // Envío de la solicitud y manejo de la respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Imprimir el código de estado y la respuesta
            System.out.println("Código de estado: " + response.statusCode());
            System.out.println("Respuesta: " + response.body());
            
            // Imprimir los encabezados de respuesta, si es necesario
            HttpHeaders headers = response.headers();
            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}