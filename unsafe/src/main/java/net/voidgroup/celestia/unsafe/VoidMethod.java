package net.voidgroup.celestia.unsafe;

import java.lang.invoke.MethodHandle;
public class VoidMethod extends MethodBase {

    protected VoidMethod(MethodHandle handle, String name) {
        super(handle, name);
    }
    public void execute() {
        executeInternal();
    }
}
