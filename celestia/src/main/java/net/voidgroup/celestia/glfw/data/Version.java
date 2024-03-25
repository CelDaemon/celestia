package net.voidgroup.celestia.glfw.data;

public record Version(int major, int minor, int revision) {
    @Override
    public String toString() {
        return STR."\{major}.\{minor}.\{revision}";
    }
}
