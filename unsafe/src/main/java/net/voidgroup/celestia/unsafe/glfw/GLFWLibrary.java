package net.voidgroup.celestia.unsafe.glfw;

import net.voidgroup.celestia.unsafe.*;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GLFWLibrary {
    static {
        String name;
        var osName = System.getProperty("os.name");
        if(osName.startsWith("Windows")) {
            name = "glfw.dll";
        } else if (osName.startsWith("Linux") || osName.startsWith("FreeBSD")) {
            name = "libglfw.so";
        } else {
            throw new RuntimeException();
        }
        PROVIDER = new SharedLibraryProvider(name);
        try {
            errorHandlerAddress = SharedLibraryProvider.linker.upcallStub(MethodHandles.lookup().findStatic(GLFWLibrary.class, "handleError", MethodType.methodType(void.class, int.class, MemorySegment.class)), FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS), Arena.global());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private static BiConsumer<Integer, String> errorHandler;
    private static void handleError(int errorCode, MemorySegment memorySegment) {
        errorHandler.accept(errorCode, memorySegment.reinterpret(StandardLibrary.strlen.execute(memorySegment) + 1).getUtf8String(0));
    }
    public static BiConsumer<Integer, String> setErrorHandler(BiConsumer<Integer, String> handler) {
        var oldErrorHandler = errorHandler;
        errorHandler = handler;
        glfwSetErrorCallback.execute(errorHandlerAddress);
        return oldErrorHandler;
    }
    public static final SharedLibraryProvider PROVIDER;
    private static final MemorySegment errorHandlerAddress;
    public static final Method<Boolean> glfwInit = PROVIDER.getMethod("glfwInit", ValueLayout.JAVA_BOOLEAN);
    public static final VoidMethod glfwTerminate = PROVIDER.getVoidMethod("glfwTerminate");
    public static final PentaMethod<Long, Integer, Integer, MemorySegment, MemorySegment, MemorySegment> glfwCreateWindow = PROVIDER.getMethod("glfwCreateWindow", ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    public static final UnoVoidMethod<Long> glfwDestroyWindow = PROVIDER.getVoidMethod("glfwDestroyWindow", ValueLayout.JAVA_LONG);
    private static final UnoVoidMethod<MemorySegment> glfwSetErrorCallback = PROVIDER.getVoidMethod("glfwSetErrorCallback", ValueLayout.ADDRESS);
}
