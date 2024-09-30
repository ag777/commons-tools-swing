package github.ag777.common.tool.swing.view;

import github.ag777.common.tool.swing.base.BasePanel;
import github.ag777.common.tool.swing.base.BasePresenter;
import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.presenter.EmptyPresenter;
import github.ag777.common.tool.swing.view.component.OutputArea;
import github.ag777.common.tool.swing.util.ui.BorderLayoutHelper;
import github.ag777.common.tool.swing.util.ui.GridBagLayoutHelper;
import github.ag777.common.tool.swing.view.interf.EmptyView;

import javax.swing.*;
import java.awt.*;


public class EmptyPanel extends BasePanel implements EmptyView {
    private EmptyPresenter mPresenter;

    private JButton btnPlus;
    private JButton btnClear;
    private OutputArea outputArea;

    public EmptyPanel(UiProperties.UiConfig uiConfig) {
        super(uiConfig);
    }

    @Override
    public BasePresenter<?> getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView(JPanel container, UiProperties.UiConfig uiConfig) {
        //输出框组
        outputArea = new OutputArea()
                .setMaxLineCount(100);

        btnPlus = new JButton("测试");
        btnClear = new JButton("清除");

        new BorderLayoutHelper<>(container)
                .addComponent(outputArea, BorderLayout.CENTER)
                .addComponent(GridBagLayoutHelper.newInstance(new JPanel())
                        .addComponent(btnPlus, 0, 0)
                        .addComponent(btnClear, 0, 1)
                        .get(), BorderLayout.SOUTH);
    }

    @Override
    protected void initData() {
        mPresenter = new EmptyPresenter();
        mPresenter.attachView(this);

        btnPlus.addActionListener(e -> mPresenter.test());
        btnClear.addActionListener(e->clear());
    }

    @Override
    public void show(String msg) {
        outputArea.appendLine(msg);
        outputArea.setProgress(0, 100, Integer.parseInt(msg));
    }

    @Override
    public void clear() {
        outputArea.clearText();
        outputArea.setProgress(0, 100, 0);
    }
}
