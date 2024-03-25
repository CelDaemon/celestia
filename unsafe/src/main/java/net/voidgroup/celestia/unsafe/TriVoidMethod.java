package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;

public class TriVoidMethod<A, B, C> extends MethodBase {

    protected TriVoidMethod(MethodHandle handle, String name) {
        super(handle, name);
    }
    public void execute(A arg1, B arg2, C arg3) {
        executeInternal(arg1, arg2, arg3);
    }
}
