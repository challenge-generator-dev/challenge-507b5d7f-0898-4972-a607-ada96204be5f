# Implementación de Servicio de Gestión de Inventario

El equipo de desarrollo de una empresa de comercio electrónico necesita un servicio de gestión de inventario que utilice las capacidades del marco de trabajo del lenguaje principal. El servicio debe permitir la gestión de productos, incluyendo la creación, lectura, actualización y eliminación de registros. Debe controlar las variables de entorno, generar registros de rastros y conectarse con una base de datos utilizando un Mapeador Relacional de Objetos (ORM). Los actores involucrados son el Gerente de Inventario, el Sistema de Ventas y la Base de Datos de Productos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno y Creación del Servicio

**Objetivo:** Configurar el entorno de desarrollo y crear el servicio básico de gestión de inventario.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el entorno de desarrollo para utilizar el marco de trabajo del lenguaje principal.
- Crear un servicio que exponga endpoints para la gestión de productos (crear, leer, actualizar, eliminar).

**Entregable:** Servicio básico de gestión de inventario con endpoints funcionales.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de variables de entorno.
- Uso de anotaciones para enrutamiento de solicitudes.

</details>

### Fase 2: Integración con Base de Datos y Manejo de Errores

**Objetivo:** Integrar el servicio con una base de datos y manejar errores de forma adecuada.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Conectar el servicio con una base de datos utilizando un ORM.
- Implementar manejo de errores para los endpoints del servicio.

**Entregable:** Servicio de gestión de inventario integrado con base de datos y con manejo de errores.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de conexión a la base de datos.
- Uso de transacciones y manejo de excepciones.

</details>

### Fase 3: Generación de Registros de Rastros y Optimización

**Objetivo:** Implementar la generación de registros de rastros y optimizar el servicio.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar la generación de registros de rastros para el servicio.
- Optimizar el servicio para mejorar el rendimiento y la escalabilidad.

**Entregable:** Servicio de gestión de inventario con generación de registros de rastros y optimizado.

<details>
<summary>Pistas de conocimiento</summary>

- Uso de librerías de logging.
- Técnicas de optimización de rendimiento.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un marco de trabajo y cómo se utiliza en el desarrollo de aplicaciones?
- **paraQueSirve**: ¿Para qué sirve la generación de registros de rastros en un servicio?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para conectar un servicio con una base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al integrar un servicio con una base de datos y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización de un servicio para mejorar el rendimiento y la escalabilidad?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo.
- Creación de un servicio básico de gestión de inventario con endpoints funcionales.
- Integración exitosa del servicio con una base de datos utilizando un ORM.
- Implementación adecuada del manejo de errores en el servicio.
- Generación de registros de rastros funcional en el servicio.
- Optimización del servicio para mejorar el rendimiento y la escalabilidad.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
