package com.wparja.buildingandroid.Rendering.ThreeD;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class GLDrawer extends GLSurfaceView {

    private GLRenderer glRenderer;
    public GLDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        glRenderer = new GLRenderer();
        setRenderer(glRenderer);
    }
}
