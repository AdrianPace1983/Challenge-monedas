# Challenge: Conversor de Monedas

Este proyecto es un conversor de monedas desarrollado en Java como parte del desafio del curso Java Orientado a Objetos G7 - One de Oracle + Alura Latam. El sistema consume el API de [ExchangeRate-API](https://www.exchangerate-api.com/docs/overview) para obtener las tasas de conversión de monedas y utiliza un menú interactivo generado a partir de un archivo JSON para realizar las conversiones.

## Características

- **Uso de ExchangeRate-API**: El sistema consume la API de tasas de cambio para realizar las conversiones entre diferentes monedas.
- **Manejo seguro del API Key**: La clave de API se maneja de manera segura utilizando la biblioteca `Dotenv` para obtenerla desde un archivo `.env`.
- **Menú dinámico**: Un menú inicial se genera a partir de un archivo JSON, donde se definen las monedas base y las monedas de destino.
- **Consumo de JSON**: Utiliza la biblioteca `Gson` para transformar las respuestas de la API y el archivo de configuración JSON.

## Requisitos

- Java 11 o superior
- [ExchangeRate-API](https://www.exchangerate-api.com/) con un API Key válido
- Biblioteca [`Dotenv`](https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java/3.0.0) para gestionar las variables de entorno
- Biblioteca [`Gson`](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.11.0) para trabajar con JSON
- Archivo `.env` que contiene la variable `API_KEY`

## Instalación

1. **Clonar el repositorio:**:

   ```bash
   git clone https://github.com/odriverar/conversor-de-monedas.git
   cd conversor-monedas
   ```
2. **Crear un archivo `.env` en el directorio raíz del proyecto con tu clave de API:**
   ```bash
   API_KEY=TU_CLAVE_DE_API
   ```
   > Asegúrate de reemplazar `TU_CLAVE_DE_API` con tu API Key de ExchangeRate-API.
3. **Asegúrate de tener las bibliotecas necesarias agregadas a tu proyecto**
4. **Configura el archivo JSON que contiene las opciones del menú. Ejemplo del formato:**
   ```json
   [
    {
    "id":  1,
    "descripcion": "Dólar =>> Nuevo sol peruano",
    "base": "USD",
    "cambio": "PEN"
    },
    {
    "id":  2,
    "descripcion": "Nuevo sol peruano =>> Dólar",
    "base": "PEN",
    "cambio": "USD"
    }
   ]
   ```

## Uso
1. **Ejecuta el programa desde IntelliJ o el IDE de tu preferencia**

## Estructura del Proyecto
```plaintext
src/
└── drr.aluradesafio.conversormonedas
    ├── dominio
    │   └── ...                 # Clases del dominio del sistema
    ├── presentacion
    │   └── Principal.java      # Clases relacionada a la interfaz de consola
    ├── recursos
    │   └── opciones.json       # Archivo JSON con las opciones del menú
    ├── servicio
    │   └── ...                 # Lógica de negocio y servicio de conversión de moneda
└── .env                        # Archivo que contiene la variable API_KEY
```
## Conclusión

Este conversor de monedas es una herramienta educativa diseñada para facilitar el uso de APIs externas y la manipulación de datos JSON en Java. Al utilizar buenas prácticas como el manejo seguro del API Key mediante `Dotenv` y la transformación de datos con `Gson`, el proyecto ofrece una estructura sólida y escalable. La funcionalidad principal se basa en un menú interactivo que permite seleccionar las monedas a convertir y obtener tasas de cambio actualizadas, proporcionando una base útil para aprender sobre integración de servicios externos en aplicaciones Java.

## Cómo Contribuir

¡Contribuciones son bienvenidas! Si deseas mejorar este proyecto, sigue estos pasos para contribuir a través de GitHub:

1. **Fork el repositorio**:
   Haz clic en el botón "Fork" en la parte superior de la página del repositorio.

2. **Clona tu fork localmente**:

   ```bash
   git clone https://github.com/tu_usuario/conversor-monedas.git
   ```
3. **Crea una nueva rama para tu cambio**
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
4. **Realiza tus cambios y haz commits descriptivos:**
   ```bash
   git commit -m "Descripción detallada de lo que has cambiado"
   ```
5. **Sube tu rama al repositorio remoto:**
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
6. **Abre un Pull Request:**
   - Ve a la pestaña "Pull requests" en el repositorio original.
   - Haz clic en "New pull request" y selecciona la rama que has subido. 
   - Proporciona una descripción clara de los cambios que has hecho.
7. **Espera la revisión:** Tu Pull Request será revisado y, si todo está en orden, será fusionado con la rama principal del proyecto.



### Contacto

Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue en el repositorio o contactarnos a través de odriverar@gmail.com.

---

>Gracias por utilizar este proyecto. Esperamos que estas herramientas te sean útiles.