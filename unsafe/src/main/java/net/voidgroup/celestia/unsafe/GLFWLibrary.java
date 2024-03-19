package net.voidgroup.celestia.unsafe;

import org.jetbrains.annotations.NotNull;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.BiConsumer;

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
    public static final int GLFW_VISIBLE = 0x00020004;
    public static final @NotNull SharedLibraryProvider PROVIDER;
    public static final Method<Boolean> glfwInit = PROVIDER.getMethod("glfwInit", ValueLayout.JAVA_BOOLEAN);
    public static final VoidMethod glfwTerminate = PROVIDER.getVoidMethod("glfwTerminate");
    public static final PentaMethod<Long, Integer, Integer, MemorySegment, Long, MemorySegment> glfwCreateWindow = PROVIDER.getMethod("glfwCreateWindow", ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
    public static final Method<MemorySegment> glfwGetVersionString = PROVIDER.getMethod("glfwGetVersionString", ValueLayout.ADDRESS);
    public static final UnoVoidMethod<Long> glfwDestroyWindow = PROVIDER.getVoidMethod("glfwDestroyWindow", ValueLayout.JAVA_LONG);
    public static final UnoVoidMethod<Long> glfwMakeContextCurrent = PROVIDER.getVoidMethod("glfwMakeContextCurrent", ValueLayout.JAVA_LONG);
    public static final UnoMethod<MemorySegment, MemorySegment> glfwGetProcAddress = PROVIDER.getMethod("glfwGetProcAddress", ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    public static final UnoVoidMethod<Long> glfwSwapBuffers = PROVIDER.getVoidMethod("glfwSwapBuffers", ValueLayout.JAVA_LONG);
    public static final UnoVoidMethod<Integer> glfwSwapInterval = PROVIDER.getVoidMethod("glfwSwapInterval", ValueLayout.JAVA_INT);
    public static final VoidMethod glfwPollEvents = PROVIDER.getVoidMethod("glfwPollEvents");
    public static final UnoMethod<Boolean, Long> glfwWindowShouldClose = PROVIDER.getMethod("glfwWindowShouldClose", ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_LONG);
    public static final DuoVoidMethod<Integer, Integer> glfwWindowHint = PROVIDER.getVoidMethod("glfwWindowHint", ValueLayout.JAVA_INT, ValueLayout.JAVA_INT);
    public static final DuoVoidMethod<Integer, MemorySegment> glfwWindowHintString = PROVIDER.getVoidMethod("glfwWindowHintString", ValueLayout.JAVA_INT, ValueLayout.ADDRESS);
    public static final Method<Long> glfwGetPrimaryMonitor = PROVIDER.getMethod("glfwGetPrimaryMonitor", ValueLayout.JAVA_LONG);
    private static final MemorySegment errorHandlerAddress;
    private static final UnoVoidMethod<MemorySegment> glfwSetErrorCallback = PROVIDER.getVoidMethod("glfwSetErrorCallback", ValueLayout.ADDRESS);
    private static BiConsumer<Integer, String> errorHandler;

    private static void handleError(int errorCode, @NotNull MemorySegment memorySegment) {
        errorHandler.accept(errorCode, UnsafeUtil.readString(memorySegment));
    }

    public static void setErrorHandler(BiConsumer<Integer, String> handler) {
        errorHandler = handler;
        glfwSetErrorCallback.execute(errorHandlerAddress);
    }
}
