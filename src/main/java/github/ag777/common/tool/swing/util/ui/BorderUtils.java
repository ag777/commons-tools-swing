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
    public static <T extends JComponent>T empty(T view, int topAndBottom, int leftAndRight) {
        // 调用重载方法以设置所有四个方向的内边距，其中前后内边距与上下相同，左右内边距与左右参数相同
        return empty(view, topAndBottom, leftAndRight, topAndBottom, leftAndRight);
    }

    /**
     * 为组件添加等间距的空白边框
     *
     * @param view   需要添加边框的组件
     * @param margin 组件四周的空白大小
     * @param <T>    组件的类型，必须是JComponent的子类
     * @return 返回添加了空白边框的组件
     */
    public static <T extends JComponent>T empty(T view, int margin) {
        return empty(view, margin, margin);
    }


}
