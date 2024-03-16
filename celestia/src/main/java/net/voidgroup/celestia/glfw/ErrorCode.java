package net.voidgroup.celestia.glfw;

public enum ErrorCode {
    NoError(0x0),
    NoCurrentContext(0x00010002),
    InvalidEnum(0x00010003),
    InvalidValue(0x00010004),
    OutOfMemory(0x00010005),
    ApiUnavailable(0x00010006),
    VersionUnavailable(0x00010007),
    PlatformError(0x00010008),
    FormatUnavailable(0x00010009),
    NoWindowContext(0x0001000A),
    CursorUnavailable(0x0001000B),
    FeatureUnavailable(0x0001000C),
    FeatureUnimplemented(0x0001000D),
    PlatformUnavailable(0x0001000E);
    public final int code;
    ErrorCode(int code) {
        this.code = code;
    }

    public static ErrorCode valueOf(int val) {
        return switch (val) {
            case 0x0 -> NoError;
            case 0x00010002 -> NoCurrentContext;
            case 0x00010003 -> InvalidEnum;
            case 0x00010004 -> InvalidValue;
            case 0x00010005 -> OutOfMemory;
            case 0x00010006 -> ApiUnavailable;
            case 0x00010007 -> VersionUnavailable;
            case 0x00010008 -> PlatformError;
            case 0x00010009 -> FormatUnavailable;
            case 0x0001000A -> NoWindowContext;
            case 0x0001000B -> CursorUnavailable;
            case 0x0001000C -> FeatureUnavailable;
            case 0x0001000D -> FeatureUnimplemented;
            case 0x0001000E -> PlatformUnavailable;
            default -> throw new IllegalStateException(STR."Unexpected value: \{val}");
        };
    }
}
