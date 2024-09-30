package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午9:34
 */
public class BorderUtils {

    /**
     * 为给定的组件设置带标题的边框
     *
     * @param view       需要设置边框的组件
     * @param borderTitle 边框的标题
     */
    public static void titleBorder(JComponent view, String borderTitle) {
        view.setBorder(new TitledBorder(new EtchedBorder(), borderTitle));
    }

}
