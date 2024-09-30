package github.ag777.common.tool.swing.view.component.loading;

import javax.swing.*;
import java.awt.*;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/29 下午5:45
 */
public class LoadingComponent<T extends Container> extends JLayeredPane {

    protected final T component;
    private final LoadingPanel loadingPanel;

    public LoadingComponent(T component) {
        this.component = component;
        loadingPanel = new LoadingPanel();
        setLayout(new OverlayLayout(this));
        // 将普通组件添加到底层
        add(component, JLayeredPane.DEFAULT_LAYER);
        // 将加载面板添加到顶层
        add(loadingPanel, JLayeredPane.PALETTE_LAYER);
    }


    public boolean isLoading() {
        return loadingPanel.isVisible();
    }

    public void setLoading(boolean loading) {
        loadingPanel.setVisible(loading);
    }
}
