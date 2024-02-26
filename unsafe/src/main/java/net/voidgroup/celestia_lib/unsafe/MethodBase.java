package net.voidgroup.celestia_lib.unsafe;

import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MethodHandle;

import static java.lang.StringTemplate.STR;
@NotNull
public class MethodBase {
    private final MethodHandle handle;
    private final String name;
    protected MethodBase(MethodHandle handle, String name) {
        this.handle = handle;
        this.name = name;
    }

    protected Object executeInternal(Object... args) {
        try {
            return handle.invokeWithArguments(args);
        } catch (Throwable throwable) {
            throw new RuntimeException(STR."Failed to call unsafe method: \{name}", throwable);
        }
    }
}
