package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.data.Version;
import net.voidgroup.celestia.glfw.error.Error;
import net.voidgroup.celestia.glfw.error.ErrorCode;
import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.unsafe.UnsafeUtil;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.HashSet;
import java.util.Set;

public class GLFW implements AutoCloseable {
    private static final Set<InternalClosable> closables = HashSet.newHashSet(4);
    public static boolean initialised;
    public GLFW() {
        if(initialised) throw new IllegalStateException("GLFW is already initialised");
        if(GLFW.getVersion().major() != 3) throw new IllegalStateException("Unsupported GLFW version in native library");
        GLFWLibrary.glfwInit.execute();
        initialised = true;
    }
    public static void register(InternalClosable closable) {
        closables.add(closable);
    }
    public static void unregister(InternalClosable closable) {
        closables.remove(closable);
    }
    public static Error getError() {
        try(var arena = Arena.ofConfined()) {
            var descriptionAddressMemory = arena.allocate(ValueLayout.ADDRESS, MemorySegment.NULL);
            var code = ErrorCode.valueOf(GLFWLibrary.glfwGetError.execute(descriptionAddressMemory));
            return new Error(code, code != ErrorCode.NoError ? UnsafeUtil.readString(descriptionAddressMemory.get(ValueLayout.ADDRESS, 0)) : "No error occurred");
        }
    }
    public static Version getVersion() {
        try(var arena = Arena.ofConfined()) {
            var majorMemory = arena.allocate(ValueLayout.JAVA_INT, 0);
            var minorMemory = arena.allocate(ValueLayout.JAVA_INT, 0);
            var revisionMemory = arena.allocate(ValueLayout.JAVA_INT, 0);
            GLFWLibrary.glfwGetVersion.execute(majorMemory, minorMemory, revisionMemory);
            return new Version(majorMemory.get(ValueLayout.JAVA_INT, 0), minorMemory.get(ValueLayout.JAVA_INT, 0), revisionMemory.get(ValueLayout.JAVA_INT, 0));
        }
    }
    public String getVersionString() {
        return UnsafeUtil.readString(GLFWLibrary.glfwGetVersionString.execute());
    }
    public void pollEvents() {
        if(!initialised) throw new IllegalStateException("GLFW is not initialised");
        GLFWLibrary.glfwPollEvents.execute();
    }
    @Override
    public void close() {
        GLFWLibrary.glfwTerminate.execute();
        closables.forEach(closable -> closable.close(false));
        closables.clear();
        initialised = false;
    }
}
