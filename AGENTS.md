# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Servicio de Gestión de Inventario**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5.6 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.postgresql:postgresql 42.7.3
- org.projectlombok:lombok 1.18.32
- org.springframework.boot:spring-boot-starter-test n/a
- org.springframework.boot:spring-boot-configuration-processor n/a
- ch.qos.logback:logback-classic 1.5.6
- jakarta.persistence:jakarta.persistence-api 3.1.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del Entorno y Creación del Servicio**: Servicio básico de gestión de inventario con endpoints funcionales.
- **Fase 2 — Integración con Base de Datos y Manejo de Errores**: Servicio de gestión de inventario integrado con base de datos y con manejo de errores.
- **Fase 3 — Generación de Registros de Rastros y Optimización**: Servicio de gestión de inventario con generación de registros de rastros y optimizado.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (15)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/inventoryservice/InventoryServiceApplication.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/inventoryservice/application/usecase/ProductUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/inventoryservice/infrastructure/config/DatabaseConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/inventoryservice/infrastructure/controller/ProductController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/inventoryservice/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/inventoryservice/infrastructure/config/LoggingConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/inventoryservice/infrastructure/controller/ProductControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.save`
      Se invoca `save` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.findById`
      Se invoca `findById` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.existsById`
      Se invoca `existsById` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.deleteById`
      Se invoca `deleteById` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java` — `ProductJpaAdapter.findAll`
      Se invoca `findAll` sobre `ProductJpaAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/inventoryservice/application/usecase/ProductUseCase.java` — `Product.isEmpty`
      Se invoca `isEmpty` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/inventoryservice/application/usecase/ProductUseCaseTest.java` — `Product.size`
      Se invoca `size` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/inventoryservice/application/usecase/ProductUseCaseTest.java` — `Product.isEmpty`
      Se invoca `isEmpty` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (18)

- `pom.xml`
- `src/main/java/com/inventoryservice/InventoryServiceApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/inventoryservice/domain/model/Product.java`
- `src/main/java/com/inventoryservice/domain/port/ProductRepository.java`
- `src/main/java/com/inventoryservice/domain/port/ProductService.java`
- `src/main/java/com/inventoryservice/infrastructure/adapter/ProductJpaRepository.java`
- `src/main/java/com/inventoryservice/infrastructure/repository/ProductJpaAdapter.java`
- `src/main/java/com/inventoryservice/infrastructure/controller/dto/ProductRequest.java`
- `src/main/java/com/inventoryservice/infrastructure/controller/dto/ProductResponse.java`
- `src/main/java/com/inventoryservice/application/usecase/ProductUseCase.java`
- `src/main/java/com/inventoryservice/infrastructure/config/DatabaseConfig.java`
- `src/main/java/com/inventoryservice/infrastructure/controller/ProductController.java`
- `src/main/java/com/inventoryservice/infrastructure/repository/ProductEntity.java`
- `src/main/java/com/inventoryservice/infrastructure/config/GlobalExceptionHandler.java`
- `src/main/java/com/inventoryservice/infrastructure/config/LoggingConfig.java`
- `src/test/java/com/inventoryservice/application/usecase/ProductUseCaseTest.java`
- `src/test/java/com/inventoryservice/infrastructure/controller/ProductControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/inventoryservice`
- `src/main/java/com/inventoryservice/domain`
- `src/main/java/com/inventoryservice/domain/model`
- `src/main/java/com/inventoryservice/domain/port`
- `src/main/java/com/inventoryservice/application`
- `src/main/java/com/inventoryservice/application/usecase`
- `src/main/java/com/inventoryservice/infrastructure`
- `src/main/java/com/inventoryservice/infrastructure/adapter`
- `src/main/java/com/inventoryservice/infrastructure/config`
- `src/main/java/com/inventoryservice/infrastructure/controller`
- `src/main/java/com/inventoryservice/infrastructure/repository`
- `src/main/resources`
- `src/test/java/com/inventoryservice`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced L2
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).
- Mision: Exponer un servicio de gestión de inventario que use las capacidades del marco de trabajo.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
