package net.voidgroup.celestia.unsafe;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class GLLibraryProvider extends MethodProvider {

    @Override
    protected MemorySegment resolveMethodAddress(String name) {
        try(var arena = Arena.ofConfined()) {
            return GLFWLibrary.glfwGetProcAddress.execute(arena.allocateUtf8String(name));
        }
    }
}
