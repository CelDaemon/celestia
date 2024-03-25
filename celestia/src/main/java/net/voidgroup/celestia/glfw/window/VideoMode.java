package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.data.Point2D;
import net.voidgroup.celestia.unsafe.UnsafeUtil;

import java.lang.foreign.MemoryLayout;
import java.lang.invoke.VarHandle;

import static net.voidgroup.celestia.unsafe.GLFWLibrary.VIDEO_MODE;
import static net.voidgroup.celestia.unsafe.GLFWLibrary.glfwGetVideoMode;

public record VideoMode(Point2D size, int redBits, int greenBits, int blueBits, int refreshRate) {
    private static final VarHandle VHWidth = VIDEO_MODE.varHandle(MemoryLayout.PathElement.groupElement("width"));
    private static final VarHandle VHHeight = VIDEO_MODE.varHandle(MemoryLayout.PathElement.groupElement("height"));
    private static final VarHandle VHRedBits = VIDEO_MODE.varHandle(MemoryLayout.PathElement.groupElement("redBits"));
    private static final VarHandle VHGreenBits = VIDEO_MODE.varHandle(MemoryLayout.PathElement.groupElement("greenBits"));
    private static final VarHandle VHBlueBits = VIDEO_MODE.varHandle(MemoryLayout.PathElement.groupElement("blueBits"));
    private static final VarHandle VHRefreshRate = VIDEO_MODE.varHandle(MemoryLayout.PathElement.groupElement("refreshRate"));

    public static VideoMode fromMonitor(Monitor monitor) {
        var videoModeStruct = UnsafeUtil.fromStructAddress(VIDEO_MODE, glfwGetVideoMode.execute(monitor.getHandle()));
        return new VideoMode(
                new Point2D((int) VHWidth.get(videoModeStruct), (int) VHHeight.get(videoModeStruct)),
                (int) VHRedBits.get(videoModeStruct),
                (int) VHGreenBits.get(videoModeStruct),
                (int) VHBlueBits.get(videoModeStruct),
                (int) VHRefreshRate.get(videoModeStruct)
        );
    }
}
