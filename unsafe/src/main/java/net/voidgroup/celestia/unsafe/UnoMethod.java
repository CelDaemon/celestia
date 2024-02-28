package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;
public class UnoMethod<R, A> extends MethodBase {

    protected UnoMethod(MethodHandle handle, String name) {
        super(handle, name);
    }

    @SuppressWarnings("unchecked")
    public R execute(A arg) {
        return (R) executeInternal(arg);
    }
}
