package github.ag777.common.tool.swing.util;

import github.ag777.common.tool.swing.model.UiProperties;

import javax.swing.*;
import java.awt.*;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/27 10:26
 */
public class UiConfigUtils {

    private UiConfigUtils() {
    }


    public static void initFontSize(JComponent component, UiProperties.Font fontConfig) {
        if (fontConfig == null) {
            return;
        }
        int textSize = fontConfig.getTextSize();
        boolean bold = fontConfig.isBold();

        Font font = new Font(Font.DIALOG, bold? Font.BOLD: Font.PLAIN, textSize);
        component.setFont(font);
    }


}
