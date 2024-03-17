package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;

public class QuadVoidMethod<A, B, C, D> extends MethodBase {
    protected QuadVoidMethod(MethodHandle handle, String name) {
        super(handle, name);
    }
    public void execute(A arg1, B arg2, C arg3, D arg4) {
        executeInternal(arg1, arg2, arg3, arg4);
    }
}
