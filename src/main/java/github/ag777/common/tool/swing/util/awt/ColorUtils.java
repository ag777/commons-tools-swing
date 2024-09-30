package github.ag777.common.tool.swing.util.awt;

import java.awt.*;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午11:06
 */
public class ColorUtils {
    /**
     * 生成从 startColor 到 endColor 的 n 个渐变颜色。
     *
     * @param startColor 起始颜色
     * @param endColor   结束颜色
     * @param n          渐变颜色的数量
     * @return 一个包含 n 个渐变颜色的数组
     */
    public static Color[] generateGradientColors(Color startColor, Color endColor, int n) {
        int rStep = (endColor.getRed() - startColor.getRed()) / (n - 1);
        int gStep = (endColor.getGreen() - startColor.getGreen()) / (n - 1);
        int bStep = (endColor.getBlue() - startColor.getBlue()) / (n - 1);

        Color[] gradientColors = new Color[n];
        for (int i = 0; i < n; i++) {
            int r = startColor.getRed() + i * rStep;
            int g = startColor.getGreen() + i * gStep;
            int b = startColor.getBlue() + i * bStep;
            gradientColors[i] = new Color(r, g, b);
        }
        return gradientColors;
    }


    public static Color getGradientColor(Color startColor, Color endColor, int n, int index) {
        // 验证索引是否有效
        if (index < 0 || index >= n) {
            throw new IllegalArgumentException("Index must be between 0 and " + (n - 1));
        }

        // 计算每一步的 RGB 值变化
        int rStep = (endColor.getRed() - startColor.getRed()) / (n - 1);
        int gStep = (endColor.getGreen() - startColor.getGreen()) / (n - 1);
        int bStep = (endColor.getBlue() - startColor.getBlue()) / (n - 1);

        // 计算指定索引处的颜色值
        int r = startColor.getRed() + index * rStep;
        int g = startColor.getGreen() + index * gStep;
        int b = startColor.getBlue() + index * bStep;

        // 返回指定索引处的颜色
        return new Color(r, g, b);
    }

}
