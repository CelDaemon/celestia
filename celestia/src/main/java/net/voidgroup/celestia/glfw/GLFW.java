package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.error.Error;
import net.voidgroup.celestia.glfw.error.ErrorCode;
import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.unsafe.UnsafeUtil;

import java.util.HashSet;
import java.util.Set;

public class GLFW implements AutoCloseable {
    private static final Set<InternalClosable> closables = HashSet.newHashSet(4);
    public static Error error;
    public static boolean initialised;
    public static Error getError() {
        return error;
    }
    public GLFW() {
        if(initialised) throw new IllegalStateException("Initialised");
        initialised = true;
        GLFWLibrary.setErrorHandler((code, message) -> error = new Error(ErrorCode.valueOf(code), message));
        GLFWLibrary.glfwInit.execute();
    }
    public static void register(InternalClosable closable) {
        closables.add(closable);
    }
    public static void unregister(InternalClosable closable) {
        closables.remove(closable);
    }
    public String getVersionString() {
        return UnsafeUtil.readString(GLFWLibrary.glfwGetVersionString.execute());
    }

    @Override
    public void close() {
        GLFWLibrary.glfwTerminate.execute();
        closables.forEach(closable -> closable.close(false));
        closables.clear();
    }
}
