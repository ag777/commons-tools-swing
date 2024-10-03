package github.ag777.common.tool.swing.util.ui;

import github.ag777.common.tool.swing.model.Theme;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class Toast {
    @Setter
    private static Color COLOR_NORMAL = new Color(0, 0, 0, 150);
    private final Window parent;
    private final String message;
    private final int duration;
    private final Color color;

    public Toast(Window parent, Color color, String message, int duration) {
        this.color = color;
        this.message = message;
        this.parent = parent;
        this.duration = duration;
    }

    /**
     * 在窗口的中心位置显示Toast消息
     * @param parent 父窗口
     * @param message 消息内容
     * @param duration 显示时长
     * @return 返回Toast窗口对象
     */
    public static Toast normal(Window parent, String message, int duration) {
        return new Toast(parent, COLOR_NORMAL, message, duration);
    }

    /**
     * 在窗口的中心位置以主题主要颜色显示Toast消息
     * @param parent 父窗口
     * @param message 消息内容
     * @param duration 显示时长
     * @return 返回Toast窗口对象
     */
    public static Toast primary(Window parent, String message, int duration) {
        return new Toast(parent, Theme.COLOR_PRIMARY, message, duration);
    }

    /**
     * 在窗口的中心位置以成功颜色显示Toast消息
     * @param parent 父窗口
     * @param message 消息内容
     * @param duration 显示时长
     * @return 返回Toast窗口对象
     */
    public static Toast success(Window parent, String message, int duration) {
        return new Toast(parent, Theme.COLOR_SUCCESS, message, duration);
    }

    /**
     * 在窗口的中心位置以错误颜色显示Toast消息
     * @param parent 父窗口
     * @param message 消息内容
     * @param duration 显示时长
     * @return 返回Toast窗口对象
     */
    public static Toast error(Window parent, String message, int duration) {
        return new Toast(parent, Theme.COLOR_ERROR, message, duration);
    }

    /**
     * 在屏幕中心显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInCenter() {
        return showToast(ViewUtils::showInCenter);
    }

    /**
     * 在屏幕中心显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenCenter() {
        return showToast((w,r)->ViewUtils.showInCenter(w));
    }

    /**
     * 在窗口的左上角显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInTopLeft() {
        return showToast(ViewUtils::showInTopLeft);
    }

    /**
     * 在屏幕左上角显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenTopLeft() {
        return showToast((w,r)->ViewUtils.showInTopLeft(w));
    }

    /**
     * 在窗口的顶部中心显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInTopCenter() {
        return showToast(ViewUtils::showInTopCenter);
    }

    /**
     * 在屏幕顶部中心显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenTopCenter() {
        return showToast((w,r)->ViewUtils.showInTopCenter(w));
    }

    /**
     * 在窗口的右上角显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInTopRight() {
        return showToast(ViewUtils::showInTopRight);
    }

    /**
     * 在屏幕右上角显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenTopRight() {
        return showToast((w,r)->ViewUtils.showInTopRight(w));
    }

    /**
     * 在窗口的左部中心显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInLeftCenter() {
        return showToast(ViewUtils::showInLeftCenter);
    }

    /**
     * 在屏幕左部中心显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenLeftCenter() {
        return showToast((w,r)->ViewUtils.showInLeftCenter(w));
    }

    /**
     * 在窗口的右部中心显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInRightCenter() {
        return showToast(ViewUtils::showInRightCenter);
    }

    /**
     * 在屏幕右部中心显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenRightCenter() {
        return showToast((w,r)->ViewUtils.showInRightCenter(w));
    }

    /**
     * 在窗口的左下角显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInBottomLeft() {
        return showToast(ViewUtils::showInBottomLeft);
    }

    /**
     * 在屏幕左下角显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenBottomLeft() {
        return showToast((w,r)->ViewUtils.showInBottomLeft(w));
    }

    /**
     * 在窗口的底部中心显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInBottomCenter() {
        return showToast(ViewUtils::showInBottomCenter);
    }

    /**
     * 在屏幕底部中心显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenBottomCenter() {
        return showToast((w,r)->ViewUtils.showInBottomCenter(w));
    }

    /**
     * 在窗口的右下角显示Toast窗口
     * @return 返回显示的JWindow对象
     */
    public JWindow showInBottomRight() {
        return showToast(ViewUtils::showInBottomRight);
    }

    /**
     * 在屏幕右下角显示Toast窗口，使用指定的显示逻辑
     * @return 返回显示的JWindow对象
     */
    public JWindow showInScreenBottomRight() {
        return showToast((w,r)->ViewUtils.showInBottomRight(w));
    }

    /**
     * 根据提供的显示逻辑显示Toast窗口
     * @param show 显示逻辑的BiConsumer，接受窗口和矩形参数
     * @return 返回显示的JWindow对象
     */
    public JWindow showToast(BiConsumer<JWindow, Rectangle> show) {
        JWindow toastWindow = getjWindow();

        // 设置位置
        show.accept(toastWindow, new Rectangle(parent.getLocation(), parent.getSize()));
        // 定时关闭
        Timer timer = new Timer(duration, e -> toastWindow.dispose());
        timer.setRepeats(false);
        timer.start();
        return toastWindow;
    }

    /**
     * 创建并返回一个 JWindow 实例，用于显示 toast 通知
     * Toast 通知通常用于在应用程序中显示临时消息
     *
     * @return JWindow 实例，不为空
     */
    private @NotNull JWindow getjWindow() {
        // 创建 JWindow 作为 toast 容器
        JWindow toastWindow = new JWindow(parent);
        // 创建 JLabel 用于显示消息
        JLabel label = new JLabel(message);
        // 设置边框，上下左右分别留出10像素、20像素、10像素、20像素的空间
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        // 设置标签为不透明，并使用指定颜色作为背景
        label.setOpaque(true);
        // 设置背景颜色，这里使用的是半透明背景
        label.setBackground(color);
        // 设置文本颜色为白色
        label.setForeground(Color.WHITE);
        // 设置字体为粗体，大小为16点
        label.setFont(label.getFont().deriveFont(Font.BOLD, 16f));

        // 将 JLabel 添加到 JWindow 的内容窗格中，放在中心位置
        toastWindow.getContentPane().add(label, BorderLayout.CENTER);
        // 调整 JWindow 的大小以适应 JLabel
        toastWindow.pack();
        // 返回装有 JLabel 的 JWindow 实例
        return toastWindow;
    }

    public enum Location {
        CENTER,
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        LEFT_CENTER,
        RIGHT_CENTER,
        BOTTOM_LEFT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT
    }
}
