package github.ag777.common.tool.swing.view.component;

import github.ag777.common.tool.swing.model.Theme;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
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
    private volatile Status status;

    public MyProgressBar() {
        super();
        status = Status.RUNNING;
        setStringPainted(true);
        setHeight(20);
        colorGenerator = info->
            switch (status) {
                case RUNNING:
                    yield Theme.COLOR_PRIMARY;
                case COMPLETE:
                    yield Theme.COLOR_SUCCESS;
                case ERROR:
                    yield Theme.COLOR_ERROR;
            };

        DecimalFormat percentFormat = new DecimalFormat("0%");
        setTextFormatter((info)-> {
            if (info.hover()) {
                return info.value()+"/"+info.max();
            } else {
                return percentFormat.format(info.progress());
            }
        });

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

    /**
     * 设置组件的高度,建议最小值是20
     *
     * @param height 组件的目标高度
     */
    public void setHeight(int height) {
        setPreferredSize(new Dimension(0, height));
    }

    /**
     * 初始化进度条
     *
     * @param max 进度条的最大值，表示任务的总工作量
     */
    public void init(int max) {
        // 在任务开始时，将进度条设置为初始状态：已完成0，总工作量为max，状态为运行中
        updateProgress(0, 0, max, Status.RUNNING);
    }

    /**
     * 更新进度条的进度范围和当前值
     * 此方法用于动态更新一个进度条的最小值、最大值和当前值
     * 它首先检查当前的最小值和最大值是否与传入的参数相同，如果不同，则进行更新
     * 最后，它更新进度条的当前值为传入的value参数
     *
     * @param value 进度条的当前值，表示进度的百分比
     * @param min 进度条的最小值，进度条的下限
     * @param max 进度条的最大值，进度条的上限
     * @param status 进度条的状态，可以是正在运行、已完成、出错等
     */
    public void updateProgress(int value, int min, int max, Status status) {
        // 检查并更新最小值
        if (getMinimum()!=min) {
            setMinimum(min);
        }
        // 检查并更新最大值
        if (getMaximum()!=max) {
            setMaximum(max);
        }
        // 更新当前值
        setValue(value);
        // 更新状态
        setStatus(status);
    }

    /**
     * 设置任务状态为运行中
     */
    public void setStatus2Running() {
        setStatus(Status.RUNNING);
    }

    /**
     * 设置任务状态为已完成
     */
    public void setStatus2Complete() {
        setStatus(Status.COMPLETE);
    }

    /**
     * 设置任务状态为错误
     */
    public void setStatus2Error() {
        setStatus(Status.ERROR);
    }

    /**
     * 设置当前对象的状态，并在状态改变时重新绘制界面
     *
     * @param status 新的状态
     */
    public void setStatus(Status status) {
        // 检查状态是否改变
        if (this.status != status) {
            // 更新当前状态
            this.status = status;
            // 触发变更事件,重新上色
            fireStateChanged();
        }
    }

    /**
     * 获取进度信息
     *
     * @return 包含最小值、最大值、当前值、进度百分比、鼠标悬停状态和任务状态的ProgressInfo对象
     */
    public ProgressInfo getProgressInfo() {
        return new ProgressInfo(getMinimum(), getMaximum(), getValue(), getProgress(), hover, status);
    }

    /**
     * 计算当前进度的百分比
     *
     * @return 当前进度的百分比，范围为0.0到1.0
     */
    public double getProgress() {
        int maximum = getMaximum();
        if (maximum == 0) {
            return 0;
        }
        return (double) getValue() / maximum;
    }

    /**
     * 刷新文本显示
     *
     * 此方法调用ProgressInfo对象的格式化器来生成文本，并更新UI
     */
    private void refreshText() {
        refreshText(getProgressInfo());
    }

    /**
     * 内部使用的方法，根据ProgressInfo对象刷新文本显示
     *
     * @param info 包含进度信息的ProgressInfo对象，用于计算和格式化文本
     */
    private void refreshText(ProgressInfo info) {
        String text=null;
        if (textFormatter != null) {
            text = textFormatter.apply(info);
        }
        setString(text);
        repaint();
    }


    public record ProgressInfo(int min, int max, int value, double progress, boolean hover, Status status) {}

    public enum Status {
        RUNNING,
        COMPLETE,
        ERROR
    }
}