package net.voidgroup.celestia.glfw.internal;

import net.voidgroup.celestia.glfw.Error;
import net.voidgroup.celestia.glfw.Version;
import net.voidgroup.celestia.internal.SharedLibraryLoader;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
@SuppressWarnings("UnusedReturnValue")
public class GLFWMethods {
    private static final Linker linker = Linker.nativeLinker();
    private static final SharedLibraryLoader loader;

    static {
        String name;
        var osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) name = "glfw.dll";
        else if (osName.startsWith("Linux") || osName.startsWith("FreeBSD")) //noinspection SpellCheckingInspection
            name = "libglfw.so";
        else throw new RuntimeException("Unsupported glfw platform");
        loader = new SharedLibraryLoader(name);
    }

    private static final MethodHandle _glfwInit = linker.downcallHandle(loader.getMethod("glfwInit"), FunctionDescriptor.of(ValueLayout.JAVA_INT));

    public static boolean glfwInit() {
        try {
            return ((int) _glfwInit.invoke()) == 1;
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    private static final MethodHandle _glfwGetError = linker.downcallHandle(loader.getMethod("glfwGetError"), FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));

    public static Error glfwGetError() {
        try {
            var descriptionReferenceMemory = Arena.ofAuto().allocate(ValueLayout.ADDRESS);
            var code = Error.ErrorCode.fromId((int) _glfwGetError.invoke(descriptionReferenceMemory));
            var descriptionValueMemory = descriptionReferenceMemory.get(ValueLayout.ADDRESS, 0);
            return new Error(code, !descriptionValueMemory.equals(MemorySegment.NULL) ? descriptionValueMemory.reinterpret(512).getUtf8String(0) : "No Error");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    // TODO monitor functionality and performance, not yet tested
    private static final MethodHandle _glfwSetErrorCallback = linker.downcallHandle(loader.getMethod("glfwSetErrorCallback"), FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    private static void handleError(Throwable throwable, String message) {
        Logger.getGlobal().log(Level.SEVERE, message, throwable);
    }

    private static Consumer<Error> currentErrorCallback;
    private static Arena currentErrorCallbackArena;

    public static Consumer<Error> glfwSetErrorCallback(Consumer<Error> callback) {
        if (currentErrorCallbackArena != null) currentErrorCallbackArena.close();
        try {
            currentErrorCallbackArena = Arena.ofConfined();
            _glfwSetErrorCallback.invoke(
                    linker.upcallStub(
                            MethodHandles.catchException(
                                    MethodHandles.collectArguments(
                                            MethodHandles.lookup().findVirtual(Consumer.class, "accept", MethodType.methodType(void.class, Object.class))
                                                    .bindTo(callback)
                                                    .asType(MethodType.methodType(void.class, Error.class)),
                                            0,
                                            MethodHandles.lookup().findStatic(Error.class, "fromNative", MethodType.methodType(Error.class, int.class, MemorySegment.class))
                                    ),
                                    Throwable.class,
                                    MethodHandles.insertArguments(MethodHandles.lookup().findStatic(GLFWMethods.class, "handleError", MethodType.methodType(void.class, Throwable.class, String.class)), 1, "Failed to run error callback")
                            ), FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS), currentErrorCallbackArena)
            );
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        var oldCallback = currentErrorCallback;
        currentErrorCallback = callback;
        return oldCallback;
    }

    private static final MethodHandle _glfwTerminate = linker.downcallHandle(loader.getMethod("glfwTerminate"), FunctionDescriptor.ofVoid());

    public static void glfwTerminate() {
        try {
            _glfwTerminate.invoke();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static final MethodHandle _glfwGetVersion = linker.downcallHandle(loader.getMethod("glfwGetVersion"), FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    public static Version glfwGetVersion() {
        var arena = Arena.ofAuto();
        try {
            var majorMemory = arena.allocate(ValueLayout.JAVA_INT);
            var minorMemory = arena.allocate(ValueLayout.JAVA_INT);
            var revisionMemory = arena.allocate(ValueLayout.JAVA_INT);
            _glfwGetVersion.invoke(majorMemory, minorMemory, revisionMemory);
            return new Version(majorMemory.get(ValueLayout.JAVA_INT, 0), minorMemory.get(ValueLayout.JAVA_INT, 0), revisionMemory.get(ValueLayout.JAVA_INT, 0));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static final MethodHandle _glfwGetVersionString = linker.downcallHandle(loader.getMethod("glfwGetVersionString"), FunctionDescriptor.of(ValueLayout.ADDRESS));

    public static String glfwGetVersionString() {
        try {
            return ((MemorySegment) _glfwGetVersionString.invoke()).reinterpret(512).getUtf8String(0);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
