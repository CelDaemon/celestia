package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.internal.GLFWMethods;

import java.lang.foreign.MemorySegment;

public class Window {
    public final MemorySegment handle;
    private Window(WindowBuilder builder) {
        GLFWMethods.glfwDefaultWindowHints();
        if(builder.focused != null) GLFWMethods.glfwWindowHint(WindowHint.Focused, builder.focused);
        if(builder.resizable != null) GLFWMethods.glfwWindowHint(WindowHint.Resizable, builder.resizable);
        if(builder.visible != null) GLFWMethods.glfwWindowHint(WindowHint.Visible, builder.visible);
        if(builder.decorated != null) GLFWMethods.glfwWindowHint(WindowHint.Decorated, builder.decorated);
        if(builder.autoIconify != null) GLFWMethods.glfwWindowHint(WindowHint.AutoIconify, builder.autoIconify);
        if(builder.floating != null) GLFWMethods.glfwWindowHint(WindowHint.Floating, builder.floating);
        if(builder.maximized != null) GLFWMethods.glfwWindowHint(WindowHint.Maximized, builder.maximized);
        if(builder.centerCursor != null) GLFWMethods.glfwWindowHint(WindowHint.CenterCursor, builder.centerCursor);
        if(builder.transparentFramebuffer != null) GLFWMethods.glfwWindowHint(WindowHint.TransparentFramebuffer, builder.transparentFramebuffer);
        if(builder.focusOnShow != null) GLFWMethods.glfwWindowHint(WindowHint.FocusOnShow, builder.focusOnShow);
        this.handle = GLFWMethods.glfwCreateWindow(builder.width, builder.height, builder.title, MemorySegment.NULL, MemorySegment.NULL);
        if(this.handle.equals(MemorySegment.NULL))
            throw new RuntimeException(GLFWMethods.glfwGetError().toString());
    }
    public static class WindowBuilder {
        private final String title;
        private final int width;
        private final int height;
        private Boolean focused;
        private Boolean resizable;
        private Boolean visible;
        private Boolean decorated;
        private Boolean autoIconify;
        private Boolean floating;
        private Boolean maximized;
        private Boolean centerCursor;
        private Boolean transparentFramebuffer;
        private Boolean focusOnShow;

        public WindowBuilder(int width, int height, String title) {
            this.title = title;
            this.width = width;
            this.height = height;
        }
        public void focused(Boolean focused) {
            this.focused = focused;
        }
        public void resizable(Boolean resizable) {
            this.resizable = resizable;
        }
        public void visible(Boolean visible) {
            this.visible = visible;
        }
        public void decorated(Boolean decorated) {
            this.decorated = decorated;
        }
        public void autoIconify(Boolean autoIconify) {
            this.autoIconify = autoIconify;
        }
        public void floating(Boolean floating) {
            this.floating = floating;
        }
        public void maximized(Boolean maximized) {
            this.maximized = maximized;
        }
        public void centerCursor(Boolean centerCursor) {
            this.centerCursor = centerCursor;
        }
        public void transparentFramebuffer(Boolean transparentFramebuffer) {
            this.transparentFramebuffer = transparentFramebuffer;
        }
        public void focusOnShow(Boolean focusOnShow) {
            this.focusOnShow = focusOnShow;
        }
        public Window build() {
            return new Window(this);
        }
    }
}
