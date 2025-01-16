package conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * Servicio que maneja la lógica de negocio para la conversión de monedas.
 * Utiliza la API de exchangerate-api.com para obtener las tasas de cambio actuales.
 */
public class ConversorService {
    /** URL base de la API de conversión de monedas */
    private static final String API_BASE_URL = "https://api.exchangerate-api.com/v4/latest/";
    
    /** Clave de API para autenticación con el servicio */
    private final String apiKey;

    /**
     * Constructor que inicializa el servicio con una clave de API.
     * @param apiKey Clave de API para el servicio de conversión
     */
    public ConversorService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Realiza la conversión entre dos monedas utilizando la API.
     * 
     * @param monedaOrigen Código de la moneda de origen
     * @param monedaDestino Código de la moneda de destino
     * @param cantidad Cantidad a convertir
     * @return Cantidad convertida en la moneda de destino
     * @throws IOException Si hay un error en la comunicación con la API
     * @throws InterruptedException Si la llamada a la API es interrumpida
     */
    public double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) 
            throws IOException, InterruptedException {
        // Crear cliente HTTP para la llamada a la API
        HttpClient cliente = HttpClient.newHttpClient();
        
        // Construir la solicitud HTTP
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_BASE_URL + monedaOrigen))
                .build();

        // Realizar la llamada a la API y procesar la respuesta
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(respuesta.body());
        double tasaCambio = jsonObject.getJSONObject("rates").getDouble(monedaDestino);
        
        // Calcular y devolver el resultado de la conversión
        return cantidad * tasaCambio;
    }
}