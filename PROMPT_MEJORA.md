# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/inventoryservice/InventoryServiceApplication.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/inventoryservice/application/usecase/ProductUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/inventoryservice/infrastructure/config/DatabaseConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/inventoryservice/infrastructure/controller/ProductController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/inventoryservice/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/inventoryservice/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/inventoryservice/infrastructure/controller/ProductControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.save`: Se invoca `save` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.findById`: Se invoca `findById` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.existsById`: Se invoca `existsById` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.deleteById`: Se invoca `deleteById` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.findAll`: Se invoca `findAll` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/inventoryservice/application/usecase/ProductUseCase.java` — `Product.isEmpty`: Se invoca `isEmpty` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/inventoryservice/application/usecase/ProductUseCaseTest.java` — `Product.size`: Se invoca `size` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/inventoryservice/application/usecase/ProductUseCaseTest.java` — `Product.isEmpty`: Se invoca `isEmpty` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced L2

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
Exponer un servicio de gestión de inventario que use las capacidades del marco de trabajo.

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de Servicio de Gestión de Inventario
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno y Creación del Servicio — objetivo: Configurar el entorno de desarrollo y crear el servicio básico de gestión de inventario. — entregable (NO resolver): Servicio básico de gestión de inventario con endpoints funcionales.
- Fase 2: Integración con Base de Datos y Manejo de Errores — objetivo: Integrar el servicio con una base de datos y manejar errores de forma adecuada. — entregable (NO resolver): Servicio de gestión de inventario integrado con base de datos y con manejo de errores.
- Fase 3: Generación de Registros de Rastros y Optimización — objetivo: Implementar la generación de registros de rastros y optimizar el servicio. — entregable (NO resolver): Servicio de gestión de inventario con generación de registros de rastros y optimizado.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.inventoryservice</groupId>
    <artifactId>inventory-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>inventory-service</name>
    <description>Servicio de gestión de inventario para comercio electrónico</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.1.0</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.32</version>
            <scope>provided</scope>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.5.6</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/inventoryservice/InventoryServiceApplication.java ===
package com.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.inventoryservice.infrastructure.config.DatabaseConfig;
import com.inventoryservice.infrastructure.config.LoggingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.inventoryservice.infrastructure.repository")
@EnableTransactionManagement
@EnableConfigurationProperties
@Import({DatabaseConfig.class, LoggingConfig.class})
public class InventoryServiceApplication implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceApplication.class);

    private final Environment environment;

    public InventoryServiceApplication(Environment environment) {
        this.environment = environment;
        validateEnvironment();
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(InventoryServiceApplication.class);
        application.setAdditionalProfiles("dev");
        application.run(args);
        logger.info("Inventory Service iniciado correctamente");
    }

    @Bean
    public String activeProfiles() {
        String[] profiles = environment.getActiveProfiles();
        logger.info("Perfiles activos: {}", Arrays.toString(profiles));
        return Arrays.toString(profiles);
    }

    private void validateEnvironment() {
        String dbUrl = environment.getProperty("spring.datasource.url");
        if (dbUrl == null || dbUrl.isBlank()) {
            throw new IllegalStateException("La propiedad 'spring.datasource.url' no está configurada");
        }

        String dbUsername = environment.getProperty("spring.datasource.username");
        if (dbUsername == null || dbUsername.isBlank()) {
            throw new IllegalStateException("La propiedad 'spring.datasource.username' no está configurada");
        }

        logger.info("Validación de entorno completada con éxito");
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/inventory

spring:
  application:
    name: inventory-service
  datasource:
    url: jdbc:postgresql://localhost:5432/inventory_db
    username: inventory_user
    password: inventory_pass
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
        jdbc:
          lob:
            non_contextual_creation: true
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

logging:
  level:
    root: INFO
    com.inventoryservice: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/inventory-service.log
    max-size: 10MB
    max-history: 7
    total-size-cap: 1GB

// === ARCHIVO: src/main/java/com/inventoryservice/domain/model/Product.java ===
package com.inventoryservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa un producto en el inventario.
 * Contiene los atributos esenciales para la gestión del inventario y validaciones básicas.
 */
public class Product {
    private final UUID id;
    private final String sku;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer stockQuantity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String category;

    public Product(UUID id, String sku, String name, String description, BigDecimal price,
                  Integer stockQuantity, LocalDateTime createdAt, LocalDateTime updatedAt, String category) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.sku = validateSku(sku);
        this.name = validateName(name);
        this.description = description;
        this.price = validatePrice(price);
        this.stockQuantity = validateStockQuantity(stockQuantity);
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
        this.updatedAt = Objects.requireNonNull(updatedAt, "La fecha de actualización no puede ser nula");
        this.category = validateCategory(category);
    }

    private String validateSku(String sku) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("El SKU no puede ser nulo o vacío");
        }
        if (sku.length() > 50) {
            throw new IllegalArgumentException("El SKU no puede exceder 50 caracteres");
        }
        return sku.trim();
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        return name.trim();
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        return price;
    }

    private Integer validateStockQuantity(Integer stockQuantity) {
        if (stockQuantity == null) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser nula");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser negativa");
        }
        return stockQuantity;
    }

    private String validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede ser nula o vacía");
        }
        if (category.length() > 50) {
            throw new IllegalArgumentException("La categoría no puede exceder 50 caracteres");
        }
        return category.trim();
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCategory() {
        return category;
    }

    public Product withUpdatedInfo(String name, String description, BigDecimal price, Integer stockQuantity, String category) {
        return new Product(
            this.id,
            this.sku,
            name != null ? name : this.name,
            description != null ? description : this.description,
            price != null ? price : this.price,
            stockQuantity != null ? stockQuantity : this.stockQuantity,
            this.createdAt,
            LocalDateTime.now(),
            category != null ? category : this.category
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + ''' +
                ", name='" + name + ''' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category='" + category + ''' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/domain/port/ProductRepository.java ===
package com.inventoryservice.domain.port;

import com.inventoryservice.domain.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para operaciones de persistencia de productos.
 * Define los contratos que la capa de infraestructura debe implementar para interactuar con la base de datos.
 */
public interface ProductRepository {
    /**
     * Guarda un producto en el repositorio.
     * @param product el producto a guardar
     * @return el producto guardado
     */
    Product save(Product product);

    /**
     * Busca un producto por su ID.
     * @param id el ID del producto
     * @return un Optional que contiene el producto si existe
     */
    Optional<Product> findById(UUID id);

    /**
     * Busca un producto por su SKU.
     * @param sku el SKU del producto
     * @return un Optional que contiene el producto si existe
     */
    Optional<Product> findBySku(String sku);

    /**
     * Elimina un producto por su ID.
     * @param id el ID del producto a eliminar
     * @return true si el producto fue eliminado, false si no existía
     */
    boolean deleteById(UUID id);

    /**
     * Obtiene todos los productos del repositorio.
     * @return una lista de todos los productos
     */
    List<Product> findAll();

    /**
     * Verifica si existe un producto con el SKU dado.
     * @param sku el SKU a verificar
     * @return true si existe un producto con ese SKU, false en caso contrario
     */
    boolean existsBySku(String sku);
}

// === ARCHIVO: src/main/java/com/inventoryservice/domain/port/ProductService.java ===
package com.inventoryservice.domain.port;

import com.inventoryservice.domain.model.Product;
import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada para operaciones de negocio relacionadas con productos.
 * Define los contratos que la capa de aplicación debe implementar para exponer la funcionalidad del dominio.
 */
public interface ProductService {
    /**
     * Crea un nuevo producto.
     * @param product el producto a crear
     * @return el producto creado
     * @throws IllegalArgumentException si el producto es inválido
     * @throws IllegalStateException si ya existe un producto con el mismo SKU
     */
    Product createProduct(Product product);

    /**
     * Obtiene un producto por su ID.
     * @param id el ID del producto
     * @return el producto encontrado
     * @throws java.util.NoSuchElementException si el producto no existe
     */
    Product getProductById(UUID id);

    /**
     * Actualiza un producto existente.
     * @param id el ID del producto a actualizar
     * @param product los nuevos datos del producto
     * @return el producto actualizado
     * @throws java.util.NoSuchElementException si el producto no existe
     * @throws IllegalArgumentException si los datos del producto son inválidos
     */
    Product updateProduct(UUID id, Product product);

    /**
     * Elimina un producto por su ID.
     * @param id el ID del producto a eliminar
     * @throws java.util.NoSuchElementException si el producto no existe
     */
    void deleteProduct(UUID id);

    /**
     * Obtiene todos los productos.
     * @return una lista de todos los productos
     */
    List<Product> getAllProducts();

    /**
     * Reduce el stock de un producto.
     * @param id el ID del producto
     * @param quantity la cantidad a reducir
     * @return el producto con el stock actualizado
     * @throws java.util.NoSuchElementException si el producto no existe
     * @throws IllegalArgumentException si la cantidad es inválida o el stock es insuficiente
     */
    Product reduceStock(UUID id, int quantity);
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java ===
package com.inventoryservice.infrastructure.adapter;


import com.inventoryservice.infrastructure.repository.ProductJpaAdapter;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.domain.port.ProductRepository;
import com.inventoryservice.infrastructure.repository.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductJpaRepository implements ProductRepository {

    private final ProductJpaAdapter productJpaAdapter;

    public ProductJpaRepository(ProductJpaAdapter productJpaAdapter) {
        this.productJpaAdapter = productJpaAdapter;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity savedEntity = productJpaAdapter.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(UUID id) {
        return productJpaAdapter.findById(id).map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findBySku(String sku) {
        return productJpaAdapter.findBySku(sku).map(this::toDomain);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (productJpaAdapter.existsById(id)) {
            productJpaAdapter.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productJpaAdapter.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productJpaAdapter.existsBySku(sku);
    }

    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        if (product.getId() != null) {
            entity.setId(product.getId());
        }
        entity.setSku(product.getSku());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStockQuantity(product.getStockQuantity());
        entity.setCategory(product.getCategory());
        entity.setCreatedAt(product.getCreatedAt() != null ? product.getCreatedAt() : LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getCategory()
        );
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/repository/ProductJpaAdapter.java ===
package com.inventoryservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaAdapter extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findBySku(String sku);
    boolean existsBySku(String sku);
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/controller/dto/ProductRequest.java ===
package com.inventoryservice.infrastructure.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "El SKU es obligatorio")
        @Size(min = 3, max = 50, message = "El SKU debe tener entre 3 y 50 caracteres")
        String sku,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 1, max = 200, message = "El nombre debe tener entre 1 y 200 caracteres")
        String name,

        @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
        String description,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        BigDecimal price,

        @NotNull(message = "La cantidad en stock es obligatoria")
        Integer stockQuantity,

        @NotBlank(message = "La categoría es obligatoria")
        @Size(max = 100, message = "La categoría no puede exceder 100 caracteres")
        String category
) {
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/controller/dto/ProductResponse.java ===
package com.inventoryservice.infrastructure.controller.dto;

import com.inventoryservice.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String sku,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String category
) {
    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory()
        );
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/application/usecase/ProductUseCase.java ===
package com.inventoryservice.application.usecase;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.domain.port.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProductUseCase.class);
    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String sku, String name, String description, 
                                  BigDecimal price, Integer stockQuantity, String category) {
        logger.info("Creating new product with SKU: {}", sku);
        
        if (productRepository.existsBySku(sku)) {
            logger.warn("Product with SKU {} already exists", sku);
            throw new IllegalArgumentException("Product with SKU " + sku + " already exists");
        }
        
        Product product = new Product(
            UUID.randomUUID(),
            sku,
            name,
            description,
            price,
            stockQuantity,
            LocalDateTime.now(),
            LocalDateTime.now(),
            category
        );
        
        Product savedProduct = productRepository.save(product);
        logger.info("Product created successfully with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(UUID id) {
        logger.debug("Fetching product by ID: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            logger.warn("Product not found with ID: {}", id);
        }
        return product;
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductBySku(String sku) {
        logger.debug("Fetching product by SKU: {}", sku);
        return productRepository.findBySku(sku);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        logger.debug("Fetching all products");
        List<Product> products = productRepository.findAll();
        logger.info("Found {} products", products.size());
        return products;
    }

    public Product updateProduct(UUID id, String name, String description, 
                                  BigDecimal price, Integer stockQuantity, String category) {
        logger.info("Updating product with ID: {}", id);
        
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product not found for update with ID: {}", id);
                return new IllegalArgumentException("Product not found with ID: " + id);
            });
        
        Product updatedProduct = existingProduct.withUpdatedInfo(
            name, description, price, stockQuantity, category
        );
        
        Product savedProduct = productRepository.save(updatedProduct);
        logger.info("Product updated successfully: {}", savedProduct.getId());
        return savedProduct;
    }

    public boolean deleteProduct(UUID id) {
        logger.info("Deleting product with ID: {}", id);
        
        boolean exists = productRepository.findById(id).isPresent();
        if (!exists) {
            logger.warn("Product not found for deletion with ID: {}", id);
            return false;
        }
        
        boolean deleted = productRepository.deleteById(id);
        if (deleted) {
            logger.info("Product deleted successfully: {}", id);
        } else {
            logger.error("Failed to delete product: {}", id);
        }
        return deleted;
    }

    public Product reduceStock(UUID id, int quantity) {
        logger.info("Reducing stock for product ID: {} by quantity: {}", id, quantity);
        
        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product not found for stock reduction: {}", id);
                return new IllegalArgumentException("Product not found with ID: " + id);
            });
        
        int currentStock = product.getStockQuantity();
        if (currentStock < quantity) {
            logger.error("Insufficient stock for product ID: {}. Current: {}, Requested: {}", 
                id, currentStock, quantity);
            throw new IllegalStateException("Insufficient stock. Available: " + currentStock);
        }
        
        int newStock = currentStock - quantity;
        Product updatedProduct = product.withUpdatedInfo(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            newStock,
            product.getCategory()
        );
        
        Product savedProduct = productRepository.save(updatedProduct);
        logger.info("Stock reduced successfully. New stock for product {}: {}", id, newStock);
        return savedProduct;
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/config/DatabaseConfig.java ===
package com.inventoryservice.infrastructure.config;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.inventoryservice.infrastructure.adapter")
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${spring.datasource.url:jdbc:postgresql://localhost:5432/inventory_db}")
    private String dbUrl;

    @Value("${spring.datasource.username:postgres}")
    private String dbUsername;

    @Value("${spring.datasource.password:postgres}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name:org.postgresql.Driver}")
    private String driverClassName;

    @Value("${spring.jpa.hibernate.ddl-auto:update}")
    private String hibernateDdlAuto;

    @Value("${spring.jpa.show-sql:false}")
    private boolean showSql;

    @Value("${spring.jpa.properties.hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.properties.hibernate.format_sql:true}")
    private boolean formatSql;

    @Bean
    @Primary
    public DataSource dataSource() {
        logger.info("Configuring PostgreSQL DataSource");
        logger.debug("Database URL: {}", dbUrl);
        
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
            .url(dbUrl)
            .username(dbUsername)
            .password(dbPassword)
            .driverClassName(driverClassName);
        
        DataSource dataSource = dataSourceBuilder.build();
        logger.info("DataSource configured successfully");
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        logger.info("Configuring JPA EntityManagerFactory");
        
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.inventoryservice.infrastructure.repository", 
                            "com.inventoryservice.domain.model");
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(showSql);
        em.setJpaVendorAdapter(vendorAdapter);
        
        Properties additionalProperties = new Properties();
        additionalProperties.setProperty("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        additionalProperties.setProperty("hibernate.dialect", hibernateDialect);
        additionalProperties.setProperty("hibernate.format_sql", String.valueOf(formatSql));
        additionalProperties.setProperty("hibernate.use_sql_comments", "true");
        additionalProperties.setProperty("hibernate.jdbc.batch_size", "20");
        additionalProperties.setProperty("hibernate.order_inserts", "true");
        additionalProperties.setProperty("hibernate.order_updates", "true");
        additionalProperties.setProperty("hibernate.jdbc.batch_versioned_data", "true");
        
        em.setJpaProperties(additionalProperties);
        logger.info("EntityManagerFactory configured successfully");
        
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        logger.info("Configuring JPA TransactionManager");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        logger.info("TransactionManager configured successfully");
        return transactionManager;
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/controller/ProductController.java ===
package com.inventoryservice.infrastructure.controller;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.infrastructure.controller.dto.ProductRequest;
import com.inventoryservice.infrastructure.controller.dto.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductUseCaseWrapper productUseCase;

    public ProductController(ProductUseCaseWrapper productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        logger.info("POST /api/v1/products - Creating product with SKU: {}", request.sku());
        try {
            Product product = productUseCase.createProduct(
                request.sku(),
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                request.category()
            );
            ProductResponse response = ProductResponse.fromDomain(product);
            logger.info("Product created successfully with ID: {}", product.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to create product: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        logger.info("GET /api/v1/products/{}", id);
        return productUseCase.getProductById(id)
            .map(product -> {
                logger.debug("Product found: {}", product.getId());
                return ResponseEntity.ok(ProductResponse.fromDomain(product));
            })
            .orElseGet(() -> {
                logger.warn("Product not found: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponse> getProductBySku(@PathVariable String sku) {
        logger.info("GET /api/v1/products/sku/{}", sku);
        return productUseCase.getProductBySku(sku)
            .map(product -> {
                logger.debug("Product found with SKU: {}", sku);
                return ResponseEntity.ok(ProductResponse.fromDomain(product));
            })
            .orElseGet(() -> {
                logger.warn("Product not found with SKU: {}", sku);
                return ResponseEntity.notFound().build();
            });
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        logger.info("GET /api/v1/products - Fetching all products");
        
        List<Product> products;
        if (category != null || minPrice != null || maxPrice != null) {
            logger.debug("Filtering products - category: {}, minPrice: {}, maxPrice: {}", 
                category, minPrice, maxPrice);
            products = productUseCase.filterProducts(category, minPrice, maxPrice);
        } else {
            products = productUseCase.getAllProducts();
        }
        
        List<ProductResponse> responses = products.stream()
            .map(ProductResponse::fromDomain)
            .collect(Collectors.toList());
        
        logger.info("Returning {} products", responses.size());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable UUID id, 
            @Valid @RequestBody ProductRequest request) {
        logger.info("PUT /api/v1/products/{}", id);
        try {
            Product product = productUseCase.updateProduct(
                id,
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                request.category()
            );
            ProductResponse response = ProductResponse.fromDomain(product);
            logger.info("Product updated successfully: {}", id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to update product {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        logger.info("DELETE /api/v1/products/{}", id);
        boolean deleted = productUseCase.deleteProduct(id);
        if (deleted) {
            logger.info("Product deleted successfully: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Product not found for deletion: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reduce-stock")
    public ResponseEntity<ProductResponse> reduceStock(
            @PathVariable UUID id, 
            @RequestParam int quantity) {
        logger.info("PUT /api/v1/products/{}/reduce-stock - quantity: {}", id, quantity);
        try {
            Product product = productUseCase.reduceStock(id, quantity);
            ProductResponse response = ProductResponse.fromDomain(product);
            logger.info("Stock reduced successfully for product: {}", id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Product not found: {}", id);
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            logger.warn("Insufficient stock for product {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/repository/ProductEntity.java ===
package com.inventoryservice.infrastructure.repository;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products", indexes = {
    @Index(name = "idx_product_sku", columnList = "sku", unique = true),
    @Index(name = "idx_product_category", columnList = "category")
})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 100)
    private String category;

    public ProductEntity() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/config/GlobalExceptionHandler.java ===
package com.inventoryservice.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(
            NoSuchElementException ex, WebRequest request) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "RECURSO_NO_ENCONTRADO",
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento inválido: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "ARGUMENTO_INVALIDO",
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "ERROR_DE_VALIDACION",
                "Los datos proporcionados no son válidos",
                LocalDateTime.now(),
                request.getDescription(false),
                fieldErrors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        logger.error("Estado ilegal detectado: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "ESTADO_CONFLICTO",
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "ERROR_INTERNO",
                "Ocurrió un error inesperado. Por favor, contacte al administrador.",
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record ErrorResponse(
            int status,
            String code,
            String message,
            LocalDateTime timestamp,
            String path
    ) {}

    public record ValidationErrorResponse(
            int status,
            String code,
            String message,
            LocalDateTime timestamp,
            String path,
            Map<String, String> fieldErrors
    ) {}
}

// === ARCHIVO: src/main/java/com/inventoryservice/infrastructure/config/LoggingConfig.java ===
package com.inventoryservice.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("!test")
public class LoggingConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);

    private static final List<String> LOG_LEVELS = Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR");

    @PostConstruct
    public void initializeLogging() {
        logger.info("========================================================");
        logger.info("Configuración de Logging inicializada para el servicio de inventario");
        logger.info("Niveles de logging disponibles: {}", LOG_LEVELS);
        logger.info("Logger raíz configurado con nivel: INFO");
        logger.info("Logger de dominio configurado con nivel: DEBUG");
        logger.info("Logger de infraestructura configurado con nivel: INFO");
        logger.info("========================================================");
    }

    public void logApplicationStartup(String profile, String port) {
        logger.info("Aplicación iniciada en perfil: {}", profile);
        logger.info("Puerto configurado: {}", port);
        logger.info("El servicio de gestión de inventario está operativo");
    }

    public void logDatabaseConnection(String databaseType, String host) {
        logger.info("Conexión a base de datos establecida");
        logger.debug("Tipo de base de datos: {}, Host: {}", databaseType, host);
    }

    public void logRequest(String method, String endpoint, String requestId) {
        logger.info("Solicitud recibida - Método: {}, Endpoint: {}, RequestId: {}", method, endpoint, requestId);
    }

    public void logResponse(String method, String endpoint, int statusCode, long durationMs) {
        logger.info("Respuesta enviada - Método: {}, Endpoint: {}, Estado: {}, Duración: {}ms", 
                method, endpoint, statusCode, durationMs);
    }

    public void logError(String operation, Exception ex) {
        logger.error("Error en operación: {}, Mensaje: {}", operation, ex.getMessage(), ex);
    }

    public void logValidationError(String field, String message) {
        logger.warn("Error de validación - Campo: {}, Mensaje: {}", field, message);
    }

    public void logBusinessRule(String rule, String details) {
        logger.debug("Regla de negocio aplicada - Regla: {}, Detalles: {}", rule, details);
    }

    public void logSecurityEvent(String eventType, String details) {
        logger.warn("Evento de seguridad - Tipo: {}, Detalles: {}", eventType, details);
    }

    public void logPerformanceMetric(String operation, long durationMs, String threshold) {
        if (durationMs > 1000) {
            logger.warn("Métrica de rendimiento - Operación: {}, Duración: {}ms (umbral: {})", 
                    operation, durationMs, threshold);
        } else {
            logger.debug("Métrica de rendimiento - Operación: {}, Duración: {}ms", operation, durationMs);
        }
    }

    public static class LogMarker {
        public static final String STARTUP = "STARTUP";
        public static final String DATABASE = "DATABASE";
        public static final String REQUEST = "REQUEST";
        public static final String RESPONSE = "RESPONSE";
        public static final String ERROR = "ERROR";
        public static final String SECURITY = "SECURITY";
        public static final String PERFORMANCE = "PERFORMANCE";
    }
}

// === ARCHIVO: src/test/java/com/inventoryservice/application/usecase/ProductUseCaseTest.java ===
package com.inventoryservice.application.usecase;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.domain.port.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductUseCase - Tests Unitarios")
class ProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductUseCase productUseCase;

    private Product testProduct;
    private UUID testProductId;

    @BeforeEach
    void setUp() {
        testProductId = UUID.randomUUID();
        testProduct = new Product(
            testProductId,
            "SKU-001",
            "Producto de Prueba",
            "Descripción del producto de prueba",
            new BigDecimal("99.99"),
            50,
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ELECTRONICS"
        );
    }

    @Nested
    @DisplayName("Crear Producto")
    class CreateProduct {

        @Test
        @DisplayName("Debería crear un producto exitosamente cuando los datos son válidos")
        void shouldCreateProductSuccessfully() {
            when(productRepository.existsBySku(testProduct.getSku())).thenReturn(false);
            when(productRepository.save(any(Product.class))).thenReturn(testProduct);

            Product result = productUseCase.createProduct(testProduct);

            assertNotNull(result);
            assertEquals(testProduct.getId(), result.getId());
            assertEquals(testProduct.getSku(), result.getSku());
            verify(productRepository).existsBySku(testProduct.getSku());
            verify(productRepository).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el SKU ya existe")
        void shouldThrowExceptionWhenSkuExists() {
            when(productRepository.existsBySku(testProduct.getSku())).thenReturn(true);

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.createProduct(testProduct);
            });

            verify(productRepository, never()).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto es nulo")
        void shouldThrowExceptionWhenProductIsNull() {
            assertThrows(NullPointerException.class, () -> {
                productUseCase.createProduct(null);
            });
        }
    }

    @Nested
    @DisplayName("Obtener Producto por ID")
    class GetProductById {

        @Test
        @DisplayName("Debería obtener un producto por su ID")
        void shouldGetProductById() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));

            Product result = productUseCase.getProductById(testProductId);

            assertNotNull(result);
            assertEquals(testProductId, result.getId());
            verify(productRepository).findById(testProductId);
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto no existe")
        void shouldThrowExceptionWhenProductNotFound() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.getProductById(nonExistentId);
            });
        }
    }

    @Nested
    @DisplayName("Actualizar Producto")
    class UpdateProduct {

        @Test
        @DisplayName("Debería actualizar un producto exitosamente")
        void shouldUpdateProductSuccessfully() {
            Product updatedProduct = new Product(
                testProductId,
                "SKU-001",
                "Producto Actualizado",
                "Nueva descripción",
                new BigDecimal("149.99"),
                100,
                testProduct.getCreatedAt(),
                LocalDateTime.now(),
                "ELECTRONICS"
            );

            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));
            when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

            Product result = productUseCase.updateProduct(testProductId, updatedProduct);

            assertNotNull(result);
            assertEquals("Producto Actualizado", result.getName());
            verify(productRepository).findById(testProductId);
            verify(productRepository).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción al actualizar un producto inexistente")
        void shouldThrowExceptionWhenUpdatingNonExistentProduct() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.updateProduct(nonExistentId, testProduct);
            });
        }
    }

    @Nested
    @DisplayName("Eliminar Producto")
    class DeleteProduct {

        @Test
        @DisplayName("Debería eliminar un producto exitosamente")
        void shouldDeleteProductSuccessfully() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));
            when(productRepository.deleteById(testProductId)).thenReturn(true);

            assertDoesNotThrow(() -> {
                productUseCase.deleteProduct(testProductId);
            });

            verify(productRepository).findById(testProductId);
            verify(productRepository).deleteById(testProductId);
        }

        @Test
        @DisplayName("Debería lanzar excepción al eliminar un producto inexistente")
        void shouldThrowExceptionWhenDeletingNonExistentProduct() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.deleteProduct(nonExistentId);
            });
        }
    }

    @Nested
    @DisplayName("Obtener Todos los Productos")
    class GetAllProducts {

        @Test
        @DisplayName("Debería obtener todos los productos")
        void shouldGetAllProducts() {
            List<Product> productList = new ArrayList<>();
            productList.add(testProduct);
            productList.add(new Product(
                UUID.randomUUID(),
                "SKU-002",
                "Segundo Producto",
                "Descripción dos",
                new BigDecimal("199.99"),
                30,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "HOME"
            ));

            when(productRepository.findAll()).thenReturn(productList);

            List<Product> result = productUseCase.getAllProducts();

            assertNotNull(result);
            assertEquals(2, result.size());
            verify(productRepository).findAll();
        }

        @Test
        @DisplayName("Debería retornar lista vacía cuando no hay productos")
        void shouldReturnEmptyListWhenNoProducts() {
            when(productRepository.findAll()).thenReturn(new ArrayList<>());

            List<Product> result = productUseCase.getAllProducts();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Reducir Stock")
    class ReduceStock {

        @Test
        @DisplayName("Debería reducir el stock exitosamente")
        void shouldReduceStockSuccessfully() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));
            when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
                Product p = invocation.getArgument(0);
                return p;
            });

            Product result = productUseCase.reduceStock(testProductId, 10);

            assertNotNull(result);
            assertEquals(40, result.getStockQuantity());
            verify(productRepository).findById(testProductId);
            verify(productRepository).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto no existe")
        void shouldThrowExceptionWhenProductNotFound() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.reduceStock(nonExistentId, 10);
            });
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el stock es insuficiente")
        void shouldThrowExceptionWhenInsufficientStock() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));

            assertThrows(IllegalStateException.class, () -> {
                productUseCase.reduceStock(testProductId, 100);
            });
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando la cantidad a reducir es negativa")
        void shouldThrowExceptionWhenQuantityIsNegative() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.reduceStock(testProductId, -5);
            });
        }
    }
}

// === ARCHIVO: src/test/java/com/inventoryservice/infrastructure/controller/ProductControllerTest.java ===
package com.inventoryservice.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryservice.application.usecase.ProductUseCase;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.infrastructure.controller.dto.ProductRequest;
import com.inventoryservice.infrastructure.controller.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@DisplayName("ProductController - Tests de Integración")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductUseCase productUseCase;

    private Product testProduct;
    private UUID testProductId;
    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        testProductId = UUID.randomUUID();
        testProduct = new Product(
            testProductId,
            "SKU-TEST-001",
            "Producto de Test",
            "Descripción del producto de test",
            new BigDecimal("99.99"),
            50,
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ELECTRONICS"
        );

        productRequest = new ProductRequest(
            "SKU-TEST-001",
            "Producto de Test",
            "Descripción del producto de test",
            new BigDecimal("99.99"),
            50,
            "ELECTRONICS"
        );

        productResponse = new ProductResponse(
            testProductId,
            "SKU-TEST-001",
            "Producto de Test",
            "Descripción del producto de test",
            new BigDecimal("99.99"),
            50,
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ELECTRONICS"
        );
    }

    @Nested
    @DisplayName("POST /api/products - Crear Producto")
    class CreateProduct {

        @Test
        @DisplayName("Debería crear un producto y retornar 201 Created")
        void shouldCreateProductAndReturn201() throws Exception {
            when(productUseCase.createProduct(any(Product.class))).thenReturn(testProduct);

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(testProductId.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-TEST-001"));
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando el SKU está vacío")
        void shouldReturn400WhenSkuIsEmpty() throws Exception {
            ProductRequest invalidRequest = new ProductRequest(
                "",
                "Producto",
                "Descripción",
                new BigDecimal("99.99"),
                50,
                "ELECTRONICS"
            );

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando el precio es negativo")
        void shouldReturn400WhenPriceIsNegative() throws Exception {
            ProductRequest invalidRequest = new ProductRequest(
                "SKU-001",
                "Producto",
                "Descripción",
                new BigDecimal("-10.00"),
                50,
                "ELECTRONICS"
            );

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 409 Conflict cuando el SKU ya existe")
        void shouldReturn409WhenSkuExists() throws Exception {
            when(productUseCase.createProduct(any(Product.class)))
                .thenThrow(new IllegalArgumentException("El SKU ya existe"));

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isConflict());
        }
    }

    @Nested
    @DisplayName("GET /api/products - Obtener Todos los Productos")
    class GetAllProducts {

        @Test
        @DisplayName("Debería obtener todos los productos y retornar 200 OK")
        void shouldGetAllProductsAndReturn200() throws Exception {
            List<Product> productList = new ArrayList<>();
            productList.add(testProduct);
            productList.add(new Product(
                UUID.randomUUID(),
                "SKU-TEST-002",
                "Segundo Producto",
                "Descripción dos",
                new BigDecimal("199.99"),
                30,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "HOME"
            ));

            when(productUseCase.getAllProducts()).thenReturn(productList);

            mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].sku").value("SKU-TEST-001"))
                .andExpect(jsonPath("$[1].sku").value("SKU-TEST-002"));
        }

        @Test
        @DisplayName("Debería retornar lista vacía cuando no hay productos")
        void shouldReturnEmptyListWhenNoProducts() throws Exception {
            when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());

            mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        }
    }

    @Nested
    @DisplayName("GET /api/products/{id} - Obtener Producto por ID")
    class GetProductById {

        @Test
        @DisplayName("Debería obtener un producto por su ID y retornar 200 OK")
        void shouldGetProductByIdAndReturn200() throws Exception {
            when(productUseCase.getProductById(testProductId)).thenReturn(testProduct);

            mockMvc.perform(get("/api/products/{id}", testProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testProductId.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-TEST-001"))
                .andExpect(jsonPath("$.name").value("Producto de Test"));
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found cuando el producto no existe")
        void shouldReturn404WhenProductNotFound() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(productUseCase.getProductById(nonExistentId))
                .thenThrow(new IllegalArgumentException("Producto no encontrado"));

            mockMvc.perform(get("/api/products/{id}", nonExistentId))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando el ID no es válido")
        void shouldReturn400WhenIdIsInvalid() throws Exception {
            mockMvc.perform(get("/api/products/{id}", "invalid-uuid"))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PUT /api/products/{id} - Actualizar Producto")
    class UpdateProduct {

        @Test
        @DisplayName("Debería actualizar un producto y retornar 200 OK")
        void shouldUpdateProductAndReturn200() throws Exception {
            Product updatedProduct = new Product(
                testProductId,
                "SKU-TEST-001",
                "Producto Actualizado",
                "Nueva descripción",
                new BigDecimal("149.99"),
                100,
                testProduct.getCreatedAt(),
                LocalDateTime.now(),
                "ELECTRONICS"
            );

            when(productUseCase.updateProduct(eq(testProductId), any(Product.class)))
                .thenReturn(updatedProduct);

            ProductRequest updateRequest = new ProductRequest(
                "SKU-TEST-001",
                "Producto Actualizado",
                "Nueva descripción",
                new BigDecimal("149.99"),
                100,
                "ELECTRONICS"
            );

            mockMvc.perform(put("/api/products/{id}", testProductId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Producto Actualizado"))
                .andExpect(jsonPath("$.price").value(149.99));
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found al actualizar un producto inexistente")
        void shouldReturn404WhenUpdatingNonExistentProduct() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(productUseCase.updateProduct(eq(nonExistentId), any(Product.class)))
                .thenThrow(new IllegalArgumentException("Producto no encontrado"));

            mockMvc.perform(put("/api/products/{id}", nonExistentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/products/{id} - Eliminar Producto")
    class DeleteProduct {

        @Test
        @DisplayName("Debería eliminar un producto y retornar 204 No Content")
        void shouldDeleteProductAndReturn204() throws Exception {
            doNothing().when(productUseCase).deleteProduct(testProductId);

            mockMvc.perform(delete("/api/products/{id}", testProductId))
                .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found al eliminar un producto inexistente")
        void shouldReturn404WhenDeletingNonExistentProduct() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            doThrow(new IllegalArgumentException("Producto no encontrado"))
                .when(productUseCase).deleteProduct(nonExistentId);

            mockMvc.perform(delete("/api/products/{id}", nonExistentId))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("PATCH /api/products/{id}/reduce-stock - Reducir Stock")
    class ReduceStock {

        @Test
        @DisplayName("Debería reducir el stock y retornar 200 OK")
        void shouldReduceStockAndReturn200() throws Exception {
            Product reducedProduct = new Product(
                testProductId,
                "SKU-TEST-001",
                "Producto de Test",
                "Descripción del producto de test",
                new BigDecimal("99.99"),
                40,
                testProduct.getCreatedAt(),
                LocalDateTime.now(),
                "ELECTRONICS"
            );

            when(productUseCase.reduceStock(testProductId, 10)).thenReturn(reducedProduct);

            mockMvc.perform(patch("/api/products/{id}/reduce-stock", testProductId)
                    .param("quantity", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stockQuantity").value(40));
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando la cantidad es negativa")
        void shouldReturn400WhenQuantityIsNegative() throws Exception {
            mockMvc.perform(patch("/api/products/{id}/reduce-stock", testProductId)
                    .param("quantity", "-5"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 409 Conflict cuando el stock es insuficiente")
        void shouldReturn409WhenInsufficientStock() throws Exception {
            when(productUseCase.reduceStock(testProductId, 100))
                .thenThrow(new IllegalStateException("Stock insuficiente"));

            mockMvc.perform(patch("/api/products/{id}/reduce-stock", testProductId)
                    .param("quantity", "100"))
                .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found cuando el producto no existe")
        void shouldReturn404WhenProductNotFound() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(productUseCase.reduceStock(nonExistentId, 10))
                .thenThrow(new IllegalArgumentException("Producto no encontrado"));

            mockMvc.perform(patch("/api/products/{id}/reduce-stock", nonExistentId)
                    .param("quantity", "10"))
                .andExpect(status().isNotFound());
        }
    }
}
```
