package github.ag777.common.tool.swing.view.component.loading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 下午4:09
 */
public class LoadingPanel extends JPanel {
    private Timer timer;
    private int angle = 0; // 用于旋转效果

    public LoadingPanel() {
        // 设置面板为透明
        setOpaque(false);

        // 创建一个计时器，用于定时更新旋转角度
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle = (angle + 10) % 360; // 每次更新旋转角度
                repaint(); // 触发重绘
            }
        });
        setVisible(false);

        // 确保面板可以响应鼠标事件，防止事件传递到下面的组件
        addMouseListener(new java.awt.event.MouseAdapter() {});
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {});
    }

    // 开始显示加载面板时，启动定时器
    public void startLoading() {
        timer.start();
    }

    // 停止加载面板时，停止定时器
    public void stopLoading() {
        timer.stop();
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            startLoading();
        } else {
            stopLoading();
        }
    }

    // 绘制半透明背景和旋转的进度条
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 设置半透明背景
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // 半透明度为0.5
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 绘制旋转的进度条
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // 进度条不透明
        drawLoadingSpinner(g2d);

        g2d.dispose();
    }

    // 绘制一个简单的旋转加载图标
    private void drawLoadingSpinner(Graphics2D g2d) {
        int size = 50; // 进度条大小
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        // 设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制旋转的圆形
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawArc(x, y, size, size, angle, 270); // 根据角度旋转
    }
}