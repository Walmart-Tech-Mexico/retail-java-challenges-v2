# Semana 01 - Carrito de Compras con Descuentos

### 🛒 Contexto

Un cliente agrega productos a su carrito. Algunos productos tienen descuentos aplicables si compra más de cierta cantidad.

### 🎯 Objetivo

Implementa la lógica que:

- Calcule el precio total del carrito
- Aplique descuentos por volumen si corresponde

### 🧪 Casos esperados (tests en CartTest.java)

- 3 productos con descuento aplicado
- Productos sin descuento
- Total correcto

### 🚧 Clases clave

- `Cart.java`
- `Product.java`
- `DiscountService.java`

¡Modifica solo donde se indique y sube tu solución!

🧠 *Nivel: Principiante-Intermedio*
---

## 📝 Implementaciones
Para cumplir con los objetivos del reto y demostrar habilidades tecnicas, se realizaron las siguientes mejoras al código original, manteniendo la funcionalidad requerida y mejorando la calidad, extensibilidad y mantenibilidad del sistema:

### 1. **Precisión en Cálculos Financieros**
- **Problema**: El uso de `double` en `Product.getTotalPrice` y `DiscountService.applyDiscount` podía causar errores de redondeo en cálculos financieros.
- **Solución**:
    - Se reemplazó  `double` por `BigDecimal` en `Product.getTotalPrice` y `DiscountService.applyDiscount` para garantizar precisión.
    - Se aplicó redondeo a 2 decimales (`RoundingMode.HALF_UP`) para alinear con estándares financieros.
- **Impacto**: Asegura que los tests de "total correcto" pasen con valores exactos (por ejemplo, 2950.00 en lugar de 2949.999...).

### 2. **Validaciones**
- **Problema**: El constructor de `Product` no validaba entradas, permitiendo valores inválidos como precios o cantidades negativas.
- **Solución**:
    - Se creó la clase `ProductValidator` en el paquete `com.walmarttech.cart.validators` para centralizar las validaciones.
    - Se validan:
        - `price >= 0` y `quantity >= 0`.
        - `name` no nulo ni vacío.
    - Los errores se acumulan en una lista y se reportan en una sola excepción para mejor retroalimentación.
- **Impacto**: Mejora la robustez del sistema y evita errores en los cálculos.

### 3. **Centralización de Constantes**
- **Problema**: Valores fijos como el factor de descuento (0.9), la cantidad mínima (3) y la escala de redondeo (2) estaban hardcodeados en `DiscountService`.
- **Solución**:
    - Se creó la clase `CartConstants` en `com.walmarttech.cart.constants` para definir constantes como:
        - `DISCOUNT_FACTOR = 0.9` (10% descuento).
        - `MIN_QUANTITY_FOR_DISCOUNT = 3`.
        - `SCALE = 2`.
        - Mensajes de error para validaciones (por ejemplo, `ERROR_PRICE_NEGATIVE`).
- **Impacto**: Facilita el mantenimiento y posibles cambios (por ejemplo, ajustar el porcentaje de descuento).

### 4. **Patrón Strategy para Descuentos**
- **Problema**: La lógica de descuentos en `DiscountService` no era extensible para nuevos tipos de descuentos.
- **Solución**:
    - Se implementó el patrón **Strategy** con:
        - Interfaz `DiscountStrategy` en `com.walmarttech.cart.strategies`.
        - Clase `VolumeDiscountStrategy` que implementa el descuento por volumen (10% para `quantity >= 3` y `hasDiscount=true`).
    - `DiscountService` ahora recibe un `DiscountStrategy` en su constructor, delegando la lógica de descuento.
    - `Cart` inicializa `DiscountService` con `VolumeDiscountStrategy` por defecto.
- **Impacto**: Permite añadir nuevas estrategias de descuento (por ejemplo, descuentos fijos o por categoría) sin modificar `DiscountService`, cumpliendo con **OCP**.

### 5. **Reducción de Código Repetitivo con Lombok**
- **Problema**: Los métodos getter en `Product` eran repetitivos.
- **Solución**:
    - Se usó la anotación `@Getter` de Lombok para generar automáticamente los getters de `name`, `price`, `quantity`, y `hasDiscount`.
    - Se mantuvo `getTotalPrice` explícito debido a su lógica personalizada.
- **Impacto**: Reduce el boilerplate, mejora la legibilidad y mantiene la funcionalidad.

### 6. **Estructura Modular del Proyecto**
- **Problema**: La estructura original no estaba preparada para clases de soporte.
- **Solución**:
    - Se añadieron paquetes:
        - `com.walmarttech.cart.constants` para `CartConstants`.
        - `com.walmarttech.cart.validators` para `ProductValidator`.
        - `com.walmarttech.cart.strategies` para `DiscountStrategy` y `VolumeDiscountStrategy`.
- **Impacto**: Mejora la organización, escalabilidad y navegación del código.

### 7. **Ajustes en los Tests**
- **Problema**: La introducción del patrón Strategy rompió los tests en `CartTest.java` debido a cambios en el constructor de `DiscountService`.
- **Solución**:
    - Se actualizó `CartTest` para instanciar `DiscountService` con `VolumeDiscountStrategy`.
    - Los tests verifican los casos esperados con precisión (por ejemplo, total de 2950.00 para un carrito con 3 productos).
- **Impacto**: Asegura que los tests pasen sin alterar la funcionalidad.

## 🛠️ Buenas Prácticas Aplicadas
- **Código Limpio**:
    - Nombres descriptivos (por ejemplo, `VolumeDiscountStrategy`, `CartConstants`).
    - Métodos cortos y enfocados en una sola responsabilidad.
    - Uso de constantes para evitar valores hardcodeados.
- **Mantenibilidad**:
    - Centralización de constantes y validaciones.
    - Estructura modular con paquetes específicos.
    - Documentación implícita a través de nombres claros y organización.
- **Robustez**:
    - Validaciones para entradas inválidas.
    - Uso de `BigDecimal` para cálculos financieros precisos.
- **Testabilidad**:
    - Los tests en `CartTest.java` cubren todos los casos esperados.
    - La inyección de dependencias facilita pruebas unitarias con diferentes estrategias de descuento.

## 🔍 Implementación de Principios SOLID
- **Single Responsibility Principle (SRP)**:
    - `Product`: Gestiona datos y cálculo del precio total.
    - `ProductValidator`: Valida entradas.
    - `DiscountService`: Delega descuentos.
    - `VolumeDiscountStrategy`: Aplica un tipo específico de descuento.
    - `Cart`: Calcula el total del carrito.
- **Open/Closed Principle (OCP)**:
    - `DiscountService` está abierto a nuevas estrategias de descuento sin modificaciones, gracias al patrón Strategy.
    - `ProductValidator` permite añadir nuevas reglas de validación.
- **Liskov Substitution Principle (LSP)**:
    - Cualquier implementación de `DiscountStrategy` (como `VolumeDiscountStrategy`) puede usarse sin afectar el comportamiento.
- **Interface Segregation Principle (ISP)**:
    - `DiscountStrategy` tiene un solo método (`applyDiscount`), evitando interfaces sobrecargadas.
- **Dependency Inversion Principle (DIP)**:
    - `Cart` y `DiscountService` dependen de la abstracción `DiscountStrategy`, no de implementaciones concretas.

## 🎨 Patrones de Diseño
- **Strategy**:
    - Implementado para la lógica de descuentos, permitiendo cambiar estrategias dinámicamente (por ejemplo, `VolumeDiscountStrategy`).
    - Mejora la extensibilidad y cumple con **OCP** y **DIP**.
- **Validator**:
    - Usado en `ProductValidator` para centralizar validaciones, separando esta responsabilidad de `Product` y cumpliendo con **SRP**.

## 📂 Estructura del Proyecto
La estructura final es modular y escalable:

```
week-01-cart-discounts
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── walmarttech
│   │               └── cart
│   │                   ├── Cart.java
│   │                   ├── Product.java
│   │                   ├── DiscountService.java
│   │                   ├── constants
│   │                   │   └── CartConstants.java
│   │                   ├── validators
│   │                   │   └── ProductValidator.java
│   │                   └── strategies
│   │                       ├── DiscountStrategy.java
│   │                       └── VolumeDiscountStrategy.java
│   └── test
│       └── java
│           └── com
│               └── walmarttech
│                   └── cart
│                       └── CartTest.java
```

## 💻 Habilidades Técnicas
- **Java**: Uso avanzado de `BigDecimal`, streams, y anotaciones de Lombok.
- **Diseño de Software**: Aplicación de principios SOLID y patrones de diseño (Strategy, Validator).
- **Pruebas Unitarias**: Ajuste de `CartTest.java` para mantener la cobertura de los casos esperados.
- **Herramientas**: Configuración de un proyecto Maven, uso de IntelliJ IDEA, y gestión de dependencias (Lombok, JUnit 5).
- **Control de Versiones**: Flujo de Git para fork, commits, y pull requests.
- **Documentación**: README detallado que explica mejoras, prácticas, y diseño.

## 🚀 Instrucciones para Ejecutar
1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/xsoto-developer/retail-java-challenges-v2.git
   cd challenges/week-01-cart-discounts
   ```
2. **Configurar el Proyecto**:
    - Abrir en IntelliJ IDEA como proyecto Maven.
    - Asegurar que Lombok esté habilitado (`Settings > Plugins > Lombok`).
    - Activar el procesamiento de anotaciones (`Settings > Build, Execution, Deployment > Compiler > Annotation Processors`).
3. **Ejecutar Tests**:
    - En IntelliJ: Clic derecho en `CartTest.java` > `Run 'CartTest'`.
    - En terminal: `mvn clean test`.
4. **Verificar Resultados**:
    - Confirmar que los tests de `CartTest.java` pasan.
5. **Enviar Solución**:
    - Crear una rama: `git checkout -b reto-01-solution`.
    - Confirmar cambios: `git commit -m "Solución Reto 01 con mejoras"`.
    - Subir: `git push origin reto-01-solution`.
    - Crear un pull request hacia el repositorio original.
