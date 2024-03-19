package net.voidgroup.celestia.unsafe;

import org.jetbrains.annotations.NotNull;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
public class StandardLibraryProvider extends MethodProvider {

    private static final SymbolLookup LOOKUP = linker.defaultLookup();
    @Override
    @NotNull
    protected MemorySegment resolveMethodAddress(String name) {
        return LOOKUP.find(name).orElseThrow();
    }
}
