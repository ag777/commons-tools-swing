package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午9:34
 */
public class BorderUtils {

    /**
     * 为组件添加标题边框
     *
     * @param view       需要添加边框的组件
     * @param borderTitle 边框的标题文本
     * @param <T>        组件的类型，必须是JComponent的子类
     * @return 返回添加了标题边框的组件
     */
    public static <T extends JComponent>T title(T view, String borderTitle) {
        view.setBorder(new TitledBorder(new EtchedBorder(), borderTitle));
        return view;
    }

    /**
     * 为组件添加左边距
     *
     * @param view  要添加左边距的组件
     * @param margin  左边距的大小
     * @return  添加左边距后的组件
     */
    public static <T extends JComponent>T marginLeft(T view, int margin) {
        return margin(view, 0, margin, 0, 0);
    }

    /**
     * 为组件添加右边距
     *
     * @param view  要添加右边距的组件
     * @param margin  右边距的大小
     * @return  添加右边距后的组件
     */
    public static <T extends JComponent>T marginRight(T view, int margin) {
        return margin(view, 0, 0, 0, margin);
    }

    /**
     * 为组件添加上边距
     *
     * @param view  要添加上边距的组件
     * @param margin  上边距的大小
     * @return  添加上边距后的组件
     */
    public static <T extends JComponent>T marginTop(T view, int margin) {
        return margin(view, margin, 0, 0, 0);
    }

    /**
     * 为组件添加下边距
     *
     * @param view  要添加下边距的组件
     * @param margin  下边距的大小
     * @return  添加下边距后的组件
     */
    public static <T extends JComponent>T marginBottom(T view, int margin) {
        return margin(view, 0, 0, margin, 0);
    }

    /**
     * 为组件添加空白边框
     *
     * @param view       需要添加边框的组件
     * @param top        组件顶部的空白大小
     * @param left       组件左侧的空白大小
     * @param bottom     组件底部的空白大小
     * @param right      组件右侧的空白大小
     * @param <T>        组件的类型，必须是JComponent的子类
     * @return 返回添加了空白边框的组件
     */
    public static <T extends JComponent>T margin(T view, int top, int left, int bottom, int right) {
        return empty(view, top, left, bottom, right);
    }

    /**
     * 给定的视图组件应用内边距
     * 此方法的目的是简化对称内边距的设置，通过指定两个参数即可设置上下和左右的内边距，
     * 而不必分别设置每个方向的内边距
     *
     * @param view           需要设置内边距的视图组件，必须是JComponent的子类
     * @param topAndBottom   上下内边距的大小
     * @param leftAndRight   左右内边距的大小
     * @param <T>            泛型参数，表示返回的视图组件类型
     * @return               返回设置了内边距的视图组件
     */
    public static <T extends JComponent>T margin(T view, int topAndBottom, int leftAndRight) {
        return empty(view, topAndBottom, leftAndRight, topAndBottom, leftAndRight);
    }

    /**
     * 为组件添加空白边框
     *
     * @param view       需要添加边框的组件
     * @param top        组件顶部的空白大小
     * @param left       组件左侧的空白大小
     * @param bottom     组件底部的空白大小
     * @param right      组件右侧的空白大小
     * @param <T>        组件的类型，必须是JComponent的子类
     * @return 返回添加了空白边框的组件
     */
    public static <T extends JComponent>T empty(T view, int top, int left, int bottom, int right) {
        view.setBorder(new EmptyBorder(top, left, bottom, right));
        return view;
    }

    /**
     * 为组件添加等间距的空白边框
     *
     * @param view   需要添加边框的组件
     * @param margin 组件四周的空白大小
     * @param <T>    组件的类型，必须是JComponent的子类
     * @return 返回添加了空白边框的组件
     */
    public static <T extends JComponent>T margin(T view, int margin) {
        return margin(view, margin, margin);
    }


}
