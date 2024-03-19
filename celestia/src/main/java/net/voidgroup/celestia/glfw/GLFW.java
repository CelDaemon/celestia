package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.error.Error;
import net.voidgroup.celestia.glfw.error.ErrorCode;
import net.voidgroup.celestia.unsafe.GLFWLibrary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@NotNull
public class GLFW implements AutoCloseable {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private static final Set<NativeClosable> closableSet = HashSet.newHashSet(4);
    @Nullable
    private static Error error;
    @Nullable
    private static GLFW instance;
    private boolean closed;
    public GLFW() {
        if(isInitialised()) throw new IllegalStateException("GLFW already initialised");
        instance = this;
        GLFWLibrary.setErrorHandler((integer, s) -> error = new Error(ErrorCode.valueOf(integer), s));
        if(!GLFWLibrary.glfwInit.execute()) throw new RuntimeException();
    }

    public static boolean isInitialised() {
        return instance != null && !instance.closed;
    }

    @NotNull
    public static GLFW getInstance() {
        if(instance == null || instance.closed) throw new IllegalStateException("GLFW is not initialised");
        return instance;
    }

    @Nullable
    public static Error getError() {
        return error;
    }

    protected void register(NativeClosable closable) {
        GLFW.closableSet.add(closable);
    }

    @Override
    public void close() {
        if(closed) return;
        GLFWLibrary.glfwTerminate.execute();
        closed = true;
        for(var closable : closableSet) {
            try {
                closable.close(false);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Failed to close", ex);
            }
        }
    }
}