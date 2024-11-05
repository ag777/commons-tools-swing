package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * 标签页组件JTabbedPane辅助类
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/11/4 上午8:55
 */
public class TabbedPaneHelper {
    private final JTabbedPane mTabbedPane;

    /**
     * 创建一个TabbedPaneHelper实例，该实例封装了一个JTabbedPane容器
     * 此方法提供了一种简便的方式来自动生成并布局一个标签页容器
     *
     * @return 返回一个TabbedPaneHelper实例，用于操作和布局JTabbedPane容器
     */
    public static TabbedPaneHelper newInstance() {
        return new TabbedPaneHelper(new JTabbedPane());
    }

    /**
     * 构造函数，初始化TabbedPaneHelper时，将传入的JTabbedPane实例封装起来
     *
     * @param tabbedPane 要封装的JTabbedPane实例
     */
    public TabbedPaneHelper(JTabbedPane tabbedPane) {
        this.mTabbedPane = tabbedPane;
    }

    /**
     * 获取封装的JTabbedPane实例
     *
     * @return 返回封装的JTabbedPane实例
     */
    public JTabbedPane get() {
        return mTabbedPane;
    }

    /**
     * 添加一个标签页到JTabbedPane
     *
     * @param title 标签页的标题
     * @param component 标签页的内容组件
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper addTab(String title, Component component) {
        mTabbedPane.addTab(title, component);
        return this;
    }

    /**
     * 添加一个带有图标的标签页到JTabbedPane
     *
     * @param title 标签页的标题
     * @param icon 标签页的图标
     * @param component 标签页的内容组件
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper addTab(String title, Icon icon, Component component) {
        mTabbedPane.addTab(title, icon, component);
        return this;
    }

    /**
     * 添加标签切换监听
     * @param onChanged 标签切换监听
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper onTabChanged(Consumer<Integer> onChanged) {
        mTabbedPane.addChangeListener(e->{
            onChanged.accept(mTabbedPane.getSelectedIndex());
        });
        return this;
    }

    /**
     * 设置JTabbedPane的标签对齐方式
     *
     * @param placement 对齐方式，如 JTabbedPane.TOP, JTabbedPane.BOTTOM, JTabbedPane.LEFT, JTabbedPane.RIGHT
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper setTabPlacement(int placement) {
        mTabbedPane.setTabPlacement(placement);
        return this;
    }

    /**
     * 设置JTabbedPane的标签布局策略
     *
     * @param layoutPolicy 布局策略，如 JTabbedPane.WRAP_TAB_LAYOUT, JTabbedPane.SCROLL_TAB_LAYOUT
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper setTabLayoutPolicy(int layoutPolicy) {
        mTabbedPane.setTabLayoutPolicy(layoutPolicy);
        return this;
    }

    /**
     * 设置JTabbedPane的工具提示文本
     *
     * @param index 标签页的索引
     * @param tip 工具提示文本
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper setToolTipTextAt(int index, String tip) {
        mTabbedPane.setToolTipTextAt(index, tip);
        return this;
    }

    /**
     * 设置JTabbedPane的标题
     *
     * @param index 标签页的索引
     * @param title 新的标题
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper setTitleAt(int index, String title) {
        mTabbedPane.setTitleAt(index, title);
        return this;
    }

    /**
     * 设置JTabbedPane的图标
     *
     * @param index 标签页的索引
     * @param icon 新的图标
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper setIconAt(int index, Icon icon) {
        mTabbedPane.setIconAt(index, icon);
        return this;
    }

    /**
     * 移除指定索引的标签页
     *
     * @param index 标签页的索引
     * @return 返回当前TabbedPaneHelper实例，以便进行链式调用
     */
    public TabbedPaneHelper removeTabAt(int index) {
        mTabbedPane.removeTabAt(index);
        return this;
    }
}
