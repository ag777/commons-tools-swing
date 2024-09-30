package github.ag777.common.tool.swing.view.component.loading;

import javax.swing.*;
import java.awt.*;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 下午5:57
 */
public class LoadingFrame extends JFrame {
    protected LoadingPanel loadingPanel;

    public LoadingFrame() {
        loadingPanel = new LoadingPanel();
        setGlassPane(loadingPanel);
    }

    public LoadingFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        loadingPanel = new LoadingPanel();
        setGlassPane(loadingPanel);
    }

    public LoadingFrame(String title) throws HeadlessException {
        super(title);
        loadingPanel = new LoadingPanel();
        setGlassPane(loadingPanel);
    }

    public LoadingFrame(GraphicsConfiguration gc) {
        super(gc);
        loadingPanel = new LoadingPanel();
        setGlassPane(loadingPanel);
    }

    public boolean isLoading() {
        return loadingPanel.isVisible();
    }

    public void setLoading(boolean loading) {
        loadingPanel.setVisible(loading);
    }
}
