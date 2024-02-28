package net.voidgroup.celestia.unsafe;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
public class StandardLibrary {
    private static final StandardLibraryProvider PROVIDER = new StandardLibraryProvider();

    public static final UnoMethod<Long, MemorySegment> strlen = PROVIDER.getMethod("strlen", ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
}
