package net.voidgroup.celestia.unsafe;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.nio.file.Path;

public class SharedLibraryProvider extends MethodProvider {
    private final SymbolLookup lookup;
    public SharedLibraryProvider(Path path) {
        lookup = SymbolLookup.libraryLookup(path, Arena.global());
    }
    @Override
    protected MemorySegment resolveMethodAddress(String name) {
        return lookup.find(name).orElseThrow();
    }
}
