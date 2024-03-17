package net.voidgroup.celestia.unsafe;

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
    public <R, A, B, C, D, E> PentaMethod<R, A, B, C, D, E> getMethod(String name, MemoryLayout returnType, MemoryLayout arg1Type,
                                                                      MemoryLayout arg2Type,
                                                                      MemoryLayout arg3Type,
                                                                      MemoryLayout arg4Type,
                                                                      MemoryLayout arg5Type) {
        return new PentaMethod<>(getMethodHandle(name, FunctionDescriptor.of(returnType, arg1Type, arg2Type, arg3Type, arg4Type, arg5Type)), name);
    }
    public VoidMethod getVoidMethod(String name) {
        return new VoidMethod(getMethodHandle(name, FunctionDescriptor.ofVoid()), name);
    }
    public <A> UnoVoidMethod<A> getVoidMethod(String name, MemoryLayout argType) {
        return new UnoVoidMethod<>(getMethodHandle(name, FunctionDescriptor.ofVoid(argType)), name);
    }
    public <A, B, C, D> QuadVoidMethod<A, B, C, D> getVoidMethod(String name, MemoryLayout arg1Type, MemoryLayout arg2Type, MemoryLayout arg3Type, MemoryLayout arg4Type) {
        return new QuadVoidMethod<>(getMethodHandle(name, FunctionDescriptor.ofVoid(arg1Type, arg2Type, arg3Type, arg4Type)), name);
    }
}
