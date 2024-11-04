package github.ag777.common.tool.swing.util.ui;

import org.intellij.lang.annotations.MagicConstant;

import javax.swing.*;
import java.awt.*;

/**
 * JLabel链式调用封装
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/11/4 上午11:43
 */
public class LabelHelper {
    private final JLabel mLabel;

    /**
     * 创建一个新的LabelHelper实例，该实例封装了一个JLabel组件
     *
     * @return 返回一个LabelHelper实例，用于操作和布局JLabel组件
     */
    public static LabelHelper newInstance() {
        return new LabelHelper(new JLabel());
    }

    public static LabelHelper newInstance(String text) {
        return new LabelHelper(new JLabel(text));
    }

    public static LabelHelper newInstance(
            String text,
            Icon icon,
            @MagicConstant(intValues = {SwingConstants.LEFT,SwingConstants.CENTER,SwingConstants.RIGHT,SwingConstants.LEADING,SwingConstants.TRAILING})
            int horizontalAlignment) {
        return new LabelHelper(new JLabel(text, icon, horizontalAlignment));
    }

    public static LabelHelper newInstance(
            String text,
            @MagicConstant(intValues = {SwingConstants.LEFT,SwingConstants.CENTER,SwingConstants.RIGHT,SwingConstants.LEADING,SwingConstants.TRAILING})
            int horizontalAlignment) {
        return new LabelHelper(new JLabel(text, horizontalAlignment));
    }

    public static LabelHelper newInstance(
            Icon icon) {
        return new LabelHelper(new JLabel(icon));
    }

    public static LabelHelper newInstance(
            Icon icon,
            @MagicConstant(intValues = {SwingConstants.LEFT,SwingConstants.CENTER,SwingConstants.RIGHT,SwingConstants.LEADING,SwingConstants.TRAILING})
            int horizontalAlignment) {
        return new LabelHelper(new JLabel(icon, horizontalAlignment));
    }

    /**
     * 构造函数，初始化LabelHelper时，将传入的JLabel实例封装起来
     *
     * @param label 要封装的JLabel实例
     */
    public LabelHelper(JLabel label) {
        this.mLabel = label;
    }

    /**
     * 获取封装的JLabel实例
     *
     * @return 返回封装的JLabel实例
     */
    public JLabel get() {
        return mLabel;
    }

    /**
     * 设置JLabel的文本
     *
     * @param text 文本内容
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setText(String text) {
        mLabel.setText(text);
        return this;
    }

    /**
     * 设置JLabel的图标
     *
     * @param icon 图标
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setIcon(Icon icon) {
        mLabel.setIcon(icon);
        return this;
    }

    /**
     * 将标签在水平方向上居左对齐
     *
     * @return LabelHelper 返回当前实例，支持链式调用
     */
    public LabelHelper leftInHorizontal() {
        mLabel.setHorizontalAlignment(SwingConstants.LEFT);
        return this;
    }

    /**
     * 将标签在水平方向上居中对齐
     *
     * @return LabelHelper 返回当前实例，支持链式调用
     */
    public LabelHelper centerInHorizontal() {
        mLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }

    /**
     * 将标签在水平方向上居右对齐
     *
     * @return LabelHelper 返回当前实例，支持链式调用
     */
    public LabelHelper rightInHorizontal() {
        mLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        return this;
    }

    /**
     * 将标签在垂直方向上顶部对齐
     *
     * @return LabelHelper 返回当前实例，支持链式调用
     */
    public LabelHelper topInVertical() {
        mLabel.setVerticalAlignment(SwingConstants.TOP);
        return this;
    }

    /**
     * 将标签在垂直方向上居中对齐
     *
     * @return LabelHelper 返回当前实例，支持链式调用
     */
    public LabelHelper centerInVertical() {
        mLabel.setVerticalAlignment(SwingConstants.CENTER);
        return this;
    }

    /**
     * 将标签在垂直方向上底部对齐
     *
     * @return LabelHelper 返回当前实例，支持链式调用
     */
    public LabelHelper bottomInVertical() {
        mLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        return this;
    }

    /**
     * 设置JLabel的水平对齐方式
     *
     * @param alignment 水平对齐方式，如 SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.RIGHT
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setHorizontalAlignment(int alignment) {
        mLabel.setHorizontalAlignment(alignment);
        return this;
    }

    /**
     * 设置JLabel的垂直对齐方式
     *
     * @param alignment 垂直对齐方式，如 SwingConstants.TOP, SwingConstants.CENTER, SwingConstants.BOTTOM
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setVerticalAlignment(int alignment) {
        mLabel.setVerticalAlignment(alignment);
        return this;
    }

    /**
     * 设置JLabel的工具提示文本
     *
     * @param tip 工具提示文本
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setToolTipText(String tip) {
        mLabel.setToolTipText(tip);
        return this;
    }

    /**
     * 设置JLabel的字体
     *
     * @param font 字体
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setFont(Font font) {
        mLabel.setFont(font);
        return this;
    }

    /**
     * 设置JLabel的前景色
     *
     * @param color 前景色
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setForeground(Color color) {
        mLabel.setForeground(color);
        return this;
    }

    /**
     * 设置JLabel的背景色
     *
     * @param color 背景色
     * @return 返回当前LabelHelper实例，以便进行链式调用
     */
    public LabelHelper setBackground(Color color) {
        mLabel.setBackground(color);
        mLabel.setOpaque(true); // 必须设置为true以使背景色生效
        return this;
    }
}
