package net.voidgroup.celestia.unsafe;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
public class StandardLibraryProvider extends MethodProvider {

    private static final SymbolLookup LOOKUP = linker.defaultLookup();
    @Override
    protected MemorySegment resolveMethodAddress(String name) {
        return LOOKUP.find(name).orElseThrow();
    }
}
