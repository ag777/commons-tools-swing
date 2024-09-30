package github.ag777.common.tool.swing.view.component;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Function;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午10:00
 */
public class MyProgressBar extends JProgressBar {
    @Setter
    private Function<ProgressInfo, String> textFormatter;
    @Setter
    private Function<ProgressInfo, Color> colorGenerator;

    private volatile boolean hover;
    @Setter
    private volatile Status status;

    @Setter
    private Color colorRunning = new Color(22,119,255);
    @Setter
    private Color colorComplete = new Color(82, 196, 26);
    @Setter
    private Color colorError = new Color(255, 77, 79);

    public MyProgressBar() {
        super();
        status = Status.RUNNING;
        colorGenerator = info->
            switch (status) {
                case RUNNING:
                    yield colorRunning;
                case COMPLETE:
                    yield colorComplete;
                case ERROR:
                    yield colorError;
            };

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                hover=true;
                refreshText();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover=false;
                refreshText();
            }
        });

        addChangeListener(e-> {
            ProgressInfo info = getProgressInfo();
            refreshText(info);
            if (colorGenerator != null) {
                Color color = colorGenerator.apply(info);
                if (color != null) {
                    setForeground(color);
                }
            }

        });

    }

    private void refreshText() {
        refreshText(getProgressInfo());
    }

    private void refreshText(ProgressInfo info) {
        String text=null;
        if (textFormatter != null) {
            text = textFormatter.apply(info);
        }
        setString(text);
        repaint();
    }

    public ProgressInfo getProgressInfo() {
        return new ProgressInfo(getMinimum(), getMaximum(), getValue(), getProgress(), hover, status);
    }

    public double getProgress() {
        return (double) getValue() / getMaximum();
    }

    public record ProgressInfo(int min, int max, int value, double progress, boolean hover, Status status) {}

    public enum Status {
        RUNNING,
        COMPLETE,
        ERROR
    }
}