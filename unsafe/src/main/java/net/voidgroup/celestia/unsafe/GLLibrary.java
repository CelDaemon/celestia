package net.voidgroup.celestia.unsafe;

import java.lang.foreign.ValueLayout;

public class GLLibrary {
    public static final GLLibraryProvider PROVIDER = new GLLibraryProvider();
    public static final int GL_COLOR_BUFFER_BIT = 0x4000;

    public final UnoVoidMethod<Integer> glClear = PROVIDER.getVoidMethod("glClear", ValueLayout.JAVA_INT);
    public final QuadVoidMethod<Float, Float, Float, Float> glClearColor = PROVIDER.getVoidMethod("glClearColor", ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT);
}
