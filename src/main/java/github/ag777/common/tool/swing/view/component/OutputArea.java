package github.ag777.common.tool.swing.view.component;

import github.ag777.common.tool.swing.util.ui.BorderUtils;
import github.ag777.common.tool.swing.util.ui.ScrollPaneUtils;
import github.ag777.common.tool.swing.util.ui.layout.BorderLayoutHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.util.function.Function;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午9:02
 */
@Accessors(chain = true)
public class OutputArea extends JPanel{

    /**
     * 文本输出区域，用于显示日志等文本内容
     */
    @Getter
    private final JTextArea textArea;

    /**
     * 进度条组件，用于显示处理进度
     */
    @Getter
    private final MyProgressBar progressBar;

    /**
     * 最大行数限制，超过此行数将删除最早的行
     */
    @Setter
    private Integer maxLineCount;

    /**
     * 是否始终显示进度条
     */
    private boolean alwaysShowProgressBar;

    /**
     * 构造函数，初始化组件并设置布局
     */
    public OutputArea() {
        textArea = new JTextArea();
        initTextarea();
        JScrollPane scrollPane = ScrollPaneUtils.wrap(textArea);
        progressBar = new MyProgressBar();
        this.alwaysShowProgressBar = false;
        initProgressBar();

        BorderLayoutHelper.newInstance(this)
                .addComponent2Center(BorderUtils.title(scrollPane, "输出"))
                .addComponent2South(progressBar);
    }

    /**
     * 初始化文本区域，添加文档监听器以实现行数限制功能
     */
    private void initTextarea() {
        // 监听文档变化
        Document document = textArea.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange(e);
            }

            /**
             * 处理文本变化事件，当超过最大行数时删除最早的行
             */
            private void handleTextChange(DocumentEvent e) {
                if (maxLineCount == null) {
                    return;
                }
                Document d = e.getDocument();
                Element root = d.getDefaultRootElement();
                int lineCount = root.getElementCount();
                if (lineCount>maxLineCount) {
                    SwingUtilities.invokeLater(()->{
                        try {
                            Element firstElement = root.getElement(0);
                            d.remove(firstElement.getStartOffset(), firstElement.getEndOffset());
                        } catch (BadLocationException ignored) {
                        }
                    });
                }

            }
        });
    }

    /**
     * 初始化进度条，设置默认高度和可见性
     */
    private void initProgressBar() {
        progressBar.setHeight(20);
        progressBar.setVisible(false);

        // 添加变更监听器
        progressBar.addChangeListener(e -> {
            if (alwaysShowProgressBar) {
                return;
            }
            int value = progressBar.getValue();
            progressBar.setVisible(value > 0);
        });
    }

    /**
     * 将进度条移动到顶部
     * @return 当前实例，支持链式调用
     */
    public OutputArea progressOnTop() {
        removeAll();
        BorderLayoutHelper.newInstance(this)
                .addComponent2North(progressBar)
                .addComponent2Center(ScrollPaneUtils.wrap(textArea));
        return this;
    }

    /**
     * 设置进度条文本格式化器
     * @param formatter 格式化函数
     * @return 当前实例，支持链式调用
     */
    public OutputArea progressBarTextFormatter(Function<MyProgressBar.ProgressInfo, String> formatter) {
        progressBar.setTextFormatter(formatter);
        return this;
    }

    /**
     * 设置进度条始终显示
     * @return 当前实例，支持链式调用
     */
    public OutputArea alwaysShowProgressBar() {
        this.alwaysShowProgressBar = true;
        if (!progressBar.isVisible()) {
            progressBar.setVisible(true);
        }
        return this;
    }

    /**
     * 设置标题
     * @param title 标题文本
     * @return 当前实例，支持链式调用
     */
    public OutputArea setTitle(String title) {
        BorderUtils.title(textArea, title);
        return this;
    }

    /**
     * 滚动到文本区域底部
     */
    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            textArea.setCaretPosition(textArea.getDocument().getLength());
            // 确保滚动条在底部
            try {
                textArea.scrollRectToVisible(textArea.modelToView2D(textArea.getDocument().getLength()).getBounds());
            } catch (Exception ignored) {}
        });
    }

    /**
     * 追加一行文本
     * @param text 要追加的文本
     */
    public void appendLine(String text) {
        textArea.append(text+"\n");
        scrollToBottom();
    }

    /**
     * 设置文本内容
     * @param text 要设置的文本
     */
    public void setText(String text) {
        textArea.setText(text+"\n");
        scrollToBottom();
    }

    /**
     * 清空文本内容
     */
    public void clearText() {
        textArea.setText("");
    }

    /**
     * 设置进度条的进度
     * @param min 最小值
     * @param max 最大值
     * @param value 当前值
     * @return 当前实例，支持链式调用
     */
    public OutputArea setProgress(int min, int max, int value) {
        progressBar.setMinimum(min);
        progressBar.setMaximum(max);
        progressBar.setValue(value);
        return this;
    }
}
