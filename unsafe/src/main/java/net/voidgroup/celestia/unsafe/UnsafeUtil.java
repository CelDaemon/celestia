package net.voidgroup.celestia.unsafe;

import java.lang.foreign.MemorySegment;

public class UnsafeUtil {
    public static String readString(MemorySegment memorySegment) {
        return memorySegment.reinterpret(StandardLibrary.strlen.execute(memorySegment) + 1).getUtf8String(0);
    }
}
