# Clase Cube - Renderizado de Cubo 3D con OpenGL ES 2.0

Esta clase Java implementa un cubo tridimensional utilizando OpenGL ES 2.0 para su visualización en dispositivos Android. La clase se encarga de definir la geometría, aplicar shaders y dibujar el cubo con diferentes colores en cada cara.

---

## Estructura

[- Ver el issue #1](https://github.com/ndaguas/Cube/issues/1)

La clase `Cube` encapsula toda la lógica necesaria para:
- Definir los vértices y el orden de dibujo.
- Aplicar una matriz MVP (Model-View-Projection).
- Usar shaders personalizados para el renderizado.
- Dibujar cada una de las 6 caras del cubo con un color distinto.

---

##  Cómo se usa?

1. **Inicialización:**
   ```java
   float[] coords = {
       -1,  1,  1,   // 0
        1,  1,  1,   // 1
        1, -1,  1,   // 2
       -1, -1,  1,   // 3
       -1,  1, -1,   // 4
        1,  1, -1,   // 5
        1, -1, -1,   // 6
       -1, -1, -1    // 7
   };
   Cube cube = new Cube(coords);
   ```

2. **Dibujo en `onDrawFrame`:**
   ```java
   cube.draw(mvpMatrix);
   ```

---

## Componentes principales

| Elemento                  | Descripción |
|---------------------------|-------------|
| `vertexBuffer`            | Buffer que contiene las coordenadas del cubo. |
| `shortBuffer`             | Buffer con el orden de dibujo para formar las caras. |
| `faceColors`              | Arreglo de colores RGBA para cada cara del cubo. |
| `drawOrder`               | Orden de los índices para dibujar los triángulos. |
| `mProgram`                | Programa de shaders creado y enlazado. |
| `draw()`                  | Método que dibuja el cubo aplicando el shader y la matriz MVP. |
| `vertexShadderCode`       | Código fuente del vertex shader. |
| `fragmentShadderCode`     | Código fuente del fragment shader. |

---

## Colores por cara

1. Rojo
2. Verde
3. Azul
4. Amarillo
5. Cian
6. Magenta

---

## Matriz MVP

El método `draw(float[] mvpMatrix)` aplica la **matriz MVP** (`uMVPMatrix`) que transforma las coordenadas del cubo desde el espacio de modelo hasta el espacio de pantalla.

---

##  Requisitos

- Android Studio
- Dispositivo o emulador con soporte para OpenGL ES 2.0
- Clase auxiliar `MyGLRenderer` con método `loadShader(int, String)`

---

## Autores

- Nelson David Aguas
- Francisco Sebástian Salazar
