# Clase Cube - Renderizado de Cubo 3D con OpenGL ES 2.0

Esta clase Java implementa un cubo tridimensional utilizando OpenGL ES 2.0 para su visualización en dispositivos Android. La clase se encarga de definir la geometría, aplicar shaders y dibujar el cubo con diferentes colores en cada cara.

---

## Estructura

![Image](https://github.com/user-attachments/assets/7a93af7f-9ebd-4504-96bb-aa386bb1ab13)

La clase `Cube` encapsula toda la lógica necesaria para:
- Definir los vértices y el orden de dibujo.
- Aplicar una matriz MVP (Model-View-Projection).
- Usar shaders personalizados para el renderizado.
- Dibujar cada una de las 6 caras del cubo con un color distinto.

Cabe recalcar el orden en el que se dibujaran los triangulos que conforman el cubo
 ```java
private final short[] drawOrder = {
            // cara derecha
            0, 1, 2, 0, 2, 3,  //Triangulos ABC Y ACD
            // cara izquierda
            4, 5, 6, 4, 6, 7,  //Triangulos EFG Y EGH
            // cara inferior
            0, 1, 5, 0, 5, 4,  //Triangulos ABF y AEF
            // cara superior
            2, 3, 7, 2, 7, 6,  //Triangulo BCG Y BGF
            // cara trasera
            0, 3, 7, 0, 7, 4,  //Triangulos ADH y AHE
            // cara frontal
            1, 2, 6, 1, 6, 5   //Triangulos BCG Y AHE
    };  
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
## Renderer
Definiremos el renderer con cuatro variables de tipo float[] las cuales dinirian nuestras matrices modelo,vista y proyeccion.
 ```java
private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];
    private final float[] mMVPMatrix = new float[16];   
```
##  Cómo se usa?

1. **Inicialización:**
   ```java
   float[] coords = {
       -1, -1, -1,   // 0 (A)
        1, -1, -1,   // 1 (B)
        1,  1, -1,   // 2 (C)
       -1,  1, -1,   // 3 (D)
       -1, -1,  1,   // 4 (E)
        1, -1,  1,   // 5 (F)
        1,  1,  1,   // 6 (G)
       -1,  1,  1    // 7 (H)
   };
   Cube cube = new Cube(coords);
   ```

2. **Dibujo en `onDrawFrame`:**
   El .draw() ocupara la matriz mMVPMatrix como parametro para ejecutar el onDrawFrame(), ademas definiremos la configuracion de la cámara a criterio personal.
   ```java
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // Configurar la cámara
        Matrix.setLookAtM(mViewMatrix, 0,
                -2.0f, -2.0f, -2.0f, // Posición de la cámara
                0.0f, 0.0f, 0.0f, // Centro de la escena
                0.0f, 1.0f, 0.0f); // Vector "arriba"


        // Combinar matrices: MVP = Proyección * Vista * Modelo
        Matrix.multiplyMM(mMVPMatrix,0,mProjectionMatrix,0,mViewMatrix,0);

        // Dibujar el cubo con la matriz transformada
        cube.draw(mMVPMatrix);
    }
   ```

---



---


##  Requisitos

- Android Studio
- Dispositivo o emulador con soporte para OpenGL ES 2.0
- Clase auxiliar `MyGLRenderer` con método `loadShader(int, String)`

---

## Autores

- Nelson David Aguas
- Francisco Sebastián Salazar
