package net.voidgroup.celestia_lib.example;

import net.voidgroup.celestia_lib.unsafe.StandardLibrary;

import java.lang.foreign.Arena;
@SuppressWarnings("preview")
public class Example {
    public static void main(String[] args) {
        try(var arena = Arena.ofConfined()) {
            var test = arena.allocateUtf8String("Test");
            System.out.println(StandardLibrary.strlen.execute(test));
        }
    }
}
