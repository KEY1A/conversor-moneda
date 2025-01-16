package conversor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Clase que maneja la interfaz gráfica del conversor de monedas.
 * Implementa un diseño simple y amigable para el usuario utilizando Swing.
 */

public class ConversorUI {
    /** Componentes principales de la interfaz */
    private final JFrame frame;
    private final JComboBox<Moneda> monedaOrigen;
    private final JComboBox<Moneda> monedaDestino;
    private final JTextField cantidad;
    private final JLabel resultado;
    
    /** Servicio de conversión */
    private final ConversorService conversorService;

    /**
     * Constructor que inicializa la interfaz de usuario.
     * @param conversorService Servicio para realizar las conversiones
     */
    public ConversorUI(ConversorService conversorService) {
        this.conversorService = conversorService;
        this.frame = new JFrame("Conversor de Monedas");
        this.monedaOrigen = new JComboBox<>(getMonedas());
        this.monedaDestino = new JComboBox<>(getMonedas());
        this.cantidad = new JTextField(10);
        this.resultado = new JLabel("El resultado aparecerá aquí");
        
        inicializarUI();
    }

     /**
     * Inicializa todos los componentes de la interfaz de usuario.
     */
    private void inicializarUI() {
        configurarFrame();
        JPanel controlPanel = crearPanelControl();
        JPanel buttonPanel = crearPanelBoton();
        JPanel resultPanel = crearPanelResultado();

        frame.add(controlPanel);
        frame.add(buttonPanel);
        frame.add(resultPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

     /**
     * Configura las propiedades básicas de la ventana principal.
     */

    private void configurarFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(240, 248, 255));
    }


    /**
     * Crea el panel de control con los campos de entrada.
     * @return Panel configurado con los componentes de control
     */
    private JPanel crearPanelControl() {
        JPanel controlPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        controlPanel.setBackground(new Color(230, 240, 250));

        JLabel labelOrigen = new JLabel("Moneda de Origen:");
        JLabel labelDestino = new JLabel("Moneda de Destino:");
        JLabel labelCantidad = new JLabel("Cantidad:");

        configurarComponentesControl(labelOrigen, labelDestino, labelCantidad);
        agregarComponentesControl(controlPanel, labelOrigen, labelDestino, labelCantidad);

        return controlPanel;
    }

    private void configurarComponentesControl(JLabel... labels) {
        for (JLabel label : labels) {
            label.setForeground(new Color(25, 25, 112));
        }
        monedaOrigen.setBackground(Color.WHITE);
        monedaDestino.setBackground(Color.WHITE);
        cantidad.setBackground(Color.WHITE);
    }

    private void agregarComponentesControl(JPanel panel, JLabel labelOrigen, 
            JLabel labelDestino, JLabel labelCantidad) {
        panel.add(labelOrigen);
        panel.add(monedaOrigen);
        panel.add(labelDestino);
        panel.add(monedaDestino);
        panel.add(labelCantidad);
        panel.add(cantidad);
    }

    private JPanel crearPanelBoton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton convertir = new JButton("Convertir");
        configurarBotonConvertir(convertir);
        buttonPanel.add(convertir);

        return buttonPanel;
    }

    private void configurarBotonConvertir(JButton convertir) {
        convertir.setBackground(new Color(70, 130, 180));
        convertir.setForeground(Color.WHITE);
        convertir.setFocusPainted(false);
        convertir.setFont(new Font("Arial", Font.BOLD, 14));
        convertir.setPreferredSize(new Dimension(150, 30));
        convertir.addActionListener(e -> convertirMoneda());
    }

    private JPanel crearPanelResultado() {
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(240, 248, 255));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        resultado.setFont(new Font("Arial", Font.BOLD, 16));
        resultado.setForeground(new Color(25, 25, 112));
        resultPanel.add(resultado);

        return resultPanel;
    }


    /**
     * Realiza la conversión de moneda cuando se presiona el botón.
     * Maneja posibles errores y muestra el resultado o mensajes de error.
     */
    private void convertirMoneda() {
        try {
            // Obtener las monedas seleccionadas
            Moneda monedaOrigenSeleccionada = (Moneda) monedaOrigen.getSelectedItem();
            Moneda monedaDestinoSeleccionada = (Moneda) monedaDestino.getSelectedItem();

            // Validar que las monedas no sean iguales
            if (monedaOrigenSeleccionada.getCodigo().equals(monedaDestinoSeleccionada.getCodigo())) {
                mostrarError("Las monedas de origen y destino no pueden ser iguales");
                return;
            }
            // Realizar la conversión
            double cantidadDouble = Double.parseDouble(cantidad.getText());
            double resultadoDouble = conversorService.convertirMoneda(
                monedaOrigenSeleccionada.getCodigo(),
                monedaDestinoSeleccionada.getCodigo(),
                cantidadDouble
            );

            actualizarResultado(cantidadDouble, resultadoDouble, 
                monedaOrigenSeleccionada.getCodigo(), monedaDestinoSeleccionada.getCodigo());

        } catch (IOException | InterruptedException e) {
            mostrarError("Error al realizar la solicitud a la API");
        } catch (NumberFormatException e) {
            mostrarError("La cantidad debe ser un número");
        }
    }

    /**
     * Actualiza la etiqueta de resultado con el valor convertido.
     */
    private void actualizarResultado(double cantidad, double resultado, 
            String monedaOrigen, String monedaDestino) {
        this.resultado.setText(String.format("%.2f %s = %.2f %s", 
            cantidad, monedaOrigen, resultado, monedaDestino));
    }

    /**
     * Muestra un diálogo de error con el mensaje especificado.
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }

    /**
     * Crea y devuelve el array de monedas disponibles para la conversión.
     * @return Array de objetos Moneda
     */
    private Moneda[] getMonedas() {
        return new Moneda[]{
            new Moneda("ARS", "Peso Argentino"),
            new Moneda("AUD", "Dólar Australia"),
            new Moneda("BRL", "Real Brasil"),
            new Moneda("CAD", "Dólar Canada"),
            new Moneda("CHF", "Franco Suizo"),
            new Moneda("CLP", "Peso Chileno"),
            new Moneda("COP", "Peso Colombia"),
            new Moneda("EUR", "Euro"),
            new Moneda("GBP", "Libra Esterlina"),
            new Moneda("JPY", "Yen Japonés"),
            new Moneda("MXN", "Peso Mexicano"),
            new Moneda("PEN", "Sol Peruano"),
            new Moneda("USD", "Dólar USA"),
            new Moneda("UYU", "Peso Uruguayo")
        };
    }
}