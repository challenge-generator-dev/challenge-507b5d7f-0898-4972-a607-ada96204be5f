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