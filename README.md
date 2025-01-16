# Conversor de Monedas

Una aplicación Java que permite convertir entre diferentes monedas utilizando tasas de cambio en tiempo real.

## Características

- Interfaz gráfica intuitiva
- Conversión en tiempo real usando la API de Exchange Rate
- Soporte para 14 monedas diferentes
- Validación de entrada de datos
- Manejo de errores
- Diseño modular y mantenible

## Requisitos Previos

- Java JDK 11 o superior
- Biblioteca org.json (incluida en el directorio `lib`)

## Estructura del Proyecto

```
conversor-monedas/
├── src/
│   └── conversor/
│       ├── Main.java           # Punto de entrada de la aplicación
│       ├── Moneda.java         # Clase que representa una moneda
│       ├── ConversorService.java # Servicio de conversión
│       └── ConversorUI.java    # Interfaz gráfica
├── lib/
│   └── json-20231013.jar      # Dependencia org.json
└── bin/                       # Archivos compilados
```

## Instalación

1. Clona o descarga este repositorio:
```bash
git clone <url-del-repositorio>
cd conversor-monedas
```

2. Asegúrate de tener el JDK instalado:
```bash
java --version
```

3. Compila el proyecto:
```bash
# Linux/Mac
javac -cp "lib/*" -d bin src/conversor/*.java

# Windows
javac -cp "lib/*" -d bin src\conversor\*.java
```

## Uso

1. Ejecuta la aplicación:
```bash
# Linux/Mac
java -cp "bin:lib/*" conversor.Main

# Windows
java -cp "bin;lib/*" conversor.Main
```

2. En la interfaz gráfica:
   - Selecciona la moneda de origen
   - Selecciona la moneda de destino
   - Ingresa la cantidad a convertir
   - Haz clic en "Convertir"

## Monedas Soportadas

- ARS - Peso Argentino
- AUD - Dólar Australiano
- BRL - Real Brasileño
- CAD - Dólar Canadiense
- CHF - Franco Suizo
- CLP - Peso Chileno
- COP - Peso Colombiano
- EUR - Euro
- GBP - Libra Esterlina
- JPY - Yen Japonés
- MXN - Peso Mexicano
- PEN - Sol Peruano
- USD - Dólar Estadounidense
- UYU - Peso Uruguayo

## Manejo de Errores

La aplicación incluye manejo de errores para:
- Entrada de datos inválida
- Problemas de conexión con la API
- Selección de monedas iguales
- Errores generales durante la conversión

## Arquitectura

El proyecto está estructurado en cuatro clases principales:

1. `Main`: Inicializa la aplicación y sus componentes.
2. `Moneda`: Representa una moneda con su código y nombre.
3. `ConversorService`: Maneja la lógica de negocio y las llamadas a la API.
4. `ConversorUI`: Implementa la interfaz gráfica de usuario.

## Desarrollo

Para contribuir al proyecto:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.


Link del Proyecto: [https://github.com/KEY1A/conversor-moneda]
