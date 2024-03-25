package net.voidgroup.celestia.unsafe;

import org.jetbrains.annotations.NotNull;

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
    public <R> @NotNull Method<R> getMethod(String name, @NotNull MemoryLayout returnType) {
        return new Method<>(getMethodHandle(name, FunctionDescriptor.of(returnType)), name);
    }
    public <R, A> @NotNull UnoMethod<R, A> getMethod(String name, @NotNull MemoryLayout returnType, MemoryLayout argumentType) {
        return new UnoMethod<>(getMethodHandle(name, FunctionDescriptor.of(returnType, argumentType)), name);
    }
    public <R, A, B, C, D, E> @NotNull PentaMethod<R, A, B, C, D, E> getMethod(String name, @NotNull MemoryLayout returnType, MemoryLayout arg1Type,
                                                                               MemoryLayout arg2Type,
                                                                               MemoryLayout arg3Type,
                                                                               MemoryLayout arg4Type,
                                                                               MemoryLayout arg5Type) {
        return new PentaMethod<>(getMethodHandle(name, FunctionDescriptor.of(returnType, arg1Type, arg2Type, arg3Type, arg4Type, arg5Type)), name);
    }
    public @NotNull VoidMethod getVoidMethod(String name) {
        return new VoidMethod(getMethodHandle(name, FunctionDescriptor.ofVoid()), name);
    }
    public <A> @NotNull UnoVoidMethod<A> getVoidMethod(String name, MemoryLayout argType) {
        return new UnoVoidMethod<>(getMethodHandle(name, FunctionDescriptor.ofVoid(argType)), name);
    }
    public <A, B> @NotNull DuoVoidMethod<A, B> getVoidMethod(String name, MemoryLayout arg1Type, MemoryLayout arg2Type) {
        return new DuoVoidMethod<>(getMethodHandle(name, FunctionDescriptor.ofVoid(arg1Type, arg2Type)), name);
    }
    public <A, B, C> @NotNull TriVoidMethod<A, B, C> getVoidMethod(String name, MemoryLayout arg1Type, MemoryLayout arg2Type, MemoryLayout arg3Type) {
        return new TriVoidMethod<>(getMethodHandle(name, FunctionDescriptor.ofVoid(arg1Type, arg2Type, arg3Type)), name);
    }
    public <A, B, C, D> @NotNull QuadVoidMethod<A, B, C, D> getVoidMethod(String name, MemoryLayout arg1Type, MemoryLayout arg2Type, MemoryLayout arg3Type, MemoryLayout arg4Type) {
        return new QuadVoidMethod<>(getMethodHandle(name, FunctionDescriptor.ofVoid(arg1Type, arg2Type, arg3Type, arg4Type)), name);
    }
}
