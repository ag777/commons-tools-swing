package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 滚动面板工具类
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午8:43
 */
public class ScrollPaneUtils {

    /**
     * 创建并返回一个带有滚动条包装的文本区域对象
     *
     *
     * @return JTextArea 返回一个经过封装的JTextArea对象
     */
    public static JTextArea textArea() {
        JTextArea ta = new JTextArea();
        wrap(ta);
        return ta;
    }


    /**
     * 创建一个带滚动条的视图容器
     * 此方法接收一个视图组件，将其包装在一个滚动面板中，
     * 并确保滚动面板的视图管理器也指向该视图组件
     *
     * @param view 被包装的视图组件
     * @return 包含视图组件的滚动面板
     */
    public static JScrollPane wrap(Component view) {
        // 创建一个滚动面板，并将传入的视图组件设置为其显示的视图
        JScrollPane scrollPane = new JScrollPane(view);
        // 设置滚动面板的视图管理器，使其滚动和显示操作针对传入的视图组件
        scrollPane.setViewportView(view);
        // 返回包含视图组件的滚动面板
        return scrollPane;
    }
}
