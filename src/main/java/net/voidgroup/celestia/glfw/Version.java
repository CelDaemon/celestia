package net.voidgroup.celestia.glfw;

public record Version(int major, int minor, int patch) {
    @Override
    public String toString() {
        return STR."\{major}.\{minor}.\{patch}";
    }
    public boolean compareCompatible(Version version) {
        if(this.major != version.major) return false;
        if(this.minor < version.minor) return false;
        return this.patch >= version.patch;
    }
}
