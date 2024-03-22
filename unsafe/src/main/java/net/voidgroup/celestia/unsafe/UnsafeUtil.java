package net.voidgroup.celestia.unsafe;

import org.jetbrains.annotations.NotNull;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;

public class UnsafeUtil {
    public static String readString(@NotNull MemorySegment memorySegment) {
        return memorySegment.reinterpret(StandardLibrary.strlen.execute(memorySegment) + 1).getUtf8String(0);
    }
    public static MemorySegment fromStructAddress(@NotNull MemoryLayout layout, long address) {
        return MemorySegment.ofAddress(address).reinterpret(layout.byteSize());
    }
}
