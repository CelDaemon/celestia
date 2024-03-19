package net.voidgroup.celestia.glfw;

public interface NativeClosable extends AutoCloseable {
    void close(boolean nativeClose);

    @Override
    default void close() {
        close(true);
    }
}
