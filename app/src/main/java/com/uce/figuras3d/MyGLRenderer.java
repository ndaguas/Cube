package com.uce.figuras3d;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];
    private final float[] mMVPMatrix = new float[16];

    Cube cube;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);//profundidad
        float[] coords = {
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f,  0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f,
                -0.5f, -0.5f,  0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                -0.5f,  0.5f,  0.5f
        };


        cube = new Cube(coords);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);
        float ratio = (float) width/height;//aspect ratio
        Matrix.frustumM(mProjectionMatrix,0,-ratio,ratio,-1,1,1,10);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // Configurar la cámara
        Matrix.setLookAtM(mViewMatrix, 0,
                3.0f, 0.0f, 0.0f, // Posición de la cámara
                0.0f, 0.0f, 0.0f, // Centro de la escena
                0.0f, 1.0f, 0.0f); // Vector "arriba"

        // Rotar el cubo para verlo de lado
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.rotateM(mModelMatrix, 0, 90, 0, 1, 0);

        // Combinar matrices: MVP = Proyección * Vista * Modelo
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        // Dibujar el cubo con la matriz transformada
        cube.draw(mMVPMatrix);

    }
    public static int loadShader(int type,String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
