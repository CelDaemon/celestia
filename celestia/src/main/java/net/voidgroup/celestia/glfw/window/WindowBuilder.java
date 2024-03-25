package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.data.Point2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Nullable
public class WindowBuilder {
    @NotNull
    private final String title;
    private Point2D size;
    private Monitor monitor;
    private Window shared;
    private boolean visible = true;
    private boolean resizable = true;
    private boolean decorated = true;
    private boolean floating;
    private boolean maximized;
    private boolean centerCursor;
    private boolean transparentFramebuffer;
    private boolean focusOnShow;

    public WindowBuilder(@NotNull String title) {
        this.title = title;
    }
    public WindowBuilder size(Point2D size) {
        this.size = size;
        return this;
    }
    public WindowBuilder fullscreen(Monitor monitor) {
        this.monitor = monitor;
        return this;
    }
    public WindowBuilder shared(Window window) {
        this.shared = window;
        return this;
    }
    public WindowBuilder visible(boolean visible) {
        this.visible = visible;
        return this;
    }
    public WindowBuilder resizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }
    public WindowBuilder decorated(boolean decorated) {
        this.decorated = decorated;
        return this;
    }
    public WindowBuilder floating(boolean floating) {
        this.floating = floating;
        return this;
    }
    public WindowBuilder maximized(boolean maximized) {
        this.maximized = maximized;
        return this;
    }
    public WindowBuilder centerCursor(boolean centerCursor) {
        this.centerCursor = centerCursor;
        return this;
    }
    public WindowBuilder transparentFramebuffer(boolean transparentFramebuffer) {
        this.transparentFramebuffer = transparentFramebuffer;
        return this;
    }
    public WindowBuilder focusOnShow(boolean focusOnShow) {
        this.focusOnShow = focusOnShow;
        return this;
    }
    public Window build() {
        return new Window(title, size, monitor, shared, visible, resizable, decorated, floating, maximized, centerCursor, transparentFramebuffer, focusOnShow);
    }

}
