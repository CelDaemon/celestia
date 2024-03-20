package net.voidgroup.celestia.glfw;

public record Color(float r, float g, float b, float a) {
    public static final Color TRANS_PINK = fromInt(245, 169, 184, 100);

    public static Color fromInt(int ri, int gi, int bi, int ai) {
        return new Color(
                intToFloat(ri),
                intToFloat(gi),
                intToFloat(bi),
                intToFloat(ai));
    }

    private static float intToFloat(int val) {
        return 1.0f / 256 * val;
    }
}
