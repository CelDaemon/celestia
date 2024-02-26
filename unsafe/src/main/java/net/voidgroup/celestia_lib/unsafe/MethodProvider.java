package net.voidgroup.celestia_lib.unsafe;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
public abstract class MethodProvider {
    public static final Linker linker = Linker.nativeLinker();
    protected abstract MemorySegment resolveMethodAddress(String name);
    private MethodHandle getMethodHandle(String name, FunctionDescriptor functionDescriptor) {
        return linker.downcallHandle(resolveMethodAddress(name), functionDescriptor);
    }
    public <R> Method<R> getMethod(String name, MemoryLayout returnType) {
        return new Method<>(getMethodHandle(name, FunctionDescriptor.of(returnType)), name);
    }
    public <R, A> UnoMethod<R, A> getMethod(String name, MemoryLayout returnType, MemoryLayout argumentType) {
        return new UnoMethod<>(getMethodHandle(name, FunctionDescriptor.of(returnType, argumentType)), name);
    }
    public VoidMethod getVoidMethod(String name) {
        return new VoidMethod(getMethodHandle(name, FunctionDescriptor.ofVoid()), name);
    }
}
