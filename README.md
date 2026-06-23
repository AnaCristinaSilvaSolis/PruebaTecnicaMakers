📄 Prueba Técnica - QA Automation Engineer
¡Bienvenido al repositorio de la prueba técnica! Este proyecto abarca la estrategia de pruebas completa para los tres
módulos solicitados: Pruebas Funcionales, Automatización de APIs con Postman y Automatización E2E con Selenium &
Cucumber Java.

📑 Contenido del Proyecto
Módulo 1: Pruebas Funcionales (Estrategia y Casos de Prueba en Notion)

Módulo 2: Automatización de API (Request Chaining en Postman)

Módulo 3: Automatización Web UI (Selenium WebDriver + Cucumber + Java)

📈 Módulo 1: Pruebas Funcionales (Notion)
Se diseñó y ejecutó una matriz de pruebas funcionales exhaustiva para el flujo de la aplicación.

Acceso al Tablero:  https://app.notion.com/p/MakersPay-3875e6115ee0803eb7d9d716e037e875?source=copy_link

Alcance: Cobertura de escenarios positivos, negativos y casos de borde utilizando técnicas de caja negra (Partición de
Equivalencia y Valores Límite) para asegurar la máxima robustez en el registro y procesamiento de datos.

📬 Módulo 2: Automatización de API (Postman)
Se creó una colección en Postman para validar los endpoints de https://reqres.in/api/. El flujo implementa Request
Chaining dinámico.

💻 Módulo 3: Automatización Web UI (Selenium + Cucumber)
Suite de pruebas de extremo a extremo (E2E) construida bajo el patrón de diseño Page Object Model (POM), utilizando Java
21, Selenium WebDriver, y Cucumber (Gherkin) para una legibilidad alineada al negocio.

🏗️ Arquitectura y Decisiones de Diseño:
BasePage Abstracta: Se implementó una clase base para centralizar y encapsular las acciones de Selenium (
driver.findElement, esperas explícitas distribuidas), aislando las páginas de errores tempranos de inicialización del
DOM.

Estrategia en Escenarios de Carrito: Para flujos lineales de agregación masiva, se optó por un diseño de escenario único
interactuando mediante capturas dinámicas de listas (driver.findElements) sobre los elementos comunes de la interfaz (
.btn_inventory), optimizando la ejecución en una única sesión activa del navegador en lugar de reiniciar el estado por
cada producto (Data-Driven tradicional).

Logs & Reportes: Integración nativa de trazas mediante un manejador de logs personalizado y hooks preparados para el
acoplamiento de reportes visuales dinámicos.

🚀 Requisitos y Ejecución Local
Asegúrate de tener instalado Java 21 y Maven en tu máquina.

Clonar el proyecto:

~~~bash
git clone https://github.com/AnaCristinaSilvaSolis/PruebaTecnicaMakers.git
cd PruebaTecnicaMakers
~~~

Ejecutar la suite completa desde la terminal:

~~~bash
mvn clean test
~~~

Desarrollado con 🛠️ y criterio de QA por Ana Cristina Silva Solis.
