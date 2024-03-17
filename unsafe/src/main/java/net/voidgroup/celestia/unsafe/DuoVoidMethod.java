package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;

public class DuoVoidMethod<A, B> extends MethodBase {

    protected DuoVoidMethod(MethodHandle handle, String name) {
        super(handle, name);
    }

    public void execute(A arg1, B arg2) {
        executeInternal(arg1, arg2);
    }
}
