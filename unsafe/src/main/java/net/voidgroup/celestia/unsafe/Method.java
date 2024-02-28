package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;
public class Method<R> extends MethodBase {
    protected Method(MethodHandle handle, String name) {
        super(handle, name);
    }

    @SuppressWarnings("unchecked")
    public R execute() {
        return (R) executeInternal();
    }
}
