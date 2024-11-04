package github.ag777.common.tool.swing.view.component;

import com.ag777.util.lang.collection.ListUtils;
import github.ag777.common.tool.swing.util.ui.TableUtils;
import github.ag777.common.tool.swing.view.component.table.ColumnConfig;
import github.ag777.common.tool.swing.view.component.table.CustomTableModel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/10/10 下午5:45
 */
public class CustomTable<T> extends JTable {
    @Getter
    private final CustomTableModel<T> model;

    public CustomTable(List<ColumnConfig<T>> configs) {
        this.model = new CustomTableModel<>(configs);
        setModel(model);
        columnWidths(
                configs.stream()
                        .mapToInt(c->c.width()!=null?c.width():-1)
                        .toArray()
        );
    }

    public CustomTable(List<T> list, List<ColumnConfig<T>> configs) {
        this(configs);
        setData(list);
    }

    public CustomTable<T> setData(List<T> list) {
        return setList(list);
    }

    public CustomTable<T> setList(List<T> list) {
        model.setData(list);
        return this;
    }

    public CustomTable<T> columnWidths(int[] columnWidths) {
        TableUtils.setColumnWidths(this, columnWidths);
        return this;
    }

    public void fireTableDataChanged() {
        model.fireTableDataChanged();
    }

    public List<T> getAllData() {
        return model.getList();
    }

    public void addRow(T row) {
        model.getList().add(row);
    }

    public void removeRow(int rowNo) {
        model.getList().remove(rowNo);
    }

    public void removeIf(Predicate<? super T> filter) {
        model.getList().removeIf(filter);
    }

    public T getRowData(int rowNo) {
        return ListUtils.get(model.getList(), rowNo);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer renderer = model.getCellRenderer(column);
        if (renderer == null) {
            renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    // 调用父类方法来获取渲染组件
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // 设置水平对齐
                    setHorizontalAlignment(model.getAlignHorizontal(column));
                    // 设置垂直居中
                    setVerticalAlignment(SwingConstants.CENTER);
                    return this;
                }
            };
        }
        return renderer;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor editor = model.getCellEditor(column);
        return editor != null ? editor : super.getCellEditor(row, column);
    }
}
