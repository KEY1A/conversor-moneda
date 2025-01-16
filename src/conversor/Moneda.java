package conversor;

/**
 * Clase que representa una moneda en el sistema de conversión.
 * Cada moneda tiene un código (por ejemplo, USD) y un nombre descriptivo.
 */
public class Moneda {
    /** Código de la moneda (ejemplo: USD, EUR, JPY) */
    private String codigo;
    
    /** Nombre completo de la moneda (ejemplo: "Dólar USA", "Euro") */
    private String nombre;

    /**
     * Constructor que inicializa una nueva moneda.
     * @param codigo Código de tres letras de la moneda (ISO 4217)
     * @param nombre Nombre completo de la moneda
     */
    public Moneda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Obtiene el código de la moneda.
     * @return Código de la moneda
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre de la moneda.
     * @return Nombre completo de la moneda
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Representa la moneda como una cadena de texto.
     * @return Formato: "código - nombre" (ejemplo: "USD - Dólar USA")
     */
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}