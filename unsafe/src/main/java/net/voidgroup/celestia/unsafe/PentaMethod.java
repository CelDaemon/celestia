package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;

public class PentaMethod<R, A, B, C, D, E> extends MethodBase {
    protected PentaMethod(MethodHandle handle, String name) {
        super(handle, name);
    }

    @SuppressWarnings("unchecked")
    public R execute(A arg1, B arg2, C arg3, D arg4, E arg5) {
        return (R) executeInternal(arg1, arg2, arg3, arg4, arg5);
    }
}
