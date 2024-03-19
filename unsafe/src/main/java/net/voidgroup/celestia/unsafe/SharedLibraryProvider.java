package net.voidgroup.celestia.unsafe;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SharedLibraryProvider extends MethodProvider {
    private final SymbolLookup lookup;
    public SharedLibraryProvider(String name) {
        Path natives = Path.of("natives", name);
        try(var stream = SharedLibraryProvider.class.getClassLoader().getResourceAsStream(name)) {
            assert stream != null;
            Files.createDirectories(Path.of("natives"));
            Files.copy(stream, natives, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lookup = SymbolLookup.libraryLookup(natives.toAbsolutePath(), Arena.global());
    }
    @Override
    @NotNull
    protected MemorySegment resolveMethodAddress(@NotNull String name) {
        return lookup.find(name).orElseThrow();
    }
}
