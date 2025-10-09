# Nequi by Perea

## Descripción
Nequi by Perea es una simulación de la aplicación Nequi (Bancolombia) desarrollada en Java 17 como proyecto académico para la materia de Programación Orientada a Objetos. El objetivo es aplicar y demostrar principios de POO mediante una réplica funcional con enfoque didáctico.

## Desarrollador
- **Nombre:** Jose Angel Perea Valencia  
- **Universidad:** Universidad del Pacífico  
- **Programa:** Ingeniería de Sistemas  
- **Semestre:** Segundo  
- **Materia:** Programación Orientada a Objetos  
- **Docente:** Wilman Andres Quiñones Valencia

## Tabla de contenidos
- [Características principales](#características-principales)  
- [Tecnologías](#tecnologías)  
- [Principios de POO aplicados](#principios-de-poo-aplicados)  
- [Instalación y ejecución](#instalación-y-ejecución)  
- [Uso básico](#uso-básico)  
- [Objetivos de aprendizaje](#objetivos-de-aprendizaje)  
- [Funcionalidades](#funcionalidades-técnicas)  

## Características principales
<details>
<summary>Operaciones bancarias básicas</summary>

- Consultar saldo  
- Recargar dinero  
- Enviar dinero a otros usuarios  
- Retirar dinero con código de seguridad  
- Pagar servicios públicos (energía, agua, gas, internet, teléfono)
</details>

<details>
<summary>Nequi Coach (asesor financiero)</summary>

- Análisis de gastos por categorías  
- Alertas sobre el estado de la cuenta  
- Recomendaciones de ahorro personalizadas  
- Desglose porcentual de gastos mensuales
</details>

<details>
<summary>Modo Retos (gamificación)</summary>

- Sistema de puntos por completar retos financieros  
- Retos: Primera recarga, Ahorrador, Pagador responsable, Generoso  
- Canje de puntos por bonificaciones  
- Seguimiento de metas
</details>

<details>
<summary>Generación de comprobantes</summary>

- Facturas PDF automáticas por transacción  
- Información detallada en cada comprobante
</details>

## Tecnologías
- Java 17  
- iText PDF (generación de facturas)  
- Java Collections Framework  
- Java Time API  
- Patrones: Estrategia, Composición, Herencia

## Principios de POO aplicados
- **Encapsulamiento:** atributos privados y métodos de acceso.  
- **Herencia:** `NequiOperation` como clase padre para operaciones concretas.  
- **Polimorfismo:** ejecución dinámica de operaciones según tipo.  
- **Abstracción:** clases abstractas que definen contratos y comportamiento base.  
- **Composición:** `NequiAccount` contiene a `User`, `NequiCard`, `HistoryItem`, etc.

## Instalación y ejecución

### Requisitos
- Java JDK 17 o superior  
- Carpeta `lib/` con dependencias (iText incluido)

## Uso básico
1. Ejecutar la aplicación desde consola.  
2. Registrar usuario: seleccionar idioma, completar datos y crear código de acceso (4 dígitos).  
3. Navegar por el menú principal y seleccionar la operación deseada.  
4. Las transacciones generan comprobantes PDF en la carpeta `invoices/`.

## Objetivos de aprendizaje
- Aplicar diseño orientado a objetos en un dominio realista.  
- Implementar patrones de diseño y manejo de colecciones.  
- Generar documentos PDF y validar operaciones financieras.  
- Producir código modular y mantenible.

## Funcionalidades
- Validación de código de acceso y verificación de saldo.  
- Mascarado de información sensible en salida.  
- Soporte para español e inglés por mensajes e interfaz de la consola  
- Historial de transacciones en memoria y categorización automática de gastos.

**Autor:** Jose Angel Perea Valencia  
**Universidad del Pacífico — 2024**  
**Materia:** Programación Orientada a Objetos
