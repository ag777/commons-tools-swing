package github.ag777.common.tool.swing.view;

import github.ag777.common.tool.swing.base.BasePanel;
import github.ag777.common.tool.swing.base.BasePresenter;
import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.util.ui.ScrollPaneUtils;
import github.ag777.common.tool.swing.util.ui.TabbedPaneHelper;
import github.ag777.common.tool.swing.util.ui.layout.BorderLayoutHelper;
import github.ag777.common.tool.swing.view.component.CustomTable;
import github.ag777.common.tool.swing.view.component.table.ColumnConfig;
import github.ag777.common.tool.swing.view.interf.TabView;

import javax.swing.*;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/11/4 上午9:03
 */
public class TabPanel extends BasePanel implements TabView {
    /**
     * 构造函数，用于初始化BasePanel
     *
     * @param config UiProperties.MenuItem对象，包含菜单项的配置信息
     */
    public TabPanel(UiProperties.MenuItem config) {
        super(config);
    }

    @Override
    protected void initView(JPanel container, UiProperties.MenuItem config) {
        new BorderLayoutHelper<>(container)
                .addComponent2Center(
                        TabbedPaneHelper.newInstance()
                                .setTabPlacement(JTabbedPane.LEFT) // 设置标签页的位置为左边
                                .addTab("输出组件", new EmptyPanel(config))
                                .addTab("表格",
                                    ScrollPaneUtils.wrap(
                                        new CustomTable<>(
                                                IntStream.range(0, 100).boxed().map(i->"项目"+i).toList(),
                                                List.of(
                                                        new ColumnConfig<String>()
                                                                .title("编号")
                                                                .width(60)
                                                                .valGetter((item, i)->i+1),
                                                        new ColumnConfig<String>()
                                                                .title("值")
                                                                .valGetter((item, i)->item)
                                                                .valSetter((rowItem, item, list, row, col)->list.set(row, (String) item))
                                                                .alignLeft()
                                                                .cellEditor(new DefaultCellEditor(new JTextField()))
                                                )
                                        )
                                    )
                                )
                                .get()
                );
    }

    @Override
    protected void initData(UiProperties.MenuItem config) {

    }

    @Override
    public BasePresenter<?> getPresenter() {
        return null;
    }
}
