package net.voidgroup.celestia.internal;

import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SharedLibraryLoader {
    private final SymbolLookup lookup;
    public SharedLibraryLoader(String name) {
        var path = Path.of("natives", name);
        try(var stream = SharedLibraryLoader.class.getClassLoader().getResourceAsStream(name)) {
            if(stream == null) throw new RuntimeException("Library not found in jar");
            Files.createDirectories(path.getParent());
            Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        lookup = SymbolLookup.libraryLookup(path, Arena.global());
    }

    public MemorySegment getMethod(String name) {
        return lookup.find(name).orElseThrow();
    }
}
