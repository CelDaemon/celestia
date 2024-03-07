package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;

public class UnoVoidMethod<A> extends MethodBase {
    protected UnoVoidMethod(MethodHandle handle, String name) {
        super(handle, name);
    }
    public void execute(A arg) {
        executeInternal(arg);
    }
}
