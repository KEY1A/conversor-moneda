package conversor;

import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación del conversor de monedas.
 * Configura los componentes necesarios y lanza la interfaz gráfica.
 */
public class Main {
    /** Clave de API para el servicio de conversión */
    private static final String API_KEY = "74d6c739e8b7b03d7ad95d2d";

    /**
     * Punto de entrada principal de la aplicación.
     * Inicializa el servicio de conversión y la interfaz de usuario.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Asegurar que la UI se crea en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Crear e inicializar el servicio de conversión
            ConversorService conversorService = new ConversorService(API_KEY);
            // Crear e inicializar la interfaz de usuario
            new ConversorUI(conversorService);
        });
    }
}