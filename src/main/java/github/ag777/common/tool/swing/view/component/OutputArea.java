package github.ag777.common.tool.swing.view.component;

import github.ag777.common.tool.swing.util.ui.BorderLayoutHelper;
import github.ag777.common.tool.swing.util.ui.BorderUtils;
import github.ag777.common.tool.swing.util.ui.ScrollPaneUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 上午9:02
 */
@Accessors(chain = true)
public class OutputArea extends JPanel{

    @Getter
    private final JTextArea textArea;
    @Getter
    private final MyProgressBar progressBar;
    @Setter
    private Integer maxLineCount;
    private boolean alwaysShowProgressBar;

    public OutputArea() {
        textArea = new JTextArea();
        initTextarea();
        JScrollPane scrollPane = ScrollPaneUtils.wrap(textArea, "输出");
        progressBar = new MyProgressBar();
        this.alwaysShowProgressBar = false;
        initProgressBar();

        BorderLayoutHelper.newInstance(this)
                .addComponent2Center(scrollPane)
                .addComponent2South(progressBar);
    }

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

    private void initProgressBar() {
        progressBar.setPreferredSize(new Dimension(0, 20));
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        DecimalFormat percentFormat = new DecimalFormat("0%");
        progressBar.setTextFormatter((info)-> {
            if (info.hover()) {
                return info.value()+"/"+info.max();
            } else {
                return percentFormat.format(info.progress());
            }
        });

        // 添加变更监听器
        progressBar.addChangeListener(e -> {
            if (alwaysShowProgressBar) {
                return;
            }
            int value = progressBar.getValue();
            progressBar.setVisible(value > 0);
        });
    }

    public OutputArea progressOnTop() {
        removeAll();
        BorderLayoutHelper.newInstance(this)
                .addComponent2North(progressBar)
                .addComponent2Center(textArea);
        return this;
    }

    public OutputArea progressBarTextFormatter(Function<MyProgressBar.ProgressInfo, String> formatter) {
        progressBar.setTextFormatter(formatter);
        return this;
    }

    public OutputArea alwaysShowProgressBar() {
        this.alwaysShowProgressBar = true;
        if (!progressBar.isVisible()) {
            progressBar.setVisible(true);
        }
        return this;
    }

    public OutputArea setTitle(String title) {
        BorderUtils.titleBorder(textArea, title);
        return this;
    }

    public void appendLine(String text) {
        textArea.append(text+"\n");
    }

    public void setText(String text) {
        textArea.setText(text+"\n");
    }

    public void clearText() {
        textArea.setText("");
    }

    public OutputArea setProgress(int min, int max, int value) {
        progressBar.setMinimum(min);
        progressBar.setMaximum(max);
        progressBar.setValue(value);
        return this;
    }
}
